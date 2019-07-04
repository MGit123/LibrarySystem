package com.Db;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.dao.OwnBook;
import com.dao.bookDao;

public class UserConenction {
	
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	static bookConnection b=new bookConnection();
	static PreparedStatement pstmt;
	
	public static int checkLogin( String name,String pass) throws ClassNotFoundException{
		String sql="select * from UserInfo";
		con=b.getConnection();
	     try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
		     while(rs.next()) {
			 if((rs.getString("userName").trim().equals(name))&&(rs.getString("pass").trim().equals(pass))) 
						return 1;    
				}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	return 0; 
	}
	
	public static String checkNum( String name) throws ClassNotFoundException{
		String sql="select * from UserInfo ";
		con=b.getConnection();
		System.out.println("num的值为:"+name);
		String num="";
	     try {
	    	   st=con.createStatement();
				rs=st.executeQuery(sql);
		     while(rs.next()) {
			 if(rs.getString("userName").trim().equals(name)){
				 num=rs.getString("userNum");
						return num;    
			 }
				}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	return num; 
	}
	
	public static int checkHbrow( String name) throws ClassNotFoundException{
		String sql="select count(*) from BrowInfo where binary userName=?  ";
		con=b.getConnection();
		 try {
			st=con.createStatement();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			rs.next();
			int n=rs.getInt(1);
			System.out.println("已借"+n+"本书!");
			if(n>0)
				return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return 0;
	}

	public List<OwnBook> checkOwnMoney(String name,HttpSession session) throws ClassNotFoundException{
		List<OwnBook> list=new ArrayList<>();
		String sql="select * from BrowInfo where binary userName=?  ";
		Date date=new Date();
		con=b.getConnection();
		 try {
			st=con.createStatement();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			float money=0;
			while(rs.next()){
			int day=(int) ((date.getTime()-rs.getDate("returnDate").getTime())/(1000*3600*24));
			System.out.print("该书已过期"+day+"天!");
			if(day>0){
				OwnBook own=new OwnBook();
				own.setBookName(rs.getString("bookName"));
				own.setOwnMoney((float) (day*0.2));
				list.add(own);
				money=money+(float) (+day*0.2);
			}
			}
			
			session.setAttribute("money", money);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
