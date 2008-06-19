/**
 *
 */
package org.insia.eLibrary.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.insia.eLibrary.dao.EBookDao;
import org.insia.eLibrary.model.EBook;
import org.insia.eLibrary.model.Reservation;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Titi
 *
 */
public class EBookDaoImpl extends HibernateDaoSupport implements EBookDao {

	/* (non-Javadoc)
	 * @see org.insia.eLibrary.dao.EBookDao#createEBook(org.insia.eLibrary.model.EBook)
	 */
	public EBook createEBook(EBook eBook) {
		logger.info("Creating eBook with title " + eBook.getTitle());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.persist(eBook);
		return eBook;
	}

	/* (non-Javadoc)
	 * @see org.insia.eLibrary.dao.EBookDao#deleteEBook(org.insia.eLibrary.model.EBook)
	 */
	public void deleteEBook(EBook eBook) {
		logger.info("Deleting the eBook " + eBook.getTitle());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(eBook);
	}

	/* (non-Javadoc)
	 * @see org.insia.eLibrary.dao.EBookDao#getEBook(java.lang.String)
	 */
	public EBook getEBook(String title) {
		logger.info("get EBook with title: "+title);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(EBook.class);
		crit.add(Expression.eq("title", title));

		EBook eBook = (EBook)crit.uniqueResult();
		return eBook;
	}

	/* (non-Javadoc)
	 * @see org.insia.eLibrary.dao.EBookDao#getEBooks()
	 */
	public List<EBook> getEBooks() {
		logger.info("get list of EBooks");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (ArrayList<EBook>)session.createQuery("From EBook").list();
	}

	/* (non-Javadoc)
	 * @see org.insia.eLibrary.dao.EBookDao#updateEBook(org.insia.eLibrary.model.EBook)
	 */
	public EBook updateEBook(EBook eBook) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.update(eBook);
		return eBook;
	}

}
