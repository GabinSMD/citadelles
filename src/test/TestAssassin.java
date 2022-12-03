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
		
		Joueur joueur1 = new Joueur("Milou");
		
		plateau.ajouterJoueur(joueur1);
		assassin.setJoueur(joueur1);
		
		// on utilise le pouvoir de l'assassin
		// NB: seul le roi peut être assassiné dans cette situation
		do {
			assassin.utiliserPouvoir();
			System.out.println("# Si si tu veux utiliser ton pouvoir :) #");
		}while(!roi.getAssassine());
		
		//Test
		Test.test(roi.getAssassine(), " roi:"+roi.getAssassine());
		Test.test(!assassin.getAssassine(), " assassin:"+assassin.getAssassine());
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
		

		Joueur joueur1 = new Joueur("Milou");
		Joueur joueur2 = new Joueur("Rantanplan");
		Joueur joueur3 = new Joueur("Gabin");
		Joueur joueur4 = new Joueur("Sofiane");
		Joueur joueur5 = new Joueur("Kilian");
		
		plateau.ajouterJoueur(joueur1);
		plateau.ajouterJoueur(joueur2);
		plateau.ajouterJoueur(joueur3);
		plateau.ajouterJoueur(joueur4);
		plateau.ajouterJoueur(joueur5);
		
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		condottiere.setJoueur(joueur3);
		eveque.setJoueur(joueur4);
		marchande.setJoueur(joueur5);
		
		// On force l'utilisation du pouvoir par l'avatar pour vérifier le bon fontionnement
		// NB: Choix arbitraire du roi pour finir la boucle mais marche avec n'imporque quel personnage
		do {
			assassin.utiliserPouvoirAvatar();			
		}while(!roi.getAssassine());
		
		//Test
		Test.test(roi.getAssassine() || marchande.getAssassine() || eveque.getAssassine() || condottiere.getAssassine(),"Un personnage est mort ");
		Test.test(!assassin.getAssassine(),"L'assassin n'est pas mort ");
	}
	
	public void test4() {
		System.out.println("TEST DE L'ASSASSINAT DU ROI");
		
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
		Test.test(!roi.getAssassine(), "Le pouvoir n'a pas été utilisé ");
	}
}
