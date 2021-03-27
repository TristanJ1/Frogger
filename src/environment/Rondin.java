package environment;

import java.awt.Color;

import graphicalElements.Element;
import util.Case;
import gameCommons.Game;

public class Rondin {

	private Game game;
	private final Color color = Color.LIGHT_GRAY;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	
	public Rondin(Game game, int length, Case leftPosition, boolean leftToRight) {
		this.game=game;
		this.leftToRight=leftToRight;
		this.leftPosition=leftPosition;
		this.length=length;
	}
	
	public void Move() {        //d�placement d'un rondin
		if (leftToRight) {
			this.leftPosition = new Case(leftPosition.absc+1,leftPosition.ord);
		}
		else {
			this.leftPosition = new Case(leftPosition.absc-1,leftPosition.ord);
		}
	}
	
	public void ChangeCaseOrd(int ord) {
		this.leftPosition=new Case(leftPosition.absc,leftPosition.ord+ord);
	}
	
	public Case getPosition() {
		return this.leftPosition;
	}
		
	public int getlength() {
		return this.length;
	}

	
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}
	
}
