package modele;

public class Eveque extends Personnage {

	public Eveque() {
		super("Eveque", 5, Caracteristiques.EVEQUE);
	}
	
	// Utilisation du pouvoir par un joueur humain
	public void utiliserPouvoir() {
		if(this.getJoueur().getNom() !=null) {
			System.out.println("L'Eveque se dirige en direction de Jerusalem");
		}
	}
	
	// Utilisation du pouvoir par un avatar
	public void utiliserPouvoirAvatar() {
		if(this.getJoueur().getNom() !=null) {
			System.out.println("L'Eveque se dirige en direction de Jerusalem");
		}
	}
	// Perception des ressources spécifiques
	public void percevoirRessourcesSpecifiques() {
		int nbQuartierReligieux=0;

		if (this.getJoueur() != null) {
			Quartier[] Cite = new Quartier[this.getJoueur().nbQuartiersDansCite()];

			for (int i = 0; i < Cite.length; i++) {
				for (int j = 0; j < this.getJoueur().getCite().length; j++) {
					Cite[i] = this.getJoueur().getCite()[i];
				}
			}
			for (int k = 0; k < Cite.length; k++) {
				if (Cite[k].getType() == "RELIGIEUX") {
					nbQuartierReligieux++;
				}
			}
			this.getJoueur().ajouterPieces(nbQuartierReligieux);
		}
	}
}
