package test;

import modele.Assassin;
import modele.Condottiere;
import modele.Eveque;
import modele.Joueur;
import modele.Marchande;
import modele.PlateauDeJeu;
import modele.Roi;

public class TestAssassin {
	public static void main(String[] args) {
		TestAssassin test = new TestAssassin();
		test.test1();
		test.test2();
		test.test3();
		test.test4();
		test.test5();
		test.test6();
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
		Test.test(plateau.getPersonnage(1)==assassin,"récupération du personnage de l'assassin");
		Test.test(plateau.getPersonnage(1).getRang()==1,"récupération du rang");		
	}
	public void test2() {
		System.out.println("TEST DE L'ASSASSINAT D'UN PERSONNAGE");
		
		//Initialisation des personnages et du Plateau de Jeu
		Roi roi = new Roi();
		Assassin assassin = new Assassin();
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		//Ajout des personnages au Plateau de Jeu
		plateau.ajouterPersonnage(roi);
		plateau.ajouterPersonnage(assassin);
		
		Joueur joueur1 = new Joueur("Milou");
		Joueur joueur2 = new Joueur("Rantanplan");
		
		plateau.ajouterJoueur(joueur1);
		plateau.ajouterJoueur(joueur2);
		
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		
		
		// on utilise le pouvoir de l'assassin
		// NB: seul le roi peut être assassiné dans cette situation
		do {
			assassin.utiliserPouvoir();
		}while(!roi.getAssassine());
		
		//Test
		Test.test(roi.getAssassine(), " roi:"+roi.getAssassine());
		Test.test(!assassin.getAssassine(), " assassin:"+assassin.getAssassine());
	}
	public void test3() {
		System.out.println("TEST DE L'ASSASSINAT D'UN PERSONNAGE (AVATAR)");
		
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

		Joueur joueur1 = new Joueur("Milou");
		Joueur joueur2 = new Joueur("Rantanplan");
		
		plateau.ajouterJoueur(joueur1);
		plateau.ajouterJoueur(joueur2);
		
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		
		// On force l'utilisation du pouvoir par l'avatar pour vérifier le bon fontionnement
		// NB: Choix arbitraire du roi pour finir la boucle mais marche avec n'imporque quel personnage
		do {
			assassin.utiliserPouvoirAvatar();			
		}while(!roi.getAssassine());
		
		//Test
		Test.test(roi.getAssassine(),"Un personnage est mort ");
	}
	
	public void test4() {
		System.out.println("TEST SURVIE DE L'ASSASSIN ");
		
		//Initialisation des personnages et du Plateau de Jeu
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		Assassin assassin = new Assassin();
		//Ajout des personnages au Plateau de Jeu
		plateau.ajouterPersonnage(roi);
		plateau.ajouterPersonnage(assassin);
		
		Joueur joueur1 = new Joueur("Milou");
		Joueur joueur2 = new Joueur("Rantanplan");

		plateau.ajouterJoueur(joueur1);
		plateau.ajouterJoueur(joueur2);

		
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);

		
		// On force l'utilisation du pouvoir par l'avatar pour vérifier le bon fontionnement
		// NB: Choix arbitraire du roi pour finir la boucle mais marche avec n'imporque quel personnage
		do {
			assassin.utiliserPouvoir();			
		}while(assassin.getAssassine());
		
		//Test
		Test.test(!assassin.getAssassine(),"L'assassin n'est pas mort ");
	}
	
	public void test5() {
		System.out.println("TEST SURVIE DE L'ASSASSIN (AVATAR)");
		
		//Initialisation des personnages et du Plateau de Jeu
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		Assassin assassin = new Assassin();
		//Ajout des personnages au Plateau de Jeu
		plateau.ajouterPersonnage(roi);
		plateau.ajouterPersonnage(assassin);
		
		Joueur joueur1 = new Joueur("Milou");
		Joueur joueur2 = new Joueur("Rantanplan");

		plateau.ajouterJoueur(joueur1);
		plateau.ajouterJoueur(joueur2);

		
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);

		
		// On force l'utilisation du pouvoir par l'avatar pour vérifier le bon fontionnement
		// NB: Choix arbitraire du roi pour finir la boucle mais marche avec n'imporque quel personnage
		do {
			assassin.utiliserPouvoirAvatar();			
		}while(assassin.getAssassine());
		
		//Test
		Test.test(!assassin.getAssassine(),"L'assassin n'est pas mort ");
	}

	
	public void test6() {
		System.out.println("TEST SELECTION DE LA VICTIME PARMIS TOUS LES PERSONNAGES PRESENT LE PLATEAU DE JEU");
		
		//Initialisation des personnages et du Plateau de Jeu
		Roi roi = new Roi();
		Assassin assassin = new Assassin();
		Condottiere condottiere = new Condottiere();
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		//Ajout des personnages au Plateau de Jeu
		plateau.ajouterPersonnage(roi);
		plateau.ajouterPersonnage(assassin);
		
		// on utilise le pouvoir de l'assassin avant l'attribution à un joueur
		// NB: Le pouvoir ne sera donc pas utiliser
		assassin.utiliserPouvoir();
		
		Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		assassin.setJoueur(joueur1);

		//Test
		Test.test(plateau.getNombrePersonnages()== 2,"Selection parmis tous les personnages du plateau");
	}
}
