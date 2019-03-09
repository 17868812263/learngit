package cn.tedu.ttms.team.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.saveRunTimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.team.dao.TeamDao;
import cn.tedu.ttms.team.entity.Team;
import cn.tedu.ttms.team.service.TeamService;

@Service("TeamService")
public class TeamServiceImpl implements TeamService {
    @Resource(name="TeamDao")
    private TeamDao dao;
    
    
    @Resource(name="ProjectDao")
	private ProjectDao projectDao;
	
    //
	/*@Override
	public List<Map<String, Object>> findPageObjects(String projectName, Integer valid, int startIndex, int pageSize) {
		List<Map<String, Object>> list=dao.findPageObjects(projectName, valid, startIndex, pageSize);
		return list;
	}*/


    
	/**
     *业务层用于计算，逻辑，持久层用于访问数据库，
     *Dao层访问数据库时所传的参数可以通过Service计算出
     *错误:直接将dao方法和service方法一样
     */
	@Override
	public Map<String, Object> findPageObjects(
			String projectName, 
			Integer valid, 
			Integer pageCurrent) {
			PageObject pageObject=new PageObject();
			//pageObject.setPageSize(3); 不写默认是2,我们在对象中定义的
			pageObject.setPageCurrent(pageCurrent);
			//根据startIndex及参数获得当前页数据
			List<Map<String,Object>> list=
			dao.findPageObjects(projectName,
					valid,
					pageObject.getStartIndex(),
					pageObject.getPageSize());
			//获得总页数
			int rowCount=
			dao.getRowCount(projectName, valid);
			pageObject.setRowCount(rowCount);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("list", list);
			map.put("pageObject", pageObject);
			return map;
	}

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
    
	//插入信息
	@Override
	public void saveObject(Team team) {
		
		if(team==null)
			throw new saveRunTimeException("保存的信息不能为空");
		int rows=dao.insertObject(team);
		if(rows==-1){
			throw new RuntimeException("save error");
		}
	}

	@Override
	public List<Map<String, Object>> findProjectIdAndNames() {
		return projectDao.findIdAndNames();
	}

}
