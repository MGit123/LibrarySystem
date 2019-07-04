<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BookInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="css/list.css">

	<style type="text/css">    
	#b{
	position:absolute;
    width:100%;
    height:400px;
    float:left;
    left:10%;
    top:8%;
	}
	
	.text{
	width:750px;
	height:60px;
	font-size:16px;
	float:left;
	text-align:left;
	text-indent:2em;
	margin-top:5px; 
	margin-left:20px;
	}
	
 table.info a{
    margin-right:10px;
 }
	
	</style>
	
   <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    
    <script type="text/javascript">
   function load(){
   $(document).ready(function(){
   var $AllLi=$("text");
      $AllLi.hide();
       $("button").click(function(){
      $AllLi.toggle();
    });
    
   
   $.ajax({
   url:'BKIServlet',
   type:'Post',
   asynx:'true',
   timeout:50000,
   contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   success:function(data){
   var m="";

     var data=JSON.parse(data); 
            $.each(data, function(i,n) {
                console.log(n);
                m+="<tr>"
                m+="<td>"+n["userNum"]+"</td>";
                m+="<td>"+n["username"]+"</td>";
                m+="<td>"+n["bookname"]+"</td>";
                m+="<td>"+n["author"]+"</td>";
                m+="<td>"+n["borrowDate"]+"</td>"; 
                m+="<td>"+n["returnDate"]+"</td>"; 
                m+="<td><a onclick='return broAg()'>续借</a><a onclick='return re(this)'>还书</a></td>";
                m+="</tr>";       
            });
            
            $(".info").append(m);  
   },
   error:function(){
   alert("数据获取失败");
   }
      });
    
  });
  
   }
     
  
   function re($this){
    var k=window.confirm("确认要归还该书吗？");
    var data="";
    var user_num="";
    var book_name="";
       if(k){
         $(document).ready(function(){
  $(".info").on("click","tr",function() {
   $($this).parent().parent().remove();
     var td= $(this).find("td");
		 user_num= td.eq(0).text();
		 book_name= td.eq(2).text();
		  data={
		   "use_num":user_num,
		   "bk_name":book_name
		}
		
		  $.ajax({
        type : "post",
		url : "DeleBook",
		timeout:50000,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data : data,
		cache : true,
		async : true,
		success: function (data ,textStatus, jqXHR){
	        	if(data.code == 200){
	        	alert("图书归还成功!");
	        	    }
	        	}
	        });
         });
         
  });
  
       return true;}
       else{
       return false;
       }
  }
  
    
       function broAg(){
    var k=window.confirm("您要续借该书吗？");
       if(k){
         $(document).ready(function(){
  $(".info").on("click","tr",function() {
       var td= $(this).find("td");
		 $(this).find("td").eq(5).html("2019-7-25");
		 alert("续借成功!");
  });
  });
       
       return true;}
       else{
       return false;
       }
       }
  
    </script>
    
     <%String info=(String)request.getAttribute("borrowInfo"); 
 if(info!=null){ %>
<script type="text/javascript" language="javascript">
alert("<%=info%>")
</script>
 <%} %>
 
  </head>
  <body onload="load()">
        <h2 >借阅信息</h2>
     <div id="b" >
       <table class="tab" style="margin-top:10px;">
     <tr>
     <th>用户账号</th>
     <th>用户名</th>
     <th>书籍</th>
     <th>作者</th>
     <th>借书日期</th>
      <th>还书日期</th>
       <th>操作</th>
     </tr>
    </table>
    <table class="info">
    </table>
    
     <button style="float:left; margin-top:10px; margin-left:20px;">温馨提示</button><br><br>
     <text class="text">尊敬的用户，欢迎使用图书管理系统。你具有20本书的借用权限，时间为一个月，过期为归还将收取每天0.2元的费用
     </text>
    </div>
  </body>
</html>
