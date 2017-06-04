/**
 * 
 */
package cc.bblog.dao;

import cc.bblog.entity.User;

/**   
 *    
 * 项目名称：blog   
 * 类名称：UserDao   
 * 类描述：   
 * 创建人：廖兴文
 * 创建时间：2017年3月27日 下午7:37:26   
 * 修改人：lxw   
 * 修改时间：2017年3月27日 下午7:37:26   
 * 修改备注：   
 * @version 1.0.0   
 *    
 */
public interface UserDao {
	/**
	 * 通过邮箱查找用户
	 */
	public User findByEmail(String email);
	/**
	 * 通过用户名查找用户
	 */
	public User findByUsername(String username);
	/**
	 * 通过id查找用户
	 */
	public User findByID(int id);
	/**
	 * 添加用户
	 * @return
	 */
	public String saveUser(User user);
	/**
	 * 修改用户
	 * @return
	 */
	public boolean updateUser(User user);
	
	/**删除用户
	 * @param email
	 */
	public void deleteUserByEmail(String email);
	
	/**
	 * 执行hql
	 * @param hql
	 */
	public void excHql(String hql);

}
