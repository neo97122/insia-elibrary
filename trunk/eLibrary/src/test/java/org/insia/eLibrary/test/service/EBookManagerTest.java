package org.insia.eLibrary.test.service;

import java.util.List;

import org.insia.eLibrary.model.Book;
import org.insia.eLibrary.model.EBook;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.BookManager;
import org.insia.eLibrary.services.EBookManager;
import org.insia.eLibrary.test.base.Integration;

public class EBookManagerTest extends Integration {

	public EBookManagerTest(String name) {
		super(name);
	}

	/**
	 * Test la r�cup�ration d'un media dans la base
	 */
	public void testGetEBook(){
		log.info("Test la récupération d'un ebook dans la base");
		EBookManager eBookManager = (EBookManager) context.getBean("eBookManager");
		EBook eBook = eBookManager.getEBook("toto");
		assertEquals(eBook.getTitle(),"toto");
	}

	/**
	 * Test l'ajout d'un nouveau media
	 */
	public void testCreateEBook(){
		EBookManager eBookManager = (EBookManager) context.getBean("eBookManager");

		log.info("Test la création d'un book existant déjà dans la base");
		ActionMessage actionMessage = eBookManager.createEBook("toto", "ibsntest01", "toto.jpg", "", "mbride", "mbride", "toto.pdf");
		assertEquals(actionMessage.getCrud(),Crud.ALREADY);

		log.info("Test la création d'un book n'existant pas déjà dans la base");
		actionMessage = eBookManager.createEBook("tarzan", "qlsjrob14", "tarzan.jpg", "dsq", "mbride", "mbride", "tarzan.pdf");
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);
	}

	/**
	 * Test l'update d'un ebook
	 */
	public void testUpdateEBook(){
		EBookManager eBookManager = (EBookManager) context.getBean("eBookManager");

		log.info("Test l'update d'un ebook dans la base");
		EBook eBook = eBookManager.getEBook("toto");
		eBook.setReference("tartanpion");

		ActionMessage actionMessage = eBookManager.updateEBook(eBook.getTitle(), eBook.getReference(), eBook.getImage_url(), eBook.getDescription(), eBook.getAuthor(), eBook.getEditor(), eBook.getUrl());
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);

		eBook = eBookManager.getEBook("toto");
		assertEquals(eBook.getReference(),"tartanpion");
	}

	/**
	 * Test le listage des medias
	 */
	public void testGetEBooks(){
		log.info("Test le listing des books");
		EBookManager eBookManager = (EBookManager) context.getBean("eBookManager");
		List<EBook> eBooks = eBookManager.getEBooks();
		assertEquals(eBooks.size(),1);
	}

	/**
	 * Test la suppression d'un media
	 */
	public void testDeleteEBook(){
		EBookManager eBookManager = (EBookManager) context.getBean("eBookManager");

		log.info("Test la supression d'un ebook");
		//EBook eBook = eBookManager.getEBook("toto");
		ActionMessage actionMessage = eBookManager.deleteEBook(1);
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);

		List<EBook> eBooks = eBookManager.getEBooks();
		assertEquals(eBooks.size(),0);
	}
}
