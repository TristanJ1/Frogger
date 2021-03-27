package environment;

import java.awt.Color;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.DARK_GRAY;

	public Car(Game game, int length, Case leftPosition, boolean leftToRight) {
		this.game=game;
		this.leftToRight=leftToRight;
		this.leftPosition=leftPosition;
		this.length=length;
	}

	public void Move() {        //d�placement d'une voiture
		if (leftToRight) {
			this.leftPosition = new Case(leftPosition.absc+1,leftPosition.ord);
		}
		else {
			this.leftPosition = new Case(leftPosition.absc-1,leftPosition.ord);
		}
	}
	
	public Case getCarLeftCase() {
		return leftPosition;
	}
	
	public void ChangeCaseOrd(int ord) {
		this.leftPosition=new Case(leftPosition.absc,leftPosition.ord+ord);
	}
	
	
	public int getlength() {
		return this.length;
	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
