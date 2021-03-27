package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	/**
	 * Indique la position de la grenouille
	 * @return la case de la grenouille
	 */
	public Case getPosition();
	
	/**
	 * Indique la direction de la grenouille
	 * @return la direction de la grenouille
	 */
	public Direction getDirection();
	
	/**
	 * Deplace la grenouille dans la direction donnee
	 * @param key la direction dans laquelle il faut deplacer la grenouille 
	 */
	public void move(Direction key);
	
	/**
	 * Modifie la direction de la grenouille
	 * @param key la nouvelle direction
	 */
	public void changeDirection(Direction key);

	/**
	 * Indique le score du joueur / l'ordonnee actuelle de la grenouille 
	 * @return le score
	 */
	public int getScore();
	
	/**
	 * Rajoute des points au joueur apres avoir recupere un bonus
	 * @param le score
	 */
	public void changeNbBonus(int x);
	
	/**
	 * Indique le nombre de points bonus total du joueur 
	 * @param le nombre de points
	 */
	public int getNbBonus();
	
	/**
	 * Tue la grenouille
	 */
	public void kill();
}
