package cn.tedu.ttms.project.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.saveRunTimeException;
import cn.tedu.ttms.common.exception.updateException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.project.service.ProjectService;

/**
 * 项目管理service对象
 * 项目中所有与业务相关的事情一般都要放在service中,例如
 * 1)判定参数是否符合业务要求
 * 2)判定dao返回的数据是否是我们需要的结果
 * 3)执行一些日志的记录
 * 4)执行一些事务的处理
   5)....
 */

@Service("ProjectService")
@Transactional//此注解写到类上表示，类中所有方法都要使用事务
public class ProjectServiceImpl implements ProjectService {
	@Resource(name="ProjectDao")
	private ProjectDao dao;
	
	/**获得多条的项目信息*/
	@Transactional(readOnly=true,
			isolation=Isolation.READ_COMMITTED)
	@Override
	public List<Project> findALL(){
		return dao.findAllObject();
	}
	
	/**
	 * @param pageObject 用于接收控制层传递过来的分页信息
	 * 1)此参数中应包含startIndex
	 * 2)此参数中应包含pageSize
	 */
	@Override
	public Map<String, Object> findPageObjects(Project project,PageObject pageObject) {
		//获得查询结果
		List<Project> list=dao.findPageObjects(project,pageObject);
		//获得总记录数并将其赋值给PageObject，据此可以计算已经封装了计算总页数的方法，可以直接取出
		pageObject.setRowCount(dao.getRowCount(project));
		//将所取到的     数据       和        分页信息        存入map中并返回
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject",pageObject );
		
		//测试
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(project.getName()+" "+project.getValid());
		System.out.println("测试select结果");
		System.out.println(map.get("list"));
		System.out.println(map.get("pageObject"));
		System.out.println();
		System.out.println();
		System.out.println();
		
		return map;
	}

	@Transactional(rollbackFor={updateException.class})
	@Override
	public void validById(String checkedIds, Integer valid) {
		
		
		System.out.println("测试validVyId");
		
		
		// 更新
		//判定参数checkedIds
		if(checkedIds==null||checkedIds.length()==0){
			throw new NullPointerException("必须有选中的 id值");
		}
		System.out.println("测试判定1");
		
		//判定参数valid
	    if(valid!=1&&valid!=0){
	    	throw new IllegalArgumentException("无效的valid");
	    }
	    
	    System.out.println("测试判定2");
		//解析字符串
	    String ids[]=checkedIds.split(",");
		
	    //访问dao层方法执行更新操作
		int rows=dao.validById(ids, valid);
		System.out.println("测试更新条数："+rows);
		System.out.println("测试判定3");
		if(rows==-1){
			throw new RuntimeException("更新失败！！");
		}
		
		
		System.out.println("更新成功！！！");
		
		
		
	}

	@Override
	public void saveObject(Project project) {
		int rows=dao.insertObject(project);
		if(rows==-1){
			throw new RuntimeException("save error");
		}
	}

	
	//修改信息
	@CacheEvict(value="projectCache",key="#entity.id")
	@Override
	public void updateObject(Project entity) {
		System.out.println("==updateObject==");
		int rows=dao.updateObject(entity);
		System.out.println("测试保存:"+rows);
		if(rows==-1){
			throw new updateException("update error");
		}
	}
    
	//根据id查找信息
	@Cacheable(value="projectCache",key="#id")//map.put(key,data),缓存
	@Override
	public Project findObjectById(Integer id) {
		System.out.println("==findObjectId==");
		if(id==null){
			throw new saveRunTimeException("id cannot be empty");
		}
		Project project=dao.findObjectById(id);
		if(project==null){
			throw new saveRunTimeException("project doesn't exists");
		}
		return project;
	}
	

}








