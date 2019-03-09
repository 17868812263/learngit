$(document).ready(function(){
	 $("#modal-dialog").on("click",".save",
			  doSaveOrUpdate);
	doInitProjectIdAndNames();
	
	
	 //当模态框隐藏时，save上绑定的事件执行解绑动作,并清除id
	$("#modal-dialog").on("hidden.bs.modal",function(){
		 $(this).off('click','.save')
	 });
});

//初始化项目id和名字列表
function doInitProjectIdAndNames(){
	var url="doFindPrjIdNames.do"
	$.getJSON(url,function(result){
		if(result.state==1){
		    setProjectSelectOptions(result.data);
		}else{
			alert(result.message);
		}
	})
}
function setProjectSelectOptions(list){
	var selectObj=$("#selectId");
	var optionObj="<option value=[id]>[name]</option>"
	for(var i in list){//List<Map<String,Object>>
	selectObj.append(optionObj
			.replace("[id]",list[i].id)
			.replace("[name]",list[i].name));
	}
}


//保存或修改数据
function doSaveOrUpdate(){
	var url="doSaveTeam.do";
	var params=getEditFormData();
	$.post(url,params,function(result){
		if(result.state==1){
			alert("save ok");
			$("#modal-dialog").modal("hide");
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}
//获得编辑框中数据
function getEditFormData(){
	var params={
		"name":$("#nameId").val(),
		"projectId":$("#selectId").val(),
		"valid":$('input[name="valid"]:checked').val(),
	    "note":$('#noteId').val()
	}
	console.log(JSON.stringify(params));
	return params;
}



 
 
 
 
 
 
 
 
 
 
