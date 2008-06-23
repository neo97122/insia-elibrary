package org.insia.eLibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends Media
{
	private int quantity;

	public Book(){}

	public Book(String title, String reference, int quantity, String image_url, String description, String author, String editor){
		this.title = title;
		this.reference = reference;
		this.quantity = quantity;
		this.description = description;
		this.image_url = image_url;
		this.editor = editor;
		this.author = author;
	}

	@Column
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
