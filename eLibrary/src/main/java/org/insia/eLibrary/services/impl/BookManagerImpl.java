package org.insia.eLibrary.services.impl;

import java.util.List;

import org.insia.eLibrary.dao.BookDao;
import org.insia.eLibrary.model.Book;
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
	public ActionMessage createBook(String title,String reference, int quantity,
			String image_url, String description, String author, String editor) {
    	logger.info("verifions que ce book n'existe pas deja");
		Book book = bookDao.getBookByReference(reference);
		if (book != null){
			logger.info("Le book "+ book.getTitle() + " existe déjà");
			return new ActionMessage("Création de ce book impossible",Crud.ALREADY);
		}else{
			book = new Book(title, reference, quantity, image_url, description, author, editor);
			book = bookDao.createBook(book);
			logger.info("Le book "+book.getTitle()+" a �t� cr�� avec succ�s");
			return new ActionMessage();
		}
	}

    /*@Transactional(readOnly=false)
	public ActionMessage deleteBook(Book book) {
		logger.info("verifions que ce book existe bien");
		//Book book = bookDao.getBookById(id);
		if (book!=null){
			logger.info("Le book "+ book.getId() + " existe bien on peut le supprimer");
			bookDao.deleteBook(book);
			return new ActionMessage();
		}else{
			logger.info("Le book "+book.getId()+" n'existe pas on ne peut pas le supprimer ");
			return new ActionMessage("Le book "+book.getId()+" n'existe pas on ne peut pas le supprimer ",Crud.IMPOSSIBLE);
		}
	}*/

    @Transactional(readOnly=false)
	public ActionMessage deleteBook(long id) {
		logger.info("verifions que ce book existe bien");
		Book book = bookDao.getBookById(id);
		if (book!=null){
			logger.info("Le book "+ book.getId() + " existe bien on peut le supprimer");
			bookDao.deleteBook(book);
			return new ActionMessage();
		}else{
			logger.info("Le book "+book.getId()+" n'existe pas on ne peut pas le supprimer ");
			return new ActionMessage("Le book "+book.getId()+" n'existe pas on ne peut pas le supprimer ",Crud.IMPOSSIBLE);
		}
	}

	@Transactional(readOnly=false)
	public ActionMessage updateBook(String title,String reference, int quantity, String image_url, String description, String author, String editor) {
		Book book = bookDao.getBook(title);
		book.setReference(reference);
		book.setQuantity(quantity);
		book.setImage_url(image_url);
		book.setDescription(description);
		book.setAuthor(author);
		book.setEditor(editor);
		bookDao.updateBook(book);
		return new ActionMessage();
	}

	public Book getBook(String title) {
		return bookDao.getBook(title);
	}

	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

}
