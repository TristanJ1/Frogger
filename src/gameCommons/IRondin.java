package gameCommons;

import util.Case;

public interface IRondin {
	
	/**
	 * Deplace le rondin
	 */
	public void Move();
	
	/** Modifie l'ordonnee du rondin
	 * @param int ord l'incrementation a realiser sur l'ordonnee du rondin
	 */
	public void ChangeCaseOrd(int ord);
	
	/** Indique la position du rondin
	 * @return la case du rondin
	 */
	public Case getPosition();
	
	/**
	 * Indique la longueur du rondin
	 * @return la longueur du rondin
	 */
	public int getlength();
	
	/**
	 * Affiche le rondin
	 */
	public void addToGraphics();
	
}
