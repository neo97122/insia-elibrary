package org.insia.eLibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends Media
{
	private int quantity;
	private String image_url;
	private String description;
	private String author;
	private String editor;

	@Column
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
}
