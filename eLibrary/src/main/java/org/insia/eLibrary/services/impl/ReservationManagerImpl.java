package org.insia.eLibrary.services.impl;

import java.util.List;

import org.insia.eLibrary.dao.ReservationDao;
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

	/**
     * injection du dao par la fabrique
     * @param userDao  : une implementation de ReservationDao
     */
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
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
	public ActionMessage createReservation(User user, Media media) {
		 logger.info("verifions que cet reservation n'existe pas deja");
			Reservation reservation = reservationDao.getReservation(media);
			if (reservation != null){
				logger.info("Le media "+ media.getTitle() + " est d�j� pris");
				return new ActionMessage("Cet r�servation est impossible",Crud.ALREADY);
			}else{
				reservation = new Reservation(media, user);
				reservation = reservationDao.createReservation(reservation);
				logger.info("La r�servation "+reservation.getMedia().getTitle()+" a �t� cr��� avec succ�s");
				return new ActionMessage();
			}
	}

	public ActionMessage deleteReservation(int id) {
		logger.info("verifions que cette r�servation existe bien");
		Reservation reservation = reservationDao.getReservation(id);
		if (reservation!=null){
			logger.info("La r�servation "+ id + " existe bien on peut la supprimer");
			reservationDao.deleteReservation(reservation);
			return new ActionMessage();
		}else{
			logger.info("La r�servation "+id+" n'existe pas on ne peut pas la supprimer ");
			return new ActionMessage("La r�servation "+id+" n'existe pas on ne peut pas la supprimer ",Crud.IMPOSSIBLE);
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
	public Reservation getReservation(int id) {
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
