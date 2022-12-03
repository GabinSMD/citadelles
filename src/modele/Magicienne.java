package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Magicienne extends Personnage{
	

	private ArrayList<Quartier> copieTableau;

	

	public Magicienne() {
		super("Magicienne", 3, Caracteristiques.MAGICIENNE);
	}
	
	public void utiliserPouvoir() {
		boolean choixPouvoir=true;
		System.out.println("Voulez-vous utiliser votre pouvoir ?");
		choixPouvoir = Interaction.lireOuiOuNon();
		if (choixPouvoir) {
			int val = menuPouvoir();
			switch(val){
				case 0: break;
				case 1: 
					echangeJoueur();
					break;
				case 2:
					remplacePioche();
					break;
				default:
					break;
			}
		}else{
			System.out.println("La magicienne n'utilise pas son pouvoir");
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
				this.getPlateau().getPioche().ajouter(copieTableau.get(choice));
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
	
	public void utiliserPouvoirAvatar() {
		Random r = new Random();
		int choix = r.nextInt(2);
		switch(choix) {
		case 0:
			System.out.println("Le Voleur n'utilise pas son pouvoir");
			break;
		case 1:
			int val = menuPouvoirAvatar();
			switch(val){
				case 0: break;
				case 1: 
					echangeJoueurAvatar();
					break;
				case 2:
					remplacePiocheAvatar();
					break;
			}
			default:
				break;
		}
	}
	
	private void echangeJoueurAvatar() {
		ArrayList<Quartier> selectedTableau;
		int max = 0;
		System.out.println("Avec quel joueur voulez vous échanger :");
		for(int i=0; i<9; i++) {
			if(this.getPlateau().getJoueur(i)!= null && this.getPlateau().getJoueur(i).getNom() != this.getJoueur().getNom()) {
				System.out.println(i+"."+ this.getPlateau().getJoueur(i).getNom() +": "+ this.getPlateau().getJoueur(i).getMain().size() + "carte(s)");
				max++;
			}
		}
		Random r = new Random();
		int select = r.nextInt(0, max);
		System.out.println("Choix du Personnage : "+select);
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
	
	private void remplacePiocheAvatar() {
		int nbCartePossedez = this.getJoueur().getMain().size();
		int max = nbCartePossedez+1;
		System.out.println("Vous avez "+ nbCartePossedez +" carte(s)");
		Random r2 = new Random();
		int val = r2.nextInt(0,max);
		System.out.println("Choix du nombre de cartes à remplacer : "+val);
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
				Random r3 = new Random();
				int choice = r3.nextInt(0,nbCartePossedez);
				System.out.println("Choix des cartes à remplacer : "+choice);
				this.getPlateau().getPioche().ajouter(copieTableau.get(choice));
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
	
	private int menuPouvoirAvatar() {
		int max=1;
		Random r4 = new Random();
		int val = r4.nextInt(0,2);
		switch (val) {
		case 0:
			System.out.println("La magicienne n'utilise pas son pouvoir");
			max=0;
			break;
		case 1:
			System.out.println("La magicienne utilise son pouvoir");
			Random r5 = new Random();
			int choix;
			if(this.getJoueur().getMain().size()>0) {
				choix = r5.nextInt(0,3);
				System.out.println("Choix du pouvoir : "+choix);
				switch (choix) {
				case 0:
					System.out.println("Finalement elle ne fait rien");
					max=0;
					break;
				case 1:
					System.out.println("Elle échange toutes ses cartes avec un adversaire");
					max=1;
					break;
				case 2:
					System.out.println("Elle remplace un nombre de carte");
					max=2;
					break;
				}
			}
			else {
				choix = r5.nextInt(0,2);
				System.out.println("Choix du pouvoir : "+choix);
				switch (choix) {
				case 0:
					System.out.println("Finalement elle ne fait rien");
					max=0;
					break;
				case 1:
					System.out.println("Elle échange toutes ses cartes avec un adversaire");
					max=1;
					break;
				}
				max=1;
			}
		}
		System.out.println("Choix du menu : "+max);
		return max;
		
	}
}