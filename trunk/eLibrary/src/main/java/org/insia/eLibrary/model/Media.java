package org.insia.eLibrary.model;

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

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Media
{
	private Long id;
	protected String reference;
	protected String title;
	protected String image_url;
	protected String description;
	protected String author;
	protected String editor;
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

	@Column(unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 120)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public String getEditor()
	{
		return editor;
	}
	public void setEditor(String editor)
	{
		this.editor = editor;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER , mappedBy = "media")
	public Set<Reservation> getReservations()
	{
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations)
	{
		this.reservations = reservations;
	}

}
