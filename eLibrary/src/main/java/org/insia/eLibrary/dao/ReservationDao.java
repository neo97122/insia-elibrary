package org.insia.eLibrary.dao;

import java.sql.Date;
import java.util.List;

import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.model.Reservation;

public interface ReservationDao {

	/**
	 * Return a Reservation object from a given id.
	 * @param id : id reservation
	 * @return the corresponding reservation object
	 */
	public Reservation getReservation(int id);

	/**
	 * Return a Reservation object from a given media and date.
	 * @param media : media reservation
	 * @param date: date now
	 * @return the corresponding reservation object
	 */
	public Reservation getReservation(Media media);

	/**
	 * Return list of reservations
	 * @return
	 */
	public List<Reservation> getReservations();

	/**
	 * Delete the reservation of the given id
	 * @param reservation
	 */
	public void deleteReservation(Reservation reservation);

	/**
	 * Create reservation of the given user object
	 * @param reservation
	 * @return created reservation
	 */
	public Reservation createReservation(Reservation reservation);

	/**
	 * Update reservation of the given reservation object
	 * @param reservation
	 * @return reservation updated
	 */
	public Reservation updateReservation(Reservation reservation);

}
