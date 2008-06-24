package org.insia.eLibrary.services.impl;

import java.util.List;

import org.insia.eLibrary.dao.MediaDao;
import org.insia.eLibrary.dao.ReservationDao;
import org.insia.eLibrary.dao.UserDao;
import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.model.Reservation;
import org.insia.eLibrary.model.User;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.ReservationManager;
import org.insia.eLibrary.services.base.BaseManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class ReservationManagerImpl extends BaseManager implements ReservationManager {

	private ReservationDao reservationDao = null;
	private UserDao userDao = null;
	private MediaDao mediaDao = null;

	/**
     * injection du dao par la fabrique
     * @param userDao  : une implementation de ReservationDao
     */
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public void setMediaDao(MediaDao mediaDao)
	{
		this.mediaDao = mediaDao;
	}

	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	/**
     * Cree une r�servation avec user et media
     * mais s'assure que cet reservation n'existe pas d�j�
     *
     * @param user utilisateur qui emprunte
     * @param media media � emprunter
     * @return  traduisant la r�ussite ou l'�chec de cette op�ration
     *
	 * @see org.apache.tutorial.tapestrySpringHibernate.services.ReservationManager#createReservation(org.insia.eLibrary.model.User, org.insia.eLibrary.model.Media)
	 */
	 @Transactional(readOnly=false)
	public ActionMessage createReservation(int user_id, int media_id) {

		 User user = userDao.getUserById(new Long(user_id));
		 Media media = mediaDao.getMediaById(new Long(media_id));

		logger.info("verifions que cette reservation n'existe pas deja");
		Reservation reservation = reservationDao.getReservation(media);
		if (reservation != null){
			logger.info("Le media "+ media.getTitle() + " est déjà pris");
			return new ActionMessage("Cet réservation est impossible",Crud.ALREADY);
		}else{
			reservation = new Reservation(media, user);
			reservation = reservationDao.createReservation(reservation);
			logger.info("La réservation "+reservation.getMedia().getTitle()+" a été créé avec succès");
			return new ActionMessage();
		}
	}

	@Transactional(readOnly=false)
	public ActionMessage deleteReservation(long id) {
		logger.info("verifions que cette réservation existe bien");
		Reservation reservation = reservationDao.getReservation(new Long(id));
		if (reservation!=null){
			logger.info("La réservation "+ id + " existe bien on peut la supprimer");
			reservationDao.deleteReservation(reservation);
			return new ActionMessage();
		}else{
			logger.info("La réservation "+id+" n'existe pas on ne peut pas la supprimer ");
			return new ActionMessage("La réservation "+id+" n'existe pas on ne peut pas la supprimer ",Crud.IMPOSSIBLE);
		}
	}

	/**
	 *
    * Return a Reservation object from a given id.
    * @param id : reservation id
    * @return  La r�servation coreespondant � cet objet
    *
    * @see org.apache.tutorial.tapestrySpringHibernate.services.ReservationManager#getReservation(java.lang.int)
    */
	@SuppressWarnings("finally")
	public Reservation getReservation(long id) {
		return reservationDao.getReservation(id);
	}


	/**
	 * retourne la liste des r�servations
	 * @return la liste des r�servations dans la base
	 *
	 * @see org.apache.tutorial.tapestrySpringHibernate.services.ReservationManager#getReservations()
	 */
	public List<Reservation> getReservations() {
		return reservationDao.getReservations();
	}

}
