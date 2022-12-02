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
		// TODO Auto-generated constructor stub
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
				for(int i=0; i<9; i++) {
					if(this.getPlateau().getPersonnage(i)!= null) {
						this.selection[i]=this.getPlateau().getPersonnage(i);
						System.out.println(i+1+"."+ this.selection[i].getNom());
						this.max++;
					}
				}
				System.out.println("Votre choix :");
				//Choix du personnage que l'on souhaite voler
				//NB: LE voleur ne pourra pas être volé ainsi que les personnages de rang 1, le joueur en sera averti et devra choisir une nouvelle cible
				do {
					this.choix =Interaction.lireUnEntier(1,this.max+1);
					if(this.selection[this.choix-1].getNom()=="Voleur") {
						System.out.println("Le voleur ne peut pas être selectionné");
					}
					else if(this.selection[this.choix-1].getRang()==1) {
						System.out.println("Le personnage est de rang 1, il ne peut donc pas être selectionné");
					}
				}while(this.selection[choix-1].getNom()=="Voleur" || this.selection[choix-1].getRang()==1);	
				
				//Vol de la cible
				this.selection[this.choix-1].setVole();
				System.out.println("Le/La "+this.selection[this.choix-1].getNom()+" a été volé.e");
			}
			else {
				System.out.println("Vous n'utilisez pas votre pouvoir");
			}
		}else {
			System.out.println("Un Joueur n'a pas été attribué au Voleur");
		}
	}
	
	public void utiliserPouvoirAvatar() {
		Random r = new Random();
		this.max=0;
		this.choix=0;
		//Affichage de la liste des personnages selectionnables
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getPersonnage(i)!= null) {
				this.selection[i]=this.getPlateau().getPersonnage(i);
				this.max++;
			}
		}
		this.choix =r.nextInt(this.max);
		switch(this.choix) {
		case 0:
			System.out.println("Le Voleur n'utilise pas son pouvoir");
			break;
		case 1:
			//Choix du personnage que l'on souhaite voler
			//NB: LE voleur ne pourra pas être volé ainsi que les personnages de rang 1, le joueur en sera averti et devra choisir une nouvelle cible
			do {
				this.choix =r.nextInt(this.max);
				if(this.selection[this.choix].getNom()=="Voleur") {
					System.out.println("Le tente de se voler lui même ( pas fût fût :/ ) \nIl cherche une autre cible");
				}
				else if(this.selection[this.choix].getRang()==1) {
					System.out.println("Le voleur tente de voler un rang 1 \nIl échoue et cherche une nouvelle cible");
				}
			}while(this.selection[this.choix].getNom()=="Voleur" || this.selection[this.choix].getRang()==1);	
			
			//Vol de la cible
			this.selection[this.choix].setVole();
			System.out.println("Le/La "+this.selection[this.choix].getNom()+" a été volé.e");
			break;
		default:
			break;
		}
	}
}
