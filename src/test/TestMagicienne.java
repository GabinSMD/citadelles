package test;

import modele.Assassin;
import modele.Magicienne;
import modele.Roi;

import modele.Joueur;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

import java.util.ArrayList;

public class TestMagicienne {
	public static void main(String[] args) {
		TestMagicienne test = new TestMagicienne();
		test.test1();
		test.test2();
		test.test3();
		test.test4();
	}
	
	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Magicienne magicienne = new Magicienne();
		plateau.ajouterPersonnage(magicienne);
		Test.test(plateau.getNombrePersonnages()== 3,"nombre de personnages");
		Test.test(plateau.getPersonnage(2)==magicienne, "récupération du personnage de la magicienne");
		Test.test(plateau.getPersonnage(2).getRang()==3, "rang de la magicienne");
		Test.test(plateau.getPersonnage(2).getJoueur()==null, "Attribut joueur");
		Test.test(!plateau.getPersonnage(2).getAssassine(), "Attribut assassine");
		Test.test(!plateau.getPersonnage(2).getVole(), "Attribut vole");
		
	}
	public void test2() {
		System.out.println("TEST DU POUVOIR DE LA MAGICIENNE : ECHANGE TOUTE SES CARTES AVEC UN ADVERSAIRE");
		System.out.println("Pour le bon déroulement du test merci de selectionner l'option 1 : Echanger toute vos cartes");
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		// création de quatre personnages
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Magicienne magicienne = new Magicienne();
		plateau.ajouterPersonnage(magicienne);
			
		// création de trois joueurs
		Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Belle");
		plateau.ajouterJoueur(joueur3);
			
		// on associe les personnages aux joueurs
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		magicienne.setJoueur(joueur3);
		
		// création d'une pioche:
		Pioche pioche = plateau.getPioche();
		Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); 
		pioche.ajouter(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); 
		pioche.ajouter(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); 
		pioche.ajouter(q);
		q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); 
		pioche.ajouter(q);
		q = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2); 
		pioche.ajouter(q);
		q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4,"A la fin de la partie, ..."); 
		pioche.ajouter(q);
		q = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5); 
		pioche.ajouter(q);
		q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); 
		pioche.ajouter(q);
		q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); 
		pioche.ajouter(q);
		q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],15); 
		pioche.ajouter(q);
		q = new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez..."); 
		pioche.ajouter(q);
		pioche.melanger();
		
		// on distribue les cartes aux joueurs:
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur3.ajouterQuartierDansMain(pioche.piocher());
		joueur3.ajouterQuartierDansMain(pioche.piocher());
		
		// on affiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainRoi = roi.getJoueur().getMain();
		for(int i = 0; i< mainRoi.size(); i++)
			System.out.print(mainRoi.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainAssassin = assassin.getJoueur().getMain();
		for(int i = 0; i< mainAssassin.size(); i++)
			System.out.print(mainAssassin.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de la magicienne (" + magicienne.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainMagicienne = magicienne.getJoueur().getMain();
		for(int i = 0; i< mainMagicienne.size(); i++)
			System.out.print(mainMagicienne.get(i).getNom() + ", ");
		System.out.println("");
				
		// on récupère la taille de la pioche:
		int taillePiocheAvantPouvoir = pioche.nombreElements();
		
		// utiliser le pouvoir de la magicienne :		
		magicienne.utiliserPouvoir();
		
		// on réaffiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainRoi.size(); i++)
			System.out.print(mainRoi.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainAssassin.size(); i++)
			System.out.print(mainAssassin.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de la magicienne (" + magicienne.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainMagicienne.size(); i++)
			System.out.print(mainMagicienne.get(i).getNom() + ", ");
		System.out.println("\n");
		
		// on vérifie que la taille de la pioche n'a pas changé:
		System.out.print("Test : Regarder si l'échange des mains a bien eu lieu\n");
	}
	
	public void test3() {
		System.out.println("TEST DU POUVOIR DE LA MAGICIENNE : DEFAUSSE DES CARTES");
		System.out.println("Pour le bon déroulement du test merci de selectionner l'option 2 : Remplacer un nombre de carte");
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		// création de quatre personnages
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Magicienne magicienne = new Magicienne();
		plateau.ajouterPersonnage(magicienne);
			
		// création de trois joueurs
		Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Belle");
		plateau.ajouterJoueur(joueur3);
			
		// on associe les personnages aux joueurs
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		magicienne.setJoueur(joueur3);
		
		// création d'une pioche:
		Pioche pioche = plateau.getPioche();
		Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); 
		pioche.ajouter(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); 
		pioche.ajouter(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); 
		pioche.ajouter(q);
		q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); 
		pioche.ajouter(q);
		q = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2); 
		pioche.ajouter(q);
		q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4,"A la fin de la partie, ..."); 
		pioche.ajouter(q);
		q = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5); 
		pioche.ajouter(q);
		q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); 
		pioche.ajouter(q);
		q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); 
		pioche.ajouter(q);
		q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],15); 
		pioche.ajouter(q);
		q = new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez..."); 
		pioche.ajouter(q);
		pioche.melanger();
		
		// on distribue les cartes aux joueurs:
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur3.ajouterQuartierDansMain(pioche.piocher());
		joueur3.ajouterQuartierDansMain(pioche.piocher());
		
		// on affiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainRoi = roi.getJoueur().getMain();
		for(int i = 0; i< mainRoi.size(); i++)
			System.out.print(mainRoi.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainAssassin = assassin.getJoueur().getMain();
		for(int i = 0; i< mainAssassin.size(); i++)
			System.out.print(mainAssassin.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de la magicienne (" + magicienne.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainMagicienne = magicienne.getJoueur().getMain();
		for(int i = 0; i< mainMagicienne.size(); i++)
			System.out.print(mainMagicienne.get(i).getNom() + ", ");
		System.out.println("");
				
		// on récupère la taille de la pioche:
		int taillePiocheAvantPouvoir = pioche.nombreElements();
		int tailleMainAvantPouvoir=magicienne.getJoueur().getMain().size();
		
		// utiliser le pouvoir de la magicienne :		
		magicienne.utiliserPouvoir();
		
		// on réaffiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainRoi.size(); i++)
			System.out.print(mainRoi.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainAssassin.size(); i++)
			System.out.print(mainAssassin.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de la magicienne (" + magicienne.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainMagicienne.size(); i++)
			System.out.print(mainMagicienne.get(i).getNom() + ", ");
		System.out.println("\n");
		
		// on vérifie que la taille de la pioche n'a pas changé:
		Test.test(taillePiocheAvantPouvoir==pioche.nombreElements(), "taille inchangée de la pioche");
		Test.test(tailleMainAvantPouvoir==magicienne.getJoueur().getMain().size(), "taille inchangée de la main");
	}
	
	public void test4() {
		System.out.println("TEST DU POUVOIR DE LA MAGICIENNE (AVATAR)");
		System.out.println("Pour ce test, l'avatar faisant ses choix de manière aléatoires, il est recomandé de refaire ce test jusqu'à ce que celui-ci effectue les différents choix");
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		// création de quatre personnages
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Magicienne magicienne = new Magicienne();
		plateau.ajouterPersonnage(magicienne);
			
		// création de trois joueurs
		Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Belle");
		plateau.ajouterJoueur(joueur3);
			
		// on associe les personnages aux joueurs
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		magicienne.setJoueur(joueur3);
		
		// création d'une pioche:
		Pioche pioche = plateau.getPioche();
		Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); 
		pioche.ajouter(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); 
		pioche.ajouter(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); 
		pioche.ajouter(q);
		q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); 
		pioche.ajouter(q);
		q = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2); 
		pioche.ajouter(q);
		q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4,"A la fin de la partie, ..."); 
		pioche.ajouter(q);
		q = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5); 
		pioche.ajouter(q);
		q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); 
		pioche.ajouter(q);
		q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); 
		pioche.ajouter(q);
		q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],15); 
		pioche.ajouter(q);
		q = new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez..."); 
		pioche.ajouter(q);
		pioche.melanger();
		
		// on distribue les cartes aux joueurs:
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur3.ajouterQuartierDansMain(pioche.piocher());
		joueur3.ajouterQuartierDansMain(pioche.piocher());
		
		// on affiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainRoi = roi.getJoueur().getMain();
		for(int i = 0; i< mainRoi.size(); i++)
			System.out.print(mainRoi.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainAssassin = assassin.getJoueur().getMain();
		for(int i = 0; i< mainAssassin.size(); i++)
			System.out.print(mainAssassin.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de la magicienne (" + magicienne.getJoueur().getNom() + "): ");
		ArrayList<Quartier> mainMagicienne = magicienne.getJoueur().getMain();
		for(int i = 0; i< mainMagicienne.size(); i++)
			System.out.print(mainMagicienne.get(i).getNom() + ", ");
		System.out.println("");
				
		// on récupère la taille de la pioche:
		int taillePiocheAvantPouvoir = pioche.nombreElements();
		
		// utiliser le pouvoir de la magicienne :		
		magicienne.utiliserPouvoirAvatar();
		
		// on réaffiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainRoi.size(); i++)
			System.out.print(mainRoi.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainAssassin.size(); i++)
			System.out.print(mainAssassin.get(i).getNom() + ", ");
		System.out.println("");
		System.out.print("Main de la magicienne (" + magicienne.getJoueur().getNom() + "): ");
		for(int i = 0; i< mainMagicienne.size(); i++)
			System.out.print(mainMagicienne.get(i).getNom() + ", ");
		System.out.println("\n");
		
		// on vérifie que la taille de la pioche n'a pas changé:
		System.out.print("Test : Regarder si l'échange des mains a bien eu lieu\n");
		Test.test(taillePiocheAvantPouvoir==pioche.nombreElements(), "taille inchangée de la pioche");
	}
}
