package cc.bblog.tools;
public class TranCharset {   
  
  
    public TranCharset() {   
     }   
  
    /**
      * 将字符串编码格式转成GB2312
      *
      * @param str
      * @return
      */  
    public static String TranEncodeTOGB(String str) { 
    	
    	if (str==null) {
			return null ; 
		}
        try {   
             String strEncode = TranCharset.getEncoding(str);   
             String temp = new String(str.getBytes(strEncode), "GB2312");   
            return temp;   
         } catch (java.io.IOException ex) {   
  
            return null;   
         }   
     }   
    
    /**
     * 将字符串编码格式转成GBK
     *
     * @param str
     * @return
     */  
   public static String TranEncodeTOGBK(String str) {   
	   if (str==null) {
			return null ; 
		}
       try {   
            String strEncode = TranCharset.getEncoding(str);   
            String temp = new String(str.getBytes(strEncode), "GBK");   
           return temp;   
        } catch (java.io.IOException ex) {   
 
           return null;   
        }   
    }   
   /**
    * 将字符串编码格式转成UTF-8
    *
    * @param str
    * @return
    */  
  public static String TranEncodeTOUTF(String str) { 
	  if (str==null) {
			return null ; 
		}
      try {   
           String strEncode = TranCharset.getEncoding(str);  
           String temp = new String(str.getBytes(strEncode), "UTF-8");   
          return temp;   
       } catch (java.io.IOException ex) {   

          return null;   
       }   
   }   
    /**
      * 判断输入字符是否为gb2312的编码格式
      *
      * @param c 输入字符
      * @return 如果是gb2312返回真，否则返回假
      */  
    public static boolean isGB2312(char c) { 
         Character ch = new Character(c);   
         String sCh = ch.toString();   
        try {   
            byte[]    bb = sCh.getBytes("gb2312");   
            if (bb.length > 1) {   
                return true;   
             }   
         } catch (java.io.UnsupportedEncodingException ex) {   
            return false;   
         }   
        return false;   
     }   
  
    /**
      * 判断字符串的编码
      *
      * @param str
      * @return
      */  
    public static String getEncoding(String str) {  
    	 if (str==null) {
 			return null ; 
 		}
         String encode = "GB2312";   
        try {   
            if (str.equals(new String(str.getBytes(encode), encode))) {   
                 String s = encode;   
                return s;   
             }   
         } catch (Exception exception) {   
         }   
         encode = "ISO-8859-1";   
        try {   
            if (str.equals(new String(str.getBytes(encode), encode))) {   
                 String s1 = encode;   
                return s1;   
             }   
         } catch (Exception exception1) {   
         }   
         encode = "UTF-8";   
        try {   
            if (str.equals(new String(str.getBytes(encode), encode))) {   
                 String s2 = encode;   
                return s2;   
             }   
         } catch (Exception exception2) {   
         }   
         encode = "GBK";   
        try {   
            if (str.equals(new String(str.getBytes(encode), encode))) {   
                 String s3 = encode;   
                return s3;   
             }   
         } catch (Exception exception3) {   
         }   
        return "";   
     }   
} 