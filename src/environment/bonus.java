package environment;

import util.Case;

import java.awt.Color;
import gameCommons.Game;
import graphicalElements.Element;

public class bonus {
	
	private Game game;
	private Case position;
	private int points;
	
	private final Color color5  = Color.YELLOW;
	private final Color color10 = Color.ORANGE;
	private final Color color20 = Color.RED;
	
	public bonus(Game game,Case c) {
		this.position = c;
		this.game = game;
		
		double r = Math.random(); //nombre entre 0 et 1
		if (r<0.5) {
			this.points = 5;
		}else if(r<0.9){
			this.points = 10;
		}else {
			this.points = 20;
		}
	}
	
	public Case getBonusPosition() {
		return this.position;
	}
	
	public int getBonusPoint() {
		return this.points;
	}
	
	public void destruction() {
		this.position = new Case(-1,-1);          //en dehors de l'écran, des cases atteignables pas la grenouille
	}
	
	public void ChangeCaseOrd(int ord) {
		this.position=new Case(position.absc,position.ord+ord);
	}
	
	public void addBonusToGraphics() {

			Color couleur = color5;
			if (this.points == 10){
		        couleur = color10;
			}
			if (this.points == 20){
				couleur = color20;
			}
			game.getGraphic()
					.add(new Element(this.position.absc, this.position.ord, couleur));
	}
}
