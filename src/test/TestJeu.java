package test;

import application.Configuration;
import application.Jeu;
import application.JeuPublic;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

public class TestJeu {
	public static void main(String[] args) {
		TestJeu test = new TestJeu();
		// test.test1();
		// test.test2();
		//test.test3();
		test.test8();
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

	//Test d'un tour de jeu lambda
	public void test3() {
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST D'UN TOUR DE JEU");
		jeu.initialisation();
		int choixPersonnage[] = {0,0,0,0};
		String choixQuartier[] = {"","","","","","","",""};
		boolean result = false;
		
		//Set un nombre de quartiers aléatoire à chaque joueurs 
		jeu.PlateauDeJeu.getJoueur(0).ajouterQuartierDansCite(jeu.PlateauDeJeu.getJoueur(0).getMain().get(jeu.generateur.nextInt(jeu.PlateauDeJeu.getJoueur(0).nbQuartiersDansMain())));
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			int nombreAlea = jeu.generateur.nextInt(3)+1;
			for (int j = 0; j < nombreAlea; j++) {
				Quartier quartierAlea = null;
				String quartierAleaNom = "";
				do {
					result = false;
					quartierAlea = jeu.pioche.piocher();
					quartierAleaNom = quartierAlea.getNom();
					for (String x : choixQuartier) {
						if (x == quartierAleaNom) {
							result = true;
							jeu.pioche.ajouter(quartierAlea);
							break;
						}
					}
				} while (result == true);
				choixQuartier[j] = quartierAleaNom;
				jeu.PlateauDeJeu.getJoueur(i).ajouterQuartierDansCite(quartierAlea);
			}
			/*System.out.print(i + 1 + " " + jeu.PlateauDeJeu.getJoueur(i).getNom() + ": ");
			for (int j = 0; j < jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
				System.out.print(j + 1 + " " + jeu.PlateauDeJeu.getJoueur(i).getCite()[j].getNom() + "(coût "
						+ jeu.PlateauDeJeu.getJoueur(i).getCite()[j].getCout() + "), ");
			}
			System.out.println("");*/
		}

		//Set 1 personnage aléatoire à chaque joueur
		for (int i = 0; i < jeu.nombreJoueurs; i++) {
			int nombreAlea = 0;
			do {
				result = false;
				nombreAlea = jeu.generateur.nextInt(jeu.nombrePersonnages);
				for (int j : choixPersonnage) {
					if (j == nombreAlea) {
						result = true;
						break;
					}
				}
			} while (result == true);
			choixPersonnage[i] = nombreAlea;
			jeu.PlateauDeJeu.getPersonnage(choixPersonnage[i]).setJoueur(jeu.PlateauDeJeu.getJoueur(i));
		}
		
	}
	
	//Test de la gestion de la couronne
	public void test4() {
		
	}
	
	//Test de la réintialisation des personnages
	public void test5() {
		
	}
	
	//Test d'une fin de partie
	public void test6() {
		
	}
	
	//Test du calcul des points
	public void test7() {
		
	}
	
	//Test d'une partie complète d'Avatar
	public void test8() {
		int aucunProbleme = 0;
		JeuPublic jeu = new JeuPublic();
		System.out.println("TEST D'UN JEU COMPLET D'AVATAR");
		jeu.Avatar=true;
		jeu.jouer();
		switch (jeu.nombreJoueurs) {
		case 4, 5, 6, 7:
			for (int i = 0; i < jeu.nombreJoueurs; i++) {
				if (jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
						Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,"La partie s'est terminé car une cité est complète");
						break;
				} else {
					aucunProbleme++;
					if (aucunProbleme==4) {
						Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7,"La partie s'est terminé car une cité est complète");
					}
				}
			}

		case 2, 3, 8:
				for (int i = 0; i < jeu.nombreJoueurs; i++) {
					if (jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8) {
							Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,"La partie s'est terminé car une cité est complète");
							break;
					} else {
						aucunProbleme++;
						if (aucunProbleme==4) {
							Test.test(jeu.PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8,"La partie s'est terminé car une cité est complète");
						}
					}
				}
		}
	}
}
