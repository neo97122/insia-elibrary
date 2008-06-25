package org.insia.eLibrary.test.service;

import java.util.List;

import org.insia.eLibrary.model.Book;
import org.insia.eLibrary.model.EBook;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.BookManager;
import org.insia.eLibrary.services.EBookManager;
import org.insia.eLibrary.test.base.Integration;

public class BookManagerTest extends Integration {

	public BookManagerTest(String name) {
		super(name);
	}

	/**
	 * Test la r�cup�ration d'un media dans la base
	 */
	public void testGetBook(){
		log.info("Test la récupération d'un book dans la base");
		BookManager bookManager = (BookManager) context.getBean("bookManager");
		Book book = bookManager.getBook("toto");
		assertEquals(book.getTitle(),"toto");
	}

	/**
	 * Test l'ajout d'un nouveau media
	 */
	public void testCreateBook(){
		BookManager bookManager = (BookManager) context.getBean("bookManager");

		log.info("Test la création d'un book existant déjà dans la base");
		ActionMessage actionMessage = bookManager.createBook("toto", "ibsntest01", 100, "toto.jpg", "", "mbride", "mbride");
		assertEquals(actionMessage.getCrud(),Crud.ALREADY);

		log.info("Test la création d'un book n'existant pas déjà dans la base");
		actionMessage = bookManager.createBook("tarzan", "qlsjrob14", 10, "tarzan.jpg", "dsq", "mbride", "mbride");
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);
	}

	/**
	 * Test l'ajout d'un nouveau media
	 */
	public void testUpdateBook(){
		BookManager bookManager = (BookManager) context.getBean("bookManager");

		log.info("Test l'update d'un book dans la base");
		Book book = bookManager.getBook("toto");
		book.setReference("tartanpion");

		ActionMessage actionMessage = bookManager.updateBook(book.getTitle(), book.getReference(),book.getQuantity() ,book.getImage_url(), book.getDescription(), book.getAuthor(), book.getEditor());
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);

		book = bookManager.getBook("toto");
		assertEquals(book.getReference(),"tartanpion");
	}


	/**
	 * Test le listage des medias
	 */
	public void testGetBooks(){
		log.info("Test le listing des books");
		BookManager bookManager = (BookManager) context.getBean("bookManager");
		List<Book> books = bookManager.getBooks();
		assertEquals(books.size(),1);
	}

	/**
	 * Test la suppression d'un media
	 */
	public void testDeleteBook(){
		BookManager bookManager = (BookManager) context.getBean("bookManager");

		log.info("Test la supression d'un book");
		Book book = bookManager.getBook("toto");
		ActionMessage actionMessage = bookManager.deleteBook(book.getId());
		assertEquals(actionMessage.getCrud(),Crud.SUCCESSFUL);

		List<Book> books = bookManager.getBooks();
		assertEquals(books.size(),0);

	}
}
