package cc.bblog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

/**   
*    评论及相关用户信息，采用视图方式来进行数据的简化，视图木有主键，所以需要将所有字段设置为主键
* 项目名称：blog   
* 类名称：OpenUserData   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年5月18日 上午9:52:43   
* 修改人：lxw   
* 修改时间：2017年5月18日 上午9:52:43   
* 修改备注：   
* @version 1.0.0   
*    
*/
@Entity
@Table(name="v_commet_user")
public class CommetAndUser implements Serializable{

	public CommetAndUser(Integer uid, String username, String headURl,
			Integer cid, Integer oid, Date date, String content, int type,
			boolean hasComment) {
		super();
		this.uid = uid;
		this.username = username;
		this.headURl = headURl;
		this.cid = cid;
		this.oid = oid;
		this.date = date;
		this.content = content;
		this.type = type;
		this.hasComment = hasComment;
	}

	public CommetAndUser() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	Integer uid; //用户id
	@Id
	String username ; //用户名
	@Id
	String headURl ; //头像
	@Id
	Integer cid ; // 评论id
	@Id
	Integer oid ;//评论对象，可以是文章，也可以是评论
	@Id
	Date date ; 
	@Id
	@NotBlank
	String content ; 
	@Id
	int type  = 0; //0为文章，1为评论
	@Id
	boolean hasComment = false;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false,updatable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Lob
	@Column(columnDefinition = "Text",nullable=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isHasComment() {
		return hasComment;
	}
	public void setHasComment(boolean hasComment) {
		this.hasComment = hasComment;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadURl() {
		return headURl;
	}

	public void setHeadURl(String headURl) {
		this.headURl = headURl;
	}


}
