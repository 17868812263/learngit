package project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.project.entity.Project;

public class pageTest {
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
	
	//测试查询总记录数
	@Test
	public void test1(){
		Project project=new Project();
		System.out.println(dao.getRowCount(project));
	}
	
	//测试查询记录（根据起始记录和查询数目）
	@Test
	public void test2(){
		Project project=new Project();
		PageObject page= new PageObject();
		//默认startIndex是0
		System.out.println(dao.findPageObjects(project,
				page));
		//求得总页数
		System.out.println("总页数："+(dao.getRowCount(project)/page.getPageSize()));
		
	}
	
	
}








