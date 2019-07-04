package com.Db;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dao.bookDao;

public class pageCount {
	
	PreparedStatement pstmt;
	ResultSet rs;
	bookConnection bc=new bookConnection();
	Connection con;
	private static Statement st;
	
	/**
	* 无条件搜索时计算总的页数
	* @return
	 * @throws ClassNotFoundException 
	*/
	public int getPage() throws ClassNotFoundException{
	int recordCount=0;        
    int t1=0;   //整数
    int t2=0;   //对5求余数
   	String  sql="select count(*) from bookInfo "; 
   	
	try {
	con=bc.getConnection();
	pstmt=con.prepareStatement(sql);
	rs=pstmt.executeQuery();
	rs.next();
	recordCount=rs.getInt(1);
	t1=recordCount%5;
	t2=recordCount/5;
	System.out.print("有"+recordCount+"条结果");
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}finally{
	bc.close(con, pstmt, rs);
	}
	if(t1 != 0){
	t2=t2+1;
	}
	return t2;
	}
	/**
	* 无条件搜索时查询指定页的数据
	* @param pageNo
	* @return
	*/
	public List<bookDao> listUser(int pageNo){
	
		List<bookDao> list=new ArrayList<>();
	int pageSize=5;
	int page=(pageNo-1)*5;

	String sql="select * from bookInfo  order by bookNum limit ?,?";   //从第page条取5条
	try {
		con=bc.getConnection();
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, page);
		pstmt.setInt(2, pageSize);
		rs=pstmt.executeQuery();

	while(rs.next()){
	bookDao book=new bookDao();
	book.setBookNum(rs.getString("bookNum").trim());
	book.setBookName(rs.getString("bookName").trim());
	book.setAuthor(rs.getString("author").trim());
	book.setType(rs.getString("bookType").trim());
	book.setRemainNum(rs.getInt("remainNum"));
	list.add(book);
	}
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}finally{
	bc.close(con, pstmt, rs);
	}
	
	return list;
	}

	
	/**
	* 有条件搜索时计算总的页数
	* @return
	 * @throws ClassNotFoundException 
	*/
	public int getPage(int type,String val) throws ClassNotFoundException{
	int recordCount=0;        
    int t1=0;   //整数
    int t2=0;   //对5求余数
    String  sql="";
    
    if(1==type){
     sql="select count(*) from bookInfo where bookInfo.bookNum= ?";
    }
    if(2==type){
    	 sql="select count(*) from bookInfo where bookInfo.author=?";
    }
    if(3==type){
   	 sql="select count(*) from bookInfo where bookInfo.bookType =? ";
   }
   	
	try {
	con=bc.getConnection();
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1, val);
	rs=pstmt.executeQuery();
	rs.next();
	recordCount=rs.getInt(1);
	t1=recordCount%5;
	t2=recordCount/5;
	System.out.print("有"+recordCount+"条结果");
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}finally{
	bc.close(con, pstmt, rs);
	}
	if(t1 != 0){
	t2=t2+1;
	}
	return t2;
	}
	
	/**
	* 有条件搜索时查询指定页的数据
	* @param pageNo
	* @return
	*/
	public List<bookDao> listUser(int pageNo,int type, String val){
	
		List<bookDao> list=new ArrayList<>();
	    int pageSize=5;
	    int page=(pageNo-1)*5;
    
	String sql="";   //从第page条取5条
	
	if(1==type){
	   sql="select * from bookInfo where bookInfo.bookNum=? order by bookNum limit ?,?";
	  }
	    if(2==type){
	    	 sql="select * from bookInfo where bookInfo.author= ? order by bookNum limit ?,?";
	    }
	    if(3==type){
	   	 sql="select * from bookInfo where bookInfo.bookType = ? order by bookNum limit ?,?";
	   }
	
	try {
		con=bc.getConnection();
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, val);
		pstmt.setInt(2, page);
		pstmt.setInt(3, pageSize);
		rs=pstmt.executeQuery();

	while(rs.next()){
	bookDao book=new bookDao();
	book.setBookNum(rs.getString("bookNum").trim());
	book.setBookName(rs.getString("bookName").trim());
	book.setAuthor(rs.getString("author").trim());
	book.setType(rs.getString("bookType").trim());
	book.setRemainNum(rs.getInt("remainNum"));
	list.add(book);
	}
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}finally{
	bc.close(con, pstmt, rs);
	}
	
	return list;
	}
	
	public int InsertBook(String bookname,String authorname,HttpServletRequest request){
		HttpSession session=request.getSession();
		String user=(String) session.getAttribute("username");
		String usernum=(String) session.getAttribute("usernum");
		String sql="insert into BrowInfo(userNum,userName,bookName,author,borrowDate,returnDate)values(?,?,?,?,?,?)";
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

          Calendar cal = new GregorianCalendar();
          Date date = new Date();
            cal.setTime(date);
			cal.add(Calendar.MONTH, 1);
		    //String latertime=sdf.format(cal.getTime());
			
			
		try {
			con=bc.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, usernum);
			pstmt.setString(2, user);
			pstmt.setString(3, bookname);
			pstmt.setString(4, authorname);                  
			pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setDate(6, new java.sql.Date(cal.getTimeInMillis()));
			pstmt.executeUpdate();
			return 1;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public java.sql.Date time() throws ParseException{
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(); //实例化日历
		Date date=new Date();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
	    String latertime=sdf.format(cal.getTime());
	    return (java.sql.Date) sdf.parse(latertime);	
	}
	
	public int checkbook(String bookname,HttpServletRequest request){
		HttpSession session=request.getSession();
         String usernum=(String) session.getAttribute("usernum");
		String sql="select * from BrowInfo where  binary userNum=?";
		try {
			con=bc.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, usernum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString("bookName").trim().equals(bookname))
					return 1;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
