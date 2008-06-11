package org.insia.eLibrary.test.service;

import java.util.List;

import org.insia.eLibrary.model.User;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.UserManager;
import org.insia.eLibrary.test.base.Integration;

public class UserManagerTest extends Integration{



	public UserManagerTest(String name) {
		super(name);
	}

	/**
	 * Test la r�cup�ration d'un User dans la base
	 */
	public void testGetUser(){
		log.info("Test la r�cup�ration d'un User dans la base");
		UserManager userManager = (UserManager) context.getBean("userManager");
		User user = userManager.getUser("test");
		assert user.getPassword().equals("test");
	}

	/**
	 * Test l'ajout d'un nouvel utilisateur
	 */
	public void testCreateUser(){
		log.info("Test la cr�ation d'un user existant d�j� dans la base");
		UserManager userManager = (UserManager) context.getBean("userManager");
		ActionMessage actionMessage = userManager.createUser("test", "test");
		assert actionMessage.getCrud().equals(Crud.ALREADY);
		log.info("Test la cr�ation d'un user n'existant pas d�j� dans la base");
		actionMessage = userManager.createUser("test14", "test");
		assert actionMessage.getCrud().equals(Crud.SUCCESSFUL);
	}

	/**
	 * Test le listage des users
	 */
	public void testGetUsers(){
		log.info("Test le listing des users");
		UserManager userManager = (UserManager) context.getBean("userManager");
		List<User> users = userManager.getUsers();
		assert users.size() == 1;
	}

	/**
	 * Test la suppression d'un utilisateur
	 */
	public void testDeleteUser(){
		log.info("Test la supression d'un user");
		UserManager userManager = (UserManager) context.getBean("userManager");
		ActionMessage actionMessage = userManager.deleteUser("test");
		assert actionMessage.getCrud().equals(Crud.SUCCESSFUL);
		List<User> users = userManager.getUsers();
		assert users.size() == 0;
		log.info("Tentative de suppression d'un user qui n'existait pas");
		actionMessage = userManager.deleteUser("Clooney");
		assert actionMessage.getCrud().equals(Crud.IMPOSSIBLE);
	}






}
