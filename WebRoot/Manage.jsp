<%@page import="com.dao.OwnBook"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'Manage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/Manage.css">

	   <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="js/Manage.js"></script>
      <script type="text/javascript">
      function Money(){
        $.ajax({
        type : "post",
		url : "RefundMoney",
		timeout:50000,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
		cache : true,
		async : true,
		success: function (data ,textStatus, jqXHR){
	        	if(data.code == 200){
	        	 alert("您已缴清欠费!");
                  $("#d").hide();
                  window.location.href="Manage.jsp";
	        	    }
	        	},
	    error:function(){
           alert("您的余额不足");
       }
	        });
       
      }
      </script>
  
  </head>


  <body onload="hide()">
  <div id="bg">
  <ul>
   <li class="block"  id="la">
  <img src="images/gere.png" alt="个人信息" class="ima" onclick="change1()"><p class="in">个人信息</p>
   <div class="mima" id="a">
  <p>用户:<span><%=session.getAttribute("username") %></span></p>
  <p>已借:<span class="Hbrow"><%=session.getAttribute("Hbrow") %>本</span></p>
  <p>可借:<span class="Abrow"><%=session.getAttribute("Abrow") %>本</span></p> 
  <p>欠费:<span class="money"><%=session.getAttribute("money") %>元</span></p>   
  </div>
  </li>
 
  
    <li class="block"  id="lb">
  <img src="images/mi.png" alt="修改密码" class="ima" onclick="change2()"><p class="in" onclick="change();">修改密码</p>
   <div class="mima" id="b">
   <form>
  <table class="t1">
   <tr><th>原密码:</th><th><input  type="text" name="oldp" class="p1"/></th></tr>
   <tr><th>新密码:</th><th><input  type="password" name="newp1" class="p2"/></th></tr>
   <tr><th>再次确认:</th><th><input  type="password" name="newp2" class="p3"/></th></tr>
   <tr><th><input type="button" value="确认"  onclick="changepa()"/>
</th></tr>
  </table>
  </form>
  </div>
  </li>


  <li class="block"  id="lc">
   <img src="images/tushuguan.png" alt="图书入库" class="ima" onclick="change3();"><p class="in">图书入库</p>
   <div class="mimc" id="c">
  <table class="t1">
  <tr><th>书号:</th><th><input  type="text" class="pnum"/></th></tr>
   <tr><th>书名:</th><th><input  type="text" class="pname"/></th></tr>
   <tr><th>作者:</th><th><input  type="text" class="pauthor"/></th></tr>
   <tr><th>图书类型:</th><th><input  type="text" class="ptype"/></th></tr>
    <tr><th>库存量:</th><th><input  type="text" class="pbn"/></th></tr>
   <tr><th><input type="button" value="确认"  onclick="addBook()"/></th></tr>
  </table>
  </div>
   </li>
   
   <li class="block"  id="ld">
   <img src="images/jiaofei.png" alt="缴费" class="ima" onclick="change4();"><p class="in">图书缴费</p>
   <div class="mimc" id="d">
  <table class="t1">
  <tr><th>过期书籍:</th></tr>
   <c:forEach items="${ownMoney}" var="own">
   <tr><th></th><th style="color:#408080">${own.bookName}</th></tr>
    </c:forEach>
     <c:if test="${ownMoney==null}">
      <tr><th></th><th style="color:white" >不存在超期书籍</th></tr>
     </c:if>
   <tr><th><input type="button" value="缴费"  onclick="Money()"/></th></tr>
  </table>
  </div>
   </li>
    </ul>
    
 
   </div>
  </body>
</html>
