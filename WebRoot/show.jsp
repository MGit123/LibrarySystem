<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" type="text/css" href="css/show.css">
     <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
  
    <script type="text/javascript">
    function show(){
      
    var loc=location.href;
     var n1=loc.length;//地址的总长度
      var n2=loc.indexOf("=");//取得=号的位置
     var num=loc.substr(n2+1, n1-n2);//从=号后面的内容
      
     $(document).ready(function(){
      $(".t1").html("sgjger");
      $.ajax({
                type: "post",//请求方式
                url: "BookInfo.json",//地址，就是json文件的请求路径
                asynx:'true',
                dataType: "json",
                timeout:50000,
                 contentType : "application/json;charset=utf-8", 
                              //数据类型可以为 text xml json  script  jsonp
　　　　　　　　　 success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                   $(".t1").html(result.list[num].BookName);
                  $(".t2").html(result.list[num].Author);
                   $(".t3").html(result.list[num].Price);
                    $(".t4").html(result.list[num].Publisher);
                     $(".t5").html(result.list[num].BookContent);
                     $(".ima").attr('src',result.list[num].url); 
                }
            });
            });
    }
    
   
    </script>
  </head>
  
  <body onload="show()">
   <a href="ListServlet" target="iframe_a">返回</a>
   <div id="bg">
   <div id="im">
  <img src="" alt="图书" class="ima"  >
   </div>
   <div id="info">
  <ul>
   <li><span class="n1">书名:</span><span class="t1">g</span></li>
    <li><span class="n1">作者:</span><text class="t2"></text></li>
     <li><span class="n1">定价:</span><text class="t3">34dfgfeds</text></li>
     <li><span class="n1">出版社:</span><text class="t4">34dfgfeds</text></li>
    <li><span class="n2">书本内容:</span><textarea rows="3" cols="4" class="t5">34dfgfeds</textarea></li>
    </ul>
    
    <hr></hr>
    <text style="margin-left:30px;">未经许可，不得以任何方式复制或抄袭本书值部分或全部内容<br>
    <span style="font-weight:bold;margin-left:30px;">版权所有，侵权必究</span>
    </text>
   </div>
   </div>
  </body>
</html>
