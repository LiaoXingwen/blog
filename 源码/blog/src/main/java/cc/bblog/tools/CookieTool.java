package cc.bblog.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.User;

public class CookieTool {

	static String 	COOKIEKEY = "cookie";
	static String SESSIONKEY = "bblog";

	public CookieTool() {
	}

	/**
	 * 获取通行字符
	 * @param u
	 * @return
	 */
	public static String getPass(User u) {
		String pass = LoginMessage.getLockMsg(new LoginMessage(u.getPassword(),
				u.getCode(), 
				System.currentTimeMillis(),
				u.getId()));
		return pass;
	}

	/**
	 * 将通行证加入sesion和cookie
	 * @param u
	 */
	public static void addPass (User u,HttpSession session ,HttpServletResponse response,HttpServletRequest request) {
		String pass = LoginMessage.getLockMsg(new LoginMessage(u.getPassword(),
				u.getCode(), 
				System.currentTimeMillis(),
				u.getId()));
		//如果有旧的cookie，立刻失效
		Cookie cookie2 = findCookie(request);
		if (cookie2!=null) {
			cookie2.setMaxAge(0);
			response.addCookie(cookie2);
		}

		Cookie cookie = new Cookie(COOKIEKEY, pass);
		cookie.setPath("/");
		cookie.setMaxAge(7*24*60*60*60*1000);
		cookie.setVersion(1);
		response.addCookie(cookie);
		session.setAttribute(SESSIONKEY+u.getId(), pass);

	}

	/**
	 * 解析通行内容
	 * @param u
	 */
	public static LoginMessage unlockCookie (HttpServletRequest request) {
		Cookie cookie = findCookie(request);
		if (cookie!=null) {
			return LoginMessage.getUnlockMsg(cookie.getValue());
		}else {
			return null;
		}
	}

	/**
	 * 取消登陆状态
	 * @param u
	 */
	public static void clearCookie (HttpServletRequest request,HttpServletResponse response) {
		//如果有旧的cookie，立刻失效
		Cookie cookie2 = findCookie(request);
		cookie2.setMaxAge(0);
		response.addCookie(cookie2);

	}
	/**
	 * 查看cookie
	 * @param cookies
	 * @return
	 */
	public static Cookie findCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies!=null&&cookies.length>0) {
			for (Cookie c: cookies) {
				Cookie cookie = c;
				if (cookie.getName().equals(COOKIEKEY)) {
					return cookie ; 
				}
			}
		}
		return null;
	}
}
