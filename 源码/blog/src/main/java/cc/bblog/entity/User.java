package cc.bblog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="t_user")
//通过 @Table 为实体Bean指定对应数据库表，目录和schema的名字
//@UniqueConstraints 可以定义表的唯一约束。在title,和content加上唯一约束
public class User implements Serializable {

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", sex=" + sex + ", brithDay=" + brithDay
				+ ", email=" + email + ", description=" + description
				+ ", headURl=" + headURl + ", state=" + state
				+ ", registerTime=" + registerTime + ", code=" + code + "]";
	}
	Integer id = 10000;// 用户编号
	String username ; //用户名
	String password ; //密码
	String sex ; //性别
	Date brithDay ;//出生时间
	String email ;//邮箱
	String description ; //描述
	String headURl ; //头像
	Integer state = 0 ; //状态：目前可以定义有三种情况：没有认证0，不可以-1，可用1
	Date registerTime ;//注册时间
	String code;//用户的验证码
	
	UserStyle style ; //关联关系
	
	@OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name = "id",insertable = false, updatable = false)
	public UserStyle getStyle() {
		if (style==null) {
			style = new UserStyle();
		}
		return style;
	}
	public void setStyle(UserStyle style) {
		this.style = style;
	}
	
	
	@Column(length=25)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User() {
	}

//	name 可选，列名（默认值是属性名）
//	unique 可选，是否在该列上设置唯一约束（默认值false）
//	nullable 可选，是否设置该列的值可以为空（默认值false）
//	insertable 可选，该列是否作为生成的insert语句中的一个列（默认值true）
//	updatable 可选，该列是否作为生成的update语句中的一个列（默认值true）
//	columnDefinition 可选，为这个特定列覆盖sql ddl片段（这可能导致无法在不同数据库间移植）
//	table 可选，定义对应的表（默认为主表）
//	length 可选，列长度（默认值255）
//	precision 可选，列十进制精度（decimal precision)(默认值0）
//	scale 可选，如果列十进制数值范围（decimal scale）可用，在此设置（默认值0）
	@Column(unique=true,length=30)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(length=1,nullable=false)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(nullable=false)
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.TIMESTAMP) 
	@Column()
	public Date getBrithDay() {
		return brithDay;
	}

	public void setBrithDay(Date brithDay) {
		this.brithDay = brithDay;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	@Column(length=255,nullable=false)
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	/**
     *  @GeneratedValue注解来定义生成策略
     *  GenerationType.TABLES 当前主键的值单独保存到一个数据库的表中
     *  GenerationType.SEQUENCE  利用底层数据库提供的序列生成标识符
     *  GenerationType.IDENTITY 采取数据库的自增策略
     *  GenerationType.AUTO 根据不同数据库自动选择合适的id生成方案，这里使用mysql,为递增型
     */
	 @Id() 
	 @GeneratedValue(generator = "paymentableGenerator")
     @GenericGenerator(name = "paymentableGenerator", strategy = "native")  
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length=5)
	public String getSex() {
		return sex;
	}

	@Column(length=100,unique=true,nullable=false)
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Column()
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@Column(length=255)
	public String getHeadURl() {
		if (headURl==null) {
			return "/blog/file/image/blog/system/logo.png";
		}
		return headURl;
	}

	public void setHeadURl(String headURl) {
		this.headURl = headURl;
	}
	private static final long serialVersionUID = -1331020904725861722L;
	
	
	/**
	 * 清除隐私信息,大部分时候id，用户名和头像才是非关键信息
	 */
	public void clearPrivate(){
		this.password = null ; 
		this.code = null ; 
		this.registerTime = null ; 
		this.state = null ; 
		this.style = null ; 
		this.sex = null ; 
		this.email = null;
		this.brithDay = null ;
		this.description = null ; 
		
	}
	
}
