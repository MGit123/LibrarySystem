package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuitServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public QuitServlet() {
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

		response.setContentType("text/html");
		
		HttpSession session= request.getSession();
		 session.invalidate();
		 
		Cookie[] cookies=request.getCookies();
        if(cookies!=null){
        	for (Cookie cookie : cookies) {
                // 获得名字是usernameCookie和cookie_password
                if ("username".equals(cookie.getName())) {
                      cookie.setMaxAge(0);
                   response.addCookie(cookie);
                }
                if ("password".equals(cookie.getName())) {
               	 cookie.setMaxAge(0);
               	 response.addCookie(cookie);
                }
            }
        }
		 
         
         request.getRequestDispatcher("/Login.jsp").forward(request, response);
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
