package cc.bblog.tools;

import org.apache.log4j.Logger;

public class LoggerHelper {

	public LoggerHelper() {
	}
	static Logger logger = Logger.getLogger(LoggerHelper.class);
	public static void debug(Object object,String message) {
		logger.debug(object.getClass().getSimpleName()+" : "+message);
	}
	
	public static void warn(Object object,String message) {
		logger.warn(object.getClass().getSimpleName()+" : "+message);
	}
	
	public static void error(Object object,String message) {
		logger.error(object.getClass().getSimpleName()+" : "+message);
	}
	public static void info(Object object,String message) {
		logger.info(object.getClass().getSimpleName()+" : "+message);
	}
}
