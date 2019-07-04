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

public class passUpdate extends HttpServlet {

	PreparedStatement pstmt;
	ResultSet rs;
	bookConnection bc=new bookConnection();
	Connection con;
	
	public passUpdate() {
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
		
		String oldpa = request.getParameter("oldpa");
		String newpa = request.getParameter("newpa");
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username"); 
		
		String sql="update Userinfo set pass=?  where binary userName=?";
		try {
			con=bc.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,newpa);
			pstmt.setString(2, username);
			int num=pstmt.executeUpdate();
			String info="";
			System.out.print("num:"+num);
			if(1==num) {
				info = "{\"code\":\"200\",\"message\":\"密码修改成功\"}";
			}else {
				info = "{\"code\":\"999\",\"message\":\"原密码输入错误\"}";
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
