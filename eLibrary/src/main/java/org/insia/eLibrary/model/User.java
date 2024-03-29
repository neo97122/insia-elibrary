package org.insia.eLibrary.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
public class User
{
	private Long id;
	private String login;
	private String password;
	private String name;
	private String firstname;
	private String mail;
	private boolean admin;

	private Set<Reservation> reservations = new HashSet<Reservation>(0);

	public User(){}

	public User(String login, String password)
	{
		this.login = login;
		this.password = password;
	}

	public User(String login, String password, String name, String firstname, String mail, boolean admin)
	{
		this.login = login;
		this.password = password;
		this.name =  name;
		this.firstname = firstname;
		this.mail = mail;
		this.admin = admin;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	@Column(unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
	public Set<Reservation> getReservations()
	{
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations)
	{
		this.reservations = reservations;
	}
}
