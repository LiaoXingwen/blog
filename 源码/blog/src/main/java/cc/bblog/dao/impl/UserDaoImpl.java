package cc.bblog.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import cc.bblog.dao.UserDao;
import cc.bblog.entity.User;
@Component("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	public UserDaoImpl() {
	}

	public User findByEmail(String email) {
		String hql = "from User where email='"+email+"'";
		@SuppressWarnings("unchecked")
		List<User> list =  (List<User>) this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	public void deleteUserByEmail(String email) {
		User user = new User();
		user.setEmail(email);
		user.setId(null);
		this.getHibernateTemplate().delete(User.class.getSimpleName(), user);

	}

	public String saveUser(User user) {
		try {
			this.getHibernateTemplate().save(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail" ;
		}
	}

	public boolean updateUser(User user) {
		try {
			this.getHibernateTemplate().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public User findByUsername(String username) {
		String hql = "from User where username='"+username+"'";
		@SuppressWarnings("unchecked")
		List<User> list =  (List<User>) this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public void excHql(String sql) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {   
			public Object doInHibernate(Session session)   
					throws HibernateException { 
				Query query =  session.createQuery(sql);
				return query.executeUpdate();

			}   
		});   
	}

	@Override
	public User findByID(int id) {
		String hql = "from User where id="+id+"";
		@SuppressWarnings("unchecked")
		List<User> list =  (List<User>) this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

}
