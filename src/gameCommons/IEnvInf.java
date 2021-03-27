package gameCommons;

import environment.Lane;
import util.Case;

public interface IEnvInf {

	/**
	 * Met a jour l'environement
	 */
	public void update();
	
	/**
	 * teste si la case ne contient pas de voiture ou d'eau
	 * @param Case c la case etudie
	 * @return true si la partie est libre, false sinon
	 */
	public boolean isSafe(Case c);
	
	/**
	 * teste si la case contient un rondin 
	 * @param Case c la case etudie
	 * @return true si la partie contient un rondin, false sinon
	 */
	public boolean isRondin(Case c);
	
	/**
	 * teste si la case contient un bonus
	 * @param Case c la case etudie
	 * @return le nombre de points du bonus,0 s'il n'y a pas de bonus
	 */
	public int isBonus(Case c);
	
	/**
	 * teste si la case contient une case speciale
	 * @param Case c la case etudie
	 * @return 0 pour de la glace,1  pour un piege, 2 pour un ralentisseur, 0 pour rien
	 */
	public int isCase_speciale(Case c);
	
	/**
	 * Indique si le joueur a atteint l'arrivee
	 * @param Case c la case de la grenouille
	 * @return true si la partie est gagnee, false sinon
	 */
	public boolean isWinningPosition(Case c);
	
	/**
	 * Cherche la voie d'ordonnee ord 
	 * @param int ord l'ordonnee
	 * @return la voie d'ordonnee ord
	 */
	public Lane find(int ord);
}
