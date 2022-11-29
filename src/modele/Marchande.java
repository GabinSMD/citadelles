package modele;

public class Marchande extends Personnage {

	public Marchande() {
		super("Marchande", 6, Caracteristiques.MARCHANDE);
	}
	
	public void utiliserPouvoir() {
		if(this.getJoueur()!=null) {
			this.getJoueur().ajouterPieces(1);
		}
	}
	//Pas de possibilité de mettre de l'aléatoire
	public void utiliserPouvoirAvatar() {
		if(this.getJoueur()!=null) {
			this.getJoueur().ajouterPieces(1);
		}
	}

	public void percevoirRessourcesSpecifiques() {
		int nbQuartierCommercant=0;
		
		super.percevoirRessourcesSpecifiques();
		if(this.getJoueur()!=null) {
			
			Quartier[] Cite = new Quartier[this.getJoueur().nbQuartiersDansCite()];
			
			for(int i =0;i<Cite.length;i++) {
				for(int j=0;j<this.getJoueur().getCite().length;j++) {
					Cite[i]=this.getJoueur().getCite()[i];
				}
			}
			for(int k =0;k<Cite.length;k++) {
				if(Cite[k].getType()=="COMMERCANT") {
					nbQuartierCommercant++;
				}
			}
			this.getJoueur().ajouterPieces(nbQuartierCommercant);
			System.out.println("["+nbQuartierCommercant+"]"+"pieces en plus dans votre trésor");
		}
	}
}
