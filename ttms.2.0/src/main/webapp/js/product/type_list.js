var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];

$(document).ready(function(){
	doGetObjects();
	debugger;
	$("#formHead")
	.on("click",".btn-add,.btn-update",loadEditPage);
});

//加载编辑页面
function loadEditPage(){
	var url="productType/editUI.do";
	if($(this).hasClass("btn-update")){
		  var id=getSelectedId();
		  console.log("id="+id);
		  if(id==-1){
			  alert("请先选中");return false;
			  }
		  $("#container").data("typeId",id);
		}
	//container为index.jsp中div的一个id
	$("#container").load(url);
}

//获得选中的id
function getSelectedId(){
	 var selections=
	 $("#typeTable")//获得table中选中的id值
	 .bootstrapTreeTable("getSelections");
	 if(selections.length==0){return -1;}//表示没选中
	 return selections[0].id;//{"id":123,"name":"...",...}
}

function loadTypeTree(){
	var url="";
	
}





function doGetObjects(){//加载数据(以树结构形式进行展示)
	var url="productType/doFindObjects.do";
	var tableId="typeTable";
	//var columns=[{},{}];//表头列名字???
	//构建treetable对象并进行初始化(参考tree.table.js)
	var table=new TreeTable(tableId,url,columns);
	table.setIdField("id");//设置选返回值
	table.setCodeField("id");//设置父子关系
	table.setParentCodeField("parentId");//设置父子关系
	table.setExpandColumn(2);//设置点击第几列展开
	table.setExpandAll(false);//设置初始化时是否全部展开
	table.init();
}







