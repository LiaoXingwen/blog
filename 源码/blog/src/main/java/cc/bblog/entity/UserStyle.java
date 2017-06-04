package cc.bblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_userstyle")
public class UserStyle {
	public static String BEFUATBGCOLOR = "#3CB371" ;
	public static String BEFUATFONTCOLOR = "#F5FFFA";
	public UserStyle() {
		bgcolor = BEFUATBGCOLOR;
		fontcolor = BEFUATFONTCOLOR;
	}
	public UserStyle(Integer uid, String bgcolor, String fontcolor) {
		this.uid = uid;
		this.bgcolor = bgcolor;
		this.fontcolor = fontcolor;
	}
	Integer uid ; //用户id
	String bgcolor; //背景颜色
	String fontcolor ; //字体颜色
	@Id
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Column(length=7)
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	@Column(length=7)
	public String getFontcolor() {
		if (fontcolor==null) {
			return BEFUATFONTCOLOR;
		}
		return fontcolor;
	}
	public void setFontcolor(String fontcolor) {
		if (fontcolor==null) {
			this.fontcolor = BEFUATFONTCOLOR ; 
		}
		this.fontcolor = fontcolor;
	}
	
}
