package cc.bblog.entity;

import java.util.List;

/**   
*    分页的数据基本类型
* 项目名称：blog   
* 类名称：ViewModelByPager   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年4月14日 下午12:10:30   
* 修改人：lxw   
* 修改时间：2017年4月14日 下午12:10:30   
* 修改备注：   
* @version 1.0.0   
*    
*/
public class ViewModelByPager {

	public ViewModelByPager() {
	}
	
	List<?> list ;//当前页面的数据
	int maxPager = 0  ; //最大的页面
	int pager = 0 ; //当前页面
	List<Integer> pagers ; 
	boolean isNull = false;
	String message ;
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getMaxPager() {
		return maxPager;
	}
	public void setMaxPager(int maxPager) {
		this.maxPager = maxPager;
	}
	public int getPager() {
		return pager;
	}
	public void setPager(int pager) {
		this.pager = pager;
	}
	public List<Integer> getPagers() {
		return pagers;
	}
	public void setPagers(List<Integer> pagers) {
		this.pagers = pagers;
	}
	public boolean isNull() {
		return isNull;
	}
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
	

}
