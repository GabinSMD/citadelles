package test;

import modele.Assassin;
import modele.Condottiere;
import modele.Eveque;
import modele.Marchande;
import modele.PlateauDeJeu;
import modele.Roi;

public class TestAssassin {
	public static void main(String[] args) {
		TestAssassin test = new TestAssassin();
		//test.test1();
		//test.test2();
		test.test3();
	}
	
	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Test.test(plateau.getNombrePersonnages()== 2,"nombre de joueurs");
		Test.test(plateau.getPersonnage(1)==assassin,
				"r�cup�ration du personnage de l'assassin");
		Test.test(plateau.getPersonnage(1).getRang()==1,
				"r�cup�ration du rang");		
	}
	public void test2() {
		System.out.println("TEST DE L'ASSASSINAT DU ROI");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Condottiere condottiere = new Condottiere();
		plateau.ajouterPersonnage(condottiere);
		
		// on utilise le pouvoir de l'assassin
		// NB: seul le roi peut �tre assassin� dans cette situation
		assassin.utiliserPouvoir();
		Test.test(roi.getAssassine()||condottiere.getAssassine(),"condotiere"+condottiere.getAssassine());
	}
	public void test3() {
		System.out.println("TEST DE L'ASSASSINAT ALEATOIRE");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Condottiere condottiere = new Condottiere();
		plateau.ajouterPersonnage(condottiere);
		Eveque eveque = new Eveque();
		plateau.ajouterPersonnage(eveque);
		Marchande marchande = new Marchande();
		plateau.ajouterPersonnage(marchande);
		
		// on utilise le pouvoir de l'assassin aléatoire
		// NB: seul le roi peut �tre assassin� dans cette situation
		assassin.utiliserPouvoirAvatar();
		Test.test(roi.getAssassine() || marchande.getAssassine() || eveque.getAssassine() || condottiere.getAssassine(),"roi:"+roi.getAssassine()+" marchande:"+marchande.getAssassine()+" condottiere:"+condottiere.getAssassine()+" eveque:"+eveque.getAssassine());
		
	}
}
