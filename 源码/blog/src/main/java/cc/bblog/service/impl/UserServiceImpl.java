package cc.bblog.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cc.bblog.dao.UserDao;
import cc.bblog.entity.User;
import cc.bblog.service.UserService;

/**   
*    用户的业务层
* 项目名称：blog   
* 类名称：UserServiceImpl   
* 类描述：   
* 创建人：廖兴文
* 创建时间：2017年4月8日 上午10:18:44   
* 修改人：lxw   
* 修改时间：2017年4月8日 上午10:18:44   
* 修改备注：   
* @version 1.0.0   
*    
*/
@Component("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDao") 
	UserDao userDao ;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	

	public UserServiceImpl() {
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public String saveUser(User user) {
		try{
			user.setRegisterTime(new Date(System.currentTimeMillis()));
			return userDao.saveUser(user);
		}catch (Exception exception){
			return "fail";
		}
		
	}

	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
	/**删除用户
	 * @param email
	 */
	public void deleteUserByEmail(String email){
		userDao.deleteUserByEmail(email);
	}



	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}



	@Override
	public void excHql(String hql) {
		userDao.excHql(hql);
		
	}



	@Override
	public User findByID(int id) {
		// TODO 自动生成的方法存根
		return userDao.findByID(id);
	}

}
