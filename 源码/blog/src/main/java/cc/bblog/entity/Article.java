package cc.bblog.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;


/**   
 *    文章
 * 项目名称：blog   
 * 类名称：Article   
 * 类描述：   
 * 创建人：廖兴文
 * 创建时间：2017年4月13日 下午9:50:54   
 * 修改人：lxw   
 * 修改时间：2017年4月13日 下午9:50:54   
 * 修改备注：   
 * @version 1.0.0   
 *    
 */
@Entity
@Table(name="t_article")
public class Article {

	
	
	Integer aid ; // 文章id
	Integer uid ; //用户
	@NotBlank
	String title ; //标题
	@NotBlank
	String md ; //md文本
	@NotBlank
	String html ; //html文本
	String label; //标签
	Date updateTime;//最后一次修改时间
	Date saveTime ; //生成时间
	Integer state = 0 ; //状态
	Integer viewCount = 0 ; 
	Integer thumbsUp = 0 ; 
	Integer thumbsDown = 0 ;
	boolean hasComment = false;
	User User ; //关联关系
	
	
//	CascadeType.MERGE级联更新：若items属性修改了那么order对象保存时同时修改items里的对象。对应EntityManager的merge方法 
//	  
//	   CascadeType.PERSIST级联刷新：获取order对象里也同时也重新获取最新的items时的对象。对应EntityManager的refresh(object)方法有效。即会重新查询数据库里的最新数据   
//	   
//	  CascadeType.REFRESH级联保存：对order对象保存时也对items里的对象也会保存。对应EntityManager的presist方法   
//	   
//	  CascadeType.REMOVE级联删除：对order对象删除也对items里的对象也会删除。对应EntityManager的remove方法   
//	cascade的值只能从CascadeType.PERSIST（级联新建）、CascadeType.REMOVE（级联删除）、CascadeType.REFRESH（级联刷新）、CascadeType.MERGE（级联更新）中选择一个或多个。还有一个选择是使用CascadeType.ALL，表示选择全部四项
	
	
//	fatch
//	可选择项包括：FetchType.EAGER和FetchType.LAZY。
//	前者表示关系类,	在主类加载的时候同时加载，
//	后者表示关系类在被访问时才加载。默认值是FetchType.LAZY。
	
//	optional属性是定义该关联类是否必须存在，
//	值为false 时，关联类双方都必须存在，如果关系被维护端不存在，查询的结果为null。
//	值为true 时, 关系被维护端可以不存在，查询的结果仍然会返回关系维护端，在关系维护端中指向关系被维护端的属性为null。
//	optional属性的默认值是true。optional 属性实际上指定关联类与被关联类的join 查询关系，
//	如optional=false 时join 查询关系为inner join, optional=true 时join 查询关系为left join
	
	@ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name = "uid",insertable = false, updatable = false)
	public User getUser() {
		return User;
	}
	public void setUser(User user) {
		User = user;
	}
	public Article() {
		updateTime = new Date();
	}

	@Column(nullable=false,columnDefinition="INT default 0")
	public Integer getViewCount() {
		if (viewCount==null) {
			return 0;
		}
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	@Column(name="state" ,nullable=false,columnDefinition="INT default 0")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Column(nullable=false)
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Temporal(TemporalType.TIMESTAMP) 
	@Column()
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(nullable=false,updatable=false)
	public Date getSaveTime() {
		if (saveTime==null) {
			saveTime = new Date();
		}
		return saveTime;
	}
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	@Id() 
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	@Column(nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Lob
	@Column(columnDefinition = "LongText",nullable=false)
	public String getMd() {
		return md;
	}
	public void setMd(String md) {
		this.md = md;
	}
	@Lob
	@Column(columnDefinition = "LongText",nullable=false)
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getLabel() {
		if (label==null) {
			return "无标签";
		}
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Column(name="thumbsup" ,nullable=false,columnDefinition="INT default 0")
	public Integer getThumbsUp() {
		return thumbsUp;
	}
	public void setThumbsUp(Integer thumbsUp) {
		this.thumbsUp = thumbsUp;
	}
	public boolean isHasComment() {
		return hasComment;
	}
	public void setHasComment(boolean hasComment) {
		this.hasComment = hasComment;
	}
	@Column(name="thumbsdown" ,nullable=false,columnDefinition="INT default 0")
	public Integer getThumbsDown() {
		return thumbsDown;
	}
	public void setThumbsDown(Integer thumbsDown) {
		this.thumbsDown = thumbsDown;
	}
}
