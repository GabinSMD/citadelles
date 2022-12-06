package test;

import application.JeuPublic;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Personnage;
import modele.Quartier;

public class TestMerveille extends JeuPublic {
	public static void main(String[] args) {
		TestMerveille test = new TestMerveille();
		//test.test1(); //Bibliothèque
		//test.test2(); //Carrière
		//test.test3(); //Cours des miracles
		//test.test4(); //Donjon
		//test.test5(); //Dracoport
		//test.test6(); //Ecole de magie
		//test.test7(); //Fontaine aux souhaits
		//test.test8(); //Forge
		//test.test9(); //Laboratoire
		//test.test10(); //Manufacture
		//test.test11(); //Salle des cartes
		//test.test12(); //Statue equestre
		//test.test13(); //Trésor Imperial
		test.test14(); //Tripot

	}

	public void test1() { // Bibliothèque
		System.out.println("TEST DE LA BIBLIOTHEQUE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.BIBLIOTHEQUE));
		
		do {
			int nbQuartiersAvantPioche = joueurAleatoire.nbQuartiersDansMain();
			jeu.percevoirRessource(joueurAleatoire.getPersonnage());
			int nbQuartiersApresPioche = joueurAleatoire.nbQuartiersDansMain();
	
			if (jeu.choix == 2) {
				Test.test(nbQuartiersApresPioche == nbQuartiersAvantPioche + 2, "Nombre de cartes en main adéquat");
			}
		}while(jeu.choix != 2);
	}

	public void test2() { // Carrière
		System.out.println("TEST DE LA CARRIERE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		int nbPrison = 0;
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.ajouterPieces(10);
		
		int nbCartePossedez = joueurAleatoire.nbQuartiersDansMain();
		for(int i=0; i<nbCartePossedez; i++) {
			joueurAleatoire.retirerQuartierDansMain();
		}

		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Prison", Quartier.TYPE_QUARTIERS[1], 2));
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Prison", Quartier.TYPE_QUARTIERS[1], 2));
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARRIERE));
		
		if (joueurAleatoire.getAvatar()) {
			while(joueurAleatoire.nbQuartiersDansCite()<3) {
				jeu.construire(joueurAleatoire.getPersonnage());
				System.out.println("Nombre de quartiers : "+joueurAleatoire.nbQuartiersDansCite()+" et Nombre de pièces restantes : "+joueurAleatoire.nbPieces());
				
			}
		} else {
			for(int i=0;i<3;i++) {
				jeu.construire(joueurAleatoire.getPersonnage());
			}
		}


		for (int i = 0; i < joueurAleatoire.nbQuartiersDansCite(); i++) {
			if (joueurAleatoire.getCite()[i].getNom()=="Prison") {
				nbPrison++;
			}
		}
		
		Test.test(nbPrison == 2, "Les prisons sont construites");
	}

	public void test3() { // Cours des miracles
		System.out.println("TEST DE LA COURS DES MIRACLES");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		int[] quartierParType = { 0, 0, 0, 0, 0 };
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Cours des miracles", Quartier.TYPE_QUARTIERS[4], 2, Caracteristiques.COURS));

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
			indexJoueurAleatoire = jeu.generateur.nextInt(jeu.nombreJoueurs);
			indexJoueurAleatoire2 = jeu.generateur.nextInt(jeu.nombreJoueurs);
		} while (indexJoueurAleatoire == indexJoueurAleatoire2);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Joueur joueurAleatoire2 = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire2);
		Personnage personnageCondottierre = jeu.plateauDeJeu.getPersonnage(7);
		Personnage personnageRoi = jeu.plateauDeJeu.getPersonnage(3);
		personnageCondottierre.setJoueur(joueurAleatoire);
		personnageRoi.setJoueur(joueurAleatoire2);
		personnageRoi.construire(new Quartier("Donjon",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.DONJON));
		
		if (joueurAleatoire.getAvatar()) {
			personnageCondottierre.utiliserPouvoirAvatar();
		} else {
			personnageCondottierre.utiliserPouvoir();
		}
		
		
		Test.test(joueurAleatoire2.quartierPresentDansCite("Donjon"), "Le quartier Donjon est toujours dans la cité");
		
	}

	public void test5() { // Dracoport
		System.out.println("TEST DU DRACOPORT");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Dracoport", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.DRACOPORT));
		
		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i)==joueurAleatoire) {
				Test.test(jeu.pointsMerveille.get(i) == 2,"Comptage des points gagnés grâce aux Merveilles");
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
		//Test devant être effectué avec un personnage qui peut percevoir des ressources spécifiques, comme le Roi, l'Evêque, la Marchande ou le Condottiere
		personnageRoi.setJoueur(joueurAleatoire); 
		personnageRoi.construire(new Quartier("Ecole de magie",Quartier.TYPE_QUARTIERS[4], 6,Caracteristiques.MAGIE));
		
		jeu.ecoleDeMagie(personnageRoi);
		
		int nbPiecesAvantPercevoir = joueurAleatoire.nbPieces();
		personnageRoi.percevoirRessourcesSpecifiques();
		int nbPiecesApresPercevoir = joueurAleatoire.nbPieces();

		if (joueurAleatoire.getCite()[0].getType() == Quartier.TYPE_QUARTIERS[2]) {
			Test.test(nbPiecesApresPercevoir == nbPiecesAvantPercevoir + 1, "1 pièce ajouté dans le trésor");
		} else {
			Test.test(nbPiecesApresPercevoir == nbPiecesAvantPercevoir, "0 pièce ajouté dans le trésor");
		}

	}

	public void test7() { // Fontaine aux souhaits
		System.out.println("TEST DE LA FONTAINE AUX SOUHAITS");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.BIBLIOTHEQUE));
		joueurAleatoire.getPersonnage().construire(new Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FONTAINE));

		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i)==joueurAleatoire) {
				Test.test(jeu.pointsMerveille.get(i) == 2,"Comptage des points gagnés grâce aux Merveilles");
			}
		}
	}

	public void test8() { // Forge
		System.out.println("TEST DE LA FORGE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Forge",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FORGE));
		
		for (int k = 0; k < 3; k++) {
			joueurAleatoire.ajouterQuartierDansMain(jeu.pioche.piocher());
		}
		
		int nbCartesAvantMerveille = joueurAleatoire.nbQuartiersDansMain();
		int nbPiecesAvantMerveille = joueurAleatoire.nbPieces();
		int nbCartesAprèsMerveille = nbCartesAvantMerveille;
		int nbPiecesAprèsMerveille = nbPiecesAvantMerveille;
		
		jeu.forge(joueurAleatoire.getPersonnage());
		
		nbCartesAprèsMerveille = joueurAleatoire.nbQuartiersDansMain();
		nbPiecesAprèsMerveille = joueurAleatoire.nbPieces();
		
		if (nbPiecesAprèsMerveille == nbPiecesAvantMerveille && nbCartesAprèsMerveille == nbCartesAvantMerveille) {
			Test.test(nbPiecesAprèsMerveille == nbPiecesAvantMerveille && nbCartesAprèsMerveille == nbCartesAvantMerveille, "Aucune différence au niveau du trésor et au niveau des cartes");
		} else {
			Test.test(nbCartesAprèsMerveille == nbCartesAvantMerveille + 3 && nbPiecesAprèsMerveille == nbPiecesAvantMerveille - 2, "3 cartes ajouté en main et 2 pièces retiré au trésor");
		}
	}

	public void test9() { // Laboratoire
		System.out.println("TEST DU LABORATOIRE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.LABORATOIRE));
		
		int nbCartesAvantMerveille = joueurAleatoire.nbQuartiersDansMain();
		int nbPiecesAvantMerveille = joueurAleatoire.nbPieces();
		int nbCartesAprèsMerveille = nbCartesAvantMerveille;
		int nbPiecesAprèsMerveille = nbPiecesAvantMerveille;
		
		jeu.laboratoire(joueurAleatoire.getPersonnage());
		
		nbCartesAprèsMerveille = joueurAleatoire.nbQuartiersDansMain();
		nbPiecesAprèsMerveille = joueurAleatoire.nbPieces();
		
		if (nbPiecesAprèsMerveille == nbPiecesAvantMerveille && nbCartesAprèsMerveille == nbCartesAvantMerveille) {
			Test.test(nbPiecesAprèsMerveille == nbPiecesAvantMerveille && nbCartesAprèsMerveille == nbCartesAvantMerveille, "Aucune différence au niveau du trésor et au niveau des cartes");
		} else {
			Test.test(nbCartesAprèsMerveille == nbCartesAvantMerveille - 1 && nbPiecesAprèsMerveille == nbPiecesAvantMerveille + 2, "1 carte retiré de la main et 2 pièces ajouté au trésor");
		}
	}

	public void test10() { // Manufacture
		System.out.println("TEST DE LA MANUFACTURE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Manufacture",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.MANUFACTURE));
		
		int nbCartePossedez = joueurAleatoire.nbQuartiersDansMain();
		for(int i=0; i<nbCartePossedez; i++) {
			joueurAleatoire.retirerQuartierDansMain();
		}
		
		joueurAleatoire.ajouterPieces(2);
		Test.test(joueurAleatoire.nbPieces() == 4, "Nombre de pièces avant construction");
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARRIERE));
		do {		
			jeu.construire(joueurAleatoire.getPersonnage());
		}while(joueurAleatoire.nbQuartiersDansCite() < 2);

		Test.test(joueurAleatoire.nbPieces() == 0, "Nombre de pièces après construction");
	}

	public void test11() { // Salle des cartes
		System.out.println("TEST DE LA SALLE DES CARTES");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Salle des cartes",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARTE));
		
		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i)==joueurAleatoire) {
				Test.test(jeu.pointsMerveille.get(i) == joueurAleatoire.nbQuartiersDansMain(),"Comptage des points gagnés grâce aux Merveilles");
			}
		}
	}

	public void test12() { // Statue equestre
		System.out.println("TEST DE LA STATUE EQUESTRE");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Statue equestre",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.STATUE));
		joueurAleatoire.setPossedeCouronne(true);

		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i)==joueurAleatoire) {
				Test.test(joueurAleatoire.getPossedeCouronne(), "Possede la couronne");
				Test.test(jeu.pointsMerveille.get(i) == 5,"Comptage des points gagnés grâce aux Merveilles");
			}
		}
	}

	public void test13() { // Trésor Imperial
		System.out.println("TEST DU TRESOR IMPERIAL");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		joueurAleatoire.getPersonnage().construire(new Quartier("Trésor Imperial",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.TRESOR));

		jeu.calculDesPoints();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i)==joueurAleatoire) {
				Test.test(jeu.pointsMerveille.get(i) == joueurAleatoire.nbPieces(),"Comptage des points gagnés grâce aux Merveilles");
			}
		}
	}

	public void test14() { // Tripot
		System.out.println("TEST DU TRIPOT");
		JeuPublic jeu = new JeuPublic();
		jeu.initialisation();
		jeu.choixPersonnages();
		
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(jeu.generateur.nextInt(jeu.nombreJoueurs));
		System.out.println("Test effectué avec le joueur "+joueurAleatoire.getNom()+" et son personnage "+joueurAleatoire.getPersonnage().getNom());
		
		if(joueurAleatoire.getAvatar()) {
			int nbCartePossedez = joueurAleatoire.nbQuartiersDansMain();
			for(int i=0; i<nbCartePossedez; i++) {
				joueurAleatoire.retirerQuartierDansMain();
			}
			
			joueurAleatoire.ajouterQuartierDansMain(new Quartier("Cathédrale",Quartier.TYPE_QUARTIERS[0], 6));
			joueurAleatoire.ajouterQuartierDansMain(new Quartier("Forteresse",Quartier.TYPE_QUARTIERS[1], 6));
			joueurAleatoire.ajouterQuartierDansMain(new Quartier("Palais",Quartier.TYPE_QUARTIERS[2], 6));
			joueurAleatoire.ajouterQuartierDansMain(new Quartier("Tripot",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.TRIPOT));

			
			do {
				jeu.construire(joueurAleatoire.getPersonnage());
			}while(!joueurAleatoire.quartierPresentDansCite("Tripot"));
			Test.test(joueurAleatoire.nbPieces()+joueurAleatoire.nbQuartiersDansMain() == 0,"Nombres de cartes ("+joueurAleatoire.nbQuartiersDansMain()+") et de pièces ("+(joueurAleatoire.nbPieces())+") en main adéquat");
		} else {
			joueurAleatoire.ajouterPieces(2);
			joueurAleatoire.ajouterQuartierDansMain(new Quartier("Tripot",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.TRIPOT));
			jeu.construire(joueurAleatoire.getPersonnage());
			Test.test(joueurAleatoire.nbPieces()+joueurAleatoire.nbQuartiersDansMain() == 2,"Nombres de cartes ("+joueurAleatoire.nbQuartiersDansMain()+") et de pièces ("+(joueurAleatoire.nbPieces())+") en main adéquat");
		}
		Test.test(joueurAleatoire.quartierPresentDansCite("Tripot"),"Tripot présent dans cité");

		
		
	}
}
