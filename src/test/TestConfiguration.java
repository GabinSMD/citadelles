package test;

import application.Configuration;
import modele.Pioche;
import modele.PlateauDeJeu;

public class TestConfiguration {
	public static void main(String[] args) {
		TestConfiguration test = new TestConfiguration();
		test.test1();
	}
	
	public void test1() {
		System.out.println("TEST DE LA PIOCHE");
		Pioche pioche = Configuration.nouvellePioche();
		Test.test(pioche.nombreElements()==54,"Taille de la pioche (Quartiers)");
		
		PlateauDeJeu plateau = Configuration.configurationDeBase(pioche);
		Test.test(plateau.getNombreJoueurs() == 4,"Nombre de joueurs");
		Test.test(plateau.getNombrePersonnages() == 8,"Nombre de personnages");
		Test.test(pioche.nombreElements() == 68,"Taille de la pioche (Quartiers+Merveilles)");
		
	}
}
