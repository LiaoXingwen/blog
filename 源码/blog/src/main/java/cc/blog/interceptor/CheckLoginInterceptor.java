package cc.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**   
 *    
 *    过滤需要登陆的页面
 * 项目名称：blog   
 * 类名称：AllInterceptor   
 * 类描述：   
 * 创建人：廖兴文
 * 创建时间：2017年4月11日 下午5:58:11   
 * 修改人：lxw   
 * 修改时间：2017年4月11日 下午5:58:11   
 * 修改备注：   
 * @version 1.0.0   
 *    
 */
public class CheckLoginInterceptor implements HandlerInterceptor {  


	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {



	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
					throws Exception {


	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
				return true;
//		if(UserLoginMessage.checkUserPower(request.getCookies())){
//			return true  ; 
//		}else {
//			request.getRequestDispatcher("/login").forward(request, response);
//			return false;
//		}
	}  

}  