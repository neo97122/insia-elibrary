package org.insia.eLibrary.dao;

import java.util.List;

import org.insia.eLibrary.model.EBook;

public interface EBookDao {

	public EBook getEBook(String title);

	public EBook getEBookByReference(String reference);

	public EBook getEBookById(long id);

	public List<EBook> getEBooks();

	public void deleteEBook(EBook eBook);

	public EBook createEBook(EBook eBook);

	public EBook updateEBook(EBook eBook);
}
