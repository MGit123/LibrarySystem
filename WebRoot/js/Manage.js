/**
 * 
 */

 function change1(){
         $(document).ready(function(){
        	 if($("#b").is(":hidden")||$("#c").is(":hidden")||$("#d").is(":hidden")){
                 $("#b").hide();
                   $("#c").hide();
                   $("#d").hide();
                 }
        $("#a").toggle();
     });
    }
    
     function change2(){
         $(document).ready(function(){
         if($("#a").is(":hidden")||$("#c").is(":hidden")||$("#d").is(":hidden")){
         $("#a").hide();
           $("#c").hide();
           $("#d").hide();
         }
        $("#b").toggle();
     });
    }
    
     function change3(){
         $(document).ready(function(){
         if($("#a").is(":hidden")||$("#b").is(":hidden")||$("#d").is(":hidden")){
         $("#a").hide();
           $("#b").hide();
           $("#d").hide();
         }
        $("#c").toggle();
     });
    }
    
     function change4(){
         $(document).ready(function(){
         if($("#a").is(":hidden")||$("#b").is(":hidden")||$("#c").is(":hidden")){
         $("#a").hide();
           $("#b").hide();
           $("#c").hide();
         }
        $("#d").toggle();
     });
    }
     
     
    function hide(){
     $(document).ready(function(){
        $(".mima").hide();
         $(".mimc").hide();
     });
    }
    
    
    function changepa(){
      var p1=$(".p1").val();
      var p2=$(".p2").val();
      var p3=$(".p3").val();
      
      var data={
        "oldpa":p1,
        "newpa":p2
      }
      
      if(p2==p3&&p2!=""&&p3!=""){
      
      $.ajax({
        type : "post",
		url : "passUpdate",
		timeout:50000,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
		data : data,
		cache : true,
		async : true,
		success: function (data ,textStatus, jqXHR){
	        	if(data.code == 200){
	        		$("#b").hide();
	        		alert("密码修改成功");
	        	}else{
	        		alert(data.message);
	        	}
		    },
	        error:function (XMLHttpRequest, textStatus, errorThrown) {      
	            alert(typeof(errorThrown));
	        }      
      });
      
      }else if(p2==""&&p3==""){
         alert("新密码不能为空")
      }
      else
      {
      alert("新密码不一致")
      }
      
    }
    
    function addBook(){
       var pnum=$(".pnum").val();
      var pname=$(".pname").val();
      var pauthor=$(".pauthor").val();
      var ptype=$(".ptype").val();
      var pbn=$(".pbn").val();
      var data={
        "pnum":pnum,
        "pname":pname,
        "pauthor":pauthor,
        "ptype":ptype,
        "pbn":pbn
      }
      
       if(pnum!=""&&pauthor!=""&&pname!=""&&pbn!=""&&ptype!=""){
      $.ajax({
        type : "post",
		url : "AddBook",
		timeout:50000,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
		data : data,
		cache : true,
		async : true,
		success: function (data ,textStatus, jqXHR){
	        	if(data.code == 200){
	        		$("#c").hide();
	        		alert("图书入库成功!");
	        	}else{
	        		alert(data.message);
	        	}
		    },
	        error:function (XMLHttpRequest, textStatus, errorThrown) {      
	            alert("图书已入库成功,请勿重复添加!");
	        }      
      });
      
      }
      else
      {
      alert("请把数据填写完整");
      }
    
    }
    
    
   