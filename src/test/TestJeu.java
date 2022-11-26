package test;

import application.JeuPublic;
import modele.Caracteristiques;
import modele.Quartier;

public class TestJeu {
	public static void main(String[] args) {
		TestJeu test = new TestJeu();
		// test.test1();
		// test.test2();
		// test.test3();
		//test.test4();
		test.test5();


	}

	public void test1() {
		int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST INITIALISATION");
		jeu.initialisation();
		Test.test(jeu.nombreJoueurs == 4, "Nombre de joueurs");

		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansMain() != 4
					&& jeu.PlateauDeJeu.getJoueur(i).nbPieces() != 2) {
				Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansMain() == 4,
						"Nombre de cartes en main " + jeu.PlateauDeJeu.getJoueur(i));
				Test.test(jeu.PlateauDeJeu.getJoueur(i).nbPieces() == 2,
						"Nombre de pièces de " + jeu.PlateauDeJeu.getJoueur(i));
			} else {
				aucunProbleme += 1;
				if (aucunProbleme == 4) {
					Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansMain() == 4,
							"Nombre de cartes des joueurs en main");
					Test.test(jeu.PlateauDeJeu.getJoueur(i).nbPieces() == 2, "Nombre de pièces des joueurs");
				}
			}
			if (jeu.PlateauDeJeu.getJoueur(i).getPossedeCouronne() == true) {
				Test.test(jeu.PlateauDeJeu.getJoueur(i).getPossedeCouronne() == true,
						"Le joueur " + jeu.PlateauDeJeu.getJoueur(i).getNom() + " possède la couronne");
			}
		}
	}

	public void test2() {
		int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST DU CHOIX DU PERSONNAGE");
		jeu.initialisation();
		jeu.choixPersonnages();
		// jeu.PlateauDeJeu.getJoueur(0).getPersonnage().reinitialiser();
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (jeu.PlateauDeJeu.getJoueur(i).getPersonnage() == null) {
				Test.test(jeu.PlateauDeJeu.getJoueur(i).getPersonnage() != null,
						"Le joueur " + jeu.PlateauDeJeu.getJoueur(i).getNom() + " à un personnage");
			} else {
				aucunProbleme += 1;
				if (aucunProbleme == 4) {
					Test.test(jeu.PlateauDeJeu.getJoueur(i).getPersonnage() != null,
							"Les joueurs ont tous un personnage");
				}
			}
		}
	}
	
	// Test d'un tour de jeu
	public void test3() {
		int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST D'UN TOUR DE JEU ET DE LA FIN DE PARTIE");
		jeu.Avatar = true;
		jeu.jouer();
		if (jeu.nombreJoueurs == 4 || jeu.nombreJoueurs == 5 || jeu.nombreJoueurs == 6 || jeu.nombreJoueurs == 7) {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
					Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,
							"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,
								"La partie s'est terminé car une cité est complète");
					}
				}
			}
		} else {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8) {
					Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,
							"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,
								"La partie s'est terminé car une cité est complète");
					}
				}
			}
		}
	}
	
	// Test d'un tour de jeu
	public void test4() {
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST DU CALCUL DES POINTS");
		jeu.initialisation();
		//Cité Joueur 1
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Comptoir", Quartier.TYPE_QUARTIERS[3], 3));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FONTAINE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Cours des miracles",Quartier.TYPE_QUARTIERS[4], 2, Caracteristiques.COURS));

		//Cité Joueur 2
		jeu.PlateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.PlateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.PlateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.PlateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.PlateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.PlateauDeJeu.getJoueur(1).ajouterQuartierDansCite(new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3));
		
		//Cité Joueur 3
		jeu.PlateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.PlateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.PlateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3));
		jeu.PlateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.PlateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));
		jeu.PlateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3));
		jeu.PlateauDeJeu.getJoueur(2).ajouterQuartierDansCite(new Quartier("Dracoport",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.DRACOPORT));
		
		//Cité Joueur 4
		jeu.PlateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1));
		jeu.PlateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3));
		jeu.PlateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5));
		jeu.PlateauDeJeu.getJoueur(3).ajouterQuartierDansCite(new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5));

		jeu.calculDesPoints();

		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			if (i==1) {
				System.out.println("");
				Test.test(jeu.pointsCoutConstruction[0] == 22,"1er joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType[0] == 3,"1er joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine[0] == 4,"1er joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				if (jeu.nombrePoints[0] == 31) {
					Test.test(jeu.pointsMerveille[0] == 2,"1er joueur : Comptage des points gagnés grâce aux Merveilles");
					Test.test(jeu.nombrePoints[0] == 31,"1er joueur : Comptage des points totaux");
				} else if (jeu.nombrePoints[0] == 30) {
					Test.test(jeu.pointsMerveille[0] == 1,"1er joueur : Comptage des points gagnés grâce aux Merveilles");
					Test.test(jeu.nombrePoints[0] == 30,"1er joueur : Comptage des points totaux");
				}
			} else if(i==2) {
				System.out.println("");
				Test.test(jeu.pointsCoutConstruction[1] == 20,"2ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType[1] == 0,"2ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine[1] == 0,"2ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille[1] == 0,"2ème joueur : Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints[1] == 20,"2ème joueur : Comptage des points totaux");
			} else if (i==3) {
				System.out.println("");
				Test.test(jeu.pointsCoutConstruction[2] == 26,"3ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType[2] == 0,"3ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine[2] == 2,"3ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille[2] == 2,"3ème joueur : Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints[2] == 30,"3ème joueur : Comptage des points totaux");
			} else if (i==4) {
				System.out.println("");
				//Test.test(jeu.pointsCoutConstruction == 18,"4ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsCoutConstruction[3] == 0,"4ème joueur : Comptage des points de construction");
				Test.test(jeu.pointsNombreType[3] == 0,"4ème joueur : Comptage des points des types présents du 1er joueur");
				Test.test(jeu.pointsCiteTermine[3] == 0,"4ème joueur : Comptage des points du 1er joueur à avoir terminé sa cité");
				Test.test(jeu.pointsMerveille[3] == 0,"4ème joueur : Comptage des points gagnés grâce aux Merveilles");
				Test.test(jeu.nombrePoints[3] == 0,"4ème joueur : Comptage des points totaux");
				Test.test(jeu.nombrePoints[3] == 18,"4ème joueur : Comptage des points totaux");
			}
		} 
	}

	// Test d'une partie complète d'Avatar
	public void test5() {
		int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST D'UN JEU COMPLET D'AVATAR");
		jeu.Avatar = true;
		jeu.jouer();
		if (jeu.nombreJoueurs == 4 || jeu.nombreJoueurs == 5 || jeu.nombreJoueurs == 6 || jeu.nombreJoueurs == 7) {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
					Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,
							"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,
								"La partie s'est terminé car une cité est complète");
					}
				}
			}
		} else {
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8) {
					Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,
							"La partie s'est terminé car une cité est complète");
					break;
				} else {
					aucunProbleme++;
					if (aucunProbleme == 4) {
						Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,
								"La partie s'est terminé car une cité est complète");
					}
				}
			}
		}
	}
	
	public void test6() {
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST DES MERVEILLES");
		jeu.Avatar = true;
		jeu.jouer();

		/*jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.BIBLIOTHEQUE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARRIERE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Cours des miracles",Quartier.TYPE_QUARTIERS[4], 2, Caracteristiques.COURS));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Donjon",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.DONJON));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Dracoport",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.DRACOPORT));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Ecole de magie",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.MAGIE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FONTAINE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Forge",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FORGE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.LABORATOIRE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Manufacture",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.MANUFACTURE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Salle des cartes",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARTE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Statue equestre",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.STATUE));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Trésor Imperial",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.TRESOR));
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(new Quartier("Tripot",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.TRIPOT));*/
	}
}
