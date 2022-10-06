package modele;

public class Marchande extends Personnage {

	public Marchande() {
		super("Marchande", 6, Caracteristiques.MARCHANDE);
	}
	
	public void utiliserPouvoir() {
		if(this.joueur!=null) {
			this.joueur.ajouterPieces(1);
		}
	}

	public void percevoirRessourcesSpecifiques() {
		int nbQuartierCommercant=0;
		
		super.percevoirRessourcesSpecifiques();
		if(this.joueur!=null) {
			
			Quartier[] Cite = new Quartier[this.joueur.nbQuartiersDansCite()];
			
			for(int i =0;i<Cite.length;i++) {
				for(int j=0;j<this.joueur.getCite().length;j++) {
					Cite[i]=this.joueur.getCite()[i];
				}
			}
			for(int k =0;k<Cite.length;k++) {
				if(Cite[k].getType()=="COMMERCANT") {
					nbQuartierCommercant++;
				}
			}
			this.joueur.ajouterPieces(nbQuartierCommercant);
			System.out.println("["+nbQuartierCommercant+"]"+"pieces en plus dans votre trésor");
		}
	}

}
