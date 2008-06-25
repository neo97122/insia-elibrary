package org.insia.eLibrary.test.service;

import java.util.List;

import org.insia.eLibrary.model.Book;
import org.insia.eLibrary.model.Reservation;
import org.insia.eLibrary.model.User;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.BookManager;
import org.insia.eLibrary.services.ReservationManager;
import org.insia.eLibrary.services.UserManager;
import org.insia.eLibrary.test.base.Integration;

public class ReservationManagerTest extends Integration {

	public ReservationManagerTest(String name) {
		super(name);
	}

	/**
	 * Test la r�cup�ration d'un media dans la base
	 */
	public void testGetReservation(){
		log.info("Test la récupération d'un book dans la base");
		ReservationManager reservationManager = (ReservationManager) context.getBean("reservationManager");
		Reservation reservation = reservationManager.getReservation(new Long(1));
		assertEquals(reservation.getMedia().getTitle(),"toto");
		assertEquals(reservation.getUser().getLogin(),"mbride");
	}

	/**
	 * Test l'ajout d'un nouveau media
	 */
	public void testCreateReservation(){

		UserManager userManager = (UserManager) context.getBean("userManager");
		BookManager bookManager = (BookManager) context.getBean("bookManager");
		ReservationManager reservationManager = (ReservationManager) context.getBean("reservationManager");

		log.info("Test la création d'une réservation dans la base");

		User user1 = userManager.getUser("mbride");
		User user2 = userManager.getUser("dlao");
		Book book = bookManager.getBook("tarzan");

		ActionMessage actionMessage = reservationManager.createReservation(user1.getId().intValue(), book.getId().intValue());
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);

		actionMessage = reservationManager.createReservation(user2.getId().intValue(), book.getId().intValue());
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);
	}

	/**
	 * Test le listage des medias
	 */
	public void testGetReservations(){
		log.info("Test le listing des reservations");
		ReservationManager reservationManager = (ReservationManager) context.getBean("reservationManager");
		List<Reservation> reservations = reservationManager.getReservations();
		assertEquals(reservations.size(),1);
	}

	/**
	 * Test la suppression d'un media
	 */
	public void testDeleteReservation(){
		ReservationManager reservationManager = (ReservationManager) context.getBean("reservationManager");

		log.info("Test la supression d'une réservation");
		//Book book = bookManager.getBook("toto");

		ActionMessage actionMessage = reservationManager.deleteReservation(1);
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);

		List<Reservation> reservations = reservationManager.getReservations();
		assertEquals(reservations.size(),0);

	}
}
