package cn.tedu.ttms.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.project.service.ProjectService;

/**
 * 产品项目管理控制器对象
 */
@Controller
public class ProjectController {
	
	@Resource(name="ProjectService")
	private ProjectService ps;
	
	
	/**
	 * 此方法用于返回项目管理的列表页面
	 * http://localhost:8080/ttms2.0/project/listUI.do
	 * @return
	 */
	@RequestMapping("/listUI")
	public String listUI(){
		//查看spring-mvc.xml (查看视图解析器)
		return "project/projectList";
	}//? return 语句返回的字符串对应一个jsp文件(
	//在哪,名字是什么)
	
	@RequestMapping("/editUI")
	public String editUI(){
		return "project/project_edit";
	}
	
	
	
	
	
	@RequestMapping("/findObjects")
	@ResponseBody//此注解的作用是将java对象转换一个json字符串,然后返回
	//页面上回将这个json字符串转换为json对象
	public Map<String,Object> findObjects(){
		Map<String,Object> map=
		new HashMap<String,Object>();
		map.put("id", 1);
		map.put("code", "TT-20170711-CHN-BJ-001");
		map.put("name", "环球游");
		return map;
	//{"id":1,"code":"TT-20170711-CHN-BJ-001","name":"环球游"};
	}
	
	@RequestMapping("/projectList")
	@ResponseBody//此注解的作用是将java对象转换一个json字符串,然后返回
	//页面上回将这个json字符串转换为json对象
	public List<Project> projectList(){
		return ps.findALL();
	}
	
	
	@RequestMapping("/projectListPage")
	@ResponseBody//此注解的作用是将java对象转换一个json字符串,然后返回
	//页面上回将这个json字符串转换为json对象
	public JsonResult findPageObjects(Project project,PageObject pageObject){
		Map<String,Object> map=ps.findPageObjects(project,pageObject);
		return new JsonResult(map);
	}
	
	
	
	
	/*
	 * 这样也可以取出查询结果但是只能取出数据，还得分开写页面数据，所以直接返回上面的Map，
	 * 页面取结果时根据Map key 可分开取出list 和 pageObject
	 * 
	 * public List<Project> findPageObjects(){
		PageObject pageObject =new PageObject();
		Map<String,Object> map=ps.findPageObjects(pageObject);
		List<Project> list=(List<Project>) map.get("list");
		return list;
	}
	
	*/
	@RequestMapping("/doValidById")
	@ResponseBody
	public JsonResult doValidById(String checkedIds,Integer valid){
		System.out.println("测试doValidById");
		ps.validById(checkedIds, valid);
		//此处由于加上了 @ResponseBody，该方法不需要返回值，
		//若不加@ResponseBody会调用视图解析器返回页面，
		
		//若上面发生异常，现在当前类寻找异常处理，若没有会找全局异常处理，
		//即return new JsonResult(e);
		//根据e所传异常类型返回页面作相应处理
		
		return new JsonResult();
	}
	
	@RequestMapping("/doSaveProject")
	@ResponseBody
	public JsonResult doSaveProject(Project project){
		ps.saveObject(project);
		return new JsonResult();
	}
	
	
	
	
	//修改页面信息
	@RequestMapping("/doUpdateObejct")
	@ResponseBody
	public JsonResult doUpdateObejct(Project project){
		ps.updateObject(project);
		System.out.println("测试修改信息保存");
		return new JsonResult();
	}
	
	//修改页面信息
		@RequestMapping("/doFindObejctById")
		@ResponseBody
		public JsonResult doFindObejctById(Integer id){
			Project project=ps.findObjectById(id);
			return new JsonResult(project);
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/new")
	public String NewFile(){
		//查看spring-mvc.xml (查看视图解析器)
		return "distributor/NewFile";
	}
	
	
	
	
	

}




