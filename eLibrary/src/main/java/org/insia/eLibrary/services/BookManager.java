package org.insia.eLibrary.services;

import java.util.List;

import org.insia.eLibrary.model.Book;
import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.model.Reservation;
import org.insia.eLibrary.model.User;
import org.insia.eLibrary.operations.ActionMessage;

public interface BookManager {

	/**
	 * Return a Book object from a given title.
	 * @param title : title book
	 * @return the corresponding book object
	 */
	public Book getBook(String title);

	/**
	 * Return list of books
	 * @return
	 */
	public List<Book> getBooks();

	/**
	 * Delete the books of the given id
	 * @param id
	 */
	public ActionMessage deleteBook(int id);

	/**
	 * Create book of the given book object
	 * @param book
	 * @return created book
	 */
	public ActionMessage createBook(Media media, int quantity, String image_url, String description, String author, String editor);

}
