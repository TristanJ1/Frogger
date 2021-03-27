package environment;

import java.util.ArrayList;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

public class Environment implements IEnvironment {
	private Game game;
	private ArrayList<Lane> lanes = new ArrayList<>();
	
	public Environment(Game game) {
		this.game=game;
		//initialiser les voies et les voitures initiales
		Lane lane0 = new Lane(game,0,0,true,0,false);
		this.lanes.add(lane0);
		for (int l=1; l<20; l++) {
			int speed=game.randomGen.nextInt(3)+1;
			boolean leftToRight=game.randomGen.nextBoolean();
			double density=0.05;
			Lane lane = new Lane(game,density,speed,leftToRight,l,false);
			lane.initializeCars(leftToRight);
			this.lanes.add(lane);
		}
	}
	
	
	
	public void update() {
		for (int i=0; i<this.lanes.size(); i++) {
			this.lanes.get(i).update();
		}
	}
	
	public boolean isWinningPosition(Case c) {
		return (c.ord>=19);
	}

	public boolean isSafe(Case c) {
		ArrayList<Car> Cars = this.lanes.get(c.ord).getCars();
		if (Cars.size()==0) {
			return true;
		}
		for (int i=0;i<Cars.size();i++) {
			for(int j=0; j<Cars.get(i).getlength();j++) {
				if ((Cars.get(i).getCarLeftCase().absc+j == c.absc)) {
					return false;
				}
			}
		}
		return true;
	}
}
