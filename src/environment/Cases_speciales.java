package environment;

import util.Case;

import java.awt.Color;
import gameCommons.Game;
import graphicalElements.Element;

public class Cases_speciales {
		
		private Game game;
		private Case position;
		private int type; //0 = glace, 1= piege 2=ralentisseur
		private boolean exist=true;
		
		private final Color Glace  = Color.CYAN;
		private final Color Piege  = Color.MAGENTA;
		private final Color Ralentisseur  = Color.WHITE;
		private final Color Rien = Color.GRAY;

		public Cases_speciales(Game game,Case c) {
			this.position = c;
			this.game = game;
			double r=Math.random();
			if(r <0.5) {
				this.type = 0;           //Glace
			}
			if(r <0.7 && r>0.5) {
				this.type = 1;           //Piège
			}
			if(r<0.8 && r>0.7) {
				this.type = 2;           //Ralentisseur
			}
		}
		
		public Case getPosition() {
			return this.position;
		}
		
		public void ChangeCaseOrd(int ord) {
			this.position=new Case(position.absc,position.ord+ord);
		}
		
		public void destruction() {
			this.exist = false;
		}
		
		public void addToGraphics() {
			Color couleur = Glace;
			if(this.type==1) {
				couleur = Piege;
			}
			if(this.type==2) {
				couleur = Ralentisseur;
			}
			if(!this.exist) {
				couleur = Rien;
			}
				game.getGraphic()
						.add(new Element(this.position.absc, this.position.ord, couleur));
		}
		
		public int getType() {
			return this.type;
		}
}
