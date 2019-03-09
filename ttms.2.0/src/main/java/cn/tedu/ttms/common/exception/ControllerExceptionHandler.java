package cn.tedu.ttms.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;

/**此注解用于标识此类为全局的异常处理类*/
@ControllerAdvice
public class ControllerExceptionHandler {
	//自定义异常，参数e为异常类型
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult handleException(Exception e){
		System.out.println("测试异常类型为：exception");
		return new JsonResult(e);
	}	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult handleException(RuntimeException e){
		System.out.println("测试异常类型为：runtime exception");
		return new JsonResult(e);
	}
}







