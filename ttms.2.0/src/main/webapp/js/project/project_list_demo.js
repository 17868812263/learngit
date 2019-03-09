   
   /*//定义全局变量当前页（第一页开始）
   var currentPage=1; */

   $(document).ready(function(){
	   //页面加载完成执行此方法
	   
	 //查询
	    $("#queryFormId").on("click",".btn-search",doGetObjects);
	   //启用禁用
	    $("#queryFormId").on("click",".btn-valid,.btn-invalid",doValidById);
	    //添加
	    $("#queryFormId").on("click",".btn-add,.btn-update",showTable);
	    
	    
	   
	    //1.发起ajax请求(findObjects.do)
	   //2.将返回的结果填充到content位置
	    doGetObjects();
	    console.log("加载！！！");
	    //注册事件
	    /*$("#pageId").on("click",".pre",doPre);
	    $("#pageId").on("click",".next",doNext);*/
	    
	    
	  //首页跳转
	    //$('#pageId').on('click','.firstPage',jumpPage);
	    //上一页跳转
	    //$('#pageId').on('click','.pre',jumpPage);
	    //下一页跳转
	    //$('#pageId').on('click','.next',jumpPage);
	    //尾页跳转
	    //$('#pageId').on('click','.lastPage',jumpPage);
	    
	    
   });
   
   
 //显示添加或修改模态框
   function showTable(){
	   debugger
	   console.log("显示模态框！！！");
	   if($(this).hasClass("btn-update")){
		   title="修改项目";
		   //将修改的项目的id绑定到模态框上,
		   //点击修改时时是根据id查询出对象，在对其进行修改            
//在doGetObjects时将id绑定到tr上，此处点击btn，获取节点.parent().parent()即 tr
		   $("#modal-dialog").data("id",$(this).parent().parent().data("id"));
		 
	   }
	   
	   if($(this).hasClass("btn-add")){
		   title="添加项目";
	   }
	   
	   var url="editUI.do";
		//在模态框的.moday-body位置异步加载url
		$("#modal-dialog .modal-body").load(url,
				function(){//加载完成执行此
			 $(".modal-title").html(title);
			//默认内容隐藏
			 $("#modal-dialog").modal("show");
		})
	   
   }
   
   
   
   
   
 //分页跳转
  /* function jumpPage(){
	var currentPage=$("#pageId").data("pageCurrent");
	console.log('当前页码：'+currentPage);
   	var classVal = $(this).attr('class');
   	if('next' == classVal){
   	 debugger
	    //获取总页数，若超过总页数，则不增
		var pageCount=$("#pageId").data("pageCount");//获得pageCount属性key对应的值
	console.log('总页数：'+pageCount);	
   	 if(currentPage<pageCount){
		currentPage++;
		console.log('next当前页码：'+currentPage);
		}
   	}
   	if('pre' == classVal){
   		if(currentPage>1){
 		   currentPage--; 
 	   }
   	}

   	doGetObjects();
   }
  */
   
   
/*   function doPre(){
	   
	   var currentPage=$("#pageId").data("currentPage");
	   
	   //上一页 当前页减一，传给params用于计算startIndex
	   if(currentPage>1){
		   currentPage--; 
	   }
	   doGetObjects();
   }
   
   
   
   function doNext(){
	   var currentPage=$("#pageId").data("currentPage");
	   //下一页 当前页加一，传给params用于计算startIndex
	   debugger
	    //获取总页数，若超过总页数，则不增
		var pageCount=$("#pageId").data("pageCount");//获得pageCount属性key对应的值
		if(currentPage<pageCount){
		currentPage++;
		}
		doGetObjects();
   }*/
   
   
   //获取项目信息
   function doGetObjects(){
	   debugger
	   var url="projectListPage.do";
	   //获得tbody对应dom节点对象
	   
	   //将currentPage值赋值给result数据中的"pageCurrent"属性
	   //注："pageCurrent"必须和pageCurrent一致,用于查询数据时起始页的计算
	   
	   //var params={"pageCurrent":$("#pageId").data("pageCurrent")};
	   var params=getQueryFormData();
	   //var pageCurrent=$('#pageId').data("pageCurrent");
	  
	   $.post(url,params,function(result){
		   console.log(params.name+"/"+params.valid);
		   debugger
		   console.log("doGetObjects");
		  
		  if(result.state==1){
			  //根据Map的key值分别取出存储的pageObject(页面信息)和list(查询的数据)
			  var list=result.data.list;
				
			   var pageObject=result.data.pageObject;
			   
			   
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
		"name":$("#searchNameId").val(),
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
		   +'<td>[code]</td><td>[name]</td><td>[beginDate]</td><td>[endDate]</td><td>[valid]</td>'
		   +'<td><button type="button" class="btn btn-default btn-update">修改</td>';
	   //追加新的数据
	   for(var i in list){//循环一次取一行数据(对应一对tr对象)
       var tr=$('<tr></tr>');//创建一对tr对象
       //var tds='<td>'+list[i].code+'</td><td>'+list[i].name+'</td>';
	   //tr.append(tds);//将td添加到tr对象中
       
       tr.data("id",list[i].id);//绑定数据,便于后续获得此数据进行修改等操作
       
       tr.append(
       temp.replace('[id]',list[i].id)
       .replace('[code]',list[i].code)		        
       .replace('[name]',list[i].name)
       .replace('[beginDate]',new Date(list[i].beginDate).toLocaleDateString())
       .replace('[endDate]',new Date(list[i].endDate).toLocaleDateString())
       .replace('[valid]',list[i].valid?'启用':'禁用')
       );
	   tBody.append(tr);//将tr对象追加tbody
	   }
   }
   
   //设置分页
   /*function setPagination(pageObject){
	 //绑定数据，将result.pageObject.pageCount  存储的数据  绑定到  $("#pageId")的"pageCount"属性上
	   //获取总页数，用于判断下一页时是否增加
	   $("#pageId").data("pageCount",pageObject.pageCount);
	   
	   //设置当前页
	   $("#pageId").data("pageCurrent",pageObject.pageCurrent);
	   
	   console.log('分页');
   }*/
   
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
     var url="doValidById.do";
     
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

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   