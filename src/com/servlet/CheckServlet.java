package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.bookConnection;
import com.Db.pageCount;

public class CheckServlet extends HttpServlet {


	
	public CheckServlet() {
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

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		String book_num=request.getParameter("book_num");
		 String book_name=request.getParameter("book_name");
		 String author_name=request.getParameter("author_name");
		 String remain_num=request.getParameter("remain_num");
		 
		 System.out.println("book_num:"+book_num);
		 System.out.println("book_name:"+book_name);
		 System.out.println("author_name"+author_name);
		 System.out.println("remain_num"+remain_num);
		 
		 pageCount p=new pageCount();
		 if(1==p.checkbook(book_name, request)){
			 request.setAttribute("borrowInfo","尊敬的用户,您已借阅过该书!");
			 request.getRequestDispatcher("/BookInfo.jsp").forward(request, response);
		 } else if(1==p.InsertBook(book_name,author_name,request)){
					 request.setAttribute("borrowInfo","您已成功借阅该书!");
				request.getRequestDispatcher("/BookInfo.jsp").forward(request, response);
				 }
			
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	
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
