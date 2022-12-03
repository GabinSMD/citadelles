package test;

import modele.Assassin;
import modele.Condottiere;
import modele.Eveque;
import modele.Roi;
import modele.Voleur;

import modele.Joueur;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

public class TestCondottiere {
	public static void main(String[] args) {
		TestCondottiere test = new TestCondottiere();
		test.test1();
		test.test2();
		test.test3();
		test.test4();
	}
	
	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Condottiere condottiere = new Condottiere();
		plateau.ajouterPersonnage(condottiere);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Voleur voleur= new Voleur();
		plateau.ajouterPersonnage(voleur);
		Test.test(plateau.getNombrePersonnages()== 3,"nombre de personnages");
		Test.test(plateau.getPersonnage(0)==condottiere, "récupération du personnage du condottiere");
		Test.test(plateau.getPersonnage(0).getRang()==8, "rang du condottiere");		
	}
	
	public void test2() {
		System.out.println("TEST DU POUVOIR DU CONDOTTIERE");
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		// création de quatre personnages
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Condottiere condottiere= new Condottiere();
		plateau.ajouterPersonnage(condottiere);
		Eveque eveque= new Eveque();
		plateau.ajouterPersonnage(eveque);
			
		// création de trois joueurs
		Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Rantanplan");
		plateau.ajouterJoueur(joueur3);
			
		// on associe les personnages aux joueurs
		roi.setJoueur(joueur1);
		condottiere.setJoueur(joueur2);
		eveque.setJoueur(joueur3);
		condottiere.ajouterPieces();
		
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
		q = new Quartier("cath�drale",Quartier.TYPE_QUARTIERS[0],5); 
		pioche.ajouter(q);
		q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); 
		pioche.ajouter(q);
		q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); 
		pioche.ajouter(q);
		q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],5); 
		pioche.ajouter(q);
		q = new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez..."); 
		pioche.ajouter(q);
		pioche.melanger();
		
		// on distribue les cartes aux joueurs:
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur3.ajouterQuartierDansCite(pioche.piocher());
		joueur3.ajouterQuartierDansCite(pioche.piocher());
		
		// on affiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		Quartier [] mainRoi = roi.getJoueur().getCite();
		for(int i = 0; i< roi.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainRoi[i].getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + eveque.getJoueur().getNom() + "): ");
		Quartier [] mainAssassin = eveque.getJoueur().getCite();
		for(int i = 0; i< eveque.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainAssassin[i].getNom() + ", ");
		System.out.println("");
		System.out.print("Main du condottiere (" + condottiere.getJoueur().getNom() + "): ");
		Quartier [] mainCondottiere = condottiere.getJoueur().getCite();
		for(int i = 0; i< condottiere.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainCondottiere[i].getNom() + ", ");
		System.out.println("");
				
		// utiliser le pouvoir du condottiere:	
		condottiere.utiliserPouvoir();
		
		// on réaffiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		for(int i = 0; i< roi.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainRoi[i].getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + eveque.getJoueur().getNom() + "): ");
		for(int i = 0; i< eveque.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainAssassin[i].getNom() + ", ");System.out.println("");
		System.out.print("Main du condottiere (" + condottiere.getJoueur().getNom() + "): ");
		for(int i = 0; i< condottiere.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainCondottiere[i].getNom() + ", ");
		System.out.println("");
		
	}
	
	public void test3() {
		System.out.println("TEST DU POUVOIR DU CONDOTTIERE (AVATAR)");
		PlateauDeJeu plateau = new PlateauDeJeu();
		
		// création de quatre personnages
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);
		Condottiere condottiere= new Condottiere();
		plateau.ajouterPersonnage(condottiere);
			
		// création de trois joueurs
		Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Rantanplan");
		plateau.ajouterJoueur(joueur3);
			
		// on associe les personnages aux joueurs
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		condottiere.setJoueur(joueur3);
		condottiere.ajouterPieces();
		
		// création d'une pioche:
		Pioche pioche = plateau.getPioche();

		pioche.ajouter(new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1)); 
		pioche.ajouter(new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2)); 
		pioche.ajouter(new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2));
		pioche.ajouter(new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5)); 
		pioche.ajouter(new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1)); 
		pioche.ajouter(new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2)); 
		pioche.ajouter(new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4,"A la fin de la partie, ...")); 
		pioche.ajouter(new Quartier("cath�drale",Quartier.TYPE_QUARTIERS[0],5)); 
		pioche.ajouter(new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3)); 
		pioche.ajouter(new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3)); 
		pioche.ajouter(new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],5)); 
		pioche.ajouter(new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez...")); 

		pioche.melanger();
		
		// on distribue les cartes aux joueurs:
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur3.ajouterQuartierDansCite(pioche.piocher());
		joueur3.ajouterQuartierDansCite(pioche.piocher());
		
		// on affiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		Quartier [] mainRoi = roi.getJoueur().getCite();
		for(int i = 0; i< roi.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainRoi[i].getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
		Quartier [] mainAssassin = assassin.getJoueur().getCite();
		for(int i = 0; i< assassin.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainAssassin[i].getNom() + ", ");
		System.out.println("");
		System.out.print("Main du condottiere (" + condottiere.getJoueur().getNom() + "): ");
		Quartier [] mainCondottiere = condottiere.getJoueur().getCite();
		for(int i = 0; i< condottiere.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainCondottiere[i].getNom() + ", ");
		System.out.println("");
				
		// utiliser le pouvoir du condottiere:		
		condottiere.utiliserPouvoirAvatar();
		
		// on réaffiche la main de chaque joueur:
		System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
		for(int i = 0; i< roi.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainRoi[i].getNom() + ", ");
		System.out.println("");
		System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
		for(int i = 0; i< assassin.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainAssassin[i].getNom() + ", ");System.out.println("");
		System.out.print("Main du condottiere (" + condottiere.getJoueur().getNom() + "): ");
		for(int i = 0; i< condottiere.getJoueur().nbQuartiersDansCite(); i++)
			System.out.print(mainCondottiere[i].getNom() + ", ");
		System.out.println("");
		
	}
	
	public void test4() {
		System.out.println("TEST DES RESSOURCES SPECIFIQUES DU CONDOTTIERE");
		Joueur joueur = new Joueur("Billy");
		Condottiere condottiere = new Condottiere();
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		condottiere.setJoueur(joueur);
		condottiere.ajouterPieces();
		Test.test(condottiere.getJoueur().nbPieces() == 2, "test du nombre de pièces d'or avant perception");
		condottiere.construire(quartier1);
		condottiere.construire(quartier2);
		condottiere.construire(quartier3);		
		condottiere.percevoirRessourcesSpecifiques();
		Test.test(condottiere.getJoueur().nbPieces() == 3, "test du nombre de pièces d'or après perception de ressources spécifiques avec 1 quartier militaire");
	}
	
	
}
