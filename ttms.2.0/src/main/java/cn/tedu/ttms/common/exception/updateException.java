package cn.tedu.ttms.common.exception;
/*
 * 自定义异常类
*/
public class updateException extends RuntimeException {

	public updateException() {
		super();
		
	}

	public updateException(String message) {
		super(message);
		
	
	}

	public updateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public updateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public updateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
