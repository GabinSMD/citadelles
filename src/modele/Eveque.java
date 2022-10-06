package modele;

public class Eveque extends Personnage {

	public Eveque() {
		super("Eveque", 5, Caracteristiques.EVEQUE);
	}

	public void percevoirRessourcesSpecifiques() {
		int nbQuartierReligieux=0;

		if (this.joueur != null) {
			Quartier[] Cite = new Quartier[this.joueur.nbQuartiersDansCite()];

			for (int i = 0; i < Cite.length; i++) {
				for (int j = 0; j < this.joueur.getCite().length; j++) {
					Cite[i] = this.joueur.getCite()[i];
				}
			}
			for (int k = 0; k < Cite.length; k++) {
				if (Cite[k].getType() == "RELIGIEUX") {
					nbQuartierReligieux++;
				}
			}
			this.joueur.ajouterPieces(nbQuartierReligieux);
		}
	}

}
