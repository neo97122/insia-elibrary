package org.insia.eLibrary.dao.hibernate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.insia.eLibrary.dao.BookDao;
import org.insia.eLibrary.model.Book;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



/**
 * Implements a strategy to perform complex actions on persistent data.
 * @author bmeurant
 */
public class BookDaoImpl extends HibernateDaoSupport implements BookDao {

	/**
	 * {@inheritDoc}
	 */
	public Book getBook(String title) {
		logger.info("get Book with title: "+title);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Expression.eq("titleBook", title));

		Book book = (Book)crit.uniqueResult();
		return book;
	}

	public List<Book> getBooks(){
		logger.info("get list of books");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (ArrayList<Book>)session.createQuery("From Book").list();
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteBook(Book book) {
		logger.info("Deleting the Book " + book);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(book);
	}

	/**
	 * @see org.apache.tutorial.tapestrySpringHibernate.dao.BookDao#createBook(java.lang.String, java.lang.String)
	 */
	public Book createBook(Book book) {
		logger.info("Creating Book with title " + book.getTitle());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.persist(book);
		return book;
	}

	/**
	 * @see org.apache.tutorial.tapestrySpringHibernate.dao.BookDao#updateBook(org.apache.tutorial.tapestrySpringHibernate.model.Book)
	 */
	public Book updateBook(Book book) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.update(book);
		return book;
	}
}