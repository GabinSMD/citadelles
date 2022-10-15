package modele;

import controleur.Interaction;

public class Condottiere extends Personnage {

	public Condottiere() {
		super("Condotierre", 8, Caracteristiques.CONDOTTIERE);
	}

	public void utiliserPouvoir() {
		int max = 0;
		if (this.joueur != null) {
			System.out.println("Voulez-vous utiliser votre pouvoir de destruction ?");
			boolean choixPouvoir=Interaction.lireOuiOuNon();
			if (choixPouvoir == true) {
				System.out.println("Voici la liste des joueurs et le contenu de leur cité :");
				for (int i = 0; i < this.getPlateau().getNombreJoueurs(); i++) {
					if (getPlateau().getJoueur(i) != null) {
						System.out.println("Voici la liste des joueurs et le contenu de leur cité :");
						System.out.println(i + " " + getPlateau().getJoueur(i).getNom() + ": ");
						for (int j = 0; j < this.getPlateau().getJoueur(j).getCite().length; j++) {
							System.out.println(i + " " + getPlateau().getJoueur(i).getCite()[j].getNom() + "(coût " + getPlateau().getJoueur(i).getCite()[j].getNom()+"), ");
						}
						System.out.println("Pour information vous avez " + this.getPlateau().getJoueur(i).nbPieces()
								+ " pièces d'or dans votre trésor");
						max++;
					}
				}
				System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire)");
				int choixJoueur = Interaction.lireUnEntier(0, max);
				System.out.println("Quel quartier choisissez-vous ?");
				boolean check = false;
				do {
					int choixQuartier = Interaction.lireUnEntier(0, max);
					int verifCoutQuartier = this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout();
					if (verifCoutQuartier - 1 > this.getJoueur().nbPieces()) {
						System.out.println("Votre trésor n'est pas suffisant");
						System.out.println("Votre choix ?");
						check = false;
					} else {
						check = true;
					}
				} while (check == false);
			}
		}
	}

	public void percevoirRessourcesSpecifiques() {
		int nbQuartierMilitaire = 0;

		super.percevoirRessourcesSpecifiques();
		if (this.joueur != null) {

			Quartier[] Cite = new Quartier[this.joueur.nbQuartiersDansCite()];

			for (int i = 0; i < Cite.length; i++) {
				for (int j = 0; j < this.joueur.getCite().length; j++) {
					Cite[i] = this.joueur.getCite()[i];
				}
			}
			for (int k = 0; k < Cite.length; k++) {
				if (Cite[k].getType() == "MILITAIRE") {
					nbQuartierMilitaire++;
				}
			}
			this.joueur.ajouterPieces(nbQuartierMilitaire);
			System.out.println("[" + nbQuartierMilitaire + "]" + "pieces en plus dans votre trésor");
		}
	}

}
