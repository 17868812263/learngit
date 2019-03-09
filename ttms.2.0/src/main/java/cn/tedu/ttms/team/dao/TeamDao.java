package cn.tedu.ttms.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.team.entity.Team;

@Repository("TeamDao")
public interface TeamDao {

	/**
	 * @param entity
	 *            代表是一个project对象
	 * @return 表示insert记录的行数
	 */
	/**
	 * 
	 * @param team
	 * @return
	 * 插入数据
	 */
	int insertObject(Team team);
	
	/**
	 * 一条记录对应一个Map(key为列的名字,value为列的值)
	 * 多条记录是多个map对象,然后多个map放到list集合
	 * */
	List<Map<String,Object>> findPageObjects(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	
	
	
	/**统计有多少条记录*/
	int getRowCount(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid);
	
	/**禁用启用记录数
	 * @return 表示更新的行数,假如返回值为-1表示
	 * 更新失败
	 * */
	public int validById(
			@Param("ids")String[] ids,
			@Param("valid")Integer valid);
	

}







