package cc.bblog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cc.bblog.tools.DateKit;

/**   
*    记录用户的动作
* 项目名称：blog   
* 类名称：UserAction   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年5月31日 下午12:46:20   
* 修改人：lxw   
* 修改时间：2017年5月31日 下午12:46:20   
* 修改备注：   
* @version 1.0.0   
*    
*/
@Entity
@Table(name="t_user_action")
public class UserAction {
	
	@Override
	public String toString() {
		return "UserAction [uid=" + uid + ", fromIP=" + fromIP + ", action="
				+ action + ", objId=" + objId + ", data=" + data + ", date="
				+ date + "]";
	}
	public UserAction() {
	}
	int uid ; 
	String fromIP;//操作的ip
	int action = 0 ; //-1，注册，0:登陆，1：查看贴，2：查看个人空间，3，评论，4，点赞，5,搜索
	Integer objId ; //操作对象的id
	String data ; //操作时携带的数据
	Date date  ;
	public UserAction(int uid, String fromIP, int action, Integer objId,
			String data) {
		super();
		this.uid = uid;
		this.fromIP = fromIP;
		this.action = action;
		this.objId = objId;
		this.data = data;
		this.date = DateKit.toZeroDate();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getFromIP() {
		return fromIP;
	}
	public void setFromIP(String fromIP) {
		this.fromIP = fromIP;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Temporal(TemporalType.DATE) 
	@Column(nullable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	} 

}
