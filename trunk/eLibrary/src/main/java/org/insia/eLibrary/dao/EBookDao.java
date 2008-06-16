package org.insia.eLibrary.dao;

import java.util.List;

import org.insia.eLibrary.model.EBook;

public interface EBookDao {

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
    public EBook getEBook(String title);

    /**
     * Return the list of EBooks
     * @return
     */
    public List<EBook> getBooks();

	/**
	 * Delete the Book of the given id
	 *
	 * @param id the id of the Book we want to delete
	 */
	public void deleteEBook(EBook ebook);

	/**
	 * Cree un Book avec le login et le mot de passe
	 *
	 * @param login
	 * @param password
	 * @return le EBook ainsi cr��.
	 */
	public EBook createBook(EBook ebook);


	/**
	 * Met � jour le EBook
	 * @param EBook
	 * @return le EBook mis � jour
	 */
	public EBook updateEBook(EBook ebook);

}
