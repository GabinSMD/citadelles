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
		test.test1();
		test.test2();
		test.test3();
	}
	
	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		
		//Initialisation des personnages et du Plateau de Jeu
		Roi roi = new Roi();
		Assassin assassin = new Assassin();
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		//Ajout des personnages au Plateau de Jeu
		plateau.ajouterPersonnage(roi);
		plateau.ajouterPersonnage(assassin);
		
		//Tests
		Test.test(plateau.getNombrePersonnages()== 2,"nombre de joueurs");
		Test.test(plateau.getPersonnage(1)==assassin,"récupération du personnage de l'assassin");
		Test.test(plateau.getPersonnage(1).getRang()==1,"récupération du rang");		
	}
	public void test2() {
		System.out.println("TEST DE L'ASSASSINAT DU ROI");
		
		//Initialisation des personnages et du Plateau de Jeu
		Roi roi = new Roi();
		Assassin assassin = new Assassin();
		Condottiere condottiere = new Condottiere();
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		//Ajout des personnages au Plateau de Jeu
		plateau.ajouterPersonnage(roi);
		plateau.ajouterPersonnage(assassin);
		
		// on utilise le pouvoir de l'assassin
		// NB: seul le roi peut �tre assassin� dans cette situation
		assassin.utiliserPouvoir();
		
		//Test
		Test.test(roi.getAssassine(), " roi:"+roi.getAssassine());
	}
	public void test3() {
		System.out.println("TEST DE L'ASSASSINAT (AVATAR)");
		
		//Initialisation des personnages et du Plateau de Jeu
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		Assassin assassin = new Assassin();
		Condottiere condottiere = new Condottiere();
		Eveque eveque = new Eveque();
		Marchande marchande = new Marchande();
		
		//Ajout des personnages au Plateau de Jeu
		plateau.ajouterPersonnage(roi);
		plateau.ajouterPersonnage(assassin);
		plateau.ajouterPersonnage(condottiere);
		plateau.ajouterPersonnage(eveque);
		plateau.ajouterPersonnage(marchande);
		
		// on utilise le pouvoir de l'assassin aléatoirement
		// NB: N'importe quel personnage peut être assassiné hormis l'assassin
		assassin.utiliserPouvoirAvatar();
		
		//Test
		Test.test(roi.getAssassine() || marchande.getAssassine() || eveque.getAssassine() || condottiere.getAssassine() && !assassin.getAssassine(),"Un personnage est mort mais pas l'assassin ");
		
	}
}
