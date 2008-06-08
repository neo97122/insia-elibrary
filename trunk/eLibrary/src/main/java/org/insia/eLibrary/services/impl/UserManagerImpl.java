package org.insia.eLibrary.services.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.insia.eLibrary.dao.UserDao;
import org.insia.eLibrary.model.User;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.UserManager;
import org.insia.eLibrary.services.base.BaseManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@WebService
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class UserManagerImpl extends BaseManager implements UserManager{


	private UserDao userDao = null;

	/**
     * injection du dao par la fabrique
     * @param userDao  : une implementation de UserDAO
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * Check if the login exists and if the password is correct.
     * @param login : user login
     * @param password : user password
     * @return true if the login exists and if the password is correct.
     * Otherwise, return false.
     *
     * @see org.apache.tutorial.tapestrySpringHibernate.services.UserManager#checkLogin(java.lang.String, java.lang.String)
     */
    public boolean checkLogin (String login, String password) {
        return userDao.checkLogin(login, password);
    }

    /**
     *
     * Change le password pour l'utilisateur titulaire du login
     * @param login : user login
     * @param password : user new password
     * @return the new  object
     *
     * @see org.apache.tutorial.tapestrySpringHibernate.services.UserManager#changePassword(java.lang.String, java.lang.String)
     *
     */
    @Transactional(readOnly=false)
    public User changePassword(String login, String password) {
        User user = userDao.getUser(login);
        if (user != null) {
            user.setPassword(password);
        }
        return user;
    }

    /**
	 *
     * Return a User object from a given login.
     * @param login : user login
     * @return  Le user coreespondant � cet objet
     *
     * @see org.apache.tutorial.tapestrySpringHibernate.services.UserManager#getUser(java.lang.String)
     */
    @SuppressWarnings("finally")
    public User getUser(String login) {
        return userDao.getUser(login);
    }


    /**
	 * retourne la liste des utilisateurs
	 * @return la liste des utilisateurs dans la base
	 *
	 * @see org.apache.tutorial.tapestrySpringHibernate.services.UserManager#getUsers()
	 */
    @WebMethod
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	/**
     * Cree un user avec login et password
     * mais s'assure que ce User n'existe pas d�j�
     *
     * @param login le login du user que l'on veut cr�er
     * @param password le password du user que l'on veut cr�er
     * @return  traduisant la r�ussite ou l'�chec de cette op�ration
     *
	 * @see org.apache.tutorial.tapestrySpringHibernate.services.UserManager#createUser(java.lang.String, java.lang.String)
	 */
	 @Transactional(readOnly=false)
	public ActionMessage createUser(String login, String password) {
		logger.info("verifions que ce user n'existe pas deja");
		User user = userDao.getUser(login);
		if (user!=null){
			logger.info("L'utilisateur "+ login + " existe d�j�");
			return new ActionMessage("Cet utilisateur existe d�j�",Crud.ALREADY);
		}else{
			user = new User(login,password);
			//Right right = rightDao.saveRight(getBasicRight());
			//user.addRight(getBasicRight());
			//user.addRight(getAdminRight());
			user = userDao.createUser(user);
			logger.info("L'utilisateur "+login+" a �t� cr�� avec succ�s");
			return new ActionMessage();
		}
	}

	/**
     * Supprime le user ayant pour login 'login'
     * @param login le login du user que l'on veut supprimer
     * @return traduisant la r�ussite ou l'�chec de cette op�ration
     *
	 * @see org.apache.tutorial.tapestrySpringHibernate.services.UserManager#deleteUser(java.lang.String)
	 */
	@Transactional(readOnly=false)
	public ActionMessage deleteUser(String login) {
		logger.info("verifions que ce user existe bien");
		User user = userDao.getUser(login);
		if (user!=null){
			logger.info("L'utilisateur "+ login + " existe bien on peut le supprimer");
			userDao.deleteUser(user);
			return new ActionMessage();
		}else{
			logger.info("L'utilisateur "+login+" n'existe pas on ne peut pas le supprimer ");
			return new ActionMessage("L'utilisateur "+login+" n'existe pas on ne peut pas le supprimer ",Crud.IMPOSSIBLE);
		}
	}
}
