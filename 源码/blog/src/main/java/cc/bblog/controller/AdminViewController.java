package cc.bblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Junction.Nature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cc.bblog.entity.Article;
import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.QueryMapping;
import cc.bblog.entity.User;
import cc.bblog.entity.UserInfo;
import cc.bblog.entity.ViewModelByPager;
import cc.bblog.service.BaseSrvice;
import cc.bblog.tools.CookieTool;
import cc.bblog.tools.UserStyleTool;

/**   
 *    展示管理页面
 * 项目名称：blog   
 * 类名称：AdminViewController   
 * 类描述：   
 * 创建人：廖兴文
 * 创建时间：2017年4月24日 上午11:43:04   
 * 修改人：lxw   
 * 修改时间：2017年4月24日 上午11:43:04   
 * 修改备注：   
 * @version 1.0.0   
 *    
 */
@Controller()
@RequestMapping("admin")
public class AdminViewController {

	BaseSrvice adminSrvice ; 
	@Autowired
	public void setAdminSrvice(BaseSrvice adminSrvice) {
		this.adminSrvice = adminSrvice;
	}


	UserStyleTool userStyleTool ; 
	@Autowired
	public void setUserStyleTool(UserStyleTool userStyleTool) {
		this.userStyleTool = userStyleTool;
	}

	public AdminViewController() {
	}
	//管理页面
	@RequestMapping("/{url}")
	public String other(@PathVariable("url")String url,HttpServletRequest request){
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null||!((User)adminSrvice.findById(new User(), message.getId())).getCode().equals(message.getCode())) {
			return "redirect:../login";
		}
		if (url.trim().equals("")) {
			return "admin/index";
		}
		return "admin/"+url;
	}




	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model,HttpServletResponse response){
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null||!((User)adminSrvice.findById(new User(), message.getId())).getCode().equals(message.getCode())) {
			return "redirect:../login";
		}
		UserInfo info = (UserInfo) adminSrvice.findById(new UserInfo(), message.getId());
		if (info==null) {
			info = new UserInfo();
			info.setUid(message.getId());
			adminSrvice.save(info);
		}
		
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("uid", message.getId());
		mapping.addEq("state", 1);
		model.addAttribute("pushcount", adminSrvice.count(new Article(), mapping.getRestrictions()));
		mapping.addEq("state", 0);
		model.addAttribute("draftcount", adminSrvice.count(new Article(), mapping.getRestrictions()));
		return "admin/index";
	}



	int strip = 6 ; 
	//管理页面
	@RequestMapping("/draft")
	public String draft(@RequestParam(defaultValue="1")Integer pager , HttpServletRequest request,Model model,HttpServletResponse response){
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null||!((User)adminSrvice.findById(new User(), message.getId())).getCode().equals(message.getCode())) {
			return "redirect:../login";
		}
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("uid", message.getId());
		mapping.addEq("state", 0);
		try {
			ViewModelByPager mv =  adminSrvice.findByQueryMapping(new Article(), mapping, strip, pager, Order.desc("updateTime"));
			model.addAttribute("mv", mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/draft";
	}




	@RequestMapping("/editor")
	public String editor(Model model,Integer aid,HttpServletRequest request,HttpServletResponse response){
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null||!((User)adminSrvice.findById(new User(), message.getId())).getCode().equals(message.getCode())) {
			return "redirect:/login";
		}
		if (aid!=null) {
			Article article = (Article) adminSrvice.findById(new Article(), aid);
			if (article!=null&&article.getUid()==message.getId()) {
				model.addAttribute(article);
				model.addAttribute("url", "./update/"+aid);
				return "admin/editor";
			}else {
				model.addAttribute("message", "你没有权限操作这篇文章");
				return "/error/fail";
			}
		}else {
			model.addAttribute("url", "./addArticle");
			return "admin/editor";
		}
	}


	//管理页面
	@RequestMapping("/released")
	public String released(@RequestParam(defaultValue="1")Integer pager , HttpServletRequest request,Model model,HttpServletResponse response){
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null||!((User)adminSrvice.findById(new User(), message.getId())).getCode().equals(message.getCode())) {
			return "redirect:../login";
		}
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("uid", message.getId());
		mapping.addEq("state", 1);
		try {
			ViewModelByPager mv =  adminSrvice.findByQueryMapping(new Article(), mapping, strip, pager, Order.desc("updateTime"));
			model.addAttribute("mv", mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/released";
	}


	//设置页面
	@RequestMapping("/setter")
	public String setter(HttpServletRequest request,Model model,HttpServletResponse response){
		userStyleTool.setMyStyle(model, request);
		return "admin/setter";
	}

}
