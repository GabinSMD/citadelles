package test;

import application.JeuPublic;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Personnage;
import modele.Quartier;

public class TestJeu {
	public static void main(String[] args) {
		TestJeu test = new TestJeu();
		test.test1();
		//test.test2();
		//test.test3();
		//test.test4();
		//test.test5();


	}

	public void test1() {
		int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST INITIALISATION");
		jeu.initialisation();
		Test.test(jeu.nombreJoueurs == 4, "Nombre de joueurs");

		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansMain() != 4
					&& jeu.plateauDeJeu.getJoueur(i).nbPieces() != 2) {
				Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansMain() == 4,
						"Nombre de cartes en main " + jeu.plateauDeJeu.getJoueur(i));
				Test.test(jeu.plateauDeJeu.getJoueur(i).nbPieces() == 2,
						"Nombre de pièces de " + jeu.plateauDeJeu.getJoueur(i));
			} else {
				aucunProbleme += 1;
				if (aucunProbleme == 4) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansMain() == 4,
							"Nombre de cartes des joueurs en main");
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbPieces() == 2, "Nombre de pièces des joueurs");
				}
			}
			if (jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne() == true) {
				Test.test(jeu.plateauDeJeu.getJoueur(i).getPossedeCouronne() == true,
						"Le joueur " + jeu.plateauDeJeu.getJoueur(i).getNom() + " possède la couronne");
			}
		}
	}

	public void test2() {
		int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST DU CHOIX DU PERSONNAGE");
		jeu.initialisation();
		jeu.choixPersonnages();
		// jeu.plateauDeJeu.getJoueur(0).getPersonnage().reinitialiser();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.plateauDeJeu.getJoueur(i).getPersonnage() == null) {
				Test.test(jeu.plateauDeJeu.getJoueur(i).getPersonnage() != null,
						"Le joueur " + jeu.plateauDeJeu.getJoueur(i).getNom() + " à un personnage");
			} else {
				aucunProbleme += 1;
				if (aucunProbleme == 4) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).getPersonnage() != null,
							"Les joueurs ont tous un personnage");
				}
			}
		}
	}
	
	// Test d'un tour de jeu
	public void test3() {
		/*int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST D'UN TOUR DE JEU ET DE LA FIN DE PARTIE");
		jeu.jouer();
		if (jeu.nombreJoueurs == 4 || jeu.nombreJoueurs == 5 || jeu.nombreJoueurs == 6 || jeu.nombreJoueurs == 7) {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,
							"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,
								"La partie s'est terminé car une cité est complète");
					}
				}
			}
		} else {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,
							"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,
								"La partie s'est terminé car une cité est complète");
					}
				}
			}
		}*/
	}
	
	// Test d'un tour de jeu
	public void test4() {
		JeuPublic jeu = new JeuPublic();
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
		
		//Cité Joueur 1
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Comptoir", Quartier.TYPE_QUARTIERS[3], 3));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FONTAINE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Cours des miracles",Quartier.TYPE_QUARTIERS[4], 2, Caracteristiques.COURS));

		//Cité Joueur 2
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.plateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3));
		
		//Cité Joueur 3
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3));
		jeu.plateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Dracoport",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.DRACOPORT));
		
		//Cité Joueur 4
		jeu.plateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.plateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.plateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.plateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));

		jeu.calculDesPoints();

		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (i==0) {
				System.out.println("");
				Test.test(jeu.pointsCoutConstruction.get(0) == 22,"1er joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType.get(0) == 3,"1er joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine.get(0) == 2,"1er joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				if (jeu.nombrePoints.get(0) == 29) {
					Test.test(jeu.pointsMerveille.get(0) == 2,"1er joueur : Comptage des points gagnés grâce aux Merveilles");
					Test.test(jeu.nombrePoints.get(0) == 29,"1er joueur : Comptage des points totaux");
				} else if (jeu.nombrePoints.get(0) == 28) {
					Test.test(jeu.pointsMerveille.get(0) == 1,"1er joueur : Comptage des points gagnés grâce aux Merveilles");
					Test.test(jeu.nombrePoints.get(0) == 28,"1er joueur : Comptage des points totaux");
				}
				System.out.println("");
			} else if(i==1) {
				System.out.println("");
				Test.test(jeu.pointsCoutConstruction.get(1) == 20,"2ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType.get(1) == 0,"2ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine.get(1) == 0,"2ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille.get(1) == 0,"2ème joueur : Comptage des points gagnés grâce aux Merveilles");
          		Test.test(jeu.nombrePoints.get(1) == 20,"2ème joueur : Comptage des points totaux");
				System.out.println("");
			} else if (i==2) {
				System.out.println("");
				Test.test(jeu.pointsCoutConstruction.get(2) == 26,"3ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType.get(2) == 0,"3ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine.get(2) == 4,"3ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille.get(2) == 2,"3ème joueur : Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(2) == 32,"3ème joueur : Comptage des points totaux");
				System.out.println("");
			} else if (i==3) {
				System.out.println("");
				Test.test(jeu.pointsCoutConstruction.get(3) == 14,"4ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType.get(3) == 0,"4ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine.get(3) == 0,"4ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille.get(3) == 0,"4ème joueur : Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints.get(3) == 14,"4ème joueur : Comptage des points totaux");
				System.out.println("");
			}
		} 
	}

	// Test d'une partie complète d'Avatar
	public void test5() {
		int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST D'UN JEU COMPLET D'AVATAR");
		jeu.jouer();
		if (jeu.nombreJoueurs == 4 || jeu.nombreJoueurs == 5 || jeu.nombreJoueurs == 6 || jeu.nombreJoueurs == 7) {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,
							"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,
								"La partie s'est terminé car une cité est complète");
					}
				}
			}
		} else {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8) {
					Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,
							"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,
								"La partie s'est terminé car une cité est complète");
					}
				}
			}
		}
	}
	
	public void test6() {
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST DES MERVEILLES");
		jeu.jouer();

		/*jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.BIBLIOTHEQUE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARRIERE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Cours des miracles",Quartier.TYPE_QUARTIERS[4], 2, Caracteristiques.COURS));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Donjon",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.DONJON));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Dracoport",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.DRACOPORT));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Ecole de magie",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.MAGIE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FONTAINE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Forge",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FORGE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.LABORATOIRE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Manufacture",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.MANUFACTURE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Salle des cartes",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARTE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Statue equestre",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.STATUE));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Trésor Imperial",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.TRESOR));
		jeu.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Tripot",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.TRIPOT));*/
	}
}
