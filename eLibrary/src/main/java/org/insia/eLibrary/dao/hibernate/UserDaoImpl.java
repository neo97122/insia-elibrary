package org.insia.eLibrary.dao.hibernate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.insia.eLibrary.dao.UserDao;
import org.insia.eLibrary.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



/**
 * Implements a strategy to perform complex actions on persistent data.
 * @author bmeurant
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	/**
	 * {@inheritDoc}
	 */
	public boolean checkLogin(String login, String password) {
		logger.info("Check user with login: "+login+" and password : [PROTECTED]");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(User.class);
		crit.add(Expression.ilike("loginUser", login));
		crit.add(Expression.eq("passwordUser", password));

		User user = (User)crit.uniqueResult();
		return (user != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public User getUser(String login) {
		logger.info("get User with login: "+login);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(User.class);
		crit.add(Expression.eq("loginUser", login));

		User user = (User)crit.uniqueResult();
		return user;
	}

	public List<User> getUsers(){
		logger.info("get list of users");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (ArrayList<User>)session.createQuery("From User").list();
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteUser(User user) {
		logger.info("Deleting the user " + user);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(user);
	}

	/**
	 * @see org.apache.tutorial.tapestrySpringHibernate.dao.UserDao#createUser(java.lang.String, java.lang.String)
	 */
	public User createUser(User user) {
		logger.info("Creating user with login " + user.getLogin());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.persist(user);
		return user;
	}

	/**
	 * @see org.apache.tutorial.tapestrySpringHibernate.dao.UserDao#updateUser(org.apache.tutorial.tapestrySpringHibernate.model.User)
	 */
	public User updateUser(User user) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.update(user);
		return user;
	}
}