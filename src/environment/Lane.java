package environment;

import java.awt.Color;
import java.util.ArrayList;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;
import util.Direction;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private ArrayList<Rondin> rondins = new ArrayList<>();
	private ArrayList<bonus> b= new ArrayList<>();
	private ArrayList<Cases_speciales> cs= new ArrayList<>();
	private int timeUntilNextObstacle;
	private int lengthNextObstacle;
	private boolean WatchForNewObstacle=true;
	
	private boolean leftToRight;
	private double density;
	private int tic=0;                //compteur de temps
	private boolean isRiver = false;
	private final Color river = Color.BLUE;

	public Lane(Game game, double density, int speed, boolean leftToRight, int ord, boolean river) {
		this.game=game;
		this.density=density;
		this.speed=speed;
		this.leftToRight=leftToRight;
		this.ord=ord;
		this.isRiver=river;
		
		if(game.randomGen.nextDouble()>0.95) {   //Ajout bonus
			Case c = new Case((int)(Math.random()*27),this.ord);
			bonus bon = new bonus(game,c);
			b.add(bon);
		}
	}

	
	public void initializeCases_speciales() {
		for (int i=0;i<26;i++) {         //Ajout cases sp�ciales
			if(game.randomGen.nextDouble()>0.98) {
				Case c = new Case(i,this.ord);
				Cases_speciales cases_spe = new Cases_speciales(game,c);
				cs.add(cases_spe);
			}
		}	
	}
	
	public void update() {
		tic++;



		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge
		if (tic == speed) {
			if (this.isRiver==false) {
				for (int i=0; i<this.cars.size(); i++) {
					this.cars.get(i).Move();
				}
				timeUntilNextObstacle--;
			}
			else {
				for (int i=0; i<this.rondins.size(); i++) {
					this.rondins.get(i).Move();
				}
				timeUntilNextObstacle--;
				if(this.game.getFrog().getPosition().ord==this.ord && isRondin(this.game.getFrog().getPosition())) { //deplacement grenouille sur les rondins
					if(leftToRight) {
						this.game.getFrog().move(Direction.right);
					}else {
						this.game.getFrog().move(Direction.left);
					}
				}
			}
			tic=0;
		}
		
		
		// Les voitures doivent etre ajoutees a l interface graphique meme quand
		// elles ne bougent pas
		
		
		if (this.isRiver==false) {
			for (int i=0; i<this.cs.size(); i++) {
				this.cs.get(i).addToGraphics();
			}
			for (int i=0; i<this.cars.size(); i++) {
				this.cars.get(i).addToGraphics();
			}
		}
		if (this.isRiver==true) {
			addRiverToGraphics();
			for (int i=0; i<this.rondins.size(); i++) {
				this.rondins.get(i).addToGraphics();
			}
		}
		for (int i=0; i<this.b.size(); i++) {
			this.b.get(i).addBonusToGraphics();
		}
	
		
		// A chaque tic d'horloge, une voiture (ou un rondin) peut �tre ajout�e
		if (this.isRiver==false) {
				if (leftToRight) {
					if(WatchForNewObstacle==true) {
						boolean b=mayAddObstacle();
						if(b) {
							lengthNextObstacle=game.randomGen.nextInt(4)+1;
							if(lengthNextObstacle==1) {
								Car car = new Car(game,lengthNextObstacle, getBeforeFirstCase(), leftToRight);
								this.cars.add(car);
								car.addToGraphics();
							}
							else {
								timeUntilNextObstacle=lengthNextObstacle-1;
								WatchForNewObstacle=false;
							}
						}
					}
					else {
						if (timeUntilNextObstacle==0) {
							Car car = new Car(game,lengthNextObstacle, getBeforeFirstCase(), leftToRight);
							this.cars.add(car);
							car.addToGraphics();
							WatchForNewObstacle=true;
						}
					}
				}
				
				else {
					boolean b=mayAddObstacle();
					if (b) {
						int length = game.randomGen.nextInt(4)+1;
						Car car=new Car(game, length ,getBeforeFirstCase(), leftToRight);
						this.cars.add(car);
						car.addToGraphics();
					}
				}
		}
		
		else {
			if (leftToRight) {
				if(WatchForNewObstacle==true) {
					boolean b=mayAddObstacle();
					if(b) {
						lengthNextObstacle=game.randomGen.nextInt(4)+3;
						if(lengthNextObstacle==1) {
							Rondin rondin = new Rondin(game,lengthNextObstacle, getBeforeFirstCase(), leftToRight);
							this.rondins.add(rondin);
							rondin.addToGraphics();
						}
						else {
							timeUntilNextObstacle=lengthNextObstacle-1;
							WatchForNewObstacle=false;
						}
					}
				}
				else {
					if (timeUntilNextObstacle==0) {
						Rondin rondin = new Rondin(game,lengthNextObstacle, getBeforeFirstCase(), leftToRight);
						this.rondins.add(rondin);
						rondin.addToGraphics();
						WatchForNewObstacle=true;
					}
				}
			}
			
			else {
				boolean b=mayAddObstacle();
				if (b) {
					int length = game.randomGen.nextInt(4)+3;
					Rondin rondin=new Rondin(game, length ,getBeforeFirstCase(), leftToRight);
					this.rondins.add(rondin);
					rondin.addToGraphics();
				}
			}
		}
	}
	
	

	public ArrayList<Car> getCars() {
		return this.cars;
	}
	public ArrayList<Rondin> getRondins() {
		return this.rondins;
	}
	public ArrayList<bonus> getBonus() {
		return this.b;
	}
	public ArrayList<Cases_speciales> getCases_speciales() {
		return this.cs;
	}
	
	public int getOrd() {
		return this.ord; 
	}
	
	
	public void changeOrd(int a) {
		this.ord = getOrd()+a; 
		for (int i=0; i<cars.size(); i++) {
			cars.get(i).ChangeCaseOrd(a);
		}
		for (int i=0; i<rondins.size(); i++) {
			rondins.get(i).ChangeCaseOrd(a);
		}
		for (int i=0; i<b.size(); i++) {
			b.get(i).ChangeCaseOrd(a);
		}
		for (int i=0; i<cs.size(); i++) {
			cs.get(i).ChangeCaseOrd(a);
		}
	}
	
	public void initializeCars(boolean leftToRight) {
		for (int i=0; i<26; i++) {
			if (game.randomGen.nextDouble() < density) {
				Case c = new Case (i,this.ord);
				int length = game.randomGen.nextInt(4)+1;
				Car car = new Car(game,length,c,leftToRight); 
				this.cars.add(car);
			}
		}
	}
	
	
	public void initializeRondins(boolean leftToRight) {
		for (int i=0; i<26; i++) {	
			if (game.randomGen.nextDouble() < density) {
				Case c = new Case (i,this.ord);
				int length=game.randomGen.nextInt(4)+3;
				Rondin rondin = new Rondin(game,length,c,leftToRight); 
				this.rondins.add(rondin);
			}
		}
	}
	
	
	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private boolean mayAddObstacle() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				return true;
			}
		}
		return false;
	}
	

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width-1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}
	

	
	public boolean isSafe(Case c) {
		if (isRiver==false) {
			if (cars.size()==0) {
				return true;
			}
			for (int i=0;i<cars.size();i++) {
				for(int j=0; j<cars.get(i).getlength();j++) {
					if ((cars.get(i).getCarLeftCase().absc+j == c.absc)) {
						return false;
					}
				}
			}
			return true;
		}
		else {
			if (rondins.size()==0) {
				return true;
			}
			for (int i=0;i<rondins.size();i++) {
				for(int j=0; j<rondins.get(i).getlength();j++) {
					if ((rondins.get(i).getPosition().absc+j == c.absc)) {
						return false;
					}
				}
			}
			return true;
		}
	}
	
	
	
	
	public boolean isRondin(Case c) {
		ArrayList<Rondin> rondins = this.getRondins();
		for (int i=0;i<rondins.size();i++) {
			for(int j=0; j<rondins.get(i).getlength(); j++) {
				if(rondins.get(i).getPosition().absc+j == c.absc) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean isBonus(Case c) {
		ArrayList<bonus> b = this.getBonus();
		for (int i=0;i<b.size();i++) {
			if(b.get(i).getBonusPosition().absc == c.absc) {
				return true;
			}
		}
		return false;
	}
	

	
	public boolean getLeftToRight() {
		return this.leftToRight;
	}
	
	public void SetSpeed(int s) {
		this.speed = s;
	}
	
	public void setRiver(boolean b) {
		this.isRiver=b;
	}
	
	public boolean getRiver() {
		return this.isRiver;
	}
	
	
	public void addRiverToGraphics() {
		for (int i=0; i<26; i++) {
			Color color = river;
			Case c = new Case (i,this.ord);
			if (isRondin(c)==false && isBonus(c)==false) {
				game.getGraphic()
						.add(new Element(i, this.ord, color));
			}
		}
	}
	
	
}