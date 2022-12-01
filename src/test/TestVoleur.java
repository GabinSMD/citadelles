package test;

import modele.Assassin;
import modele.Roi;
import modele.Voleur;

import modele.Joueur;
import modele.Marchande;
import modele.PlateauDeJeu;

public class TestVoleur {
	public static void main(String[] args) {
		TestVoleur test = new TestVoleur();
		test.test1();
		test.test2();
		test.test3();
	}
	
	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Voleur voleur = new Voleur();
		plateau.ajouterPersonnage(voleur);		
		Test.test(plateau.getNombrePersonnages()== 3,"nombre de joueurs");
		Test.test(plateau.getPersonnage(2)==voleur,
				"récupération du personnage du voleur");
		Test.test(plateau.getPersonnage(2).getRang()==2,
				"rang du voleur");
		
	}
	public void test2() {
		System.out.println("TEST DU VOL DU ROI");
		PlateauDeJeu plateau = new PlateauDeJeu();
		// cr ation de trois personnages
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Voleur voleur = new Voleur();
		plateau.ajouterPersonnage(voleur);		
			
		// cr ation de trois joueurs
		Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Rantanplan");
		plateau.ajouterJoueur(joueur3);
		
		// on associe les personnages aux joueurs
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		voleur.setJoueur(joueur3);
		
		// on utilise le pouvoir du voleur
		// NB: seul le roi peut  tre vol  dans cette situation

		roi.ajouterPieces();
		voleur.utiliserPouvoir();
		
		// tests:
		Test.test(roi.getVole(),"le roi est bien volé ");
		Test.test(!voleur.getVole(),"le voleur n'est pas volé ");
		Test.test(roi.getJoueur().nbPieces()==0, "le trésor du roi est vide");
		Test.test(voleur.getJoueur().nbPieces()==2, "le trésor du voleur contient deux pièces");
	}
	public void test3() {
		System.out.println("TEST DU VOL (AVATAR)");
		PlateauDeJeu plateau = new PlateauDeJeu();
		// création de trois personnages
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Voleur voleur = new Voleur();
		plateau.ajouterPersonnage(voleur);	
		Marchande marchande = new Marchande();
		plateau.ajouterPersonnage(marchande);	
		
		//Affichage de la liste des cibles visible par l'avatar
		System.out.println("Liste des cibles du Voleur :");
		for(int i=0; i<4; i++) {
			System.out.println(plateau.getPersonnage(i).getNom());
		}
		
		// on utilise le pouvoir aléatoire du voleur
		voleur.utiliserPouvoirAvatar();
		
		// tests:
		Test.test(roi.getVole()||marchande.getVole(),"Un personnage aléatoire ( Roi ou Marchande ) à été volé  ");
		Test.test(!voleur.getVole(), "Le voleur n'a pas été volé");
		Test.test(!assassin.getVole(), "Le assassin n'a pas été volé");
	}
}
