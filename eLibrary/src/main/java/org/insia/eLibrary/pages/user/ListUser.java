package org.insia.eLibrary.pages.user;

import java.util.List;

import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Service;
import org.apache.tapestry.beaneditor.Validate;
import org.insia.eLibrary.model.User;
import org.insia.eLibrary.services.UserManager;


public class ListUser {

	@Inject
	@Service("userManager")
	private UserManager userManager;

	private User user;

	private String login;
	private String password;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers(){
		return userManager.getUsers();
	}

	public boolean isEmptyList(){
		return userManager.getUsers().isEmpty();
	}

	public String getLogin() {
		return login;
	}

	@Validate("required")
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	@Validate("required")
	public void setPassword(String password) {
		this.password = password;
	}

	String onSuccess()
	{
		//userManager.createUser(login, password);
		return null;
	}

}
