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

public class AddBook extends HttpServlet {

	PreparedStatement pstmt;
	ResultSet rs;
	bookConnection bc=new bookConnection();
	Connection con;
	
	public AddBook() {
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
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String pnum = request.getParameter("pnum");
		String pname = request.getParameter("pname");
		String pauthor = request.getParameter("pauthor");
		String ptype= request.getParameter("ptype");
		String pbn= request.getParameter("pbn");
		int n=Integer.valueOf(pbn);
		
		
		String sql="insert into bookInfo(bookNum,bookName,author,bookType,remainNum )values(?,?,?,?,?)";
		try {
			con=bc.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,pnum);
			pstmt.setString(2,pname);
			pstmt.setString(3,pauthor);
			pstmt.setString(4,ptype);
			pstmt.setInt(5,n);
			int num=pstmt.executeUpdate();
			String info="";
			System.out.print("num:"+num);
			if(1==num) {
				info = "{\"code\":\"200\",\"message\":\"密码修改成功\"}";
			}else {
				info = "{\"code\":\"999\",\"message\":\"数据库已存在该书，库存量已增加!\"}";
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
