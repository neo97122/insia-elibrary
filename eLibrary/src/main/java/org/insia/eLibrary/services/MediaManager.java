package org.insia.eLibrary.services;

import java.util.List;

import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.operations.ActionMessage;

public interface MediaManager {

	/**
	 * Return a media object from a given title.
	 * @param title : title media
	 * @return the corresponding media object
	 */
	public Media getMedia(String title);

	/**
	 * Return list of Medias
	 * @return
	 */
	public List<Media> getMedias();

	/**
	 * Delete the Media of the given id
	 * @param id
	 */
	public ActionMessage deleteMedia(int id);

	/**
	 * Create Media of the given Media object
	 * @param Media
	 * @return created Media
	 */
	public ActionMessage createMedia(String reference, String title);


}
