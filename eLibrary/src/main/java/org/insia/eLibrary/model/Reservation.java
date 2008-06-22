package org.insia.eLibrary.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation
{
	private Long id;
	private User user;
	private Media media;
	private Date outDate;
	private Date returnDate;

	public Reservation(){}

	public Reservation (Media media, User user){
		this.media = media;
		this.user = user;
		this.outDate = new Date();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable=false)
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Column
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	@Column
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
