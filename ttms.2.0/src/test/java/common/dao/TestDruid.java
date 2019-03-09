package common.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;


public class TestDruid {
	@Test
	public void test1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-pool.xml");
		//获得数据源对象
		//配置文件中     class="com.alibaba.druid.pool.DruidDataSource"，创建时 类DruidDataSource
		DruidDataSource dataSource = (DruidDataSource) ctx.getBean("dataSource");
		//测试数据源是否为空 方式一：
		System.out.println(dataSource);
		//测试数据源是否为空 测试方法二：
		Assert.assertNotEquals(dataSource, null);
				

	}
	
	
	
	@Test
	public void testSessionFactory() {
			ApplicationContext ctx=
			new ClassPathXmlApplicationContext("spring-mvc.xml",
					"spring-pool.xml",
					"spring-mybatis.xml");    
			Object sessionFactory=ctx.getBean("sqlSessionFactory");
			System.out.println(sessionFactory);
			Assert.assertNotEquals(sessionFactory, null);
	}

	

}









