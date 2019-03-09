package project.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.project.service.ProjectService;


public class TestProjectService {
	
private ProjectService service;
	
	@Before
	public void init(){
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext(
						"spring-mvc.xml",
						"spring-pool.xml",
						"spring-mybatis.xml");
		service=(ProjectService)ctx.getBean("ProjectService");
	}
	
	@Test
	public void test1(){
		List<Project> project=service.findALL();
		System.out.println(project);
	}

}




