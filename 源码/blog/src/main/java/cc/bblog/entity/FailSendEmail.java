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


@Entity
@Table(name="t_failemail")
public class FailSendEmail {
	
	public FailSendEmail(String email, String type, Date time, Integer state,
			int id) {
		super();
		this.email = email;
		this.type = type;
		this.time = time;
		this.state = state;
		this.id = id;
	}
	public FailSendEmail() {
	}
	String  email ;
	String type ; 
	Date time ; 
	Integer state ; 
	int id ;
	@Column(length=100)
	public String getEmail() {
		return email;
	}
	@Column(length=20)
	public String getType() {
		return type;
	}
	@Temporal(TemporalType.TIMESTAMP) 
	@Column()
	public Date getTime() {
		return time;
	}
	@Column(length=1)
	public Integer getState() {
		return state;
	}
	@Id() 
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	public int getId() {
		return id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setId(int id) {
		this.id = id;
	} 
	

}
