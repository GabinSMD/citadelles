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
			for(Quartier q: this.getJoueur().getCite()) {
			     if(q!=null&&q.getType()=="COMMERCANT") {
 					  nbQuartierCommercant++;
 	                     }
			}
			this.getJoueur().ajouterPieces(nbQuartierCommercant);
			System.out.println("["+nbQuartierCommercant+"]"+"pieces en plus dans votre trésor");
		}
	}
}
