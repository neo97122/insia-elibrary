package org.insia.eLibrary.services;

import java.util.List;

import org.insia.eLibrary.model.User;
import org.insia.eLibrary.operations.ActionMessage;

public interface UserManager {

	/**
     * Check if the login exists and if the password is correct.
     * @param login : user login
     * @param password : user password
     * @return true if the login exists and if the password is correct.
     * Otherwise, return false.
     */
    public boolean checkLogin (String login, String password);

    /**
     * Return a User object from a given login.
     * @param login : user login
     * @return the corresponding user object.
     */
    public User getUser(String login);

    /**
     * Change le password pour l'utilisateur titulaire du login
     * @param login : user login
     * @param password : user new password
     * @return  the new User object
     */
    public User changePassword (String login, String password);


	/**
	 * retourne la liste des utilisateurs
	 * @return la liste des user
	 */
	public List<User> getUsers();

	/**
     * Cree un user avec login et password
     * mais s'assure que ce User n'existe pas d�j�
     *
     * @param login le login du user que l'on veut cr�er
     * @param password le password du user que l'on veut cr�er
     * @return ActionMessage traduisant la r�ussite ou l'�chec de cette op�ration
     */
    public ActionMessage createUser (String login, String password, String name, String firstname, String email, boolean admin);


    /**
     * Supprime le user ayant pour login 'login'
     * @param login le login du user que l'on veut supprimer
     * @return ActionMessage traduisant la r�ussite ou l'�chec de cette op�ration
     */
    public ActionMessage deleteUser (String login);

    public ActionMessage updateUser(User user);





}
