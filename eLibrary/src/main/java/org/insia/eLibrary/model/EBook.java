package org.insia.eLibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EBook extends Media
{
	private String url;

	public EBook(){}

	public EBook(String title, String reference, String image_url, String description, String author, String editor, String url){
		this.title = title;
		this.reference = reference;
		this.description = description;
		this.image_url = image_url;
		this.editor = editor;
		this.author = author;
		this.url = url;
	}

	@Column(unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
