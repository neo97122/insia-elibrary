package org.insia.eLibrary.model;

import static org.hibernate.annotations.CascadeType.ALL;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Media
{
	private Long id;
	protected String reference;
	protected String title;
	private Set<Reservation> reservations = new HashSet<Reservation>(0);

	/*public Media(String reference, String title){
		this.reference = reference;
		this.title = title;
	}*/

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "media")
	public Set<Reservation> getReservations()
	{
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations)
	{
		this.reservations = reservations;
	}

}
