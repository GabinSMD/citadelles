package modele;

import java.util.Random;

import controleur.Interaction;

public class Voleur extends Personnage {

	public Voleur() {
		super("Voleur", 2, Caracteristiques.VOLEUR);
		// TODO Auto-generated constructor stub
	}

	public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous voler ?");
		int max = 0;
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getPersonnage(i)!= null && this.getPlateau().getPersonnage(i).getNom() != this.getNom() && this.getPlateau().getPersonnage(i).getRang()!=1) {
				System.out.println(i+"."+ this.getPlateau().getPersonnage(i).getNom());
				max++;
			}
		}
		System.out.println("Votre choix :");
		int choix =Interaction.lireUnEntier(0,max);
		
		this.getPlateau().getPersonnage(choix).setVole();
		return;
	}
	
	public void utiliserPouvoirAvatar() {
		int max = 0;
		Random r = new Random();
		Personnage[] selection = new Personnage[9];
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getPersonnage(i)!= null && this.getPlateau().getPersonnage(i).getNom() != this.getNom()&& this.getPlateau().getPersonnage(i).getRang()!=1) {
				selection[max]=this.getPlateau().getPersonnage(i);
				max++;
			}
		}
		int choix =r.nextInt(max);
		selection[choix].setVole();
		System.out.println("Le/La "+selection[choix].getNom()+" a été volé.e");
		return;
	}
}
