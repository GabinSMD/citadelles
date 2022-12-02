package modele;

import java.util.Random;

import controleur.Interaction;

public class Condottiere extends Personnage {

	private int choix=0;
	private int max = 0;
	private Personnage[] selection = new Personnage[9];

	public Condottiere() {
		super("Condotierre", 8, Caracteristiques.CONDOTTIERE);
	}

	// Utilisation du pouvoir par un joueur humain
	public void utiliserPouvoir() {
		int choixJoueur;
		int	choixQuartier;
		String nomQuartier ="";
		boolean choix;
		System.out.println("Voulez-vous utiliser votre pouvoir de destruction ?");
		choix=Interaction.lireOuiOuNon();
		if (choix) {
			System.out.println("Voici maintenant la liste des victimes potentielles :");
			for(int i=0; i<9; i++) {
				if(this.getPlateau().getPersonnage(i)!= null) {
					System.out.print("\n"+ (i + 1) + " " + getPlateau().getJoueur(i).getNom() + ": ");
					if(this.getPlateau().getJoueur(i).getPersonnage() != null) {
					for (int j = 0; j < this.getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
						System.out.print(j + 1 + " " + getPlateau().getJoueur(i).getCite()[j].getNom() + "(coût "+ getPlateau().getJoueur(i).getCite()[j].getCout() + "), ");
					}
					max++;
					}
				}
			}
			do {
				System.out.println("\nQui ciblez-vous ?( 0 pour annuler )");
				choixJoueur =Interaction.lireUnEntier(0, max+1);
				if(choixJoueur==0) {
					System.out.println("Vous n'utilisez pas votre pouvoir de destruction");
					break;
				}else if(this.getPlateau().getJoueur(choixJoueur-1).getPersonnage().getNom() == "Eveque" && this.getPlateau().getJoueur(choixJoueur-1).getPersonnage().getAssassine()==false){
					System.out.println("L'évêque est vivant c'est impossible");
				}else {
					do {
						System.out.println("Quel quartier choisissez-vous ? (0 pour annuler)");
						choixQuartier = Interaction.lireUnEntier(0,getPlateau().getJoueur(choixJoueur-1).nbQuartiersDansCite()+1);
						if(choixQuartier==0) { // L'index 0 correspond à la liberté de ne pas continuer
							break;
						}else if((this.getPlateau().getJoueur(choixJoueur-1).getCite()[choixQuartier-1].getCout() - 1) > this.getJoueur().nbPieces()) {
							System.out.println("Pas assez d'argent pour détruire ce quartier");
						}
					}while(this.getPlateau().getJoueur(choixJoueur-1).getCite()[choixQuartier-1] == null || (this.getPlateau().getJoueur(choixJoueur-1).getCite()[choixQuartier-1].getCout() - 1) > this.getJoueur().nbPieces()); //tourne tant que le quartier n'est pas achetable
					if(choixQuartier==0) {//porte de sortie au cas ou il sort avant d'avoir choisie un quartier
						System.out.println("Vous n'utilisez pas votre pouvoir de destruction");
						break;
					} 
					choixQuartier -= 1;
					nomQuartier = this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getNom();
					int verifCoutQuartier = this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout();
					if (verifCoutQuartier - 1 > this.getJoueur().nbPieces()) {
						System.out.println("Votre trésor n'est pas suffisant");
						System.out.println("Votre choix ? (0 pour annuler)");
					} else {
						if(nomQuartier != "Donjon") {
							this.getJoueur().retirerPieces(verifCoutQuartier - 1);
							this.getPlateau().getJoueur(choixJoueur).retirerQuartierDansCite(nomQuartier);
							System.out.println("=> On retire  " + nomQuartier + " à " + this.getPlateau().getJoueur(choixJoueur).getNom());
							System.out.println("Pour information vous avez " + this.getJoueur().nbPieces() + " pièces d'or dans votre trésor");
						}else {
							System.out.println("Vous ne pouvez pas détruire le Donjon");
						}
					}
					nomQuartier = this.getPlateau().getJoueur(choixJoueur-1).getCite()[choixQuartier-1].getNom();
					this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choixJoueur-1).getCite()[choixQuartier-1].getCout() - 1);
					this.getPlateau().getJoueur(choixJoueur-1).retirerQuartierDansCite(nomQuartier);
					System.out.println(this.getPlateau().getJoueur(choixJoueur-1).getNom()+" votre quartier : " + nomQuartier + " à  été détruit par le Condottiere");
				}
				
				
			}while(this.getPlateau().getJoueur(choixJoueur-1).retirerQuartierDansCite(nomQuartier) != null || this.getPlateau().getJoueur(choixJoueur-1).getPersonnage().getNom() == "Eveque" && this.getPlateau().getJoueur(choixJoueur-1).getPersonnage().getAssassine()==false);
			
				}else {System.out.println("Vous n'utilisez pas votre pouvoir de destruction");}
			}
	
	// Utilisation du pouvoir par un avatar
	public void utiliserPouvoirAvatar() {
		Random r = new Random();
		int choixJoueur;
		int	choixQuartier;
		String nomQuartier ="";
		// Incrémentation de la liste des personnages selectionnables
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getPersonnage(i)!= null) {
				selection[i]=this.getPlateau().getPersonnage(i);
				max++;
			}
		}
		choix = r.nextInt(2);
		switch (choix) {
			case 0:
				System.out.println("Le condotiere n'utilise pas son pouvoir de destruction");
				break;
			case 1:
				System.out.println("Le condotiere utilise son pouvoir de destruction");
				choixJoueur = r.nextInt(max+1);// L'index max+1 correspond à la liberté de ne pas continuer
				if (choixJoueur == max+1) {
					System.out.println("Le pouvoir échoue");
					break;
				}else if(this.getPlateau().getJoueur(choixJoueur).getPersonnage().getNom() == "Eveque" && this.getPlateau().getJoueur(choixJoueur).getPersonnage().getAssassine()==false) {
					break;
				}
				else if(this.getPlateau().getJoueur(choixJoueur) != null){
					do {
						choixQuartier = r.nextInt(getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite()+1);
						if(choixQuartier==getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite()+1) { // L'index getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite()+1 correspond à la liberté de ne pas continuer
							System.out.println("Le pouvoir échoue");
							break;
						}
					}while(this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier] == null ||(this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout() - 1) > this.getJoueur().nbPieces()); //tourne tant que le quartier n'est pas achetable
					nomQuartier = this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getNom();
					this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout() - 1);
					this.getPlateau().getJoueur(choixJoueur).retirerQuartierDansCite(nomQuartier);
					System.out.println(this.getPlateau().getJoueur(choixJoueur).getNom()+" votre quartier : " + nomQuartier + " à  été détruit par le Condottiere");
				}
				break;
			default:
				break;
		}
	}
	// Perception des ressources spécifiques
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
