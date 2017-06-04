package cc.bblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**   
*   借助servlet3的规范和Spring3.1功能增强，可以利用扩展 AbstractAnnotationConfigDispatcherServletInitializer 的方法来代替 DispatcherServlet 在web.xml的配置
*    在servlet3容器中：
*    实现 AbstractAnnotationConfigDispatcherServletInitializer了所有类都会自动配置Dispatcher—Servlet 和spring 应用上下文
*    
*    
* 项目名称：blog   
* 类名称：WebIinitializer   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年4月7日 上午9:16:16   
* 修改人：lxw   
* 修改时间：2017年4月7日 上午9:16:16   
* 修改备注：   
* @version 1.0.0   
*    
*/
public class WebIinitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// 拦截所有，至于静态页面，交给WebConfig配置
		return new String[]{"/"};
	}

}
