package cc.bblog.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.User;
import cc.bblog.service.UserService;
import cc.bblog.tools.CookieTool;
import cc.bblog.tools.TranCharset;

/**   
 *   基本控制器，控制基本页面 
 * 项目名称：blog   
 * 类名称：BaseController   
 * 类描述：   
 * 创建人：廖兴文
 * 创建时间：2017年4月7日 上午9:55:14   
 * 修改人：lxw   
 * 修改时间：2017年4月7日 上午9:55:14   
 * 修改备注：   
 * @version 1.0.0   
 *    
 */
@Controller()
@ImportResource("classpath:applicationContext.xml")
public class BaseController {

	@Resource(name="userService")
	UserService userService ; 
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//登陆页面
	@RequestMapping("/login")
	public String login(String email,Model model,HttpServletRequest request){
		//先检测cookie
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			//半天不要重复登陆验证
			if (System.currentTimeMillis()-message.getLogintime()<12*60*60*1000) {
				return "redirect:/user/logincheck";
			}
			User user = userService.findByID(message.getId());
			if (user!=null) {
				if (user.getPassword().equals(message.getPassword())&&user.getCode().equals(message.getCode())) {
					return "redirect:/user/logincheck";
				}
			}
		}
		model.addAttribute("email", email);
		return "/user/login";
	}

	//注册页面
	@RequestMapping("/register")
	public String register(String message,String email,Model model) throws UnsupportedEncodingException{
		model.addAttribute("message",TranCharset.TranEncodeTOUTF(message));
		model.addAttribute("email", email);
		return "/user/register";
	}
}
