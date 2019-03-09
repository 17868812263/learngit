package product.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import common.dao.TestBaseDao;

public class TestProductTypeDao extends TestBaseDao{

	@Test
	public void testFindObjects(){
		ProductTypeDao typeDao=
	    (ProductTypeDao)
		ctx.getBean("productTypeDao");
		List<Map<String,Object>> list=
		typeDao.findObjects();
		System.out.println(list);
		Assert.assertNotEquals(null, list);
	}
	
	@Test
	public void testInsertObject(){
		ProductTypeDao typeDao=
	    (ProductTypeDao)
	    ctx.getBean("productTypeDao");
		ProductType t1=new ProductType();
		t1.setName("美国游");
		t1.setSort(2);
		t1.setParentId(141);
		t1.setNote("美国....");
		t1.setCreatedUser("admin");
		t1.setModifiedUser("admin");
		int rows=typeDao.insertObject(t1);
		Assert.assertEquals(1, rows);
	}
	
}
