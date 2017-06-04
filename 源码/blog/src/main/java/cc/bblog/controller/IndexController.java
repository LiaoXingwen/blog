package cc.bblog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Junction.Nature;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cc.bblog.entity.Article;
import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.QueryMapping;
import cc.bblog.entity.UserAction;
import cc.bblog.entity.ViewModelByPager;
import cc.bblog.log.UserActionTool;
import cc.bblog.service.BaseSrvice;
import cc.bblog.tools.CookieTool;
import cc.bblog.tools.UserStyleTool;
@Controller()
public class IndexController {

	enum Sort{
		viewCount,
		updateTime,
		thumbsUp,
		random
	}
	
	UserActionTool actionTool ;
	@Resource(name="userActionTool")
	public void setActionTool(UserActionTool actionTool) {
		this.actionTool = actionTool;
	}
	
	BaseSrvice baseSrvice ; 
	@Autowired
	public void setBaseSrvice(BaseSrvice baseSrvice) {
		this.baseSrvice = baseSrvice;
	}
	UserStyleTool userStyleTool ; 
	@Autowired
	public void setUserStyleTool(UserStyleTool userStyleTool) {
		this.userStyleTool = userStyleTool;
	}
	
	
	public IndexController() {
	}
	int strip = 12;
	//首页
		@RequestMapping({"/","/index","/home"})
		public String index(HttpServletRequest request ,Model model,@RequestParam(defaultValue="viewCount")String type,@RequestParam(defaultValue="0")Integer pager,@RequestParam(defaultValue="")String search){
			model.addAttribute("type", type);
			LoginMessage message =CookieTool.unlockCookie(request);
			if (message!=null) {
				userStyleTool.setMyStyle(model, message.getId());
				model.addAttribute("uid", message.getId());
				actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.SEARCH, null, search));
			}else {
				userStyleTool.setMyStyle(model, -1);
			}
			
			QueryMapping mapping = new QueryMapping(Nature.AND);
			mapping.addEq("state", 1);
			model.addAttribute("count", baseSrvice.count(new Article(), mapping.getRestrictions()));
			if (!search.trim().equals("")) {
				mapping.addLike("title", "%"+search+"%");
				mapping.addLike("label", "%"+search+"%");
				model.addAttribute("search", search);
				//记录用户动作
			
			}
				if (type.equals("random")) {
					ViewModelByPager mv =  baseSrvice.findByQueryMapping(new Article(), mapping, strip, pager, Order.desc(randomSortWay()));
					model.addAttribute("mv", mv);
				}else {
					ViewModelByPager mv =  baseSrvice.findByQueryMapping(new Article(), mapping, strip, pager, Order.desc(type));
					model.addAttribute("mv", mv);
				}
				
				
			return "index";
		}



	String[] sorts = new String[]{
			"aid","title","md","uid","saveTime",
			"html","updateTime","viewCount",
			"thumbsUp","thumbsDown","label"
	};

	public String randomSortType() {
		return sorts[(int) (sorts.length*Math.random())];
	}
	String[] sortway = new String[]{
			"desc","asc"
	};
	public String randomSortWay() {
		return sorts[(int) (sortway.length*Math.random())];
	}
}
