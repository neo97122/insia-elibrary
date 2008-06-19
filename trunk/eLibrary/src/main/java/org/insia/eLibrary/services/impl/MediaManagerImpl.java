/**
 *
 */
package org.insia.eLibrary.services.impl;

import java.util.List;

import org.insia.eLibrary.dao.MediaDao;
import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.MediaManager;
import org.insia.eLibrary.services.base.BaseManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Titi
 *
 */

@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class MediaManagerImpl extends BaseManager implements MediaManager {

	private MediaDao mediaDao = null;

	/**
     * injection du dao par la fabrique
     * @param userDao  : une implementation de ReservationDao
     */
    public void setMediaDao(MediaDao mediaDao) {
        this.mediaDao = mediaDao;
    }

	/**
	 * @see org.insia.eLibrary.services.MediaManager#createMedia(java.lang.String, java.lang.String)
	 */
    @Transactional(readOnly=false)
	public ActionMessage createMedia(String reference, String title) {
		logger.info("verifions que cet reservation n'existe pas deja");
		Media media = mediaDao.getMediaByReference(reference);
		if (media != null){
			logger.info("Le media "+ media.getTitle() + " existe déjà");
			return new ActionMessage("Création de ce média impossible",Crud.ALREADY);
		}else{
			media = new Media(reference, title);
			media = mediaDao.createMedia(media);
			logger.info("Le média "+media.getTitle()+" a été créé avec succès");
			return new ActionMessage();
		}
	}

	/**
	 * @see org.insia.eLibrary.services.MediaManager#deleteMedia(int)
	 */
	public ActionMessage deleteMedia(int id) {
		logger.info("verifions que ce media existe bien");
		Media media = mediaDao.getMediaById(id);
		if (media!=null){
			logger.info("Le media "+ id + " existe bien on peut le supprimer");
			mediaDao.deleteMedia(media);
			return new ActionMessage();
		}else{
			logger.info("Le média "+id+" n'existe pas on ne peut pas le supprimer ");
			return new ActionMessage("Le média "+id+" n'existe pas on ne peut pas le supprimer ",Crud.IMPOSSIBLE);
		}
	}

	/**
	 * @see org.insia.eLibrary.services.MediaManager#getMedia(java.lang.String)
	 */
	@SuppressWarnings("finally")
	public Media getMedia(String title) {
		return mediaDao.getMedia(title);
	}

	/**
	 * @see org.insia.eLibrary.services.MediaManager#getMedias()
	 */
	public List<Media> getMedias() {
		return mediaDao.getMedias();
	}

}
