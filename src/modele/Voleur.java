package modele;

import java.util.Random;

import controleur.Interaction;

public class Voleur extends Personnage {
	private int choix=0;
	private int max = 0;
	private Personnage[] selection = new Personnage[9];
	boolean choixPouvoir= true;

	public Voleur() {
		super("Voleur", 2, Caracteristiques.VOLEUR);
	}

	public void utiliserPouvoir() {
		this.max=0;
		this.choix=0;
		
		if (this.getJoueur() != null) {
			System.out.println("Voulez-vous utiliser votre pouvoir ?");
			this.choixPouvoir = Interaction.lireOuiOuNon();
			if (this.choixPouvoir) {
				System.out.println("Quel personnage voulez-vous voler ?");
				//Affichage de la liste des personnages selectionnables
				for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++) {
					if(this.getPlateau().getPersonnage(i)!= null) {
						System.out.println(i+1+"."+ this.getPlateau().getPersonnage(i).getNom());
					}
				}
				System.out.println("Votre choix :");
				//Choix du personnage que l'on souhaite voler
				//NB: LE voleur ne pourra pas être volé ainsi que les personnages de rang 1, le joueur en sera averti et devra choisir une nouvelle cible
				do {
					this.choix =Interaction.lireUnEntier(0,this.getPlateau().getNombrePersonnages());
					if(this.getPlateau().getPersonnage(this.choix-1).getNom()=="Voleur") {
						System.out.println("Le voleur ne peut pas être selectionné");
					}
					else if(this.getPlateau().getPersonnage(this.choix-1).getRang()==1) {
						System.out.println("Le personnage est de rang 1, il ne peut donc pas être selectionné");
					} else if(this.choix==0) {
						System.out.println("Vous n'utilisez pas votre pouvoir");
						break;
					}
				}while(this.getPlateau().getPersonnage(this.choix-1).getNom()=="Voleur" || this.getPlateau().getPersonnage(this.choix-1).getRang()==1);	
				if(this.choix!=0) {
					//Vol de la cible
					this.getPlateau().getPersonnage(this.choix-1).setVole();
					System.out.println("Le/La "+this.getPlateau().getPersonnage(this.choix-1).getNom()+" a été volé.e");
				}
			} else {
				System.out.println("Vous n'utilisez pas votre pouvoir");
			}
		}else {
			System.out.println("Un Joueur n'a pas été attribué au Voleur");
		}
	}
	
	public void utiliserPouvoirAvatar() {
		Random r = new Random();
		this.choix=0;
		this.choix =r.nextInt(2);
		switch(this.choix) {
		case 0:
			System.out.println("Le Voleur n'utilise pas son pouvoir");
			break;
		case 1:
			//Choix du personnage que l'on souhaite voler
			//NB: LE voleur ne pourra pas être volé ainsi que les personnages de rang 1, le joueur en sera averti et devra choisir une nouvelle cible
			do {
				this.choix =r.nextInt(this.getPlateau().getNombrePersonnages()+1);
				if(this.choix==0) {
					System.out.println("Vous n'utilisez pas votre pouvoir");
					break;
				}
				else if(this.getPlateau().getPersonnage(this.choix-1).getNom()=="Voleur") {
					System.out.println("Le tente de se voler lui même \nIl cherche une autre cible");
				}
				else if(this.getPlateau().getPersonnage(this.choix-1).getRang()==1) {
					System.out.println("Le voleur tente de voler un rang 1 \nIl échoue et cherche une nouvelle cible");
				}
			}while(this.getPlateau().getPersonnage(this.choix-1).getNom()=="Voleur" || this.getPlateau().getPersonnage(this.choix-1).getRang()==1);	
			if(this.choix!=0) {
				//Vol de la cible
				this.getPlateau().getPersonnage(this.choix-1).setVole();
				System.out.println("Le/La "+this.getPlateau().getPersonnage(this.choix-1).getNom()+" a été volé.e");
			}
			break;
		default:
			break;
		}
	}
}
