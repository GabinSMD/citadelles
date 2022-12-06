package test;

import modele.Assassin;
import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Roi;

public class TestPlateauDeJeu {

	public static void main(String[] args) {
		TestPlateauDeJeu testPlateau = new TestPlateauDeJeu();
		testPlateau.test1();
		testPlateau.test2();	
		testPlateau.test3();
		testPlateau.test4();
	}
	
	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Test.test(plateau.getNombreJoueurs()== 0, "test du nombre de joueurs");
		Test.test(plateau.getNombrePersonnages()== 0, "test du nombre de personnages");
		Test.test(plateau.getPioche()!= null && plateau.getPioche().nombreElements()==0, "test de l'existance de la pioche");
	}
	public void test2() {
		System.out.println("TEST DE L'AJOUT D'UN JOUEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Joueur joueur = new Joueur("Billy");
		Joueur joueur2 = new Joueur("Toto");
		plateau.ajouterJoueur(joueur);
		Test.test(plateau.getNombreJoueurs()== 1,"nombre de joueurs");
		Test.test(plateau.getJoueur(0)== joueur, "récupération de ce joueur depuis le tableau");

		//ajout des personnages pour remplir la liste
		plateau.ajouterJoueur(joueur);
		plateau.ajouterJoueur(joueur);
		plateau.ajouterJoueur(joueur);
		plateau.ajouterJoueur(joueur);
		plateau.ajouterJoueur(joueur);
		plateau.ajouterJoueur(joueur);
		plateau.ajouterJoueur(joueur);
		plateau.ajouterJoueur(joueur);
		
		plateau.ajouterJoueur(joueur2);
		Test.test(plateau.getJoueur(8)!= joueur2, "test ajout d'un nouveau joueur si la liste est pleine");
	
	}
	
	public void test3() {
		System.out.println("TEST DE L'AJOUT D'UN PERSONNAGE");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Test.test(plateau.getNombrePersonnages()== 1,"nombre de joueurs");
		Test.test(plateau.getPersonnage(0)== roi, "récupération du personnage depuis le tableau");
		
		Assassin assassinMort =new Assassin();
		assassinMort.setAssassine();
		Assassin assassinVole =new Assassin();
		assassinVole.setVole();
		Assassin assassinNormal =new Assassin();
		
		plateau.ajouterPersonnage(assassinNormal);
		plateau.ajouterPersonnage(assassinMort);
		plateau.ajouterPersonnage(assassinVole);
		Test.test(plateau.getPersonnage(1)== assassinNormal && plateau.getPersonnage(2)!=assassinMort && plateau.getPersonnage(3)!=assassinVole, "Seul le personange conforme à été ajouté (pas assassiné/pas volé");
		
		
		
	}
	
	public void test4() {
		System.out.println("TEST DU CONTENU DE LA PIOCHE");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Test.test(plateau.getPioche().nombreElements()== 0,"récupération de la pioche");
	}
}
