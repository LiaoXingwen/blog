package cc.bblog.tools;

import java.util.Random;

import com.google.gson.Gson;

public class StringTools {
	public static Gson gson = new Gson();
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWSYZ";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	public static String parseJson(Object object) {
		return gson.toJson(object);
	}
	
}
