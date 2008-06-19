/**
 *
 */
package org.insia.eLibrary.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.insia.eLibrary.dao.MediaDao;
import org.insia.eLibrary.model.EBook;
import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.model.Reservation;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Titi
 *
 */
public class MediaDaoImpl extends HibernateDaoSupport implements MediaDao {

	/**
	 * @see org.insia.eLibrary.dao.MediaDao#createMedia(org.insia.eLibrary.model.Media)
	 */
	public Media createMedia(Media media) {
		logger.info("Creating media with title " + media.getTitle());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.persist(media);
		return media;
	}

	/**
	 * @see org.insia.eLibrary.dao.MediaDao#deleteMedia(org.insia.eLibrary.model.Media)
	 */
	public void deleteMedia(Media media) {
		logger.info("Deleting the media " + media.getTitle());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(media);
	}

	/**
	 * @see org.insia.eLibrary.dao.MediaDao#getMedia(java.lang.String)
	 */
	public Media getMedia(String title) {
		logger.info("get Media with title: "+title);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(Media.class);
		crit.add(Expression.eq("title", title));

		Media media = (Media)crit.uniqueResult();
		return media;
	}

	/**
	 * @see org.insia.eLibrary.dao.MediaDao#getMediaByReference(java.lang.String)
	 */
	public Media getMediaByReference(String reference) {
		logger.info("get Media with reference: "+reference);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(Media.class);
		crit.add(Expression.eq("reference", reference));

		Media media = (Media)crit.uniqueResult();
		return media;
	}

	/**
	 * @see org.insia.eLibrary.dao.MediaDao#getMediaById(java.lang.String)
	 */
	public Media getMediaById(int id) {
		logger.info("get Media with id: "+id);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(Media.class);
		crit.add(Expression.eq("id", id));

		Media media = (Media)crit.uniqueResult();
		return media;
	}

	/**
	 * @see org.insia.eLibrary.dao.MediaDao#getMedias()
	 */
	public List<Media> getMedias() {
		logger.info("get list of Medias");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (ArrayList<Media>)session.createQuery("From Media").list();
	}

	/**
	 * @see org.insia.eLibrary.dao.MediaDao#updateMedia(org.insia.eLibrary.model.Media)
	 */
	public Media updateMedia(Media media) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.update(media);
		return media;
	}

}
