package cc.bblog.emailTools;

import cc.bblog.entity.User;


public abstract class SendEmailMethod{
	
		/**
		 * 发送邮件
		 * @return
		 */
		abstract boolean sendEmail();
		/**
		 * 处理异常
		 */
		abstract void handleError();
		
		/**获取用户对象
		 * @return
		 */
		abstract User getUser();
	}