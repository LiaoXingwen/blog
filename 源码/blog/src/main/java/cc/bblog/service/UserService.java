package cc.bblog.service;

import cc.bblog.entity.User;

public interface UserService {
	/**
	 * 通过邮箱查找用户
	 */
	public User findByEmail(String email);
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
	 * 通过用户名查找用户
	 */
	public User findByUsername(String username);
	
	/**
	 * 执行hql
	 * @param hql
	 */
	public void excHql(String hql);
}
