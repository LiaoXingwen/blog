package cc.bblog.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import cc.bblog.dao.FailEmailDao;
import cc.bblog.entity.FailSendEmail;
@Component("failEmailDao")
public class FailEmailDaoImpl extends HibernateDaoSupport implements FailEmailDao{

	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	public FailEmailDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FailSendEmail> findFailEmails() {
		String hql = "from FailSendEmail where state=0";
		return (List<FailSendEmail>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteEmail(int id) {
		FailSendEmail failSendEmail = new FailSendEmail();
		failSendEmail.setId(id);
		this.getHibernateTemplate().delete(failSendEmail);
	}

	@Override
	public void save(FailSendEmail email) {
		this.getHibernateTemplate().save(email);
	}

	@Override
	public void update(FailSendEmail email) {
		this.getHibernateTemplate().update(email);
	}

}
