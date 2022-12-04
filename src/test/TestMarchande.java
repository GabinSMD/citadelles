package test;

import modele.Marchande;
import modele.PlateauDeJeu;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Quartier;

public class TestMarchande {

	public static void main(String[] args) {
		TestMarchande test= new TestMarchande();
		test.test1();
		test.test2();
		test.test3();
		test.test4();
		test.test5();
		test.test6();
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Marchande Marchande = new Marchande();
		Test.test(Marchande.getNom().equals("Marchande"),"test du nom du personnage");
		Test.test(Marchande.getRang()== 6,"test du rang du personnage");
		Test.test(Marchande.getCaracteristiques().equals(Caracteristiques.MARCHANDE),
				"test des caractéristiques du personnage");
		Test.test(Marchande.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(Marchande.getAssassine()==false, "test de l'initialisation de la variable \"assassine\"");
		Test.test(Marchande.getVole()==false, "test de l'initialisation de la variable \"vole\"");
	}
	
	public void test2(){
		System.out.println("TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES");
		Joueur joueur = new Joueur("Billy");
		Marchande marchande = new Marchande();
		Quartier quartier1 = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2);
		marchande.setJoueur(joueur);
		Test.test(marchande.getJoueur().nbPieces() == 0, "test du nombre de pièces d'or avant perception des ressources spécifiques");
		marchande.construire(quartier1);
		marchande.construire(quartier2);
		marchande.construire(quartier3);		
		marchande.percevoirRessourcesSpecifiques();
		Test.test(marchande.getJoueur().nbPieces() == 2, "test du nombre de pièces d'or après perception de ressources spécifiques avec 2 quartiers commerçants");			
	}
	
	public void test3(){
		System.out.println("TEST DE L'UTILISATEUR DU POUVOIR");
		Joueur joueur = new Joueur("Billy");
		Marchande marchande = new Marchande();
		marchande.setJoueur(joueur);
		Test.test(marchande.getJoueur().nbPieces() == 0, "test du nombre de pièces d'or avant l'utilisation du pouvoir");
		marchande.utiliserPouvoir();
		Test.test(marchande.getJoueur().nbPieces() == 1, "test du nombre de pièces d'or après l'utilisation du pouvoir");
			
	}
	
	public void test4(){
		System.out.println("TEST DE L'UTILISATEUR DU POUVOIR (AVATAR)");
		Joueur joueur = new Joueur("Billy");
		Marchande marchande = new Marchande();
		marchande.setJoueur(joueur);
		Test.test(marchande.getJoueur().nbPieces() == 0, "test du nombre de pièces d'or avant l'utilisation du pouvoir");
		marchande.utiliserPouvoirAvatar();
		Test.test(marchande.getJoueur().nbPieces() == 1, "test du nombre de pièces d'or après l'utilisation du pouvoir");
			
	}
	
	public void test5(){
		System.out.println("TEST DE L'UTILISATION DU POUVOIR SANS ASSOCIATION DE JOUEUR");
		// On crée un plateau et on ajoute des cartes Quartier à la pioche:		
				PlateauDeJeu plateau = new PlateauDeJeu();

				// On ajoute le personnage au plateau:
				Marchande marchande = new Marchande();
				plateau.ajouterPersonnage(marchande);

				marchande.utiliserPouvoir();
				Test.test(marchande.getJoueur()==null, "Non attribution de Joueur ");
	}

	public void test6(){
		System.out.println("TEST DE L'UTILISATION DU POUVOIR SANS ASSOCIATION DE JOUEUR (AVATAR)");
		// On crée un plateau et on ajoute des cartes Quartier à la pioche:		
		PlateauDeJeu plateau = new PlateauDeJeu();

		// On ajoute le personnage au plateau:
		Marchande marchande = new Marchande();
		plateau.ajouterPersonnage(marchande);

		marchande.utiliserPouvoirAvatar();
		Test.test(marchande.getJoueur()==null, "Non attribution de Joueur ");

	}
}
