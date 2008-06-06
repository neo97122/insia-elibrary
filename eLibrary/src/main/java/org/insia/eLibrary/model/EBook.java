package org.insia.eLibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EBook extends Media
{
	private String url;

	@Column(unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
