package gameCommons;

import util.Case;

public interface IBonus {

	/**
	 * Indique la position du bonus
	 * @return la position du bonus
	 */
	public Case getBonusPosition();
	
	/**
	 * Indique le nombre de points du bonus
	 * @return le nombre de points du bonus
	 */
	public int getBonusPoint();
	
	/**
	 * Detruit le bonus
	 */
	public void destruction();
	
	/** Modifie l'ordonnee du bonus
	 * @param int ord l'incrementation a realiser sur l'ordonnee du bonus
	 */
	public void ChangeCaseOrd(int ord);
	
	/** 
	 * Affiche le bonus
	 */
	public void addBonusToGraphics();
	
}
