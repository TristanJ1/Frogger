package gameCommons;

import util.Case;

public interface ICase_speciales {
	
	/**
	 * Indique la position de l'objet
	 * @return la position de l'objet
	 */
	public Case getPosition();
	
	/**
	 * Detruit l'objet
	 */
	public void destruction();
	
	/** Modifie l'ordonnee de l'objet
	 * @param int ord l'incrementation a realiser sur l'ordonnee de l'objet
	 */
	public void ChangeCaseOrd(int ord);
	
	/** 
	 * Affiche l'objet
	 */
	public void addToGraphics();
	
	/**
	 * Indique le type de l'objet
	 * @return 0 pour la glace, 1 pour un piege, 2 pour le ralentisseur
	 */
	public int getType();
	
}
