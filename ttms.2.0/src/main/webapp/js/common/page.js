$(document).ready(function(){
	/*$("#pageId").on('click','.pre',doPre);
	$("#pageId").on('click','.next',doNext);*/
	//首页
	$('#pageId').on('click','.firstPage',jumpPage);
	//上一页跳转
    $('#pageId').on('click','.pre',jumpPage);
    //下一页跳转
    $('#pageId').on('click','.next',jumpPage);
    //尾页跳转
    $('#pageId').on('click','.lastPage',jumpPage);
    
    //查询
	
});
//设置分页
function setPagination(pageObject){
	//绑定总页数，便于后面取出使用，而不用设置全局
	 $("#pageId").data("pageCount",
	 pageObject.pageCount);
	//绑定当前页，便于后面取出使用，而不用设置全局
	 $("#pageId").data("pageCurrent",
			 pageObject.pageCurrent);
}



function jumpPage(){
	//获取当前页，用于计算startIndex---- return (pageCurrent-1)*pageSize;
	//最后记得更新数据即将计算完的currentPage重新绑定到pageCurrent
	var currentPage=$("#pageId").data("pageCurrent");
	//获取总页数
	var pageCount=$("#pageId").data("pageCount");//获得pageCount属性key对应的值
	
	console.log('当前页码：'+currentPage);
   	var classVal = $(this).attr('class');
   	if('next' == classVal){
   	 debugger
	    //获取总页数，若超过总页数，则不增
		
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
   	if('firstPage' == classVal){
   		currentPage=1;
   	}
   	if('lastPage' == classVal){
   		currentPage=pageCount;
   	}
   	
   	
   	//必须重新绑定数据，不然数据不变
   	//重新绑定pageCurrent
   	$('#pageId').data("pageCurrent",currentPage);

   	doGetObjects();
   }



