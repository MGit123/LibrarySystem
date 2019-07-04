package com.Filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) Request;
        HttpServletResponse response = (HttpServletResponse) Response;
        
        // ��ȡsession����
        HttpSession session=request.getSession();
        Object user = request.getSession().getAttribute("username");
 
        
        String usernameCookie=null;
        String passwordCookie=null;
        
    	String from=request.getServletPath();
        
		if(from.endsWith("Login.jsp")||from.endsWith("AutoLogin")||from.endsWith("ICodeCheck")||from.endsWith("Quit")){
        //��ȡcookie
		if(user!=null){
        Cookie[] cookies=request.getCookies();
        
        if(cookies!=null){
        	for (Cookie cookie : cookies) {
                // ���������usernameCookie��cookie_password
                if ("username".equals(cookie.getName())) {
                    usernameCookie= cookie.getValue();
                    // ��cookie�е�ֵ����
                    usernameCookie = URLDecoder.decode(usernameCookie,
                            "UTF-8");
                }
                if ("password".equals(cookie.getName())) {
                	passwordCookie = cookie.getValue();
                }
            }
        }
        
        if(usernameCookie!=null&&passwordCookie!=null){
        	//if(usernameCookie.equals("lgx")&&passwordCookie.equals("123")){
        	session.setAttribute("username", usernameCookie);
        	session.setAttribute("password", passwordCookie);
        	request.getRequestDispatcher("/index.jsp").forward(request, response);
        	//}
        }
        
		}
		chain.doFilter(request, response);
        }
		else if(from.endsWith(".jsp")){
			// ȡ����ǰ�Ự��username����
						if (user!= null && !session.equals("")) {
							// ��ǰ�Ự�������û���¼����������
							chain.doFilter(request, response);
						} else {
							// ��ǰ�Ự�л�δ���û���¼������ת����¼ҳ��
							request.getRequestDispatcher("/Login.jsp").forward(request, response);
						}
		}
		else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
