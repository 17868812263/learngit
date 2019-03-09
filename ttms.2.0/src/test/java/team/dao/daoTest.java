package team.dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.team.dao.TeamDao;
import cn.tedu.ttms.team.entity.Team;

public class daoTest {
private TeamDao dao;
	
	@Before
	public void init(){
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext(
						"spring-mvc.xml",
						"spring-pool.xml",
						"spring-mybatis.xml");
		dao=(TeamDao)ctx.getBean("TeamDao");
	}
    
	
	//插入
	@Test
	public void test1(){
		Team team=new Team();
		team.setId(5);
		team.setProjectId(84);//必须是项目中有的id
		team.setName("西安三日团");
		team.setValid(0);
		team.setNote("钟楼....");
		team.setCreatedUser("admin");
		team.setModifiedUser("admin");
		int row=dao.insertObject(team);
		System.out.println("插入成功！");
		
	}

	//查询所有
	@Test
	public void test2() {
//		System.out.println(dao.findAll());
	}
    
	
	//关联查询
	@Test
	public void test3() {
		String projectName=null;
		int valid=0;
		int pageSize=2;
		int startIndex=0;
		System.out.println(dao.findPageObjects(projectName,valid,startIndex,pageSize
				));
	}
	
	
	//总记录数
		@Test
		public void test4() {
			String projectName=null;
			int valid=0;
			int rows=dao.getRowCount(projectName, valid);
			System.out.println(rows);
		}
	
}
