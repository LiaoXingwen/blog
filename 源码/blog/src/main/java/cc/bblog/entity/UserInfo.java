package cc.bblog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_userinfo")
public class UserInfo {
	Integer uid  ; 
	Integer thumbsUp ; 
	Integer thumbsDown ; 
	Integer viewcount ; 
	Integer articles ; 
	Integer comments ;
	Integer following ; //关注的人的总数
	Integer followers ;//追随者 的总数
	Integer fileSize ; //占用空间大小
	@Id() 
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	public Integer getUid() {
		return uid;
	}
	public UserInfo() {
	}
	public UserInfo(Integer uid, Integer thumbsUp, Integer thumbsDown,
			Integer viewcount, Integer articles, Integer comments,
			Integer following, Integer followers, Integer fileSize) {
		this.uid = uid;
		this.thumbsUp = thumbsUp;
		this.thumbsDown = thumbsDown;
		this.viewcount = viewcount;
		this.articles = articles;
		this.comments = comments;
		this.following = following;
		this.followers = followers;
		this.fileSize = fileSize;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getThumbsUp() {
		return thumbsUp;
	}
	public void setThumbsUp(Integer thumbsUp) {
		this.thumbsUp = thumbsUp;
	}
	public Integer getThumbsDown() {
		return thumbsDown;
	}
	public void setThumbsDown(Integer thumbsDown) {
		this.thumbsDown = thumbsDown;
	}
	public Integer getViewcount() {
		return viewcount;
	}
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}
	public Integer getArticles() {
		return articles;
	}
	public void setArticles(Integer articles) {
		this.articles = articles;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public Integer getFollowing() {
		return following;
	}
	public void setFollowing(Integer following) {
		this.following = following;
	}
	public Integer getFollowers() {
		return followers;
	}
	public void setFollowers(Integer followers) {
		this.followers = followers;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	

}
