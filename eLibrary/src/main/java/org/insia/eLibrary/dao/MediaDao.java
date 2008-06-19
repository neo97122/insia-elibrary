package org.insia.eLibrary.dao;

import java.util.List;

import org.insia.eLibrary.model.Media;

public interface MediaDao {

	public Media getMedia(String title);

	public Media getMediaByReference(String reference);

	public Media getMediaById(int id);

	public List<Media> getMedias();

	public void deleteMedia(Media media);

	public Media createMedia(Media media);

	public Media updateMedia(Media media);
}
