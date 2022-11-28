package modele;

public class Marchande extends Personnage {

	public Marchande() {
		super("Marchande", 6, Caracteristiques.MARCHANDE);
	}
	
	// Utilisation du pouvoir par un joueur humain
	public void utiliserPouvoir() {
		if(this.getJoueur()!=null) {
			this.getJoueur().ajouterPieces(1);
		}
	}
	
	// Utilisation du pouvoir par un avatar
	public void utiliserPouvoirAvatar() {
		if(this.getJoueur()!=null) {
			this.getJoueur().ajouterPieces(1);
		}
	}
	
	// Perception des ressources spécifiques
	public void percevoirRessourcesSpecifiques() {
		super.percevoirRessourcesSpecifiques();
		int nbQuartierCommercant=0;
		
		if(this.getJoueur()!=null) {
			Quartier[] Cite = new Quartier[this.getJoueur().nbQuartiersDansCite()];
			for(int i =0;i<Cite.length;i++) {
				for(int j=0;j<this.getJoueur().getCite().length;j++) {
					Cite[i]=this.getJoueur().getCite()[i];
				}
			}
			for(int i =0;i<Cite.length;i++) {
				if(Cite[i].getType()=="COMMERCANT") {
					nbQuartierCommercant++;
				}
			}
			this.getJoueur().ajouterPieces(nbQuartierCommercant);
			System.out.println("["+nbQuartierCommercant+"]"+"pieces en plus dans votre trésor");
		}
	}
}
