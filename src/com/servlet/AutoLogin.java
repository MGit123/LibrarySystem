package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.UserConenction;
import com.dao.OwnBook;

public class AutoLogin extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public AutoLogin() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		String user = request.getParameter("user");     //账号
		String pass = request.getParameter("pass");     //获取密码
		 String flag = request.getParameter("auto"); //自动登录状态
		 String code = request.getParameter("icode");    //获取验证码
		HttpSession session=request.getSession();
	    String relCode=(String)session.getAttribute("mycode");   //获取真正的code
	    UserConenction uc= new UserConenction();
	    
	    if(code.equals(relCode)){ //验证码正确 
		try {
			if(1==uc.checkLogin(user, pass)){
				
				if(flag!=null){
					Cookie usernameCookie =new Cookie("username",user);
					Cookie passwordCookie =new Cookie("password",pass);
					
					usernameCookie.setPath(request.getContextPath());
					passwordCookie.setPath(request.getContextPath());
					//设置存活时间
					usernameCookie.setMaxAge(60*60*1);
					passwordCookie.setMaxAge(60*60*1);
					
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				}
				List<OwnBook> list=new ArrayList<>();
				String num=uc.checkNum(user);
				int Hbrow=uc.checkHbrow(user);
				list=uc.checkOwnMoney(user,session);
				int a=20-Hbrow;
				session.setAttribute("username", user);
				session.setAttribute("usernum", num);
				session.setAttribute("Hbrow", Hbrow);
				session.setAttribute("Abrow", a);
				session.setAttribute("ownMoney", list);
				System.out.println("用户账号为:"+num);
				System.out.println("用户为:"+user);
				 request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("info", "账号或密码错误");
			 request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }else{
	    	request.setAttribute("info", "验证码错误");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
	    }
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
