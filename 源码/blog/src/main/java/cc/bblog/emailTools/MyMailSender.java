package cc.bblog.emailTools;

import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MyMailSender {

	public MyMailSender(){}
	//获取参数
	@Value("${email.host}")
	private  String emailHost;
	@Value("${email.username}")
	private  String userName;
	@Value("${email.password}")
	private  String password;
	private boolean mailAuth=true;

	@Bean(name="mailSender")
	private JavaMailSender mailSender() {
		//从配置文件中读取相应的邮件配置属性
		JavaMailSenderImpl  impl = new JavaMailSenderImpl();
		impl.setHost(emailHost);
		impl.setUsername( userName ) ;  //  根据自己的情况,设置username 
		impl.setPassword( password ) ;  //  根据自己的情况, 设置password 
		Properties prop  =   new  Properties() ;
		prop.put( "mail.smtp.auth" ,  mailAuth ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
		prop.put( "mail.smtp.timeout" ,  "5000" ) ;
		prop.setProperty(Velocity.ENCODING_DEFAULT, "GBK");
		prop.setProperty(Velocity.INPUT_ENCODING, "GBK");
		prop.setProperty(Velocity.OUTPUT_ENCODING, "GBK");    
		prop.put("mail.smtp.ssl.enable", "true"); 
		prop.put("mail.smtp.socketFactory.class", "javax.NET.ssl.SSLSocketFactory"); 
		impl.setJavaMailProperties(prop); 
		impl.setDefaultEncoding("GBK");
		return impl ;
	}

}
