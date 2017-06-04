package cc.bblog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.validator.constraints.NotBlank;

/**   
* 评论实体
* 项目名称：blog   
* 类名称：Comment   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年5月17日 下午8:40:05   
* 修改人：lxw   
* 修改时间：2017年5月17日 下午8:40:05   
* 修改备注：   
* @version 1.0.0   
*    
*/
@Entity
@Table(name="t_comment")
public class Comment {

	public Comment() {
		date = new Date();
	}
	Integer cid ; // 评论id
	Integer oid ;//评论对象，可以是文章，也可以是评论
	Integer aid ;//评论父类

	Date date ; //时间
	@NotBlank
	String content ; //内容
	int type  = 0; //0为文章，1为评论
	boolean hasComment = false;
	Integer uid;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Id() 
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
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
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	
}
