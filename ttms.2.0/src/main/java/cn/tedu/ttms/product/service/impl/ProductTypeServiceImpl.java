package cn.tedu.ttms.product.service.impl;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.saveRunTimeException;
import cn.tedu.ttms.common.exception.updateException;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;
@Service
public class ProductTypeServiceImpl implements ProductTypeService{
    @Resource
	private ProductTypeDao productTypeDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		return productTypeDao.findObjects();
	}
	@Override
	public List<Map<String, Object>> findTreeNodes() {
		List<Map<String,Object>> map=
				productTypeDao.findTreeNodes();
	    System.out.println("map="+map);
	    return map;
	}
	@Override
	public void saveObject(ProductType type) {
		int rows=productTypeDao.insertObject(type);
		if(rows==-1)
		throw new saveRunTimeException("save error");
		
	}
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		if(id==null)
		    throw new RuntimeException("id can not be null");
			Map<String,Object> map=
			productTypeDao.findObjectById(id);
			return map;
	}
	@Override
	public void updateObject(ProductType type) {
		int rows=productTypeDao.updateObject(type);
		if(rows==-1)
		throw new updateException("更新失败");
	}
}
