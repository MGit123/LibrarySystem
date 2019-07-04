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
        
        // 获取session对象
        HttpSession session=request.getSession();
        Object user = request.getSession().getAttribute("username");
 
        
        String usernameCookie=null;
        String passwordCookie=null;
        
    	String from=request.getServletPath();
        
		if(from.endsWith("Login.jsp")||from.endsWith("AutoLogin")||from.endsWith("ICodeCheck")||from.endsWith("Quit")){
        //获取cookie
		if(user!=null){
        Cookie[] cookies=request.getCookies();
        
        if(cookies!=null){
        	for (Cookie cookie : cookies) {
                // 获得名字是usernameCookie和cookie_password
                if ("username".equals(cookie.getName())) {
                    usernameCookie= cookie.getValue();
                    // 对cookie中的值解码
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
			// 取出当前会话的username属性
						if (user!= null && !session.equals("")) {
							// 当前会话中已有用户登录，放行请求
							chain.doFilter(request, response);
						} else {
							// 当前会话中还未有用户登录，则跳转到登录页面
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
