package cc.bblog.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.bblog.dao.BaseDao;
import cc.bblog.entity.Follow;
import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.ResponseMessage;
import cc.bblog.entity.UserAction;
import cc.bblog.log.UserActionTool;
import cc.bblog.tools.CookieTool;
@Controller()
@RequestMapping("follow/")
public class FollowController {

	public FollowController() {
	}
	BaseDao baseDao ; 
	@Resource(name="baseDao")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	UserActionTool actionTool ;
	@Resource(name="userActionTool")
	public void setActionTool(UserActionTool actionTool) {
		this.actionTool = actionTool;
	}
	@RequestMapping("{toid}")
	@ResponseBody
	public String followToOne(@PathVariable("toid")Integer toid,HttpServletRequest request) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			Follow follow = new Follow(message.getId(), toid, 1, new Date());
			baseDao.saveOrUpdate(follow);
			actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.follow, toid, "following"));
			return ResponseMessage.SUCCESSMESSAGE.toString();
			
		}else {
			return ResponseMessage.LOGINMESSAGE.toString() ; 
		}
	}
	@RequestMapping("cancel/{toid}")
	@ResponseBody
	public String canelToOne(@PathVariable("toid")Integer toid,HttpServletRequest request) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			Follow follow = new Follow(message.getId(), toid, 0, new Date());
			baseDao.saveOrUpdate(follow);
			actionTool.addAction(new UserAction(message.getId(), request.getRemoteAddr(), UserActionTool.follow, toid, "followed"));
			return ResponseMessage.SUCCESSMESSAGE.toString();
			
		}else {
			return ResponseMessage.LOGINMESSAGE.toString() ; 
		}
	}

}
