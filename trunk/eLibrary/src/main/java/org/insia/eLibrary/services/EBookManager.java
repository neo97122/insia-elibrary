package org.insia.eLibrary.services;

import java.util.List;

import org.insia.eLibrary.model.EBook;
import org.insia.eLibrary.operations.ActionMessage;

public interface EBookManager {

	/**
	 * Return a EBook object from a given title.
	 * @param title : title ebook
	 * @return the corresponding Ebook object
	 */
	public EBook getEBook(String title);

	/**O
	 * Return list of ebooks
	 * @return
	 */
	public List<EBook> getEBooks();

	/**
	 * Delete the ebook of the given id
	 * @param id
	 */
	public ActionMessage deleteEBook(long id);

	/**
	 * Create ebook of the given ebook object
	 * @param ebook
	 * @return created ebook
	 */
	public ActionMessage createEBook(String title,String reference,
		String image_url, String description, String author, String editor, String url);

	/**
	 * Update ebook of the given ebook object
	 * @param ebook
	 * @return created ebook
	 */
	public ActionMessage updateEBook(String title,String reference,
		String image_url, String description, String author, String editor, String url);
}
