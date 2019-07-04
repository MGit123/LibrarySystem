<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <base href="<%=basePath%>">  
    <title>图书管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/leftStu.css">
	 
  </head>
  
  <body>
    <div id="container">
    <!-- 头部 -->
  <div id="header">
 <div id="h">
  <h1 class="biao">图书管理系统</h1>
   <p class="huan">欢迎进入图书管理系统</p>
   </div>
  
   <div id="tx">
  <img src="images/tx.png" alt="TouXian icon" class="icon">
   <p class="p">HELLO,${userName}</p>
   </div>
   </div>
   
   <!-- 左部 -->
   <div id="left">
  <%@include file="/menu.jsp" %>
   </div>
   
   <!-- 内容 -->
    <div id="content">
        <iframe src="welcome.jsp" name="iframe_a" height="500px" width="98%" position="absolute" left="10px" frameborder="0" scrolling="auto"></iframe>
  </div>
  
  <!-- 页脚-->
  <div id="footer">
   </div>
   
   </div>
  </body>
</html>
