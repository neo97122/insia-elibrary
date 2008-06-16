package org.insia.eLibrary.dao.hibernate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.insia.eLibrary.dao.EBookDao;
import org.insia.eLibrary.model.EBook;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



/**
 * Implements a strategy to perform complex actions on persistent data.
 * @author bmeurant
 */
public class EBookDaoImpl extends HibernateDaoSupport implements EBookDao {

	/**
	 * {@inheritDoc}
	 */
	/*
	public boolean checkLogin(String login, String password) {
		logger.info("Check EBook with login: "+login+" and password : [PROTECTED]");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(EBook.class);
		crit.add(Expression.ilike("loginEBook", login));
		crit.add(Expression.eq("passwordEBook", password));

		EBook EBook = (EBook)crit.uniqueResult();
		return (EBook != null);
	}
	*/
	/**
	 * {@inheritDoc}
	 */
	public EBook getEBook(String title) {
		logger.info("get EBook with title: "+title);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(EBook.class);
		crit.add(Expression.eq("titleEBook", title));

		EBook ebook = (EBook)crit.uniqueResult();
		return ebook;
	}

	public List<EBook> getEBooks(){
		logger.info("get list of ebooks");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (ArrayList<EBook>)session.createQuery("From EBook").list();
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteEBook(EBook ebook) {
		logger.info("Deleting the EBook " + ebook);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(ebook);
	}

	/**
	 * @see org.apache.tutorial.tapestrySpringHibernate.dao.EBookDao#createEBook(java.lang.String, java.lang.String)
	 */
	public EBook createEBook(EBook ebook) {
		logger.info("Creating EBook with title " + ebook.getTitle());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.persist(ebook);
		return ebook;
	}

	/**
	 * @see org.apache.tutorial.tapestrySpringHibernate.dao.EBookDao#updateEBook(org.apache.tutorial.tapestrySpringHibernate.model.EBook)
	 */
	public EBook updateEBook(EBook ebook) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.update(ebook);
		return ebook;
	}
}