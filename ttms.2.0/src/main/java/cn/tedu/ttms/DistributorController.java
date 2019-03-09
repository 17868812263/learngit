package cn.tedu.ttms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 产品项目管理控制器
*/

@Controller
@RequestMapping("/distributor")
public class DistributorController {
	
	//写上面相当于下面直接写/distributor/listUI
	@RequestMapping("/listUI")
	public String listUI(){
		System.out.println("listUI()");
		//根据配置value="/WEB-INF/pages/"，jsp文件位置，由于存在子目录distributor
		//返回时加上目录
		return "distributor/distributor_list";
	}
	
	@RequestMapping("/findObjects")
	@ResponseBody//作用是将java对象转化为一个json字符串然后返回,页面上回将这个json字符串转换为json对象
	public Map<String,Object> findObjects(){
		Map<String,Object> map=new HashMap();
		map.put("id", 1);
		map.put("code", "IH-DAD-20180809");
		map.put("name", "啦啦啦");
		return map;
	}
	


	
	
}





















