package modele;
import java.util.Random;

import controleur.Interaction;

public class Assassin extends Personnage {
	private int choix=0;

	public Assassin() {
		super("Assassin", 1, Caracteristiques.ASSASSIN);
	}

	public void utiliserPouvoir() {
		if (this.getJoueur() != null) {
			System.out.println("Voulez-vous utiliser votre pouvoir ?");
			boolean choixPouvoir = Interaction.lireOuiOuNon();
			if (choixPouvoir) {
				System.out.println("Quel personnage voulez-vous assassiner ?");
		
				//Affichage de la liste des personnages selectionnables
				for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++) {
					if(this.getPlateau().getPersonnage(i)!= null) {
						System.out.println(i+1+"."+ this.getPlateau().getPersonnage(i).getNom());
					}
				}
				//Choix du personnage que l'on souhaite assassiner
				//NB: L'assassin ne pourra pas être assassiné, le joueur en sera avertis et devra choisir une nouvelle cible
				System.out.println("Choix de la cible :");
				do {
					choix =Interaction.lireUnEntier(1,this.getPlateau().getNombrePersonnages()+1);
					if(this.getPlateau().getPersonnage(choix-1).getNom()=="Assassin") {
						System.out.println("L'assassin ne peut pas être selectionné");
					}
				}while(this.getPlateau().getPersonnage(choix-1).getNom()=="Assassin");
				//Assassinat de la cible choisie
				this.getPlateau().getPersonnage(choix-1).setAssassine();
				System.out.println("Le/La "+this.getPlateau().getPersonnage(choix-1).getNom()+" a été assassiné.e");
			}
		}
		else {
			System.out.println("Un Joueur n'a pas été attribué au Voleur");
		}
	}
	
	public void utiliserPouvoirAvatar() {
		Random r = new Random();
		int choix= r.nextInt(2);
		//Rempli la liste des personnages selectionnables 
		
		switch(choix) {
			case 0:
				System.out.println("L'Assassin n'utilise pas son pouvoir");
				break;
			case 1:
				System.out.println("L'Assassin utilise son pouvoir");
				
				//Choix du personnage à assassiner par l'avatar
				//NB: L'assassin ne pourra pas être assassiné, le choix s'effectuera jusqu'à ce qu'un autre personnage soit sélectionné
				do {
					choix =r.nextInt(this.getPlateau().getNombrePersonnages());
				}while(this.getPlateau().getPersonnage(choix).getNom()=="Assassin");
				//Assassinat de la cible choisie
				this.getPlateau().getPersonnage(choix).setAssassine();
				System.out.println("Le/La "+this.getPlateau().getPersonnage(choix).getNom()+" a été assassiné.e");
				break;
			default:
				break;
		}
	}
}