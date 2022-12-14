package application;

import application.Jeu;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Personnage;
import modele.Quartier;
import test.Test;

public class TestJeu {
	public static void main(String[] args) {
		TestJeu test = new TestJeu();
		//test.test1(); //initialisation()
		//test.test2(); //choixPersonnages()
		//test.test3(); //percevoirRessource()
		//test.test4(); //construire()
		//test.test5(); //gestionCouronne()
		//test.test6(); //reinitialisationPersonnages()
		//test.test7(); //calculDesPoints()
		//test.test8(); //tourDeJeu()
		test.test9(); //jouer()
	}

	//Test de l'initialisation					   
	public void test1() {
		int aucunProbleme = 0;
		int nombreJoueurAvatar = 0;
		Jeu jeu = new Jeu();
		System.out.println("TEST INITIALISATION");
		jeu.initialisation();
		Test.test(jeu.nombreJoueurs == 4, "Nombre de joueurs");

		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansMain() != 4 && jeu.plateauDeJeu.getJoueur(i).nbPieces() != 2) {
				Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansMain() == 4,"Nombre de cartes en main " + jeu.plateauDeJeu.getJoueur(i));
				Test.test(jeu.plateauDeJeu.getJoueur(i).nbPieces() == 2,"Nombre de pièces de " + jeu.plateauDeJeu.getJoueur(i));
			} else {
				aucunProbleme += 1;
				if (aucunProbleme == 4) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansMain() == 4,"Nombre de cartes des joueurs en main");
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbPieces() == 2, "Nombre de pièces des joueurs");
				}
			}
			if (jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				Test.test(jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne(),"Le joueur " + jeu.plateauDeJeu.getJoueur(i).getNom() + " possède la couronne");
			}
			
			if (jeu.plateauDeJeu.getJoueur(i).getAvatar()) {
				nombreJoueurAvatar += 1;
			}
			
		}
		
		System.out.println("Il y a "+nombreJoueurAvatar+" Avatar initialisé");
		for (int j = 0; j < jeu.nombreJoueurs; j++) {
			if(jeu.plateauDeJeu.getJoueur(j).getAvatar()) {
				Test.test(jeu.plateauDeJeu.getJoueur(j).getAvatar(),"Le joueur "+jeu.plateauDeJeu.getJoueur(j).getNom()+" est un Avatar");
			}
		}
	}

	//Test du choix du personnage
	public void test2() { // AVATAR OK
		Jeu jeu = new Jeu();
		System.out.println("TEST DU CHOIX DU PERSONNAGE");
		jeu.initialisation();
		jeu.choixPersonnages();
		// jeu.plateauDeJeu.getJoueur(0).getPersonnage().reinitialiser();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				Test.test(jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne() && jeu.plateauDeJeu.getJoueur(i).getPersonnage() != null,"Le joueur " + jeu.plateauDeJeu.getJoueur(i).getNom() + " à la couronne et à choisi le personnage "+jeu.plateauDeJeu.getJoueur(i).getPersonnage().getNom());
			} else {
				Test.test(jeu.plateauDeJeu.getJoueur(i).getPersonnage() != null,"Le joueur " + jeu.plateauDeJeu.getJoueur(i).getNom() + " et à choisi le personnage "+jeu.plateauDeJeu.getJoueur(i).getPersonnage().getNom());
			}
		}
	}
	
	//Test de la percepetion des ressources de base
	public void test3() { //
		Jeu jeu = new Jeu();
		System.out.println("TEST DE LA PERCEPTION DES RESSOURCES");
		jeu.initialisation();
		jeu.choixPersonnages();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			int nbPiecesAvantPercepetion = jeu.plateauDeJeu.getJoueur(i).nbPieces();
			int nbCartesAvantPercepetion = jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansMain();
			jeu.percevoirRessource(jeu.plateauDeJeu.getJoueur(i).getPersonnage());
			int nbPiecesApresPercepetion = jeu.plateauDeJeu.getJoueur(i).nbPieces();
			int nbCartesApresPercepetion = jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansMain();
			Test.test(nbPiecesApresPercepetion == nbPiecesAvantPercepetion+2 || nbCartesApresPercepetion == nbCartesAvantPercepetion+1,"Le nombre de ressources de "+jeu.plateauDeJeu.getJoueur(i).getNom()+" est adéquat");
		} 
	}

	//Test de la construction
	public void test4() {
		Jeu jeu = new Jeu();
		System.out.println("TEST DE LA CONSTRUCTION");
		jeu.initialisation();

		int indexJoueurAleatoire = jeu.generateur.nextInt(4);
		int indexPersonnageAleatoire = jeu.generateur.nextInt(8);
		Joueur joueurAleatoire = jeu.plateauDeJeu.getJoueur(indexJoueurAleatoire);
		Personnage personnageAleatoire = jeu.plateauDeJeu.getPersonnage(indexPersonnageAleatoire);
		personnageAleatoire.setJoueur(joueurAleatoire);
		joueurAleatoire.ajouterPieces(3);

		int nbCartePossedez = personnageAleatoire.getJoueur().nbQuartiersDansMain();
		for (int i = 0; i < nbCartePossedez; i++) {
			personnageAleatoire.getJoueur().retirerQuartierDansMain();
		}

		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		joueurAleatoire.ajouterQuartierDansMain(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		
		System.out.println(personnageAleatoire.getNom());
		if(personnageAleatoire.getNom()=="Architecte") {
			joueurAleatoire.ajouterPieces(1);
			if(joueurAleatoire.getAvatar()==true) { 
				do {
					jeu.construire(personnageAleatoire);
					jeu.architecte(personnageAleatoire);
				} while(personnageAleatoire.getJoueur().nbQuartiersDansCite() < 2);
			} else {
				jeu.construire(personnageAleatoire);
				jeu.architecte(personnageAleatoire);
			}
		} else {
			jeu.construire(personnageAleatoire);
			jeu.construire(personnageAleatoire);
			jeu.construire(personnageAleatoire);
		}
		
		if (personnageAleatoire.getJoueur().nbQuartiersDansCite() == 1) {
			for (int i = 0; i < personnageAleatoire.getJoueur().nbQuartiersDansCite(); i++) {
				Test.test((joueurAleatoire.getCite()[i].getNom() == "Temple") || (joueurAleatoire.getCite()[i].getNom() == "Forteresse"), "Un quartier à été construit");
			}
		} else if (personnageAleatoire.getJoueur().nbQuartiersDansCite() > 1) {
			Test.test(personnageAleatoire.getJoueur().nbQuartiersDansCite() == 2 && personnageAleatoire.getNom() == "Architecte", "Le joueur a construit 2 quartiers grâce à l'architecte");
			
		} else {
			Test.test(personnageAleatoire.getJoueur().nbQuartiersDansCite() == 0, "Le joueur à décider de ne pas construire");
		}
	}
	
	//Test de la gestion de la couronne
	public void test5() {
		Jeu jeu = new Jeu();
		System.out.println("TEST DE LA GESTION DE LA COURONNE");
		jeu.initialisation();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				Test.test(jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne(),"Le joueur " + jeu.plateauDeJeu.getJoueur(i).getNom() + " possède la couronne");
			}
		}
		
		jeu.choixPersonnages();
		jeu.gestionCouronne();
		
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			
			if (jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				Test.test(jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne(),"Le joueur " + jeu.plateauDeJeu.getJoueur(i).getNom() + " possède la couronne");
			}
		}
	}

	//Test de la reinitialisation des personnages
	public void test6() {
		Jeu jeu = new Jeu();
		System.out.println("TEST DE LA REINITIALISATION DES PERSONNAGES");
		jeu.initialisation();
		jeu.choixPersonnages();
		jeu.reinitialisationPersonnages();
		for (int i = 0; i < jeu.nombrePersonnages; i++) {
			Test.test(jeu.plateauDeJeu.getPersonnage(i).getAssassine() == false && jeu.plateauDeJeu.getPersonnage(i).getVole() == false && jeu.plateauDeJeu.getPersonnage(i).getJoueur() == null,"Le personnage " + jeu.plateauDeJeu.getPersonnage(i).getNom() + " n'est ni volé, ni assassiné et ne possède pas de joueur");
		}		
	}
	
	//Test du calcul des points
	public void test7() {
		Jeu jeu = new Jeu();
		System.out.println("TEST DU CALCUL DES POINTS");
		jeu.initialisation();

		Joueur joueur0 = jeu.plateauDeJeu.getJoueur(0);
		Personnage personnage0 = jeu.plateauDeJeu.getPersonnage(0);
		personnage0.setJoueur(joueur0);

		Joueur joueur1 = jeu.plateauDeJeu.getJoueur(1);
		Personnage personnage1 = jeu.plateauDeJeu.getPersonnage(1);
		personnage1.setJoueur(joueur1);

		Joueur joueur2 = jeu.plateauDeJeu.getJoueur(2);
		Personnage personnage2 = jeu.plateauDeJeu.getPersonnage(2);
		personnage2.setJoueur(joueur2);

		Joueur joueur3 = jeu.plateauDeJeu.getJoueur(3);
		Personnage personnage3 = jeu.plateauDeJeu.getPersonnage(3);
		personnage3.setJoueur(joueur3);

		jeu.winner = jeu.plateauDeJeu.getJoueur(2);

		// Cité Joueur 1
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Comptoir", Quartier.TYPE_QUARTIERS[3], 3));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Fontaine aux souhaits", Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FONTAINE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Cours des miracles", Quartier.TYPE_QUARTIERS[4], 2, Caracteristiques.COURS));

		// Cité Joueur 2
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3));

		// Cité Joueur 3
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Dracoport", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.DRACOPORT));

		// Cité Joueur 4
		jeu.plateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.plateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.plateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.plateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));

		jeu.calculDesPoints();

		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			System.out.println("");
			if (i == 0) {
				Test.test(jeu.pointsCoutConstruction.get(0) == 22, "1er joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType.get(0) == 3,"1er joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine.get(0) == 2,"1er joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				if (jeu.nombrePoints.get(0) == 29) {
					Test.test(jeu.pointsMerveille.get(0) == 2,"1er joueur : Comptage des points gagnés grâce aux Merveilles");
					Test.test(jeu.nombrePoints.get(0) == 29, "1er joueur : Comptage des points totaux");
				} else if (jeu.nombrePoints.get(0) == 28) {
					Test.test(jeu.pointsMerveille.get(0) == 1,"1er joueur : Comptage des points gagnés grâce aux Merveilles");
					Test.test(jeu.nombrePoints.get(0) == 28, "1er joueur : Comptage des points totaux");
				}
			} else if (i == 1) {
				Test.test(jeu.pointsCoutConstruction.get(1) == 20,"2ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType.get(1) == 0,"2ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine.get(1) == 0,"2ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille.get(1) == 0,"2ème joueur : Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(1) == 20, "2ème joueur : Comptage des points totaux");
			} else if (i == 2) {
				Test.test(jeu.pointsCoutConstruction.get(2) == 26,"3ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType.get(2) == 0,"3ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine.get(2) == 4,"3ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille.get(2) == 2,"3ème joueur : Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(2) == 32, "3ème joueur : Comptage des points totaux");
			} else if (i == 3) {
				Test.test(jeu.pointsCoutConstruction.get(3) == 14, "4ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType.get(3) == 0,"4ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine.get(3) == 0,"4ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille.get(3) == 0,"4ème joueur : Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(3) == 14, "4ème joueur : Comptage des points totaux");
			}
		}
	}

	//Test d'un tour de jeu
	public void test8() {
		Jeu jeu = new Jeu();
		System.out.println("TEST D'UN TOUR DE JEU");
		jeu.initialisation();
		jeu.tourDeJeu();

	}

	// Test d'une partie complète d'Avatar
	public void test9() {
		int aucunProbleme = 0;
		Jeu jeu = new Jeu();
		System.out.println("TEST D'UN JEU COMPLET D'AVATAR");
		jeu.jouer();
		if (jeu.nombreJoueurs == 4 || jeu.nombreJoueurs == 5 || jeu.nombreJoueurs == 6 || jeu.nombreJoueurs == 7) {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,"La partie s'est terminé car une cité est complète");
					}
				}
			}
		} else {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,"La partie s'est terminé car une cité est complète");
					}
				}
			}
		}
	}
}
