package cc.bblog.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.bblog.emailTools.SendEmail;
import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.User;
import cc.bblog.service.UserService;
import cc.bblog.tools.CookieTool;
import cc.bblog.tools.MD5Lock;
import cc.bblog.tools.StringTools;

import com.google.gson.Gson;

@Controller
@RequestMapping(value="user/")
public class UserController {

	Gson json = new Gson();

	@Resource(name="userService")
	UserService userService ; 

	@Resource(name="sendEmail")
	SendEmail sendEmail ;

	@RequestMapping(value="/logincheck")
	public String logincheck(User user, HttpSession httpSession,HttpServletResponse response,HttpServletRequest request,Model model) throws Exception{
		User u = null;
		if (user.getEmail()==null) {
			//先检测cookie
			LoginMessage message = CookieTool.unlockCookie(request);
			if (message!=null) {
				u = userService.findByID(message.getId());
				if (u!=null) {
					if (!(u.getPassword().equals(message.getPassword())&&u.getCode().equals(message.getCode()))) {
						model.addAttribute("message", "用户需要重新登陆");
						return "/user/login";
					}
				}else {
					return "/user/login";
				}
			}
		}else{
			u = userService.findByEmail(user.getEmail());
			if (u==null) {
				model.addAttribute("message", "用户没有找到");
				return "/user/login";
			}else {
				if (!u.getPassword().equals(MD5Lock.EncoderByMd5(user.getPassword()))) {
					model.addAttribute("message"," 密码错误");
					return "/user/login";
				}
			}
		}
		
		
		user.setCode(StringTools.getRandomString(20));

		//能运行到这里，证明用户已经登陆成功
		httpSession.setMaxInactiveInterval(12*60*60*60*1000);

		CookieTool.addPass(u, httpSession, response,request);

		switch (u.getState()) {
		case -1:
			model.addAttribute("message", user.getEmail()+" 用户锁定状态，不可用");
			return "/user/login";
		case 0:
			return "redirect:/user/validateEmail?email="+user.getEmail();
		case 1 :
			if (u.getUsername()==null||u.getUsername().length()<5) {
				return "redirect:/user/completeData";
			}

		default:
			return "redirect:/index";
		}
	}

	/**
	 * 检测email是否被注册
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/checkEmail")
	@ResponseBody 
	public String checkEmail(String email){
		try {
			if (userService.findByEmail(email)==null) {
				return "notExist";
			}else {
				return "exist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "exist";
		}
	}

	/**
	 * 检测email是否被注册
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/checkUsername")
	@ResponseBody 
	public String checkUsername(String username){
		try {
			if (userService.findByUsername(username)==null) {
				return "notExist";
			}else {
				return "exist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "exist";
		}
	}

	/**
	 * 重新发送邮件
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/sendRegisterEmail")
	public ModelAndView sendRegisterEmail(String email , Model model){
		model.addAttribute("email", email);
		User user = userService.findByEmail(email) ; 
		if (user!=null) {
			if (user.getState()==0) {
				sendEmail.asynchronousSenRegisterEmail(user);
			}
		}
		return validateEmail(email);
	}

	@RequestMapping(value="/addUser", method=POST)
	public String addUser(User user,Model mv){
		user.setId(0);
		user.setState(0);
		user.setRegisterTime(new Date(System.currentTimeMillis()));
		mv.addAttribute("email", user.getEmail());
		try {
			String code = StringTools.getRandomString(20);
			user.setCode(code);
			user.setPassword(MD5Lock.EncoderByMd5(user.getPassword()));
			String result = userService.saveUser(user);
			if (result.equals("success")) {
				sendEmail.asynchronousSenRegisterEmail(user);
				return  "redirect:/user/validateEmail";

			}else {
				mv.addAttribute("message", user.getEmail()+"  注册异常，请重新再试");
				return "redirect:../register";
			}
		} catch (Exception e) 
		{
			mv.addAttribute("message", user.getEmail()+"  注册异常，请重新再试");
			return "redirect:../register";
		}
	}
	//todo  http://localhost/blog/user/validateEmail?email=s185252@qq.com
	@RequestMapping(value="/validateEmail")
	public ModelAndView validateEmail(@RequestParam(value="email",required=true)String email){
		System.out.println(email);
		ModelAndView mv = new ModelAndView();
		User user = userService.findByEmail(email);
		//没有找到用户
		if (user==null) {
			mv.addObject("disabled", "disabled");
			mv.addObject("message", email+"  用户没有找到，请重新注册");
			mv.setViewName("/user/validateEmail");
		}else {
			//认证通过了，登陆
			if(user.getState()==1){
				mv.addObject("tip", email+"  认证通过了，点击下一步进行操作");
				mv.setViewName("/user/validateEmail");
			}else{
				//等待认证
				mv.addObject("email",email);
				mv.addObject("tip", "请到 "+email+" 邮箱验证了你的账号再来刷新此页面,验证即可以进行下一步");
				mv.addObject("disabled", "disabled");
				mv.setViewName("/user/validateEmail");
			}
		}
		return mv;
	}
	//todo http://localhost/blog/user/validateCode?email=s180522@qq.com&code=xqme1us2WURoEZUA2dSv
	@RequestMapping(value="/validateCode")
	public String validateCode(@RequestParam(value="email",required=true)String email,
			@RequestParam(value="code",required=true)String code ,
			Model mv){
		mv.addAttribute("email", email);
		User user = userService.findByEmail(email);
		if (user!=null) {
			if (user.getCode().equals(code)) 
			{
				user.setState(1);
				userService.updateUser(user);
				return "/user/login" ; 
			}
		}else {
			mv.addAttribute("message",email+" 验证失败，请重新注册");
			userService.deleteUserByEmail(email);
		}
		return "./register";
	}

	@RequestMapping(value="/completeData")
	public String completeData(HttpServletRequest request,
			Model model) throws UnsupportedEncodingException{
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			User user = userService.findByID(message.getId());
			if (user==null) {
				return "redirect:/user/login";
			}else {
				if (user.getUsername()==null||user.getUsername().trim().length()<50) {
					return "/user/completeData";
				}else {
					return "redirect:/index";
				}
			}
		}else {
			return "redirect:/user/login";
		}
	}


	@RequestMapping("/dataCheck")
	public String dataCheck(@RequestParam(required=true)String username,@RequestParam(defaultValue="/file/image/blog/system/logo.png")String headurl,@RequestParam(required=true)String sex,@RequestParam(defaultValue="空空如我")String description,@RequestParam(required=true)String brithDay, HttpServletRequest request,Model model,HttpSession session) throws Exception{
		
		if (username.trim().length()<5||sex.trim().length()==0||brithDay.trim().length()<5) {
			model.addAttribute("message", "数据有误,请重新再试");
			return "/user/completeData";
		}
		
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null) {
			return "redirect:/user/login";
		}else {
			try {
				userService.excHql("update User  set username='"+username+
						"',headURl='"+headurl+
						"',sex='"+sex+
						"',description='"+description+
						"',brithDay='"+brithDay+"' "+
						" where id="+message.getId()+"");
			} catch (Exception e) {
				model.addAttribute("message", "数据保存出错，请重新再试");
				return "/user/completeData";
			}
			
			return "redirect:/index";
		}


	}



}
