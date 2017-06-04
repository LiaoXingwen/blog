package cc.bblog.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Junction.Nature;
import org.hibernate.criterion.Order;
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
import cc.bblog.entity.CommetAndUser;
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
import cc.bblog.tools.UserStyleTool;

import com.google.gson.Gson;

@Controller
@RequestMapping("article/")
public class ArticleController {
	UserActionTool actionTool ;
	@Resource(name="userActionTool")
	public void setActionTool(UserActionTool actionTool) {
		this.actionTool = actionTool;
	}
	
	BaseSrvice baseSrvice;
	@Autowired
	public void setBaseSrvice(BaseSrvice baseSrvice) {
		this.baseSrvice = baseSrvice;
	}

	BaseDao baseDao ; 
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	UserStyleTool userStyleTool ; 
	@Autowired
	public void setUserStyleTool(UserStyleTool userStyleTool) {
		this.userStyleTool = userStyleTool;
	}

	public ArticleController() {
	}

	@RequestMapping("{aid}")
	public String  viewArticle(@PathVariable("aid") Integer aid,Model model,HttpServletRequest request) {
		Article article = (Article) baseSrvice.findById(new Article(), aid);
		if (article!=null&&article.getState()==1) {
			QueryMapping mapping = new QueryMapping(Nature.AND);
			mapping.addEq("oid", aid);
			mapping.addEq("type", 1);

			User user = (User) baseSrvice.findById(new User(), article.getUid());
			model.addAttribute("article",article);
			model.addAttribute("user", user);
			model.addAttribute("hasComment", article.isHasComment());
			model.addAttribute("viewcount",article.getViewCount()+1);
			mapping.addEq("type", 0);
			baseSrvice.execute("update Article set viewCount="+(article.getViewCount()+1)+" where aid="+aid);
			
			LoginMessage message = CookieTool.unlockCookie(request);
			if (message!=null) {
				model.addAttribute("fromid", message.getId());
				actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.VIEWARTICLE, aid, null));
			}else {
				model.addAttribute("fromid", -1);
			}
			
			return "/article/view";

		}
		return "/error/fail";
	}

	/**
	 * 表示赞贬的数据，根据aid和cookie来获取文章和操作对象
	 * @param aid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/{aid}/thumbs")
	@ResponseBody
	public String  thumbs(@PathVariable("aid") Integer aid,Model model,HttpServletRequest request) {
		LoginMessage message = CookieTool.unlockCookie(request);
	
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("oid", aid);
		mapping.addEq("type", 0);
		ThumbsMessage thumbsMessage = new ThumbsMessage();
		thumbsMessage.setDowncount(baseSrvice.count(new Thumbs(), mapping.getRestrictions()));
		mapping.addEq("type", 1);
		thumbsMessage.setUpcount(baseSrvice.count(new Thumbs(),  mapping.getRestrictions()));
		if (message!=null) {
			mapping.addEq("uid", message.getId());
			if (baseSrvice.count(new Thumbs(),  mapping.getRestrictions())>0) {
				thumbsMessage.setIsup(true);
			}else {
				mapping.addEq("type", 0);
				if (baseSrvice.count(new Thumbs(),  mapping.getRestrictions())>0) {
					thumbsMessage.setIsdown(true);
				}	
			}
			
		}
		
		
		return new ResponseMessage(10000,thumbsMessage.toString()).toString();
	}
	int strip = 10 ; 
	/**
	 * 获取文章的评论
	 * @param aid
	 * @param model
	 * @param pager
	 * @return
	 */
	@RequestMapping("/comment")
	@ResponseBody
	public String viewComment(@RequestParam(value="oid",required=true) Integer oid,Model model,@RequestParam(value="pager",defaultValue="1")Integer pager,@RequestParam(value="type",defaultValue="0")Integer type) {
		//初步判断是否有数据
		if (type==0) {
			Article article = (Article) baseSrvice.findById(new Article(), oid);
			if (article==null||!article.isHasComment()) {
				return new ResponseMessage(30000, "没有数据").toString();
			}
		}else {
			Comment comment = (Comment) baseSrvice.findById(new Comment(), oid);
			if (comment==null||!comment.isHasComment()) {
				return new ResponseMessage(30000, "没有数据").toString();
			}
		}
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("oid", oid);
		mapping.addEq("type", type);
		ViewModelByPager viewModelByPager =  baseSrvice.findByQueryMapping(new CommetAndUser(), mapping, strip, pager, Order.asc("date"));
		return new ResponseMessage(10000, new Gson().toJson(viewModelByPager)).toString();

	}
	/**
	 * 添加文章的评论
	 * @param aid
	 * @param model
	 * @param pager
	 * @return
	 */
	@RequestMapping("addcomment")
	@ResponseBody
	public String addComment(Comment comment,Model model,HttpServletRequest request) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null) {
			return ResponseMessage.LOGINMESSAGE.toString();
		}else {
			User user = (User) baseSrvice.findById(new User(), message.getId());
			comment.setUid(user.getId());
			if (comment.getType()==0) {
				baseDao.execute("update Article set hasComment=true where aid="+comment.getOid());
				actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.COMMENT, comment.getAid(), comment.getContent()));
			}
			Integer id  = (Integer) baseDao.save(comment);
			CommetAndUser cu = new CommetAndUser(message.getId(), user.getUsername(), user.getHeadURl(),
					id, comment.getOid(), comment.getDate(), comment.getContent(), comment.getType(), comment.isHasComment());
			comment.setCid(id);
			return new ResponseMessage(10000, new Gson().toJson(cu)).toString();
		}
	}


	/**
	 * 表示赞，根据aid和cookie来获取文章和操作对象
	 * @param aid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/{aid}/thumbsup")
	@ResponseBody
	public String  thumbsup(@PathVariable("aid") Integer aid,Model model,HttpServletRequest request) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null) {
			return ResponseMessage.LOGINMESSAGE.toString();
		}
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("type", 0);
		mapping.addEq("oid", aid);

		Thumbs thumbsUp = new Thumbs();
		thumbsUp.setOid(aid);
		thumbsUp.setType(1);
		thumbsUp.setUid(message.getId());
		baseSrvice.saveOrUpdate(thumbsUp);

		ThumbsMessage thumbsMessage = new ThumbsMessage();
		thumbsMessage.setDowncount(baseSrvice.count(new Thumbs(), mapping.getRestrictions()));
		mapping.addEq("type", 1);
		thumbsMessage.setUpcount(baseSrvice.count(new Thumbs(),  mapping.getRestrictions()));
		baseDao.execute("update from Article set thumbsUp="+thumbsMessage.getUpcount()+
				", thumbsDown="+thumbsMessage.getDowncount()+
				" where aid="+aid);
		actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.THUMBS, aid, "good"));
		thumbsMessage.setIsup(true);
		return new ResponseMessage(10000,thumbsMessage.toString()).toString();
	}

	/**
	 * 删除贬或赞
	 * @param aid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/{aid}/delthumbs")
	@ResponseBody
	public String  delthumbs(@PathVariable("aid") Integer aid,Model model,HttpServletRequest request) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null) {
			return ResponseMessage.LOGINMESSAGE.toString();
		}
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("type", 0);
		mapping.addEq("oid", aid);

		Thumbs thumbsUp = new Thumbs();
		thumbsUp.setOid(aid);
		thumbsUp.setUid(message.getId());
		baseSrvice.delete(thumbsUp);

		ThumbsMessage thumbsMessage = new ThumbsMessage();
		thumbsMessage.setDowncount(baseSrvice.count(new Thumbs(), mapping.getRestrictions()));
		mapping.addEq("type", 1);
		thumbsMessage.setUpcount(baseSrvice.count(new Thumbs(),  mapping.getRestrictions()));
		baseDao.execute("update from Article set thumbsUp="+thumbsMessage.getUpcount()+
				", thumbsDown="+thumbsMessage.getDowncount()+
				" where aid="+aid);
		actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.THUMBS, aid, "del"));
		return new ResponseMessage(10000,thumbsMessage.toString()).toString();
	}


	/**
	 * 表示贬，根据aid和cookie来获取文章和操作对象
	 * @param aid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/{aid}/thumbsdown")
	@ResponseBody
	public String  thumbsdown(@PathVariable("aid") Integer aid,Model model,HttpServletRequest request) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message==null) {
			return ResponseMessage.LOGINMESSAGE.toString();
		}
		QueryMapping mapping = new QueryMapping(Nature.AND);
		mapping.addEq("type", 0);
		mapping.addEq("oid", aid);

		Thumbs thumbsDown = new Thumbs();
		thumbsDown.setOid(aid);
		thumbsDown.setType(0);
		thumbsDown.setUid(message.getId());
		baseSrvice.saveOrUpdate(thumbsDown);

		ThumbsMessage thumbsMessage = new ThumbsMessage();
		thumbsMessage.setDowncount(baseSrvice.count(new Thumbs(),  mapping.getRestrictions()));
		mapping.addEq("type", 1);
		thumbsMessage.setUpcount(baseSrvice.count(new Thumbs(),  mapping.getRestrictions()));
		thumbsMessage.setIsdown(true);
		baseDao.execute("update from Article set thumbsUp="+thumbsMessage.getUpcount()+
				", thumbsDown="+thumbsMessage.getDowncount()+
				" where aid="+aid);
		actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.THUMBS, aid, "lost"));
		return new ResponseMessage(10000,thumbsMessage.toString()).toString();
	}




	//文章贬赞的数据对象
	class ThumbsMessage{
		boolean isup = false; 
		boolean isdown = false; 
		long upcount = 0;
		long downcount = 0 ;
		public boolean isIsup() {
			return isup;
		}
		public ThumbsMessage() {
			super();
		}
		public ThumbsMessage(boolean isup, boolean iddown, int upcount,
				int downcount) {
			super();
			this.isup = isup;
			this.isdown = iddown;
			this.upcount = upcount;
			this.downcount = downcount;
		}
		public void setIsup(boolean isup) {
			this.isup = isup;
		}
		public boolean isIsdown() {
			return isdown;
		}
		public void setIsdown(boolean iddown) {
			this.isdown = iddown;
		}
		public long getUpcount() {
			return upcount;
		}
		public void setUpcount(long upcount) {
			this.upcount = upcount;
		}
		public long getDowncount() {
			return downcount;
		}
		public void setDowncount(long downcount) {
			this.downcount = downcount;
		} 
		@Override
		public String toString() {
			return new Gson().toJson(this);
		}
	}
}
