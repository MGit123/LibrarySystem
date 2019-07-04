package com.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ICodeCheck extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public ICodeCheck() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	

	public static final int W=100;
	public static final int H=26;
	public static String icode=null;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("image/jpeg");
	    
	  //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
	    
	  //创建内存图像并获得图形上下文
	    BufferedImage img=new BufferedImage(W,H,BufferedImage.TYPE_INT_RGB);
		Graphics g=img.getGraphics();
		
		//画背景，填充背景颜色
		g.setColor(Color.white);
		g.fillRect(0, 0, W, H);
	    //画边框
		g.setColor( Color.blue);
		g.drawRect(1, 1, W-2, H-2);
		
		//填字符
		 icode=icodeBuilder(g,4);
		 request.getSession().setAttribute("mycode", icode);   //放进Session
		//画线
		drawLines(g,5);
		//传输到浏览器
		ServletOutputStream out=response.getOutputStream();
		ImageIO.write(img, "JPG", out);
	}
    
     


	public static int getRandomNum(int max)
	{
		return (int)(Math.random()*max);
	}
	
	
	//生成验证码的数字
	public static String icodeBuilder(Graphics g,int num){
		String rs="";
		String codes="0123456789ABCDEFGHIJKabcdefg";
		int p=10;
		for(int i=0;i<num;i++){
			char c=codes.charAt(getRandomNum(codes.length()));
			Color color=new Color(getRandomNum(256),getRandomNum(256),getRandomNum(256));
			g.setColor(color);
			g.setFont(new Font("宋体",0,25));
			g.drawString(""+c,p,22);
			p+=25;
			rs=rs+c;
		}
		return rs;
	}
	
	public static void drawLines(Graphics g,int num){
		
		for(int i=0;i<num;i++){
		int x1=(int)(Math.random()*getRandomNum(W));
		int x2=(int)(Math.random()*getRandomNum(W));
		int y1=(int)(Math.random()*getRandomNum(H));
		int y2=(int)(Math.random()*getRandomNum(H));
		Color color=new Color(getRandomNum(256),getRandomNum(256),getRandomNum(256));
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
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
