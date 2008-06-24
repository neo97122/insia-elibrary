package org.insia.eLibrary.services.impl;

import java.util.List;

import org.insia.eLibrary.dao.EBookDao;
import org.insia.eLibrary.model.EBook;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.EBookManager;
import org.insia.eLibrary.services.base.BaseManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class EBookManagerImpl extends BaseManager implements EBookManager {

	private EBookDao eBookDao = null;

	/**
     * injection du dao par la fabrique
     * @param eBookDao  : une implementation de EBookDao
     */
	public void setEBookDao(EBookDao bookDao)
	{
		eBookDao = bookDao;
	}

    @Transactional(readOnly=false)
	public ActionMessage createEBook(String title,String reference,
		String image_url, String description, String author, String editor, String url) {
    	logger.info("verifions que cet ebook n'existe pas deja");
		EBook eBook = eBookDao.getEBookByReference(reference);
		if (eBook != null){
			logger.info("Le book "+ eBook.getTitle() + " existe déjà");
			return new ActionMessage("Création de ce book impossible",Crud.ALREADY);
		}else{
			eBook = new EBook(title, reference, image_url, description, author, editor, url);
			eBook = eBookDao.createEBook(eBook);
			logger.info("Le ebook "+eBook.getTitle()+" a été créé avec succès");
			return new ActionMessage();
		}
	}

    @Transactional(readOnly=false)
	public ActionMessage deleteEBook(long id) {
		logger.info("verifions que ce ebook existe bien");
		EBook eBook = eBookDao.getEBookById(id);
		if (eBook!=null){
			logger.info("Le ebook "+ id + " existe bien on peut le supprimer");
			eBookDao.deleteEBook(eBook);
			return new ActionMessage();
		}else{
			logger.info("Le ebook "+id+" n'existe pas on ne peut pas le supprimer ");
			return new ActionMessage("Le ebook "+id+" n'existe pas on ne peut pas le supprimer ",Crud.IMPOSSIBLE);
		}
	}

	public EBook getEBook(String title) {
		return eBookDao.getEBook(title);
	}

	public List<EBook> getEBooks() {
		return eBookDao.getEBooks();
	}

	@Transactional(readOnly=false)
	public ActionMessage updateEBook(String title, String reference, String image_url, String description, String author, String editor, String url)
	{
		EBook eBook = eBookDao.getEBook(title);
		eBook.setReference(reference);
		eBook.setImage_url(image_url);
		eBook.setDescription(description);
		eBook.setAuthor(author);
		eBook.setEditor(editor);
		eBook.setUrl(url);
		eBookDao.updateEBook(eBook);
		return new ActionMessage();
	}

}
