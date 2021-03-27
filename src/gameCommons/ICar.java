package gameCommons;

import util.Case;

public interface ICar {

	/**
	 * Deplace la voiture
	 */
	public void Move();
	
	/**
	 * Indique la position du bord gauche de la voiture
	 * @return la case de la voiture la plus a gauche
	 */
	public Case getCarLeftCase();
	
	/** Modifie l'ordonnee de la voiture
	 * @param int ord l'incrementation a realiser sur l'ordonnee de la voiture
	 */
	public void ChangeCaseOrd(int ord);
	
	/**
	 * Indique la longueur de la voiture
	 * @return la longueur de la voiture
	 */
	public int getlength();
	
	/**
	 * Affiche la voiture
	 */
	public void addToGraphics();
}
