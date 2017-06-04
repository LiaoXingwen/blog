package cc.bblog.entity;

import java.io.Serializable;
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
@Table(name="t_follow")
public class Follow implements Serializable {

	public Follow() {
	}
	int fromid ;
	int toid ; 
	int state ; //记录关注的状态，1为正在关注，0为曾经关注
	Date date ;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	public int getFromid() {
		return fromid;
	}
	public void setFromid(int fromid) {
		this.fromid = fromid;
	}
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	public int getToid() {
		return toid;
	}
	public Follow(int fromid, int toid, int state, Date date) {
		super();
		this.fromid = fromid;
		this.toid = toid;
		this.state = state;
		this.date = date;
	}
	public void setToid(int toid) {
		this.toid = toid;
	}
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(nullable=false,updatable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	} 
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
