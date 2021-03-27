package gameCommons;

import java.util.ArrayList;

import environment.Car;
import environment.Cases_speciales;
import environment.Rondin;
import environment.bonus;
import util.Case;

public interface ILane {
	
	/**
	 * Ajoute des objets a la creation de la voie
	 */
	public void initializeCases_speciales();
	
	/**
	 * Met a jour la voie
	 */
	public void update();
	
	/**
	 * Indique l'ensemble des voitures se trouvant sur la voie
	 * @return un ArrayList contenant toutes les voitures de la voie
	 */
	public ArrayList<Car> getCars();
	
	/**
	 * Indique l'ensemble des rondins se trouvant sur la voie
	 * @return un ArrayList contenant toutes les rondins de la voie
	 */
	public ArrayList<Rondin> getRondins();
	
	/**
	 * Indique l'ensemble des bonus se trouvant sur la voie
	 * @return un ArrayList contenant toutes les bonus de la voie
	 */
	public ArrayList<bonus> getBonus();
	
	/**
	 * Indique l'ensemble des objets se trouvant sur la voie
	 * @return un ArrayList contenant toutes les objets de la voie
	 */
	public ArrayList<Cases_speciales> getCases_speciales();
	
	/**
	 * Indique l'ordonnee de la voie
	 * @return l'ordonnee de la voie
	 */
	public int getOrd();
	
	/**
	 * Modifie l'ordonnee de la voie
	 * @param a le nombre corespond a l'incrementation a realiser sur l'ordonnee
	 */ 
	public void changeOrd(int a);
	
	/**
	 * Ajoute des voitures a la creation de la voie
	 * 
	 * @param boolean leftToRight le sens de la voie: 1 pour de gauche a droite, 0 sinon
	 */
	public void initializeCars(boolean leftToRight);
	
	/**
	 * Ajoute des rondins a la creation de la voie
	 * 
	 * @param boolean leftToRight le sens de la voie: 1 pour de gauche a droite, 0 sinon
	 */
	public void initializeRondins(boolean leftToRight);
	
	/**
	 * Teste si la case ne contient pas de voiture
	 * @param c la case etudie
	 * @return true si la case ne contient pas de voiture, false sinon
	 */
	public boolean isSafe1(Case c);
	
	/**
	 * Teste si la case contient un rondin
	 * @param c la case etudie
	 * @return true si la case contient un rondin, false sinon
	 */
	public boolean isSafe2(Case c);
	
	/**
	 * Teste si la case contient un rondin
	 * @param c la case etudie
	 * @return true si la case contient un rondin, false sinon
	 */
	public boolean isRondin(Case c);
	
	/**
	 * Teste si la case contient un bonus
	 * @param c la case etudie
	 * @return true si la case contient un bonus, false sinon
	 */
	public boolean isBonus(Case c);
	
	/**
	 * Indique le sens de la voie
	 * @return le sens de la voie
	 */
	public boolean getLeftToRight();
	
	/**
	 * Modifie la vitesse de toutes les voitures sur la voie
	 * @param a la nouvelle vitesse
	 */
	public void SetSpeed(int s);
	
	/**
	 * Modifie le type de la voie: 0 = route, 1 = riviere 
	 * @param b le nouveau type
	 */
	public void setRiver(boolean b);
	
	/**
	 * Indique le type de la voie: 0 = route, 1 = riviere 
	 * @return le type de la voie
	 */
	public boolean getRiver();
	
	/**
	 * Affiche la voie
	 */
	public void addToGraphics();
}
