<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib 
    uri="http://java.sun.com/jsp/jstl/core"
    prefix="c"%>
    
 <c:set var="basePath" 
   value="${pageContext.request.contextPath}"></c:set>   

<html>
<head>
<!--  引入jquery方式三种：-->
<!-- <script type="text/javascript"
src="../js/jquery-1.4.3.js">
</script> -->

<%-- 
<script type="text/javascript" 
src="<%=request.getContextPath()%>/jquery/jquery-3.2.1.min.js"></script>
 --%>

<script type="text/javascript"
src="${basePath}/js/jquery-1.4.3.js">
</script>


<script type="text/javascript">
     
    /* 
       可以在同一个页面中无限次地使用$(document).ready()事件。
       其中注册的函数会按照（代码中的）先后顺序依次执行。
      一般为降低耦合度，可以先在加载函数中调用函数，具体函数实现写在分开写在下面
    */

	/* 页面加载时执行 */
	$(document).ready(function() {
		//降低耦合度
		f1();
		f2();
		f3();
		f4();
		f5();


	});
	
	function f1(){
		$("#content").load("findObjects.do");
    }
	
	function f2(){
		//返回数据为json对象时：getJSON(url, [data], [callback]),
		//url为请求地址（必选）
		//data为待发送的参数（可选）
		//callback为载入成功时调用的函数（可选）
		
		//发送ajax请求，更新页面
		$.getJSON("findObjects.do",function(result){
			$("#content2").html(result.name);
		});
	}
        
	function f3(){
		$.getJSON("findObjects.do",function(result){
			$("#content3").html(result.code);
		});
	}
	
		
	
	function f4(){
		//类似的方法当 HTTP POST 请求载入信息(调用该方法时就是post请求)：
		//jQuery.post(url, [data], [callback], [type])
		//urlString发送请求地址。

		//data (可选)Map待发送 Key/value 参数。

		//callback (可选)Function发送成功时回调函数。

		//type （可选） String返回内容格式，xml, html, script, json, text, _default。

		$.post("findObjects.do",function(result){
			$("#content4").html(result.name+':'+result.code);
		});
	}
		
	
	function f5(){
		$.getJSON("projectList.do",function(list){
			var jsonStr="";
			for(var i in list){
				jsonStr+='/'+list[i].id+'/'+list[i].name+'</br>';
			}
			
			$("#projects").html(jsonStr);
		});
	}
		

    /* 页面加载时执行二： */
	/* $ (function(){

		}); */
</script>


</head>
<body style="font-size:30px;">
   测试成功！！！
  <h1>Project 管理</h1>
   <div id="content"></div>
   <div id="content2"></div>
   <div id="content3"></div>
   <div id="content4"></div>
   
   <div id="projects">
     <tr></tr>
    </div>   
</body>
</html>





