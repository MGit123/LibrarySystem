<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>My JSP 'menu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	  
	<link rel="stylesheet" href="css/menu.css" media="screen" type="text/css" />
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

  </head>
  
  <body>
   <div id="menu">
     <ul>
            <li class="active"><i class="fa fa-home fa-lg"></i> <a href="welcome.jsp" target="iframe_a">首页</a></li>
            <li><i class="fa fa-envelope fa-lg"></i><a href="list.jsp" target="iframe_a">图书信息</a></li>
            <li><i class="fa fa-user fa-lg"></i><a href="BookInfo.jsp" target="iframe_a" >借阅信息</a></li>
            <li><i class="fa fa-cogs fa-lg"></i><a href="Manage.jsp" target="iframe_a" >管理</a></li>
            <li><i class="fa fa-power-off fa-lg"></i><a href="Quit">退出</a></li>
        </ul>
    </div>
    
  </body>
</html>
