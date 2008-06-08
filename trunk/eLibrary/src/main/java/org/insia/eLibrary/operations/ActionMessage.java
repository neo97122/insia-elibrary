package org.insia.eLibrary.operations;

/**
 * @author Michael Courcy
 * Action Message est inspir� de Struts 
 * Il comporte un message eventuellement null 
 * Et un r�sultat Crud pour indiquer l'�tat de l'op�ration
 * 
 * Cet objet est renvoy� par les methodes de services 
 * pour indiquer � la partie MVC le r�sultat de la transaction
 * 
 *  Les methodes de sauvegarde suppressions ou de mise � jour 
 *  doivent renvoyer un actionMessage
 *  
 *  
 */
public class ActionMessage {
	
	/**
	 * Le constructeur par d�faut 
	 * Sans arguments on suppose que tout c'est bien pass� 
	 * et qu'il li n'y a rien a dire
	 */
	public ActionMessage(){
		this.crud = Crud.SUCCESSFUL;
		this.message = null;
	}
	
	/**
	 * Le constructeur avec seulement le crud permet 
	 * de signaler l'�tat d'une transaction sans ajouter 
	 * de message suppl�mentaire 
	 * 
	 * @param crud le Crud correspondant � la transaction
	 */
	public ActionMessage(Crud crud){
		this.crud = crud;
	}
	
	public ActionMessage(String message, Crud crud){
		this.crud = crud;
		this.message = message;
	}
	
	private  String message;
	private Crud crud;
	public Crud getCrud() {
		return crud;
	}
	public void setCrud(Crud crud) {
		this.crud = crud;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
