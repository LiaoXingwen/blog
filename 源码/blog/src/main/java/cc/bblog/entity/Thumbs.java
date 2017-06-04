package cc.bblog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
*    表示赞与贬，thumbs手指，上为赞，下为贬
* 项目名称：blog   
* 类名称：Thumbs   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年4月18日 下午10:37:25   
* 修改人：lxw   
* 修改时间：2017年4月18日 下午10:37:25   
* 修改备注：   
* @version 1.0.0   
*    
*/
@Entity
@Table(name="t_thumbs")
public class Thumbs implements Serializable {

	private static final long serialVersionUID = 2325088568535426908L;
	public Thumbs() {
	}
	
	Integer oid; //对象的id：暂定对象可以：文章或评论
	Integer uid;//操作的用户id
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	Integer type ; //0为贬，1为赞 
	Date time ;
	@Id() 
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	@Id
	@GeneratedValue(generator= "oid")
	@GenericGenerator(name= "oid", strategy = "assigned")
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thumbs other = (Thumbs) obj;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
}
