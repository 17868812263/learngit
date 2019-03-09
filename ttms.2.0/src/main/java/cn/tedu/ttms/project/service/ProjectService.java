package cn.tedu.ttms.project.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;

public interface ProjectService {
	//接口默认public
	
	List<Project> findALL();
	
	//添加分页查询方法
	Map<String,Object> findPageObjects(Project project,PageObject pageObject);
	
	
	//启用 禁用
	void validById(String checkedIds,Integer valid);
	
	void saveObject(Project project);
	
	
	//修改信息
	public void updateObject(Project entity);
		
		
	//根据id查询信息
	public Project findObjectById(Integer id);

}
