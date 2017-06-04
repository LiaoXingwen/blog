package cc.bblog.emailTools;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import cc.bblog.dao.FailEmailDao;
import cc.bblog.entity.FailSendEmail;
import cc.bblog.entity.User;
import cc.bblog.tools.LoggerHelper;
import cc.bblog.tools.PublicRes;

@ComponentScan
@Component("sendEmail")
public class SendEmail {
	@Value("${email.username}")
	private  String userName;
	JavaMailSender mailSender;
	private VelocityEngine velocityEngine;

	@Resource(name="mailSender")
	public  void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	FailEmailDao failEmailDao ; 
	@Resource(name="failEmailDao")
	public void setFailEmailDao(FailEmailDao failEmailDao) {
		this.failEmailDao = failEmailDao;
	}

	private SendEmail(){}

	//发送email
	public  void sendEmail(final Map<String,Object> model,final String subject,final String vmfile,final String[] mailTo,final String [] files) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			//注意MimeMessagePreparator接口只有这一个回调函数
			public void prepare(MimeMessage mimeMessage) throws Exception
			{
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"GBK");
				//这是一个生成Mime邮件简单工具，如果不使用GBK这个，中文会出现乱码
				//如果您使用的都是英文，那么可以使用MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(mailTo);//设置接收方的email地址
				message.setSubject( subject);//设置邮件主题B为base64方式

				message.setFrom(userName);//设置发送方地址
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, vmfile,"GBK", model);
				//从模板中加载要发送的内容，vmfile就是模板文件的名字
				//注意模板中有中文要加GBK，model中存放的是要替换模板中字段的值
				message.setText(text, true);
				//将发送的内容赋值给MimeMessageHelper,后面的true表示内容解析成html
				//如果您不想解析文本内容，可以使用false或者不添加这项
				FileSystemResource file;
				if(files!=null)
					for(String s:files)//添加附件
					{
						file = new FileSystemResource(new File(s));//读取附件
						message.addAttachment(s, file);//向email中添加附件
					}
			}
		};
		mailSender.send(preparator);//发送邮件
	}

	/**
	 * 异步发送信息,失败可尝试5次
	 */
	public void asynchronousSendEmail(SendEmailMethod method) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int error  = 0 ;
				while (!method.sendEmail()) {
					LoggerHelper.warn(SendEmail.this, method.getUser().getEmail()+"邮件发送失败，第"+error+"次重试");
					if (error>5) {
						LoggerHelper.error(SendEmail.this, method.getUser().getEmail()+"邮件发送失败,");
						method.handleError();
						break ; }
					else {
						method.sendEmail();
						error++;
					}
				}
			}
		}).start();
	}


	/**
	 * 异步注册信息发送信息,
	 */
	public void asynchronousSenRegisterEmail(User user) {
		SendEmailMethod method = new SendEmailMethod() {
			@Override
			public boolean sendEmail() {
				try {
					Map<String,Object> model = new HashMap<String,Object>();
					model.put("email",user.getEmail());
					model.put("url", PublicRes.BASEURL+"user/validateCode?"+ 
							"email="+user.getEmail()+	
							"&code="+ user.getCode());
					SendEmail.this.sendEmail(model,
							"注册验证",
							"vm/emailCheck.vm",
							new String[]{user.getEmail()},
							null);
				} catch (Exception e) {
					return false ;
				}
				return true;
			}

			@Override
			public void handleError() {
				LoggerHelper.info(SendEmail.this, getUser().getEmail()+"邮箱注册发送邮件失败，正在保存失败信息");
				failEmailDao.save(new FailSendEmail(getUser().getEmail(),EmailType.register.toString(),new Date(System.currentTimeMillis()),0,0));
			}

			@Override
			public User getUser() {
				return user;
			}
		};
		asynchronousSendEmail(method);
	}





}
