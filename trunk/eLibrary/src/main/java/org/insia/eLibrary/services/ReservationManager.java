package org.insia.eLibrary.services;

import java.sql.Date;
import java.util.List;

import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.model.Reservation;
import org.insia.eLibrary.model.User;
import org.insia.eLibrary.operations.ActionMessage;

public interface ReservationManager {
	/**
	 * Return a Reservation object from a given id.
	 * @param id : id reservation
	 * @return the corresponding reservation object
	 */
	public Reservation getReservation(long id);

	/**
	 * Return list of reservations
	 * @return
	 */
	public List<Reservation> getReservations();

	/**
	 * Delete the reservation of the given id
	 * @param reservation
	 */
	public ActionMessage deleteReservation(long id);

	/**
	 * Create reservation of the given user object
	 * @param reservation
	 * @return created reservation
	 */
	public ActionMessage createReservation(int user_id, int media_id);

	public ActionMessage updateReservation(long id, int user_id, int media_id, String outDate, String returnDate);

}
