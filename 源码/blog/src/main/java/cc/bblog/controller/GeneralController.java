package cc.bblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * 通用controller，具有通用限制的控制器
*    
* 项目名称：blog   
* 类名称：GeneralController   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年4月14日 上午1:38:29   
* 修改人：lxw   
* 修改时间：2017年4月14日 上午1:38:29   
* 修改备注：   
* @version 1.0.0   
*    
*/
@Controller("general")
public class GeneralController {

	public GeneralController() {
	}
	/**
	 * @param url需要相对于首页的
	 * @return
	 */
	@RequestMapping("/printhtml")
	@ResponseBody
	public String other(String html){
		return html;
	}
	
	
}
