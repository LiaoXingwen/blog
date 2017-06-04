package cc.bblog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.Gson;
import com.sun.glass.ui.Size;

/**  
 * 上传文件与用户的映射关系 
*    
* 项目名称：blog   
* 类名称：FileMapping   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年4月20日 上午10:09:12   
* 修改人：lxw   
* 修改时间：2017年4月20日 上午10:09:12   
* 修改备注：   
* @version 1.0.0   
*    
*/
@Entity
@Table(name="t_filemapping")
public class FileMapping {

	public FileMapping() {
		uploadTime = new Date();
	}

	public FileMapping( Integer uid, String url,float size) {
		this.uid = uid;
		this.url = url;
		this.size = size ;
		uploadTime = new Date();
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	Integer fid ;
	Integer uid ; 
	String url ;
	Date uploadTime;
	float size ; 
	
	
	
	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	@Id() 
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(nullable=false,updatable=false)
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
}
