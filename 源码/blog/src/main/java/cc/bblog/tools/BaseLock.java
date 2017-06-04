package cc.bblog.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BaseLock {

	 /** 
	   * BASE解密 
	   * 
	   * @param key 
	   * @return 
	   * @throws Exception 
	   */ 
	  public static String decryptBASE(String key) throws Exception { 
	    return new String((new BASE64Decoder()).decodeBuffer(key)); 
	  } 
	  /** 
	   * BASE加密 
	   * 
	   * @param key 
	   * @return 
	   * @throws Exception 
	   */ 
	  public static String encryptBASE(byte[] key) throws Exception { 
	    return (new BASE64Encoder()).encodeBuffer(key); 
	  } 


}
