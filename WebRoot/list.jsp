<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/list.css">

    
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    
    <script type="text/javascript">
 
    function bro(){
    var k=window.confirm("确认要借阅该书吗？");
       if(k){
         $(document).ready(function(){
  $("#up").on("click","tr",function() {
		var td= $(this).find("td");
		var book_num= td.eq(0).text();
		 var book_name= td.eq(1).text();
		  var author_name = td.eq(2).text();
		  var remain_num=td.eq(4).text();
		  var money="<%=session.getAttribute("ownMoney")%>";
		  if(money!="null"){
		   alert("您已欠费，请先缴清费用!");
		   window.location.href="Manage.jsp"	
		 }else{
		   if(remain_num>0){
		  window.location.href="CheckServlet?book_num="+book_num+"&book_name="+book_name+"&author_name="+author_name+"&remain_num="+remain_num;	
		  }
		   else{
		  alert("图书馆中该书已被借阅完!")
		  } 
		  }
		  
	});
   });
     return true;
     }else{
     return false;
    }
  }
  
    function checkInfo(){
      $(document).ready(function () {  
            $("#up td").click(function () {  
                //var tdSeq = $(this).parent().find("td").index($(this)[0]);  
                var trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]);  
  
                window.location.href="show.jsp?txt="+trSeq;
            });  
        });  
    //var tn=event.srcElement; // 通过event.srcElement 获取激活事件的对象 td 
       //var num=(tn.parentElement.rowIndex+1);   
        //alert("选择的行号为:"+num);
    }
    
    </script>
   
   
  </head>
  
  <body>
   <h2 >图书信息</h2>
    <div id="bl" style="margin-top: 15px;">
     
     <table class="sou">
     <tr>
      <form action="ListServlet" method="POST">
     <th>图书编码:</th><th><input type="text" name="bookNum" placeholder="请输入图书编码" ></input></th>
     <th>作者:</th><th><input type="text" name="author" placeholder="请输入作者" ></input></th>
     <th>图书类型:</th><th><input type="text" name="bookType" placeholder="请输入图书类型"></input></th>
     <th>
     <input type="submit" value="条件搜索" >
     </th>
     </form>
     
    <form action="ListServlet" method="POST">
    <th> 
     <input type="submit" value="搜索" >
     </th> 
     </form>
     </tr>
     </table>
   
       
     <table class="tab" style="margin-top:20px;">
     <tr>
     <th>书号</th>
     <th>书名</th>
     <th>作者</th>
     <th>图书类型</th>
     <th>库存量</th>
      <th>操作</th>
     </tr>
    </table>
    
    <table class="tab" id="up">
     <c:if test="${listss!=null}">
   <c:forEach items="${listss}" var="person">
   <tr>
   <td >${person.bookNum}</td>
   <td >${person.bookName}</td>
   <td >${person.author }</td>
    <td >${person.type }</td>
     <td >${person.remainNum }</td>
      <td><a  style="margin-right:10px;" target="iframe_a" onclick="checkInfo()">查看</a><a target="iframe_a" onclick="return bro()">借阅</a></td>
   </tr>
  </c:forEach>
  </c:if>
 </table>
   
 
  <p>
  <c:if test="${pageNos>1 }">
  <a href="ListServlet?pageNos=1" >首页</a>
  <a href="ListServlet?pageNos=${pageNos-1 }">上一页</a>
  </c:if>
  
  <c:if test="${pageNos <recordCount }">
  <a href="ListServlet?pageNos=${pageNos+1 }">下一页</a>
  <a href="ListServlet?pageNos=${recordCount }">末页</a>
  </c:if>
  
   <form action="ListServlet" method="post">
   <h4 align="center">共${recordCount}页  
   <input type="text" value="${pageNos}" name="pageNos" size="1">页
   <input type="submit" value="到达">
   </h4>
   </form>
   </div>
</body>
</html>
