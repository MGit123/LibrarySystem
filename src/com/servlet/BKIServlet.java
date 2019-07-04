package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.bookConnection;
import com.dao.BookInfoDao;
import com.dao.bookDao;

import net.sf.json.JSONArray;




public class BKIServlet extends HttpServlet {

	PreparedStatement pstmt;
	ResultSet rs;
	bookConnection bc=new bookConnection();
	Connection con;
	private static Statement st;
	
	public BKIServlet() {
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
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		 String user=(String) session.getAttribute("username");
		String sql= "select * from BrowInfo where binary userName=?";
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookInfoDao> list=new ArrayList<>();
		try {
			con=bc.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,user);
			rs=pstmt.executeQuery();
			while(rs.next()){
		   /*s.append("<td height='35' style='text-align: center;'>"+  rs.getString(1) +"</td>");
		   s.append("<td height='35' style='text-align: center;'>"+  rs.getString(2) +"</td>");
		   s.append("<td height='35' style='text-align: center;'>"+  rs.getString(3) +"</td>");
		   s.append("<td height='35' style='text-align: center;'>"+  rs.getString(4) +"</td>");
		   s.append("<td height='35' style='text-align: center;'>"+  rs.getString(5) +"</td>");
		   s.append("<td height='35' style='text-align: center;'>"+  rs.getString(6) +"</td>");*/
			BookInfoDao book=new BookInfoDao();
				book.setUserNum(rs.getString(1));
				book.setUsername(rs.getString(2));
				book.setBookname(rs.getString(3));
				book.setAuthor(rs.getString(4));
				book.setBorrowDate(df.format(rs.getDate(5)));
				book.setReturnDate(df.format(rs.getDate(6)));
				list.add(book);   
			}
			//s.append("</tr>");
			//out.print(s);
			 String json=JSONArray.fromObject(list).toString();
		     JSONArray jsonArray = JSONArray.fromObject(json);
		     System.out.println(jsonArray);
		        out.println(jsonArray);
		        out.flush();
		        out.close();
		       send(jsonArray);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public JSONArray send(JSONArray j){
		return j;
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
