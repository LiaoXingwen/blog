package cc.bblog.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Junction.Nature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.bblog.dao.BaseDao;
import cc.bblog.entity.Article;
import cc.bblog.entity.Comment;
import cc.bblog.entity.Follow;
import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.QueryMapping;
import cc.bblog.entity.ResponseMessage;
import cc.bblog.entity.Thumbs;
import cc.bblog.entity.User;
import cc.bblog.entity.UserAction;
import cc.bblog.entity.ViewModelByPager;
import cc.bblog.log.UserActionTool;
import cc.bblog.service.BaseSrvice;
import cc.bblog.tools.CookieTool;

@Controller
@RequestMapping(value="user/")
public class MeController {
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

	BaseDao baseDao ;
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public MeController() {
	}
	/**
	 * 
	 * 展示个人信息
	 * @param uid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{uid}")
	public String seeOther(@PathVariable("uid")Integer uid,HttpServletRequest request ,Model model) {

		LoginMessage message = CookieTool.unlockCookie(request) ; 
		User user = (User) baseSrvice.findById(new User(), uid);
		if (user==null) {
			model.addAttribute("message", "没有该用户");
			return "/error/fail";
		}
		model.addAttribute("user",user);
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("uid", uid);
		long activecount = baseSrvice.count(new Comment(), mapping.getEqJunction(Nature.AND))+
				baseSrvice.count(new Thumbs(), mapping.getEqJunction(Nature.AND));
		long articlecount = baseSrvice.count(new Article(), mapping.getEqJunction(Nature.AND));
		model.addAttribute("activecount", activecount);
		model.addAttribute("articlecount", articlecount);
		
		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> list = baseDao.executeSQL("select sum(t.thumbsup) as up , sum(t.thumbsdown) as down ,sum(t.viewCount)as viewCount  from t_article t where uid="+uid);
		Map<Object, Object> map = list.get(0);
		model.addAttribute("thumbsup", map.get("up")==null?0: map.get("up"));
		model.addAttribute("thumbsdown", map.get("down")==null?0: map.get("down"));
		model.addAttribute("viewCount", map.get("viewCount")==null?0: map.get("viewCount"));
		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> followers = baseDao.executeSQL("select count(1) as followers  from t_follow where state=1 and toid="+uid);
		Map<Object, Object> follower = followers.get(0);
		model.addAttribute("follower", follower.get("followers")==null?0: follower.get("followers"));
		if (message!=null) {
			model.addAttribute("pathid", message.getId());
			mapping.clearEq();
			mapping.addEq("fromid",  message.getId());
			mapping.addEq("toid", uid);
			mapping.addEq("state", 1);
			if (baseDao.count(new Follow(), mapping.getEqJunction(Nature.AND))>0) {
				model.addAttribute("isfollow", true);
			}else {
				model.addAttribute("isfollow", false);
			}
			actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.VIEWUSERINFO, uid, null));
		}else {
			model.addAttribute("pathid", -1);//为登陆的状态
		}
		//展示该用户的贴
		QueryMapping arts = new QueryMapping(Nature.AND);
		arts.addEq("state", 1);
		arts.addEq("uid", uid);
		if (articlecount>0) {
			ViewModelByPager mv =  baseSrvice.findByQueryMapping(new Article(), arts, 15, 1, Order.desc("viewCount"));
			model.addAttribute("articles", mv);
		}
		return "/user/me" ; 
	}

	/**
	 * 更改头像
	 * @param request
	 * @param model
	 * @param headurl
	 * @return
	 */
	@RequestMapping("/show/change/head")
	@ResponseBody
	public String changeHead(HttpServletRequest request ,Model model,@RequestParam("headurl")String headurl) {
		LoginMessage message = CookieTool.unlockCookie(request) ; 
		if (message!=null) {
			User user = (User) baseSrvice.findById(new User(), message.getId());
			if (user==null||!user.getCode().equals(message.getCode())) {
				return ResponseMessage.LOGINMESSAGE.toString();
			}else {
				user.setHeadURl(headurl);
				baseSrvice.saveOrUpdate(user);
				return ResponseMessage.SUCCESSMESSAGE.toString();
			}
		}
		return ResponseMessage.LOGINMESSAGE.toString();
	}
}
