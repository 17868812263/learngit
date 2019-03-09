package project.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.project.entity.Project;

public class testdao {
	private ProjectDao dao;
	
	@Before
	public void init(){
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext(
						"spring-mvc.xml",
						"spring-pool.xml",
						"spring-mybatis.xml");
		dao=(ProjectDao)ctx.getBean("ProjectDao");
	}

	@Test
	public void test1() throws ParseException {
				
		Project entity = new Project();
        System.out.println(entity);
		//写入数据
        entity.setId(5);
		entity.setCode("TT-20170811-CHN-YA-001");
		entity.setName("延安红色游1");
		SimpleDateFormat sdf=
		new SimpleDateFormat("yyyy-MM-dd");
		entity.setBeginDate(sdf.parse("2017-07-22"));
		entity.setEndDate(sdf.parse("2017-08-22"));
		//数值为 0
		entity.setValid(0);
		entity.setNote("延安红色游1....");
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		
		int n=dao.insertObject(entity);
		System.out.println("测试节点！！！");
		Assert.assertEquals(1, n);
		//System.out.println(dao);
	}
	
	@Test
	public void test2(){
		System.out.println(dao.findAllObject());
	}
	
	@Test
	public void test3(){
		System.out.println(dao.findObjectById(1));
		Project project=dao.findObjectById(1);
		project.setName("中国黄金游1");
		int row=dao.updateObject(project);
		System.out.println(row);
	}
	
	
	
	
	

}
