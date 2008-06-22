package org.insia.eLibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EBook extends Media
{
	private String url;

	public EBook(Media media, String url){
		//super(media.getReference(), media.getTitle());
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
