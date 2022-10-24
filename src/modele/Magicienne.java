package modele;

import java.util.ArrayList;
import java.util.InputMismatchException;

import controleur.Interaction;

public class Magicienne extends Personnage{
	

	private ArrayList<Quartier> copieTableau;
	

	public Magicienne() {
		super("Magicienne", 3, Caracteristiques.MAGICIENNE);
	}
	
	public void utiliserPouvoir() {
		int val = menuPouvoir();
		switch(val){
			case 0: break;
			case 1: 
				echangeJoueur();
				break;
			case 2:
				remplacePioche();
				break;
		}
	}
	
	private void echangeJoueur() {
		ArrayList<Quartier> selectedTableau;
		int max = 0;
		System.out.println("Avec quel joueur voulez vous échanger :");
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getJoueur(i)!= null && this.getPlateau().getJoueur(i).getNom() != this.getJoueur().getNom()) {
				System.out.println(i+"."+ this.getPlateau().getJoueur(i).getNom() +": "+ this.getPlateau().getJoueur(i).getMain().size() + "carte(s)");
				max++;
			}
		}
		int select = Interaction.lireUnEntier(0, max);
		selectedTableau = new ArrayList<Quartier>(this.getPlateau().getJoueur(select).getMain());
		copieTableau = new ArrayList<Quartier>(this.getJoueur().getMain());
		for(int i=0; i<copieTableau.size(); i++) {
			this.getJoueur().retirerQuartierDansMain();
		}
		for(int i=0; i<selectedTableau.size(); i++) {
			this.getPlateau().getJoueur(select).retirerQuartierDansMain();
		}
		for(int i=0; i<selectedTableau.size(); i++) {
			this.getJoueur().ajouterQuartierDansMain(selectedTableau.get(i));
		}
		for(int i=0; i<copieTableau.size(); i++) {
			this.getPlateau().getJoueur(select).ajouterQuartierDansMain(copieTableau.get(i));
		}
	}
	
	private void remplacePioche() {
		int nbCartePossedez = this.getJoueur().getMain().size();
		int max = nbCartePossedez+1;
		System.out.println("Vous avez "+ nbCartePossedez +" carte(s)");
		System.out.println("Combien de carte voulez vous remplacer :");
		int val = Interaction.lireUnEntier(0,max);
		if(val == max-1) {
			copieTableau = new ArrayList<Quartier>(this.getJoueur().getMain());
			for(int i=0; i<=copieTableau.size(); i++) {
				this.getPlateau().getPioche().ajouter(this.getJoueur().retirerQuartierDansMain());
				this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
			}
		}else if (val > 0) {
			copieTableau = new ArrayList<Quartier>(this.getJoueur().getMain());
			int i;
			for (int j = val; j > 0; j--) {
				System.out.println("Quelle carte voulez vous supprimer ? (" + j + " cartes restantes)");
				for (i = 0; i < nbCartePossedez; i++) {
					System.out.println(i + ". " + copieTableau.get(i).getNom());
				} 
				int choice = Interaction.lireUnEntier(0,max);
				this.getPlateau().pioche.ajouter(copieTableau.get(choice));
				copieTableau.remove(choice);
				copieTableau.add(this.getPlateau().getPioche().piocher());
			}
			for(i=0; i<=this.getJoueur().getMain().size(); i++) {
				this.getJoueur().retirerQuartierDansMain();
			}
			for(i=0; i<copieTableau.size(); i++) {
				this.getJoueur().ajouterQuartierDansMain(copieTableau.get(i));
			}
		}
	}
	
	private int menuPouvoir() {
		int max=1;
		System.out.println("Voulez vous utilisez votre pouvoir : ");
		System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
		if(Interaction.lireOuiOuNon()) {
			System.out.println("Quel action voulez vous effectuez");
			System.out.println("0. Ne rien faire");
			System.out.println("1. Echanger toutes vos cartes avec un adversaire");
			if(this.getJoueur().getMain().size()>0) {
				System.out.println("2. Remplacer un nombre de carte");
				 max=3;
			}
		}
		int val = Interaction.lireUnEntier(0, max);
		return val;
	}

}
