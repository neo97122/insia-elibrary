package org.insia.eLibrary.test.service;

import java.util.List;

import org.insia.eLibrary.model.Media;
import org.insia.eLibrary.model.User;
import org.insia.eLibrary.operations.ActionMessage;
import org.insia.eLibrary.operations.Crud;
import org.insia.eLibrary.services.MediaManager;
import org.insia.eLibrary.services.UserManager;
import org.insia.eLibrary.test.base.Integration;

public class MediaManagerTest extends Integration {

	public MediaManagerTest(String name) {
		super(name);
	}

	/**
	 * Test la récupération d'un media dans la base
	 */
	public void testGetMedia(){
		log.info("Test la récupération d'un média dans la base");
		MediaManager mediaManager = (MediaManager) context.getBean("mediaManager");
		Media media = mediaManager.getMedia("Harry Potter");
		assert media.getTitle().equals("1000");
	}

	/**
	 * Test l'ajout d'un nouveau media
	 */
	public void testCreateMedia(){
		log.info("Test la création d'un média existant déjà dans la base");
		MediaManager mediaManager = (MediaManager) context.getBean("mediaManager");
		ActionMessage actionMessage = mediaManager.createMedia("0001", "Martine");
		assert actionMessage.getCrud().equals(Crud.ALREADY);
		log.info("Test la création d'un média n'existant pas déjà dans la base");
		actionMessage = mediaManager.createMedia("0002", "Da Vinci");
		assert actionMessage.getCrud().equals(Crud.SUCCESSFUL);
	}

	/**
	 * Test le listage des medias
	 */
	public void testGetMedias(){
		log.info("Test le listing des medias");
		MediaManager mediaManager = (MediaManager) context.getBean("mediaManager");
		List<Media> medias = mediaManager.getMedias();
		assert medias.size() == 1;
	}

	/**
	 * Test la suppression d'un media
	 */
	public void testDeleteMedia(){
		log.info("Test la supression d'un media");
		MediaManager mediaManager = (MediaManager) context.getBean("mediaManager");
		ActionMessage actionMessage = mediaManager.deleteMedia(1);
		assert actionMessage.getCrud().equals(Crud.SUCCESSFUL);
		List<Media> medias = mediaManager.getMedias();
		assert medias.size() == 0;
		log.info("Tentative de suppression d'un user qui n'existait pas");
		actionMessage = mediaManager.deleteMedia(10);
		assert actionMessage.getCrud().equals(Crud.IMPOSSIBLE);
	}


}
