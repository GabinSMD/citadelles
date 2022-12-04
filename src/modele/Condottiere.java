package modele;

import java.util.Random;

import controleur.Interaction;

public class Condottiere extends Personnage {

	public Condottiere() {
		super("Condotierre", 8, Caracteristiques.CONDOTTIERE);
	}

	// Utilisation du pouvoir par un joueur humain
	public void utiliserPouvoir() {
		int choixPersonnage;
		int	choixQuartier=0;
		String nomQuartier ="";
		boolean choix;
		
		System.out.println("Voulez-vous utiliser votre pouvoir de destruction ?");
		choix=Interaction.lireOuiOuNon();
		if (choix) {
			do {
				System.out.println("Voici maintenant la liste des victimes potentielles :");
				for(int i=0; i<9; i++) {
					if(this.getPlateau().getPersonnage(i)!= null) {
						System.out.print("\n"+ (i + 1) + " " + getPlateau().getPersonnage(i).getNom() + ": ");
						if(this.getPlateau().getPersonnage(i) != null) {
							for (int j = 0; j < this.getPlateau().getPersonnage(i).getJoueur().nbQuartiersDansCite(); j++) {
								System.out.print(j + 1 + " " + getPlateau().getPersonnage(i).getJoueur().getCite()[j].getNom() + "(coût "+ getPlateau().getJoueur(i).getCite()[j].getCout() + "), ");
							}
						}
					}
				}
				do {
					System.out.println("\nQui ciblez-vous ?( 0 pour annuler )");
					choixPersonnage =Interaction.lireUnEntier(0, this.getPlateau().getNombrePersonnages()+1);
				}while(this.getPlateau().getPersonnage(choixPersonnage-1) == null || this.getPlateau().getPersonnage(choixPersonnage-1).getJoueur()==null);
				if(choixPersonnage==0) {
					System.out.println("Vous n'utilisez pas votre pouvoir de destruction");
					break;
				}else if(this.getPlateau().getPersonnage(choixPersonnage-1).getNom() == "Eveque" && !this.getPlateau().getPersonnage(choixPersonnage-1).getAssassine()){
					System.out.println("L'évêque est vivant vous ne pouvez pas le selectionner");
				}else if(this.getPlateau().getPersonnage(choixPersonnage-1)!=null){
					do {
						System.out.println("Quel quartier choisissez-vous ? (0 pour annuler)");
						choixQuartier = Interaction.lireUnEntier(0,getPlateau().getPersonnage(choixPersonnage-1).getJoueur().nbQuartiersDansCite()+1);
						if(choixQuartier==0) { // L'index 0 correspond à la liberté de ne pas continuer
							System.out.println("Vous annulez le choix du quartier");
							break;
						}else if((this.getPlateau().getPersonnage(choixPersonnage-1).getJoueur().getCite()[choixQuartier-1].getCout() - 1) > this.getJoueur().nbPieces()) {
							System.out.println("Pas assez d'argent pour détruire ce quartier");
						}else if(this.getPlateau().getPersonnage(choixPersonnage-1).getJoueur().getCite()[choixQuartier-1].getNom()=="Donjon") {
							System.out.println("Le Donjon n'est pas destructible");	
						}
					}while(this.getPlateau().getJoueur(choixPersonnage-1).getCite()[choixQuartier-1].getCout() - 1 > this.getJoueur().nbPieces()|| this.getPlateau().getPersonnage(choixPersonnage-1).getJoueur().getCite()[choixQuartier-1].getNom()=="Donjon"); //tourne tant que le quartier n'est pas achetable
					if(choixQuartier!=0) {
						nomQuartier = this.getPlateau().getJoueur(choixPersonnage-1).getCite()[choixQuartier-1].getNom();
						this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choixPersonnage-1).getCite()[choixQuartier-1].getCout() - 1);
						this.getPlateau().getJoueur(choixPersonnage-1).retirerQuartierDansCite(nomQuartier);
						System.out.println(this.getPlateau().getJoueur(choixPersonnage-1).getNom()+" votre quartier : " + nomQuartier + " à  été détruit par le Condottiere");
					}
				}
			}while(this.getPlateau().getPersonnage(choixPersonnage-1).getNom() == "Eveque" && !this.getPlateau().getPersonnage(choixPersonnage-1).getAssassine() || choixQuartier==0);
		}else {System.out.println("Vous n'utilisez pas votre pouvoir de destruction");}
	}
	
	// Utilisation du pouvoir par un avatar
	public void utiliserPouvoirAvatar() {
		int choix=0;
		Random r = new Random();
		int choixPersonnage;
		int	choixQuartier;
		String nomQuartier ="";

		choix = r.nextInt(2);
		switch (choix) {
			case 0:
				System.out.println("Le condotiere n'utilise pas son pouvoir de destruction");
				break;
			case 1:
				System.out.println("Le condotiere utilise son pouvoir de destruction");
				do {
					choixPersonnage = r.nextInt(this.getPlateau().getNombrePersonnages()+1);// L'index max+1 correspond à la liberté de ne pas continuer
				}while(this.getPlateau().getPersonnage(choixPersonnage)==null || this.getPlateau().getPersonnage(choixPersonnage).getJoueur()==null);
				if (choixPersonnage == this.getPlateau().getNombrePersonnages()) {
					System.out.println("Le pouvoir échoue");
					break;
				}else if(this.getPlateau().getPersonnage(choixPersonnage).getNom() == "Eveque" && !this.getPlateau().getPersonnage(choixPersonnage).getAssassine()) {
					break;
				}else if(this.getPlateau().getPersonnage(choixPersonnage) != null){
					do {
						choixQuartier = r.nextInt(this.getPlateau().getPersonnage(choixPersonnage).getJoueur().nbQuartiersDansCite()+1);
						if(choixQuartier==this.getPlateau().getPersonnage(choixPersonnage).getJoueur().nbQuartiersDansCite()) { // L'index getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite()+1 correspond à la liberté de ne pas continuer
							System.out.println("Le pouvoir échoue");
							break;
						}
					}while(this.getPlateau().getPersonnage(choixPersonnage).getJoueur().getCite()[choixQuartier].getCout() - 1 > this.getJoueur().nbPieces() && this.getPlateau().getPersonnage(choixPersonnage).getJoueur().getCite()[choixQuartier].getNom()!="Donjon"); //tourne tant que le quartier n'est pas achetable
					
					if(choixQuartier!=this.getPlateau().getPersonnage(choixPersonnage).getJoueur().nbQuartiersDansCite()) {
						nomQuartier = this.getPlateau().getPersonnage(choixPersonnage).getJoueur().getCite()[choixQuartier].getNom();
						this.getJoueur().retirerPieces(this.getPlateau().getPersonnage(choixPersonnage).getJoueur().getCite()[choixQuartier].getCout() - 1);
						this.getPlateau().getPersonnage(choixPersonnage).getJoueur().retirerQuartierDansCite(nomQuartier);
						System.out.println(this.getPlateau().getPersonnage(choixPersonnage).getJoueur().getNom()+" votre quartier : " + nomQuartier + " à  été détruit par le Condottiere");
					}
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
