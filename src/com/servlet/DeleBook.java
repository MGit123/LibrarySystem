package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.bookConnection;

public class DeleBook extends HttpServlet {

	PreparedStatement pstmt;
	ResultSet rs;
	bookConnection bc=new bookConnection();
	Connection con;

	public DeleBook() {
		super();
	}

	
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
		String user_num = request.getParameter("use_num");
		String book_name = request.getParameter("bk_name");
		String sql="DELETE FROM  BrowInfo where  userNum=? and bookName=?";
        String info="";
			try {
				con=bc.getConnection();
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,user_num);
				pstmt.setString(2,book_name);
				
				System.out.println(user_num);
				System.out.println(book_name);
				
				int num=pstmt.executeUpdate();
				if(num>0) {
					info = "{\"code\":\"200\",\"message\":\"书籍归还成功!\"}";
				}else {
					info = "{\"code\":\"999\",\"message\":\"书籍归还失败!\"}";
				}
				response.getWriter().write(info);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
