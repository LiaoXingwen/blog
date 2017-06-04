package cc.bblog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.ResponseMessage;
import cc.bblog.entity.UserStyle;
import cc.bblog.service.BaseSrvice;
import cc.bblog.tools.CookieTool;

@Controller()
@RequestMapping("userstyle")
public class UserStyleController {
	BaseSrvice adminSrvice ; 
	@Autowired
	public void setAdminSrvice(BaseSrvice adminSrvice) {
		this.adminSrvice = adminSrvice;
	}

	/**
	 * 保存个人主题，直接返回json
	 * @param fontcolor 字体
	 * @param bgcolor 背景
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveStyle")
	@ResponseBody
	public String saveStyle(String fontcolor,String bgcolor,HttpServletRequest request) {
		try {
			LoginMessage loginMessage = CookieTool.unlockCookie(request);
			if (loginMessage!=null) {
				UserStyle style = new UserStyle();
				style.setUid(loginMessage.getId());
				if (bgcolor!=null&&bgcolor.trim().length()>0) {
					style.setBgcolor(bgcolor);
				}
				if (fontcolor!=null&&fontcolor.trim().length()>0) {
					style.setFontcolor(fontcolor);
				}
				adminSrvice.save(style);
				return ResponseMessage.SUCCESSMESSAGE.toString();
			}else {
				return ResponseMessage.LOGINMESSAGE.toString(); 
			}
		} catch (Exception e) {
			return ResponseMessage.SYSTEMERRORMESSAGE.toString(); 
		}
	}

}
