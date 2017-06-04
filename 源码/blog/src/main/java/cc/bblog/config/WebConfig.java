package cc.bblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc   	//启用springmvc
@ComponentScan("cc")	//启用组件扫描
public class WebConfig extends WebMvcConfigurerAdapter{

	//配置静态资源处理
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * 添加物理视图jsp的解析器
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");//设置前缀	
		viewResolver.setSuffix(".jsp");//设置后缀
		viewResolver.setExposeContextBeansAsAttributes(true);//将上下文bean暴露为属性
		viewResolver.setContentType("text/html;charset=UTF-8");
		return viewResolver;
		
		
		
	}

}
