package org.insia.eLibrary.dao;

import java.util.List;

import org.insia.eLibrary.model.User;

public interface UserDao {

	/**
     * Check if the login exists and if the password is correct in datasource.
     * @param login : user login
     * @param password : user password
     * @return true if the login exists and if the password is correct.
     * Otherwise, return false.
     * @throws DataAccessException in case of Data access errors
     * (database unreachable, etc.)
     */
    public boolean checkLogin (String login, String password);

    /**
     * Return a User object from a given login.
     * @param login : user login
     * @return the corresponding user object.
     * @throws DataAccessException in case of Data access errors
     * (database unreachable, etc.)
     */
    public User getUser(String login);

    /**
     * Return the list of Users
     * @return
     */
    public List<User> getUsers();

	/**
	 * Delete the user of the given id
	 *
	 * @param id the id of the user we want to delete
	 */
	public void deleteUser(User user);

	/**
	 * Cree un user avec le login et le mot de passe
	 *
	 * @param login
	 * @param password
	 * @return le user ainsi cr��.
	 */
	public User createUser(User user);


	/**
	 * Met � jour le user
	 * @param user
	 * @return le user mis � jour
	 */
	public User updateUser(User user);

}
