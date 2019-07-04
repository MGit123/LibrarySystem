package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Db.pageCount;
import com.dao.bookDao;

public class ListServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public ListServlet() {
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
		PrintWriter out = response.getWriter();
		int pageNo = 1;
		int selectType=1;
		pageCount bookpage=new pageCount();
	    
		String pageno=request.getParameter("pageNos");
		String author=request.getParameter("author");   //作者
		String bookNum=request.getParameter("bookNum"); //图书编码
		String type=request.getParameter("bookType");   //类型
		int recordCount=1;
		System.out.println("author:" + author);
		System.out.println("bookNum:" + bookNum);
		System.out.println("type:" + type);
		if(pageno!= null && pageno!=""){
		pageNo=Integer.parseInt(pageno);
		}
		
		List<bookDao> lists=new ArrayList();
		if(author==null&&bookNum==null&&type==null){
			lists=bookpage.listUser(pageNo);
			try {
				 recordCount=bookpage.getPage();
				request.setAttribute("recordCount", recordCount);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(bookNum!=""&&bookNum!=null){
			selectType=1;
		  lists=bookpage.listUser(pageNo,selectType,bookNum);
		  System.out.println("bookNum:"+bookNum+"111111");
		  try {
				 recordCount=bookpage.getPage(selectType,bookNum);
				request.setAttribute("recordCount", recordCount);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	      if(author!=""&&author!=null){
			selectType=2;
		  lists=bookpage.listUser(pageNo,selectType,author);
		  System.out.println("author"+author+"111112");
		  try {
				 recordCount=bookpage.getPage(selectType,author);
				request.setAttribute("recordCount", recordCount);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
          if(type!=""&&type!=null){
			selectType=3;
		  lists=bookpage.listUser(pageNo,selectType,type);
		  System.out.println("type"+type+"111113");
		  try {
				 recordCount=bookpage.getPage(selectType,type);
				request.setAttribute("recordCount", recordCount);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("listss", lists);
		request.setAttribute("pageNos", pageNo);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
		//response.sendRedirect("/pages/list.jsp");
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
