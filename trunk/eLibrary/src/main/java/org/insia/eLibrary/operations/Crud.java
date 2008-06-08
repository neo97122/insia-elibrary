package org.insia.eLibrary.operations;

/**
 * @author Michael Courcy
 * 
 * Le but de l'�numeration Crud est d'�viter la gestion 
 * des echecs transactionnels dans les cycles Crud par des exceptions.
 * En effet une exception ne doit �tre envoy� que si 
 * l'on ne peut vraiment plus rien faire. Trop souvent 
 * les exceptions sui sont envoy�es correspondent a des 
 * situations que l'on peut g�rer.  
 * 
 * Exemple : Un utilisateur aboutit sur une liste d'item 
 * qu'il peut supprimer au cas par cas en cliquant sur un lien
 * pendant qu'il prend sa d�cision un autre utilisateur aura
 * d�j� supprim� l'item sur le quel is se d�cidera lui-m�me
 * 
 * Cette situation est pr�visible et ne m�rite pas l'envoie 
 * d'une exception au contraire on renverra Crud.DELETION_ALREADY
 * qui permettra de donner les informations necessaires � l'utilisateur
 * et lui permettre de comprendre les causes de son echec
 *
 */
public enum Crud {
	
	FORBIDDEN,
	IMPOSSIBLE,
	SUCCESSFUL,
	ALREADY,
	
}
