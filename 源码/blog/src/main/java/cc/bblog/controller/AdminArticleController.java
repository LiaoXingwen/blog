package cc.bblog.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.bblog.entity.Article;
import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.UserAction;
import cc.bblog.log.UserActionTool;
import cc.bblog.service.BaseSrvice;
import cc.bblog.tools.CookieTool;


@Controller()
@RequestMapping("admin")
public class AdminArticleController {
	UserActionTool actionTool ;
	@Resource(name="userActionTool")
	public void setActionTool(UserActionTool actionTool) {
		this.actionTool = actionTool;
	}
	
	BaseSrvice adminSrvice ; 
	@Autowired
	public void setAdminSrvice(BaseSrvice adminSrvice) {
		this.adminSrvice = adminSrvice;
	}

	public AdminArticleController() {
	}
	
	/**
	 * 添加文章，保存再草稿箱
	 * @param article
	 * @param model
	 * @param result
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addArticle")
	public String addArticle(Article article,Model model,BindingResult result,HttpServletRequest request,HttpServletResponse response) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			article.setUid(message.getId());
			article.setUpdateTime(new Date());
		}else {
			CookieTool.clearCookie(request, response);
			return "redirect:../login";
		}
		//参数有误
		if (result.hasErrors()) {
			model.addAttribute(article);
			return "admin/editor";
		}
		try {
			if (adminSrvice.save(article)!=null) {
				actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.addart, null, null));
				return "redirect:./draft";
			}else {
				model.addAttribute(article);
				return "admin/editor";
			}	
		} catch (Exception e) {
			model.addAttribute(article);
			return "admin/editor";
		}
	}
	/**
	 * 更新文章内容
	 * @param aid
	 * @param article
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/update/{aid}")
	public String updaterticle(@PathVariable("aid")Integer aid,Article article,Model model,HttpServletRequest request,HttpServletResponse response) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			//操作成功
			Article ar = (Article) adminSrvice.findById(article, aid);
			if (message.getId()==ar.getUid()) {
				ar.setTitle(article.getTitle());
				ar.setMd(article.getMd());
				ar.setUpdateTime(new Date());
				ar.setHtml(article.getHtml());
				adminSrvice.update(ar);
				actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.addart, aid, null));
				if (ar.getState()==1) {
					return "redirect:../released";
				}else {
					return "redirect:../draft";
				}
			}
		}
		return "redirect:../login";

	}

	/**
	 * 发布文章，已经保存的状态
	 * 
	 * @param aid
	 * @param article
	 * @param model
	 * @param request
	 * @param response
	 * @return 已发布
	 */
	@RequestMapping("/push/{aid}")
	public String pushAterticle(@PathVariable("aid")Integer aid,Article article,Model model,HttpServletRequest request,HttpServletResponse response) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			//操作成功
			Article ar = (Article) adminSrvice.findById(article, aid);
			if (ar!=null&&message.getId()==ar.getUid()) {
				ar.setHtml(article.getHtml());
				ar.setMd(article.getMd());
				ar.setTitle(article.getTitle());
				ar.setUpdateTime(new Date());
				ar.setState(1);
				adminSrvice.update(ar);
				actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.addart, aid, null));
			}
			return "redirect:../released";
		}
		return "redirect:../login";
	}
	/**
	 * 提交新的push，还没有保存数据
	 * @param article
	 * @param model
	 * @param request
	 * @param response
	 * @return 已发布
	 */
	@RequestMapping("/push")
	public String pushAterticle(Article article,Model model,HttpServletRequest request,HttpServletResponse response) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			article.setUid(message.getId());
			article.setUpdateTime(new Date());
			article.setState(1);
			adminSrvice.save(article);
			actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.pushart, article.getAid(), null));
			return "redirect:./released";
		}
		return "redirect:../login";
	}

	/**
	 * 提交发布，根据id
	 * @param aid
	 * @param model
	 * @param request
	 * @param response
	 * @return 草稿箱
	 */
	@RequestMapping("/pushaid")
	public String pushAterticle(Integer aid,Model model,HttpServletRequest request,HttpServletResponse response) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			Article ar = (Article) adminSrvice.findById(new Article(), aid);
			if (ar!=null&&message.getId()==ar.getUid()) {
				ar.setState(1);
				adminSrvice.update(ar);
			}
			actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.addart, ar.getAid(), null));
			return "redirect:./draft";
		}
		return "redirect:../login";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String  delete(@RequestParam(required=true)Integer aid,HttpServletResponse response,HttpServletRequest request) {
		try {
			LoginMessage message = CookieTool.unlockCookie(request);
			if (message==null) {
				return "redirect:../login";
			}else {
				Article article = (Article) adminSrvice.findById(new Article(), aid);
				
				if (article.getUid()==message.getId()) {
					article.setState(-1);
					adminSrvice.update(article);
					actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.addart, aid, null));
					return "success";
				}else {
					return "error";
				}
			}
		} catch (Exception e) {
			return "error";
		}
	}
	/**
	 * 取消发布状态
	 * @param article
	 * @param model
	 * @param request
	 * @param response
	 * @return 已发布
	 */
	@RequestMapping("/cancelpush/{aid}")
	public String cancelAterticle(@PathVariable("aid")Integer aid,Article article,Model model,HttpServletRequest request,HttpServletResponse response) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			//操作成功
			Article ar = (Article) adminSrvice.findById(article, aid);
			if (ar!=null&&message.getId()==ar.getUid()) {
				ar.setState(0);
				adminSrvice.update(ar);
			}
			return "redirect:../released";
		}
		return "redirect:../login";
	}
}
