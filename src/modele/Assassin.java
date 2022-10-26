package modele;
import java.util.Random;

import controleur.Interaction;

public class Assassin extends Personnage {

	public Assassin() {
		super("Assassin", 1, Caracteristiques.ASSASSIN);
		// TODO Auto-generated constructor stub
	}

	public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous assassiner ?");
		int max = 0;
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getPersonnage(i)!= null && this.getPlateau(). getPersonnage(i).getNom() != this.getNom()) {
				System.out.println(i+"."+ this.getPlateau().getPersonnage(i).getNom());
				max++;
			}
		}
		System.out.println("Votre choix :");
		int choix =Interaction.lireUnEntier(0,max+1);
		this.getPlateau().getPersonnage(choix).setAssassine();
		return;
	}
	
	public void utiliserPouvoirAvatar() {
		int max = 0;
		Random r = new Random();
		Personnage[] selection = new Personnage[9];
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getPersonnage(i)!= null && this.getPlateau().getPersonnage(i).getNom() != this.getNom()) {
				
				selection[max]=this.getPlateau().getPersonnage(i);
				
				max++;
			}
		}
		/* test contenu tableau de selection
		 * for(int i=0; i<max; i++) {
			if(selection[0]!=null) {
				System.out.println("selection index: "+i+" "+selection[i]);
			}
		}*/

		int choix =r.nextInt(max);
		selection[choix].setAssassine();
		System.out.println("Le/La "+selection[choix].getNom()+" a été assassiné.e");
		return;
	}
}