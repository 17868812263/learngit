package project.dao;

import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.project.entity.Project;

public class TestProjectDao {

	
	@Test
	public void testInsertObject()throws Exception{
		//1.获得ApplicationContext对象
		ApplicationContext ctx=
		new ClassPathXmlApplicationContext(
				"spring-mvc.xml",
				"spring-pool.xml",
				"spring-mybatis.xml");
		//2.获得DAO对象
		ProjectDao projectDao=
		(ProjectDao)ctx.getBean("ProjectDao");
		//3.构建entity对象
		Project entity=new Project();
		entity.setId(2);
		entity.setCode("TT-20170811-CHN-SH-001");
		entity.setName("中国黄金游4");
		SimpleDateFormat sdf=
		new SimpleDateFormat("yyyy-MM-dd");
		entity.setBeginDate(sdf.parse("2017-07-20"));
		entity.setEndDate(sdf.parse("2017-08-20"));
		entity.setValid(0);
		entity.setNote("中国黄金游....");
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		//4.将对象写入到数据库
		int n=projectDao.insertObject(entity);
		Assert.assertEquals(1, n);
		System.out.println("测试！！！");
		System.out.println(n);
	}
	
	
	@Test
	public void test2(){
		//1.获得ApplicationContext对象
				ApplicationContext ctx=
				new ClassPathXmlApplicationContext(
						"spring-mvc.xml",
						"spring-pool.xml",
						"spring-mybatis.xml");
				//2.获得DAO对象
				ProjectDao projectDao=
				(ProjectDao)ctx.getBean("ProjectDao");
		
		System.out.println(projectDao.findAllObject());
	}
}






