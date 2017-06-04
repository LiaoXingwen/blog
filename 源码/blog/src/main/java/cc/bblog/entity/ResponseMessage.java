package cc.bblog.entity;

import com.google.gson.Gson;

public class ResponseMessage {

	//10000 - 20000 成功的信息，
	//40000 - 50000 错误的信息，
	
	public static ResponseMessage LOGINMESSAGE = new ResponseMessage(40000, "请登陆");
	public static ResponseMessage SYSTEMERRORMESSAGE = new ResponseMessage(40001, "系统错误");
	//主要用来异步数据提交数据的的成功与否
	public static ResponseMessage SUCCESSMESSAGE = new ResponseMessage(10000, "SUCCESS");
	public static ResponseMessage FAILMESSAGE = new ResponseMessage(40004, "FAIL");
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public ResponseMessage() {
		// TODO 自动生成的构造函数存根
	}
	
	int state ; 
	String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
public ResponseMessage(int state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
@Override
public String toString() {
	return new Gson().toJson(this);
}
}
