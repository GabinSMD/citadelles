package modele;

import java.util.Random;

import controleur.Interaction;

public class Voleur extends Personnage {
	private int choix=0;
	private int max = 0;
	private Personnage[] selection = new Personnage[9];

	public Voleur() {
		super("Voleur", 2, Caracteristiques.VOLEUR);
		// TODO Auto-generated constructor stub
	}

	public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous voler ?");
		//Affichage de la liste des personnages selectionnables
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getPersonnage(i)!= null) {
				selection[i]=this.getPlateau().getPersonnage(i);
				System.out.println(i+1+"."+ selection[i].getNom());
				max++;
			}
		}
		System.out.println("Votre choix :");
		//Choix du personnage que l'on souhaite voler
		//NB: LE voleur ne pourra pas être volé ainsi que les personnages de rang 1, le joueur en sera averti et devra choisir une nouvelle cible
		do {
			choix =Interaction.lireUnEntier(1,max+1);
			if(selection[choix-1].getNom()=="Voleur") {
				System.out.println("Le voleur ne peut pas être selectionné");
			}
			else if(selection[choix-1].getRang()==1) {
				System.out.println("Le personnage est de rang 1, il ne peut donc pas être selectionné");
			}
		}while(selection[choix-1].getNom()=="Voleur" || selection[choix-1].getRang()==1);	
		
		//Vol de la cible
		selection[choix-1].setVole();
		System.out.println("Le/La "+selection[choix-1].getNom()+" a été volé.e");
		return;
	}
	
	public void utiliserPouvoirAvatar() {
		Random r = new Random();
		//Affichage de la liste des personnages selectionnables
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getPersonnage(i)!= null) {
				selection[i]=this.getPlateau().getPersonnage(i);
				max++;
			}
		}
		//Choix du personnage que l'on souhaite voler
		//NB: LE voleur ne pourra pas être volé ainsi que les personnages de rang 1, le joueur en sera averti et devra choisir une nouvelle cible
		do {
			choix =r.nextInt(max);
			if(selection[choix].getNom()=="Voleur") {
				System.out.println("Le tente de se voler lui même ( pas fût fût :/ ) \nIl cherche une autre cible");
			}
			else if(selection[choix].getRang()==1) {
				System.out.println("Le voleur tente de voler un rang 1 \nIl échoue et cherche une nouvelle cible");
			}
		}while(selection[choix].getNom()=="Voleur" || selection[choix].getRang()==1);	
		
		//Vol de la cible
		selection[choix].setVole();
		System.out.println("Le/La "+selection[choix].getNom()+" a été volé.e");
		return;
	}
}
