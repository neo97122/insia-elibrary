package org.insia.eLibrary.services.impl;

import java.util.List;

import org.insia.eLibrary.dao.BookDao;
import org.insia.eLibrary.model.Book;
import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.BookManager;
import org.insia.eLibrary.services.base.BaseManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class BookManagerImpl extends BaseManager implements BookManager {

	private BookDao bookDao = null;

	/**
     * injection du dao par la fabrique
     * @param bookDao  : une implementation de ReservationDao
     */
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional(readOnly=false)
	public ActionMessage createBook(Media media, int quantity,
			String image_url, String description, String author, String editor) {
    	logger.info("verifions que cet reservation n'existe pas deja");
		Book book = bookDao.getBookByReference(media.getReference());
		if (book != null){
			logger.info("Le book "+ media.getTitle() + " existe déjà");
			return new ActionMessage("Création de ce book impossible",Crud.ALREADY);
		}else{
			book = new Book(media, quantity, image_url, description, author, editor);
			book = bookDao.createBook(book);
			logger.info("Le book "+book.getTitle()+" a été créé avec succès");
			return new ActionMessage();
		}
	}

	public ActionMessage deleteBook(int id) {
		logger.info("verifions que ce book existe bien");
		Book book = bookDao.getBookById(id);
		if (book!=null){
			logger.info("Le book "+ id + " existe bien on peut le supprimer");
			bookDao.deleteBook(book);
			return new ActionMessage();
		}else{
			logger.info("Le book "+id+" n'existe pas on ne peut pas le supprimer ");
			return new ActionMessage("Le book "+id+" n'existe pas on ne peut pas le supprimer ",Crud.IMPOSSIBLE);
		}
	}

	public Book getBook(String title) {
		return bookDao.getBook(title);
	}

	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

}
