package cc.bblog.emailTools;

import javafx.collections.ListChangeListener.Change;


/**   
*    发送邮件的信息队列，防止大量邮件需要发送导致虚拟机崩溃
* 项目名称：blog   
* 类名称：EmailSendQueueInterface   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年4月10日 下午6:08:14   
* 修改人：lxw   
* 修改时间：2017年4月10日 下午6:08:14   
* 修改备注：   
* @version 1.0.0   
*    
*/
public interface EmailSendQueueInterface {
	
	void initQueue();
	
	boolean SendEmail(SendEmailMethod method);
	
	Thread getFreeThread();
	
	int freeCount();
	
	void colseThread();
	
	void openThread();
	
	void closeAll() ; 
	
	
}
