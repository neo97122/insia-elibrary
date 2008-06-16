package org.insia.eLibrary.dao.hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.insia.eLibrary.dao.ReservationDao;
import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.model.Reservation;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ReservationDaoImpl extends HibernateDaoSupport implements ReservationDao {

	/**
	 * {@inheritDoc}
	 */
	public Reservation createReservation(Reservation reservation) {
		logger.info("Creating reservation with id " + reservation.getId());
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.persist(reservation);
		return reservation;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteReservation(Reservation reservation) {
		logger.info("Deleting the reservation " + reservation);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(reservation);
	}

	/**
	 * {@inheritDoc}
	 */
	public Reservation getReservation(int id) {
		logger.info("get Reservation with id: "+id);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(Reservation.class);
		crit.add(Expression.eq("id", id));

		Reservation reservation = (Reservation)crit.uniqueResult();
		return reservation;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Reservation> getReservations() {
		logger.info("get list of reservations");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (ArrayList<Reservation>)session.createQuery("From Reservation").list();
	}

	/**
	 * {@inheritDoc}
	 */
	public Reservation updateReservation(Reservation reservation) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.update(reservation);
		return reservation;
	}

	/**
	 * {@inheritDoc}
	 */
	public Reservation getReservation(Media media) {
		logger.info("get Reservation with media : " + media);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// create a new criteria
		Criteria crit = session.createCriteria(Reservation.class);
		crit.add(Expression.eq("media", media));
		crit.add(Expression.isNull("returnDate"));

		Reservation reservation = (Reservation)crit.uniqueResult();
		return reservation;
	}


}
