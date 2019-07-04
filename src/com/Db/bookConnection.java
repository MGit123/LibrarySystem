package com.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bookConnection {
	
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	
	public static Connection getConnection() throws ClassNotFoundException  {	 
		String userName = "root";//用户名
		String userPwd = "自己的数据库登录密码";//密码
		String dburl = "jdbc:mysql://"端口号"/BookM";//数据库名
		String driverName = "com.mysql.jdbc.Driver";//数据库驱动
		    Connection dbCon;  
		      try
		      {
		       Class.forName(driverName);
		       dbCon= DriverManager.getConnection(dburl, userName, userPwd);
		       return dbCon;
		       }
		      catch(SQLException e)
		      {
		       e.printStackTrace();
		       }
		return null;
		     }
	
	public void close(Connection conn,PreparedStatement pstmt,ResultSet result){
		if(conn != null){
		try {
		conn.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		}
		}
		if(pstmt != null){
		try {
		pstmt.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		if(result != null){
		try {
		result.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		}
	
}
