package cc.bblog.tools;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.UserStyle;
import cc.bblog.service.BaseSrvice;

@Component()
public class UserStyleTool {

	BaseSrvice baseSrvice ;
	@Autowired
	public void setBaseSrvice(BaseSrvice baseSrvice) {
		this.baseSrvice = baseSrvice;
	}

	public UserStyleTool() {
	}
	/**
	 * 添加个人风格
	 * @param model
	 * @param request
	 */
	public void setMyStyle(Model model , HttpServletRequest request) {
		LoginMessage message =CookieTool.unlockCookie(request);
		if (message!=null) {
			try {
				UserStyle style = (UserStyle) baseSrvice.findById(new UserStyle(), message.getId());
				if (style!=null) {
					model.addAttribute("bgcolor", style.getBgcolor());
					model.addAttribute("fontcolor",style.getFontcolor());
				}else{
					model.addAttribute("bgcolor", UserStyle.BEFUATBGCOLOR);
					model.addAttribute("fontcolor",UserStyle.BEFUATFONTCOLOR);
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("bgcolor", UserStyle.BEFUATBGCOLOR);
				model.addAttribute("fontcolor",UserStyle.BEFUATFONTCOLOR);
			}

		}else {
			model.addAttribute("bgcolor", UserStyle.BEFUATBGCOLOR);
			model.addAttribute("fontcolor",UserStyle.BEFUATFONTCOLOR);
		}
	}

	/**
	 * 添加个人风格
	 * @param model
	 * @param message
	 */
	public void setMyStyle(Model model ,LoginMessage message) {
		if (message!=null) {
			UserStyle style = (UserStyle) baseSrvice.findById(new UserStyle(), message.getId());
			if (style!=null) {
				model.addAttribute("bgcolor", style.getBgcolor());
				model.addAttribute("fontcolor",style.getFontcolor());
			}else{
				model.addAttribute("bgcolor", UserStyle.BEFUATBGCOLOR);
				model.addAttribute("fontcolor",UserStyle.BEFUATFONTCOLOR);
			}
		}else {
			model.addAttribute("bgcolor", UserStyle.BEFUATBGCOLOR);
			model.addAttribute("fontcolor",UserStyle.BEFUATFONTCOLOR);
		}
	}

	/**
	 * 添加个人风格
	 * @param model
	 * @param message
	 */
	public void setMyStyle(Model model ,int uid) {
		if (uid==-1) {
			model.addAttribute("bgcolor", UserStyle.BEFUATBGCOLOR);
			model.addAttribute("fontcolor",UserStyle.BEFUATFONTCOLOR);
			return ;
		}
		UserStyle style = (UserStyle) baseSrvice.findById(new UserStyle(),uid);
		if (style!=null) {
			model.addAttribute("bgcolor", style.getBgcolor());
			model.addAttribute("fontcolor",style.getFontcolor());
		}else{
			model.addAttribute("bgcolor", UserStyle.BEFUATBGCOLOR);
			model.addAttribute("fontcolor",UserStyle.BEFUATFONTCOLOR);
		}
	}
}
