package cn.tedu.ttms.team.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.team.entity.Team;
import cn.tedu.ttms.team.service.TeamService;

@Controller
public class TeamController {
	@Resource(name="TeamService")
	private TeamService ts;
	
	
	//返回团管理页面
	@RequestMapping("/teamlistUI")
	public String listUI(){
		//查看spring-mvc.xml (查看视图解析器)
		return "team/teamList";
	}
	
	
	@RequestMapping("/team_editUI")
	public String team_editUI(){
		System.out.println("模态框页面jsp");
		return "team/team_edit";
	}
	
	//错误写法
    //查询
	/*@RequestMapping("")
	@ResponseBody//此注解的作用是将java对象转换一个json字符串,然后返回
	//页面上回将这个json字符串转换为json对象
	public JsonResult findPageObjects(
			String projectName,
			Integer valid,
			int startIndex,
			int pageSize){
		
		List<Map<String, Object>> list=ts.findPageObjects(projectName, valid, startIndex, pageSize);
		return new JsonResult(list);
	}*/
	
	
	@RequestMapping("/dofindPageObjects")
	@ResponseBody//此注解的作用是将java对象转换一个json字符串,然后返回
	//页面上回将这个json字符串转换为json对象
	public JsonResult doFindPageObjects(
			String projectName, 
			Integer valid, 
			Integer pageCurrent){
		Map<String,Object> map=ts.findPageObjects(projectName, valid, pageCurrent);
		return new JsonResult(map);
	}
	
	
	
	
	
	@RequestMapping("/doTeamValidById")
	@ResponseBody
	public JsonResult doValidById(String checkedIds,Integer valid){
		System.out.println("测试doTeamValidById");
		ts.validById(checkedIds, valid);
		//此处由于加上了 @ResponseBody，该方法不需要返回值，
		//若不加@ResponseBody会调用视图解析器返回页面，
		
		//若上面发生异常，现在当前类寻找异常处理，若没有会找全局异常处理，
		//即return new JsonResult(e);
		//根据e所传异常类型返回页面作相应处理
		
		return new JsonResult();
	}
	
	
	
	
	@RequestMapping("/doSaveTeam")
	@ResponseBody
	public JsonResult doSaveProject(Team team){
		ts.saveObject(team);
		return new JsonResult();
	}
	
	
	
	/**查询项目id和名字*/
	@RequestMapping("/doFindPrjIdNames")
	@ResponseBody
	public JsonResult doFindProjectIdAndNames(){
		List<Map<String,Object>> map=
		ts.findProjectIdAndNames();
		return new JsonResult(map);
	}
	

}













