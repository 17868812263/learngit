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
		
	/* 	$("#projects").html('hahhahha'); */
		//降低耦合度
		f1();


	});
	
	function f1(){
		$("#projects").html('hahhahha');
    }
	
	</script>


</head>
<body style="font-size:30px;">
    <h1>测试</h1>
   <div id="projects">
 <table id="tb1">
    <tr id="tr">
       <td>id</td>
       <td>code</td>
       <td>name</td>
     </tr>
   </table>
    </div>   
    <div id="d3">
			<table width="100%">
				<thead>
					<tr>
						<td>代码</td>
						<td>名称</td>
						<td>价格</td>
					</tr>
				</thead>
				<tbody id="tb1">
				</tbody>
			</table>
		</div>
</body>
</html>





