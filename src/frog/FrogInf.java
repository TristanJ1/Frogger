package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.IFrogInf;
import util.Case;
import util.Direction;

public class FrogInf implements IFrog {
	
	private Game game;
	private Case Case;
	private Direction Direction;
	private int score=0;
	private int nbBonus= 0;

	public FrogInf(Game game) {
		this.game=game;
		this.Case=new Case(Math.toIntExact(game.width/2),1);
	}
	
	public Case getPosition() {
		return this.Case;
	}
	
	public Direction getDirection() {
		return this.Direction;
	}
	
	public void changeDirection(Direction key) {
		this.Direction=key;
	}
	
	public void move(Direction key) {
		if (key==Direction.right) {
			this.Case = new Case(this.Case.absc+1,this.Case.ord);
		}
		if (key==Direction.left) {
			this.Case = new Case(this.Case.absc-1,this.Case.ord);
		}
		if (key==Direction.up) {
			score++;
		}
		if (key==Direction.down) {
			score--;
		}
		this.Direction=key;
	}
	
	public void kill() {
		this.Case = new Case(-1,1);          //une case qui donne isSafe()=false
	}
	
	public void changeNbBonus(int x) {
		this.nbBonus+=x;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getNbBonus() {
		return this.nbBonus;
	}
}

