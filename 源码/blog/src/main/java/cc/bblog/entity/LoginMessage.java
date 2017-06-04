package cc.bblog.entity;

import java.net.URLDecoder;
import java.net.URLEncoder;

import cc.bblog.tools.BaseLock;
import cc.bblog.tools.StringTools;

import com.google.gson.JsonSyntaxException;


public class LoginMessage {
	
	@Override
	public String toString() {
		return "LogionMessage [password=" + password + ", code=" + code
				+ ", logintime=" + logintime + ", id=" + id + "]";
	}
	public LoginMessage() {
	}
	public LoginMessage(String password, String code, long logintime, int id) {
		super();
		this.password = password;
		this.code = code;
		this.logintime = logintime;
		this.id = id;
	}
	public static String getLockMsg(LoginMessage logionMessage) {
		try {
			return URLEncoder.encode(BaseLock.encryptBASE(StringTools.gson.toJson(logionMessage).getBytes()),"UTF-8");
		} catch (Exception e) {
			return StringTools.gson.toJson(logionMessage);
		}
	}
	
	public static LoginMessage getUnlockMsg(String string) {
		try {
		return	StringTools.gson.fromJson(BaseLock.decryptBASE(URLDecoder.decode(string,"UTF-8")), LoginMessage.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getLogintime() {
		return logintime;
	}
	public void setLogintime(long logintime) {
		this.logintime = logintime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	String password ; 
	String code ; 
	long logintime ; 
	int id ; 
}
