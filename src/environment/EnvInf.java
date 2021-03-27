package environment;


import java.util.ArrayList;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;
import util.Direction;

public class EnvInf implements IEnvironment{
	
	private Game game;
	private ArrayList<Lane> lanes = new ArrayList<>();
	private int ord_river;                         //ordonn�e � partir de laquelle les lanes constituent la rivi�re
	
	public EnvInf(Game game) {
		this.game=game;
		this.ord_river=game.randomGen.nextInt(20)+game.height+1;
		//initialiser les voies et les voitures
		Lane lane0 = new Lane(game,0,0,true,0,false);
		this.lanes.add(lane0);
		Lane lane1 = new Lane(game,0,0,true,1,false);
		this.lanes.add(lane1);
		for (int l=2; l<20; l++) {
			int speed=game.randomGen.nextInt(3)+1;
			boolean leftToRight=game.randomGen.nextBoolean();
			double density=0.05;
			Lane lane = new Lane(game,density,speed,leftToRight,l,false);
			lane.initializeCars(leftToRight);
			lane.initializeCases_speciales();
			this.lanes.add(lane);
		}
	}
	
	
	public void update() {
		
		if (this.game.getFrog().getDirection()==Direction.up) {
			addLane(lanes.size());
		}
		
		
		if (this.game.getFrog().getDirection()==Direction.down) {
			for (int l=0; l<lanes.size(); l++) {       //changement des ordonn�es des lignes
				lanes.get(l).changeOrd(1);
			}
		}
		
		for (int l=0; l<lanes.size(); l++) { 
			lanes.get(l).update();
		}
		
		
		
		this.game.getFrog().changeNbBonus(isBonus(this.game.getFrog().getPosition()));
		
		
		if(isCase_speciale(this.game.getFrog().getPosition()) == 0){ 
			if(this.game.getFrog().getDirection()==Direction.up) {
				//glissement sur la glace //
				addLane(lanes.size());
			}
			
			else if (this.game.getFrog().getDirection()==Direction.down) {
				for (int l=0; l<lanes.size(); l++) {       //changement des ordonn�es des lignes
					lanes.get(l).changeOrd(1);
				}
			}
			
			this.game.getFrog().move(this.game.getFrog().getDirection());
		}
	
		if(isCase_speciale(this.game.getFrog().getPosition()) == 1){   //piege
			this.game.getFrog().kill();
		}
		
		if(isCase_speciale(this.game.getFrog().getPosition()) == 2){   //ralentisseur
			
			for (int i=0;i<ord_river;i++) {
				this.lanes.get(i).SetSpeed(8);
			}
			
		}
		
		this.game.getFrog().changeDirection(Direction.neutral);
	}
	
	
	
	public boolean isWinningPosition(Case c) {
		return false;
	}
	
	
	public boolean isSafe(Case c) {          
		if (this.find(c.ord).getRiver()==false) {
			ArrayList<Car> Cars = this.lanes.get(this.game.getFrog().getScore()+1).getCars();
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
		else {
			ArrayList<Rondin> Rondins = this.lanes.get(this.game.getFrog().getScore()+1).getRondins();
			if (Rondins.size()==0) {
				return true;
			}
			for (int i=0;i<Rondins.size();i++) {
				for(int j=0; j<Rondins.get(i).getlength();j++) {
					if ((Rondins.get(i).getPosition().absc+j == c.absc)) {
						return true;
					}
				}
			}
			return false;
		}
	} 	
	

	public int isBonus(Case c) {
		ArrayList<bonus> b = this.lanes.get(this.game.getFrog().getScore()+1).getBonus();
		for (int i=0;i<b.size();i++) {
			if(b.get(i).getBonusPosition().absc == c.absc) {
				b.get(i).destruction();
				return b.get(i).getBonusPoint();
			}
		}
		return 0;
	}
	
	
	public int isCase_speciale(Case c) {
		ArrayList<Cases_speciales> g = this.lanes.get(this.game.getFrog().getScore()+1).getCases_speciales();
		for (int i=0;i<g.size();i++) {
			if(g.get(i).getPosition().absc == c.absc) {
				int t = g.get(i).getType();
				if(t==2) {         //detruit ralentisseur
					g.get(i).destruction();
					return 2;
				}
				return t;
			}
		}
		return -1;
	}
	
	
	public Lane find(int ord) {                          //trouve la lane correspondant � l'ordonn�e ord
		int y=0;
		for (int i=1; i<this.lanes.size();i++) {
			if (this.lanes.get(i).getOrd()==ord) {
				y=i;
				break;
			}
		}
		return this.lanes.get(y);
	}
	
	
	public void addLane(int count) {                                          //rajoute une lane en haut de l'�cran
		if(this.lanes.size()<ord_river) {
			int speed=game.randomGen.nextInt(3)+1;
			boolean leftToRight=game.randomGen.nextBoolean();
			double density = 0.05;
			Lane lane = new Lane(game,density,speed,leftToRight,20,false);
			lane.initializeCars(leftToRight);
			lane.initializeCases_speciales();
			this.lanes.add(lane);    //nouvelle ligne en haut
		}
		else {
			//ajouter une rivi�re
			int speed=game.randomGen.nextInt(2)+5;
			boolean leftToRight=game.randomGen.nextBoolean();
			double density = 0.9/(count-game.height);
			Lane lane = new Lane(game,density,speed,leftToRight,20,true);
			lane.initializeRondins(leftToRight);
			this.lanes.add(lane);    //nouvelle ligne en haut
		}

		for (int l=0; l<lanes.size(); l++) {       //changement des ordonn�es des lignes
			lanes.get(l).changeOrd(-1);
		}
	}
	
}
