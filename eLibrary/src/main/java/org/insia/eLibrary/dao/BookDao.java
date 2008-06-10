package org.insia.eLibrary.dao;

import java.util.List;

import org.insia.eLibrary.model.Book;

public interface BookDao {

	/**
     * Check if the login exists and if the password is correct in datasource.
     * @param login : Book login
     * @param password : Book password
     * @return true if the login exists and if the password is correct.
     * Otherwise, return false.
     * @throws DataAccessException in case of Data access errors
     * (database unreachable, etc.)
     */
    /*
	public boolean checkLogin (String login, String password);
	*/
    /**
     * Return a Book object from a given login.
     * @param login : Book login
     * @return the corresponding Book object.
     * @throws DataAccessException in case of Data access errors
     * (database unreachable, etc.)
     */
    public Book getBook(String title);

    /**
     * Return the list of Books
     * @return
     */
    public List<Book> getBooks();

	/**
	 * Delete the Book of the given id
	 *
	 * @param id the id of the Book we want to delete
	 */
	public void deleteBook(Book book);

	/**
	 * Cree un Book avec le login et le mot de passe
	 *
	 * @param login
	 * @param password
	 * @return le Book ainsi cr��.
	 */
	public Book createBook(Book book);


	/**
	 * Met � jour le Book
	 * @param Book
	 * @return le Book mis � jour
	 */
	public Book updateBook(Book book);

}
