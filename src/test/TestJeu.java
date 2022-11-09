package test;

import application.Configuration;
import application.Jeu;
import modele.Pioche;
import modele.PlateauDeJeu;

public class TestJeu {
	public static void main(String[] args) {
		TestJeu test = new TestJeu();
		//test.test1();
		//test.test2();
		test.test3();

	}
	
	public void test1() {
		Pioche pioche = Configuration.nouvellePioche();
		PlateauDeJeu plateau = Configuration.configurationDeBase(pioche);

		System.out.println("TEST DU NOMBRE DE PIECES");
		Jeu jeu = new Jeu();
		jeu.jouer();
		Test.test(plateau.getJoueur(0).nbPieces() == 2,"Nombre de pièces");
		Test.test(plateau.getJoueur(0).nbQuartiersDansMain() == 4,"Nombre de cartes en main");
	}
	
	public void test2() {
		Pioche pioche = Configuration.nouvellePioche();
		PlateauDeJeu plateau = Configuration.configurationDeBase(pioche);
		
		System.out.println("TEST DU PLACEMENT ALEATOIRE DE LA COURONNE");
		Jeu jeu = new Jeu();
		jeu.jouer();
		int nombreJoueurs = plateau.getNombreJoueurs();
		for (int i = 0; i < nombreJoueurs; i++) {
			if (plateau.getJoueur(i).getPossedeCouronne() == true) {
				Test.test(plateau.getJoueur(i).getPossedeCouronne() == true,plateau.getJoueur(i).getNom()+" a la couronne");
			}
		}
	}
	
	public void test3() {
		Pioche pioche = Configuration.nouvellePioche();
		PlateauDeJeu plateau = Configuration.configurationDeBase(pioche);
		
		System.out.println("TEST DU PLACEMENT ALEATOIRE DE LA COURONNE");
		Jeu jeu = new Jeu();
		jeu.jouer();
		int nombreJoueurs = plateau.getNombreJoueurs();
		for (int i = 0; i < nombreJoueurs; i++) {
			if (plateau.getJoueur(i).getPossedeCouronne() == true) {
				Test.test(plateau.getJoueur(i).getPossedeCouronne() == true,plateau.getJoueur(i).getNom()+" a la couronne");
			}
		}
	}
}
