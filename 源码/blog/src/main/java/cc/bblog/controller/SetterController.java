package cc.bblog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cc.bblog.dao.BaseDao;
import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.ResponseMessage;
import cc.bblog.entity.UserStyle;
import cc.bblog.tools.CookieTool;

@Controller()
@RequestMapping("admin/setter/")
public class SetterController {
	BaseDao baseDao ; 
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public SetterController() {
	}
	@RequestMapping("changestyle")
	public String changeStyle(@RequestParam("bgcolor") String bgcolor ,@RequestParam("fontcolor") String fontcolor , HttpServletRequest request  ) {
		LoginMessage message = CookieTool.unlockCookie(request);
		if (message!=null) {
			baseDao.saveOrUpdate(new UserStyle(message.getId(), bgcolor, fontcolor));
			return ResponseMessage.SUCCESSMESSAGE.toString() ; 
		}else {
			return ResponseMessage.LOGINMESSAGE.toString() ; 
		}
	}

}
