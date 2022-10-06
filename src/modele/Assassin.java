package modele;
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
		int choix =Interaction.lireUnEntier(0,max);
		this.getPlateau().getPersonnage(choix).setAssassine();
		return;
	}
}