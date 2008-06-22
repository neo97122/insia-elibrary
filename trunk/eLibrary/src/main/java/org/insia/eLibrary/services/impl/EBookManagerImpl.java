package org.insia.eLibrary.services.impl;

import java.util.List;

import org.insia.eLibrary.dao.EBookDao;
import org.insia.eLibrary.model.EBook;
import org.insia.eLibrary.model.Media;
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
	public ActionMessage createEBook(Media media, String url) {
    	logger.info("verifions que cet eBook n'existe pas deja");
		EBook eBook = eBookDao.getEBookByReference(media.getReference());
		if (eBook != null){
			logger.info("Le ebook "+ media.getTitle() + " existe d�j�");
			return new ActionMessage("Cr�ation de ce ebook impossible",Crud.ALREADY);
		}else{
			eBook = new EBook(media, url);
			eBook = eBookDao.createEBook(eBook);
			logger.info("Le book "+eBook.getTitle()+" a �t� cr�� avec succ�s");
			return new ActionMessage();
		}
	}

	public ActionMessage deleteEBook(int id) {
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

}
