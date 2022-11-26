package modele;

import java.util.Random;

import controleur.Interaction;

public class Condottiere extends Personnage {

	public Condottiere() {
		super("Condotierre", 8, Caracteristiques.CONDOTTIERE);
	}

	public void utiliserPouvoir() {
		int max = 1;
		if (this.getJoueur().getNom() != null) {
			System.out.println("Voulez-vous utiliser votre pouvoir de destruction ?");
			boolean choixPouvoir = Interaction.lireOuiOuNon();
			if (choixPouvoir == true) {
				System.out.println("Voici la liste des joueurs et le contenu de leur cité :");
				int nombreJoueurs=this.getPlateau().getNombreJoueurs();
				for (int i = 0; i < nombreJoueurs; i++) {
					max++;
					if (getPlateau().getJoueur(i) != null) {
						System.out.print(i + 1 + " " + getPlateau().getJoueur(i).getNom() + ": ");
						for (int j = 0; j < this.getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
							System.out.print(j + 1 + " " + getPlateau().getJoueur(i).getCite()[j].getNom() + "(coût "
									+ getPlateau().getJoueur(i).getCite()[j].getCout() + "), ");
						}
						System.out.println("");
					}
				}
				System.out.println("Pour information vous avez " + this.getJoueur().nbPieces() + " pièces d'or dans votre trésor");
				System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire)");
				boolean checkEveque = false;
				int choixJoueur;
				do {
					choixJoueur = Interaction.lireUnEntier(0, max+1);
					if (choixJoueur == 0) {
						break;
					}
					choixJoueur -= 1;
					if (this.getPlateau().getJoueur(choixJoueur).getPersonnage().getCaracteristiques() == Caracteristiques.EVEQUE) {

						if (this.getPlateau().getJoueur(choixJoueur).monPersonnage.getAssassine()==true) {
							System.out.println("L'évêque a été assassiné, il est grand temps de pillé ses biens !");
							checkEveque = true;
						} else {
							checkEveque = false;
							System.out.println("Vous ne pouvez cibler l'évêque.");
							System.out.println("Votre choix ? (0 pour annuler)");
						}
					} else {
						checkEveque = true;
					}
				} while(checkEveque == false);
				System.out.println("Quel quartier choisissez-vous ? (0 pour annuler)");
				boolean check = false;
				do {
					int choixQuartier = Interaction.lireUnEntier(0, getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite()+1);
					if (choixQuartier == 0) {
						break;
					} 
					choixQuartier -= 1;
					String nomQuartier = this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getNom();
					int verifCoutQuartier = this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout();
					if (verifCoutQuartier - 1 > this.getJoueur().nbPieces()) {
						System.out.println("Votre trésor n'est pas suffisant");
						System.out.println("Votre choix ? (0 pour annuler)");
						check = false;
					} else {
						check = true;
						if(nomQuartier != "Donjon") {
							this.getJoueur().retirerPieces(verifCoutQuartier - 1);
							this.getPlateau().getJoueur(choixJoueur).retirerQuartierDansCite(nomQuartier);
							System.out.println("=> On retire  " + nomQuartier + " à " + this.getPlateau().getJoueur(choixJoueur).getNom());
							System.out.println("Pour information vous avez " + this.getJoueur().nbPieces() + " pièces d'or dans votre trésor");
						}else {
							System.out.println("Vous ne pouvez pas détruire le Donjon");
						}
					}
				} while (check == false);
			}
		}
	}
	
	public void utiliserPouvoirAvatar() {
		int max = 1;
		if (this.getJoueur().getNom() != null) {
			Random r = new Random();
			int choix = r.nextInt(2);
			System.out.println("Le choix d'utilisation du pouvoir par l'avatar : "+choix);
			boolean choixPouvoir=false;
			switch (choix) {
			case 0:
				choixPouvoir=false;
				System.out.println("Le condotiere n'utilise pas son pouvoir de destruction");
				break;
				
			case 1:
				choixPouvoir=true;
				System.out.println("Le condotiere utilise son pouvoir de destruction");
				break;
			}			
			if (choixPouvoir == true) {
				System.out.println("Voici la liste des joueurs et le contenu de leur cité :");
				int nombreJoueurs=this.getPlateau().getNombreJoueurs();
				for (int i = 0; i < nombreJoueurs; i++) {
					max++;
					if (getPlateau().getJoueur(i) != null) {
						System.out.print(i + 1 + " " + getPlateau().getJoueur(i).getNom() + ": ");
						for (int j = 0; j < this.getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
							System.out.print(j + 1 + " " + getPlateau().getJoueur(i).getCite()[j].getNom() + "(coût "
									+ getPlateau().getJoueur(i).getCite()[j].getCout() + "), ");
						}
						System.out.println("");
					}
				}
				System.out.println("Pour information vous avez " + this.getJoueur().nbPieces() + " pièces d'or dans votre trésor");
				System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire)");
				boolean checkEveque = false;
				Random r2 = new Random();
				int choixJoueur;
				boolean check = false;
				do {
					choixJoueur = r2.nextInt(0, max);
					System.out.println("Le choix du Joueur par l'avatar : "+choixJoueur);
					if (choixJoueur == 0) {
						check=true;
						break;
					}
					choixJoueur -= 1;
					if (this.getPlateau().getJoueur(choixJoueur).getPersonnage().getCaracteristiques() == Caracteristiques.EVEQUE) {

						if (this.getPlateau().getJoueur(choixJoueur).monPersonnage.getAssassine()==true) {
							System.out.println("L'évêque a été assassiné, il est grand temps de pillé ses biens !");
							checkEveque = true;
						} else {
							checkEveque = false;
							System.out.println("Vous ne pouvez cibler l'évêque.");
							System.out.println("Votre choix ? (0 pour annuler)");
						}
					} else {
						checkEveque = true;
					}
				} while(checkEveque == false);

				Random r3 = new Random();
				while (check == false) {
					System.out.println("Quel quartier choisissez-vous ? (0 pour annuler)");
					int choixQuartier = r3.nextInt(0, getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite()+1);
					System.out.println("Le choix du Quartier par l'avatar : "+choixQuartier);
					if (choixQuartier == 0) {
						break;
					} 
					choixQuartier -= 1;
					String nomQuartier = this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getNom();
					int verifCoutQuartier = this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout();
					if (verifCoutQuartier - 1 > this.getJoueur().nbPieces()) {
						System.out.println("Votre trésor n'est pas suffisant");
						System.out.println("Votre choix ? (0 pour annuler)");
						check = false;
					} else {
						check = true;
						this.getJoueur().retirerPieces(verifCoutQuartier - 1);
						this.getPlateau().getJoueur(choixJoueur).retirerQuartierDansCite(nomQuartier);
						System.out.println("=> On retire  " + nomQuartier + " à " + this.getPlateau().getJoueur(choixJoueur).getNom());
						System.out.println("Pour information vous avez " + this.getJoueur().nbPieces() + " pièces d'or dans votre trésor");
					}
				} 
			}
		}
	}

	public void percevoirRessourcesSpecifiques() {
		int nbQuartierMilitaire = 0;

		super.percevoirRessourcesSpecifiques();
		if (this.getJoueur().getNom() != null) {

			Quartier[] Cite = new Quartier[this.getJoueur().nbQuartiersDansCite()];

			for (int i = 0; i < Cite.length; i++) {
				for (int j = 0; j < this.getJoueur().getCite().length; j++) {
					Cite[i] = this.getJoueur().getCite()[i];
				}
			}
			for (int k = 0; k < Cite.length; k++) {
				if (Cite[k].getType() == "MILITAIRE") {
					nbQuartierMilitaire++;
				}
			}
			this.getJoueur().ajouterPieces(nbQuartierMilitaire);
			System.out.println("[" + nbQuartierMilitaire + "]" + "pieces en plus dans votre trésor");
		}
	}

}
