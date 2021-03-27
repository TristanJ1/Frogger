package gameCommons;

import java.awt.Color;
import java.util.Date;
import java.util.Random;
import javax.swing.Timer;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public boolean loose = false;
	public double startTime;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;
	private Timer timer;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}
	
	


	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) { //
		this.frog = frog;
	}
///////////////////////////
	public IFrog getFrog() {
		return this.frog;
	}
	//////////////////////
	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}
	
	
	
	public void setTimer(Timer timer) {
		this.timer=timer;
	}
	
	public void setstartTime(double t) {
		this.startTime=t;
	}
	

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
	
		if(!environment.isSafe(this.frog.getPosition()) || this.frog.getPosition().absc<0 || this.frog.getPosition().absc>this.width-1 || this.frog.getScore()<0) {
			int s = this.getFrog().getScore()+this.getFrog().getNbBonus();
			timer.stop();
			double t = ((new Date()).getTime() - startTime)*0.001;
			t=(double)Math.round(t * 10) / 10;                         //arrondi au dixi�me
			graphic.endGameScreen("You loose, your score: " + s + " pts" + ", game duration : " + t + " s");
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		
		if(environment.isWinningPosition(frog.getPosition())) {
			int s = this.getFrog().getScore()+this.getFrog().getNbBonus();
			timer.stop();
			double t = ((new Date()).getTime() - startTime)*0.001;
			t=(double)Math.round(t * 10) / 10;                         //arrondi au dixi�me
			graphic.endGameScreen("you win, game duration : " + t + " s");
			return true;
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();
		testWin();
	}
}



