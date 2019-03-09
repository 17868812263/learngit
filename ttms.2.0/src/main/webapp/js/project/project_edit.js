 $(document).ready(function(){
	 //页面加载完成执行此方法
	 $("#modal-dialog").on("click",".save",doSaveUpdate);
	 
	 //获得id值
	 var id=$("#modal-dialog").data("id");
	//假如id有值,说明这是修改,然后根据id获得对象,初始化模态框数据
	if(id)doGetObjectById(id);
	 
	 //当模态框隐藏时，save上绑定的事件执行解绑动作,并清除id
	$("#modal-dialog").on("hidden.bs.modal",function(){
		 $(this).off('click','.save').removeData("id")
	 });
	
	
	
	
 });
  
 
 
 //根据id查找信息
 function doGetObjectById(id){
	 var url="doFindObejctById.do";
	 var params={"id":id};
	 $.post(url,params,function(result){
		 if(result.state==1){
			 //初始化表单数据
			 doFillFormData(result.data);
		 }
		 else{
			 alert(result.message);
		 }
	 });
 }
 
 //将数据填充到表格
 function doFillFormData(obj){
	 console.log("doFillFormData");
	$("#nameId").val(obj.name);
	$("#codeId").val(obj.code);
	$("#beginDateId").val(new Date(obj.beginDate).toLocaleDateString());
	$("#endDateId").val(new Date(obj.endDate).toLocaleDateString());
	$("#noteId").html(obj.note);
	// 启用禁用
	$('#editFormId input[name="valid"]').each(function() {
		console.log($(this).val() + "/" + obj.valid);
		if ($(this).val() == obj.valid) {
			$(this).prop("checked", true);
		}
	})
 }
 
 
 
 // 保存信息
 function doSaveUpdate(){
	 //用于非空验证
	 if($("#editFormId").valid()){
 
     //1.获取表单数据
	 var params=getEditFormData();
	 var id=$("#modal-dialog").data("id");
	 //2.将数据发送至服务端,根据id是否有值判断更新还是添加
	 var url=id?"doUpdateObejct.do":"doSaveProject.do";
	 $.post(url,params,function(result){
		 if(result.state==1){
			 //1.隐藏模态框
			 $("#modal-dialog").modal("hide");
			 //2.重新查询列表数据
			 doGetObjects();
		 }else{
			 alert(result.message);
		 }
	 });
	 
	 }
	 
 }
 
 //获取表单数据
 function getEditFormData(){
	 var params={
			 //更新时需要id
			 "id":$("#modal-dialog").data("id"),
			 "name":$("#nameId").val(),
			 "code":$("#codeId").val(),
			 "beginDate":$("#beginDateId").val(),
			 "endDate":$("#endDateId").val(),
			 "valid":$("input[name='valid']:checked").val(),
			 "note":$("#noteId").val()
	 }
	 console.log(params);
	 return params;
 }
 
 
 
 
 
 
 
 
 
 
 
