
   $(document).ready(function(){
	   
	    $("#queryFormId").on("click",".btn-search",doGetObjects);
	  //添加与修改
	    $("#queryFormId").on("click",".btn-add,.btn-update",showTable);
	   //启用 禁用
	    $("#queryFormId").on("click",".btn-valid,.btn-invalid",doValidById);
	    
	    doGetObjects();
	    console.log("加载！！！");
	   
   });
   
   
   
   
   //显示添加或修改模态框
   function showTable(){
	   debugger
	   console.log("显示模态框！！！");
	   if($(this).hasClass("btn-update")){
		   title="修改团信息";
		   //将修改的项目的id绑定到模态框上,
		   //点击修改时时是根据id查询出对象，在对其进行修改            
//在doGetObjects时将id绑定到tr上，此处点击btn，获取节点.parent().parent()即 tr
		   $("#modal-dialog").data("id",$(this).parent().parent().data("id"));
		 
	   }
	   
	   if($(this).hasClass("btn-add")){
		   title="添加团信息";
	   }
	   
	   var url="team_editUI.do";
		//在模态框的.moday-body位置异步加载url
		$("#modal-dialog .modal-body").load(url,
				function(){//加载完成执行此
			 $(".modal-title").html(title);
			//默认内容隐藏
			 $("#modal-dialog").modal("show");
		})
	   
   }
   
   
   
   

   //获取项目信息
   function doGetObjects(){
	   debugger
	   var url="dofindPageObjects.do";
       
	   var params=getQueryFormData();
	   console.log(params);
	   var pageCurrent=$("#pageId").data("pageCurrent");
	   if(pageCurrent){
		   params.pageCurrent=pageCurrent;
	   }else{
		   params.pageCurrent=1;
	   }
	   
	   
	   $.post(url,params,function(result){
		   console.log(params.projectName+"/"+params.valid);
		   debugger
		   console.log("doGetObjects");
		  
		  if(result.state==1){
			  //根据Map的key值分别取出存储的pageObject(页面信息)和list(查询的数据)
			  var list=result.data.list;
				
			   var pageObject=result.data.pageObject;
			   console.log(pageObject);
			   console.log("测试post");
			   //调用填充表格
			   setTableRows(list);
			   
			   //调用分页
			   setPagination(pageObject);
		  }else{
			  alert(result.message);
		  }
		   
		   
		   
	   });

   }
   

   function getQueryFormData(){
		var params={
		"projectName":$("#searchNameId").val(),
		"valid":$("#searchValidId").val(),
		"pageCurrent":$("#pageId").data("pageCurrent")
		}
		return params;
	}
   
   
   //填充表格
   function setTableRows(list){
	   var tBody=$('#tbodyId');
	 //首先清空tbody中数据
	   tBody.empty();
	   
	   var temp=
		   '<td><input type="checkbox" name="checkedItem" value="[id]"></td>'
		   +'<td>[name]</td><td>[projectName]</td><td>[valid]</td>'
		   +'<td><button type="button" class="btn btn-default btn-update">修改</td>';
	   //追加新的数据
	   for(var i in list){//循环一次取一行数据(对应一对tr对象)
       var tr=$('<tr></tr>');//创建一对tr对象
       //var tds='<td>'+list[i].code+'</td><td>'+list[i].name+'</td>';
	   //tr.append(tds);//将td添加到tr对象中
       
       tr.data("id",list[i].id);//绑定数据,便于后续获得此数据进行修改等操作
       
       tr.append(
       temp.replace('[id]',list[i].id)
       .replace('[name]',list[i].name)		        
       .replace('[projectName]',list[i].projectName)
       .replace('[valid]',list[i].valid?'启用':'禁用')
       );
	   tBody.append(tr);//将tr对象追加tbody
	   }
	   
	   
	   console.log(list[i].projectName+'//'+list[i].projectName.note);
   }
   
  
   
   
   
   /**启动/禁用*/
   function doValidById(){
   	
     //1.判定触发的是启用还是禁用按钮(根据类选择器)
     var state;//定义一个状态值,表示状态
     if($(this).hasClass("btn-valid")){
   	  state=1;//启用(将选中的记录的valid修改为1)
     }else{
   	  state=0;//禁用
     }
     //2.获得选中的checkbox对应的id值(id是记录的唯一标识)
     var checkedIds=getCheckedIds();
     console.log("checkedIds="+checkedIds);
     
     var params={"checkedIds":checkedIds,
		      "valid":state};
     //3.获得的数据通过ajax发送异步请求到服务器然后
     //执行更新操作
     var url="doTeamValidById.do";
     
     $.post(url,params,function(result){
    	 if(result.state==1){
    		 doGetObjects();
    	 }else{
    		 //全局异常处理返回相应异常交给页面处理
    		 alert(result.message);
    	 }
    	 
     })
   }
   
   function getCheckedIds(){
   	var checkedIds='';
   	//迭代input对象
   	$('tbody input[name="checkedItem"]')
   	.each(function(){
   		//判定是否选中
   		if($(this).is(":checked")){
   			if(checkedIds==''){
   			checkedIds+=$(this).val();
   			}else{
   			checkedIds+=","+$(this).val();
   			}//"1,2,3,4,5";
   		};
   	})
   	return checkedIds;
   }
   
   
   
   
   
   
   
   
   
   
   