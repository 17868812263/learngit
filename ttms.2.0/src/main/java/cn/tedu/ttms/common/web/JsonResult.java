package cn.tedu.ttms.common.web;

/*
 * 通过此对象封装控制层返回的JSON结果
 * 便于对控制层返回的数据统一格式化
 * 友好性管理
*/
public class JsonResult {
	public static final int SUCCESS=1;
	public static final int ERROR=0;
	
	/**
	 * 信息给用户的提示
	 */
	private String message;
	/**
	 * 服务端的响应状态
	 */
	private int state;
	/**
	 * 具体的业务数据
	 */
	private Object data;
	
	public JsonResult(){
		this.state=SUCCESS;
		this.message="ok";
	}
	
	public JsonResult(Object data){
		this();
		this.data=data;
	}
	
	public JsonResult(Throwable e){
		this.state=ERROR;
		this.message=e.getMessage();
		System.out.println("测试JsonResult:");
		System.out.println(message);
	}
	public String getMessage(){
		return message;
	}
	public Object getData() {
		return data;
	}
	public int getState() {
		return state;
	}

}
