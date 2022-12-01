package test;

import java.util.ArrayList;

import application.JeuPublic;
import controleur.Interaction;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Personnage;
import modele.Quartier;

public class TestMerveille extends JeuPublic {
	public static void main(String[] args) {
		TestMerveille test = new TestMerveille();
		//test.test1(); //Bibliothèque OK
		test.test2(); //Carrière
		//test.test3(); //Cours des miracles OK
		//test.test4(); //Donjon OK
		//test.test5(); //Dracoport OK
		//test.test6(); //Ecole de magie OK
		//test.test7(); //Fontaine aux souhaits OK
		//test.test8(); //Forge OK
		//test.test9(); //Laboratoire OK
		//test.test10(); //Manufacture OK
		//test.test11(); //Salle des cartes OK
		//test.test12(); //Statue equestre OK
		//test.test13(); //Trésor Imperial OK
		//test.test14(); //Tripot OK

	}

	public void test1() { // Bibliothèque
		System.out.println("TEST DE LA BIBLIOTHEQUE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		
		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.BIBLIOTHEQUE));
		
		int nbQuartiersAvantPioche = joueurAleatoire.nbQuartiersDansMain();
		jeu.percevoirRessource(personnageAleatoire);
		int nbQuartiersApresPioche = joueurAleatoire.nbQuartiersDansMain();

		if (jeu.choix == 2) {
			Test.test(nbQuartiersApresPioche == nbQuartiersAvantPioche + 2, "Nombre de cartes en main adéquat");
		}
	}

	public void test2() { // Carrière
		System.out.println("TEST DE LA CARRIERE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		
		int nbPrison=0;
		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		joueurAleatoire.ajouterPieces(10);
		
		int nbCartePossedez = personnageAleatoire.getJoueur().nbQuartiersDansMain();
		for(int i=0; i<nbCartePossedez; i++) {
			personnageAleatoire.getJoueur().retirerQuartierDansMain();
		}

		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Prison", Quartier.TYPE_QUARTIERS[1], 2));
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Prison", Quartier.TYPE_QUARTIERS[1], 2));
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARRIERE));

		for(int i=0;i<3;i++) {
			jeu.construire(personnageAleatoire);
		}


		for (int i = 0; i < personnageAleatoire.getJoueur().nbQuartiersDansCite(); i++) {
			if (personnageAleatoire.getJoueur().getCite()[i].getNom()=="Prison") {
				nbPrison++;
			}
		}
		
		Test.test(nbPrison == 2, "Les prisons sont construites");
	}

	public void test3() { // Cours des miracles
		System.out.println("TEST DE LA COURS DES MIRACLES");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		int[] quartierParType = { 0, 0, 0, 0, 0 };
		
		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Cours des miracles", Quartier.TYPE_QUARTIERS[4], 2, Caracteristiques.COURS));

		jeu.calculDesPoints();
		for (int i = 0; i < joueurAleatoire.nbQuartiersDansCite(); i++) {
			String typeQuartier = joueurAleatoire.getCite()[i].getType();
			if (typeQuartier == "RELIGIEUX") {
				quartierParType[0] += 1;
			} else if (typeQuartier == "MILITAIRE") {
				quartierParType[1] += 1;
			} else if (typeQuartier == "NOBLE") {
				quartierParType[2] += 1;
			} else if (typeQuartier == "COMMERCANT") {
				quartierParType[3] += 1;
			} else if (typeQuartier == "MERVEILLE") {
				quartierParType[4] += 1;
			}
			Test.test(quartierParType[0] == 0, "Nombre des quartiers de type religieux == 0");
			Test.test(quartierParType[1] == 0, "Nombre des quartiers de type militaire == 0");
			Test.test(quartierParType[2] == 0, "Nombre des quartiers de type noble == 0");
			Test.test(quartierParType[3] == 0, "Nombre des quartiers de type commerçant == 0");
			Test.test(quartierParType[4] == 1, "Nombre des quartiers de type merveille == 1");
		}
	}

	public void test4() { // Donjon
		System.out.println("TEST DU DONJON");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		
		int indexJoueurAleatoire = 0;
		int indexJoueurAleatoire2 = 0;
		do { 
			indexJoueurAleatoire = jeu.generateur.nextInt(4);
			indexJoueurAleatoire2 = jeu.generateur.nextInt(4);
		} while (indexJoueurAleatoire == indexJoueurAleatoire2);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Joueur joueurAleatoire2 = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire2);
		Personnage personnageCondottierre = jeu.plateauDeJeu.getPersonnage(7);
		Personnage personnageRoi = jeu.plateauDeJeu.getPersonnage(3);
		personnageCondottierre.setJoueur(joueurAleatoire);
		personnageRoi.setJoueur(joueurAleatoire2);
		personnageRoi.construire(new Quartier("Donjon",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.DONJON));
		
		personnageCondottierre.utiliserPouvoir();
		
		Test.test(joueurAleatoire2.quartierPresentDansCite("Donjon") == true, "Le quartier Donjon est toujours dans la cité");
		
	}

	public void test5() { // Dracoport
		System.out.println("TEST DU DRACOPORT");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Dracoport", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.DRACOPORT));
		
		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (i == indexJoueurAleatoire) {
				Test.test(jeu.pointsCoutConstruction.get(indexJoueurAleatoire) == 6, "Comptage des points de construction");
				Test.test(jeu.pointsMerveille.get(indexJoueurAleatoire) == 2,"Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(indexJoueurAleatoire) == 8, "Comptage des points totaux");
			}
		}
	}

	public void test6() { // Ecole de magie
		System.out.println("TEST DE L'ECOLE DE MAGIE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		
		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageRoi = jeu.plateauDeJeu.getPersonnage(3);
		personnageRoi.setJoueur(joueurAleatoire);
		personnageRoi.construire(new Quartier("Ecole de magie",Quartier.TYPE_QUARTIERS[4], 6,Caracteristiques.MAGIE));
		
		//Reconstruction de la merveille
		for (int k = 0; k < joueurAleatoire.nbQuartiersDansCite(); k++) {
			if (joueurAleatoire.quartierPresentDansCite("Ecole de magie")) {
				System.out.println("Comment considerez-vous l'école de magie ?");
				for (int l = 0; l < nbTypeQuartier; l++) {
					System.out.println(l + " - " + Quartier.TYPE_QUARTIERS[l]);
				}
				choix = Interaction.lireUnEntier(0, nbTypeQuartier);
				switch (choix) {
				case 0:
					joueurAleatoire.getCite()[k].setType(Quartier.TYPE_QUARTIERS[0]);
					break;
				case 1:
					joueurAleatoire.getCite()[k].setType(Quartier.TYPE_QUARTIERS[1]);
					break;
				case 2:
					joueurAleatoire.getCite()[k].setType(Quartier.TYPE_QUARTIERS[2]);
					break;
				case 3:
					joueurAleatoire.getCite()[k].setType(Quartier.TYPE_QUARTIERS[3]);
					break;
				case 4:
					joueurAleatoire.getCite()[k].setType(Quartier.TYPE_QUARTIERS[4]);
					break;
				}
			}
		}
		
		int nbPiecesAvantPercevoir = joueurAleatoire.nbPieces();
		personnageRoi.percevoirRessourcesSpecifiques();
		int nbPiecesApresPercevoir = joueurAleatoire.nbPieces();

		if (choix == 2) {
			Test.test(nbPiecesApresPercevoir == nbPiecesAvantPercevoir + 1, "1 pièce ajouté dans le trésor");
		} else {
			Test.test(nbPiecesApresPercevoir == nbPiecesAvantPercevoir, "0 pièce ajouté dans le trésor");
		}

	}

	public void test7() { // Fontaine aux souhaits
		System.out.println("TEST DE LA FONTAINE AUX SOUHAITS");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();

		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.BIBLIOTHEQUE));
		personnageAleatoire.construire(new Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FONTAINE));

		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (i == indexJoueurAleatoire) {
				Test.test(jeu.pointsCoutConstruction.get(indexJoueurAleatoire) == 11, "Comptage des points de construction");
				Test.test(jeu.pointsMerveille.get(indexJoueurAleatoire) == 2,"Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(indexJoueurAleatoire) == 13, "Comptage des points totaux");
			}
		}
	}

	public void test8() { // Forge
		System.out.println("TEST DE LA FORGE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		
		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Forge",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FORGE));
		
		int nbCartesAvantMerveille = joueurAleatoire.nbQuartiersDansMain();
		int nbPiecesAvantMerveille = joueurAleatoire.nbPieces();
		int nbCartesAprèsMerveille = nbCartesAvantMerveille;
		int nbPiecesAprèsMerveille = nbPiecesAvantMerveille;
		
		if (joueurAleatoire.quartierPresentDansCite("Forge")) {
			System.out.println("Vous avez " + joueurAleatoire.nbPieces() + " pièces dans votre trésorerie.");
			System.out.println("Voulez vous payez 2 pièces d’or pour piocher 3 cartes ?");
			System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
			if (Interaction.lireOuiOuNon()) {
				for (int k = 0; k < 3; k++) {
					joueurAleatoire.ajouterQuartierDansMain(jeu.pioche.piocher());
				}
				nbCartesAprèsMerveille = joueurAleatoire.nbQuartiersDansMain();
				Test.test(nbCartesAprèsMerveille == nbCartesAvantMerveille + 3, "3 cartes ajouté en main");
				
				joueurAleatoire.retirerPieces(2);
				nbPiecesAprèsMerveille = joueurAleatoire.nbPieces();
				Test.test(nbPiecesAprèsMerveille == nbPiecesAvantMerveille - 2, "2 pièces retiré du trésor");

			} else {
				Test.test(nbCartesAprèsMerveille == nbCartesAvantMerveille, "Aucune différence au niveau des cartes en main");
				Test.test(nbPiecesAprèsMerveille == nbPiecesAvantMerveille, "Aucune différence au niveau du trésor");
			}
		}
		

	}

	public void test9() { // Laboratoire
		System.out.println("TEST DU LABORATOIRE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();

		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.LABORATOIRE));
		
		int nbCartesAvantMerveille = joueurAleatoire.nbQuartiersDansMain();
		int nbPiecesAvantMerveille = joueurAleatoire.nbPieces();
		int nbCartesAprèsMerveille = nbCartesAvantMerveille;
		int nbPiecesAprèsMerveille = nbPiecesAvantMerveille;
		
		if (joueurAleatoire.quartierPresentDansCite("Laboratoire")) {
			System.out.println("Voulez-vous vous défausser d'1 carte pour recevoir 2 pièces d’or  ?");
			System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
			if (Interaction.lireOuiOuNon()) {
				int nbCartePossedee = joueurAleatoire.getMain().size();
				ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(joueurAleatoire.getMain());
				System.out.println("Quelle carte voulez vous défausser ?");
				for (int k = 0; k < nbCartePossedee; k++) {
					System.out.println(k + ". " + copieTableau.get(k).getNom());
				}
				int choix = Interaction.lireUnEntier(0, nbCartePossedee);
				jeu.pioche.ajouter(copieTableau.get(choix));
				copieTableau.remove(choix);
				joueurAleatoire.ajouterPieces(2);
				nbPiecesAprèsMerveille = joueurAleatoire.nbPieces();
				Test.test(nbPiecesAprèsMerveille == nbPiecesAvantMerveille + 2, "2 pièces ajouté au trésor");
				
				nbCartePossedee = joueurAleatoire.getMain().size();
				for (int k = 0; k < nbCartePossedee; k++) {
					joueurAleatoire.retirerQuartierDansMain();
				}
				for (int k = 0; k < copieTableau.size(); k++) {
					joueurAleatoire.ajouterQuartierDansMain(copieTableau.get(k));
				}
				nbCartesAprèsMerveille = joueurAleatoire.nbQuartiersDansMain();
				Test.test(nbCartesAprèsMerveille == nbCartesAvantMerveille - 1, "1 carte retiré de la main");
			} else {
				Test.test(nbCartesAprèsMerveille == nbCartesAvantMerveille, "Aucune différence au niveau des cartes en main");
				Test.test(nbPiecesAprèsMerveille == nbPiecesAvantMerveille, "Aucune différence au niveau du trésor");
			}
		}
	}

	public void test10() { // Manufacture
		System.out.println("TEST DE LA MANUFACTURE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();

		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Manufacture",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.MANUFACTURE));
		
		joueurAleatoire.ajouterPieces(2);
		Test.test(joueurAleatoire.nbPieces() == 4, "Nombre de pièces avant construction");
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARRIERE));
		jeu.construire(personnageAleatoire);
		Test.test(joueurAleatoire.nbPieces() == 0, "Nombre de pièces après construction");
	}

	public void test11() { // Salle des cartes
		System.out.println("TEST DE LA SALLE DES CARTES");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		
		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);

		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Salle des cartes",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARTE));
		
		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (i == indexJoueurAleatoire) {
				Test.test(jeu.pointsCoutConstruction.get(indexJoueurAleatoire) == 5, "Comptage des points de construction");
				Test.test(jeu.pointsMerveille.get(indexJoueurAleatoire) == 4,"Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(indexJoueurAleatoire) == 9, "Comptage des points totaux");
			}
		}
	}

	public void test12() { // Statue equestre
		System.out.println("TEST DE LA STATUE EQUESTRE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();

		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Statue equestre",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.STATUE));
		joueurAleatoire.setPossedeCouronne(true);

		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (i == indexJoueurAleatoire) {
				Test.test(joueurAleatoire.getPossedeCouronne() == true, "Possede la couronne");
				Test.test(jeu.pointsCoutConstruction.get(indexJoueurAleatoire) == 3, "Comptage des points de construction");
				Test.test(jeu.pointsMerveille.get(indexJoueurAleatoire) == 5,"Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(indexJoueurAleatoire) == 8, "Comptage des points totaux");
			}
		}
	}

	public void test13() { // Trésor Imperial
		System.out.println("TEST DU TRESOR IMPERIAL");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();

		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.construire(new Quartier("Trésor Imperial",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.TRESOR));

		System.out.println(joueurAleatoire.nbPieces());

		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (i == indexJoueurAleatoire) {
				Test.test(jeu.pointsCoutConstruction.get(indexJoueurAleatoire) == 5, "Comptage des points de construction");
				Test.test(jeu.pointsMerveille.get(indexJoueurAleatoire) == 2,"Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(indexJoueurAleatoire) == 7, "Comptage des points totaux");
			}
		}
	}

	public void test14() { // Tripot
		System.out.println("TEST DU TRIPOT");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();

		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		personnageAleatoire.ajouterPieces();
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Tripot",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.TRIPOT));
		jeu.construire(personnageAleatoire);
		
		
		Test.test(joueurAleatoire.quartierPresentDansCite("Tripot") == true,"Tripot présent dans cité");
		Test.test(joueurAleatoire.nbPieces()+joueurAleatoire.nbQuartiersDansMain() == 2,"Nombres de cartes et de pièces en main adéquat");

		
		
	}
		/*
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6,
		 * Caracteristiques.BIBLIOTHEQUE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5,
		 * Caracteristiques.CARRIERE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Cours des miracles",Quartier.TYPE_QUARTIERS[4], 2,
		 * Caracteristiques.COURS));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Donjon",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.DONJON));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Dracoport",Quartier.TYPE_QUARTIERS[4], 6,
		 * Caracteristiques.DRACOPORT));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Ecole de magie",Quartier.TYPE_QUARTIERS[4], 6,
		 * Caracteristiques.MAGIE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4], 5,
		 * Caracteristiques.FONTAINE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Forge",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FORGE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[4], 5,
		 * Caracteristiques.LABORATOIRE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Manufacture",Quartier.TYPE_QUARTIERS[4], 5,
		 * Caracteristiques.MANUFACTURE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Salle des cartes",Quartier.TYPE_QUARTIERS[4], 5,
		 * Caracteristiques.CARTE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Statue equestre",Quartier.TYPE_QUARTIERS[4], 3,
		 * Caracteristiques.STATUE));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Trésor Imperial",Quartier.TYPE_QUARTIERS[4], 5,
		 * Caracteristiques.TRESOR));
		 * 
		 * jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new
		 * Quartier("Tripot",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.TRIPOT));
		 */
}
