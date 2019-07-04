<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'leftLogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/leftLogin.css">
	
     <script type="text/javascript">
    function changeIcode(){
    var obj=document.getElementById("code");
    obj.src="/LibraryManage/ICodeCheck?time=" + new Date().getTime(); 
    };
    </script>
    
    <!-- 提示错误类型 -->
 <%String info=(String)request.getAttribute("info"); 
 if(info!=null){ %>
 
<script type="text/javascript" language="javascript">
alert("<%=info%>")
</script>
 <%} %> 
 
 <%if((session.getAttribute("username")!=null)&&(session.getAttribute("password")!=null)){ 
 request.getRequestDispatcher("/index.jsp").forward(request, response);
 }
 %>
   </head>
  <body>
  <div id="bg">
       
         <ul>
         <form action="AutoLogin" methd="post">
         <li style="text-align:center;font-size:24px; ">用户登录</li>
         
        <li>
        <img src="images/user.png" alt=" User icon" class="icon">
        <input type="text" name="user" class="te" placeholder="请输入用户名"> </input>
        </li>
        
        <li>
       <img src="images/mima.png" alt="Mima icon" class="icon">
       <input type="password" name="pass" class="te" placeholder="请输入用户密码"> </input>
       </li>
       
       <li>
       <img src="images/key.png" alt="Key icon" class="icon">
       <input type="text" name="icode" class="ic" placeholder="请输入验证码">
       <img src="/LibraryManage/ICodeCheck" id="code" onclick="changeIcode()">
       </li>
       
       <li> 
       <input type="checkbox" value="y" name="auto" class="re"><span class="r">自动登录</span>
       <span class="register"><a >立即注册</a></span></li>
       
       <li>
       <input  type="submit"  value="登录" class="lo"></input>
       </li>
        </form>
         
      </ul>
    
    </div>
  </body>
</html>
