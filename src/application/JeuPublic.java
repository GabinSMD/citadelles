package application;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;

public class JeuPublic {
	public PlateauDeJeu PlateauDeJeu;
	public int numeroConfiguration;
	public Random generateur;
	public int nombreJoueurs;
	public int nombrePersonnages;
	public Pioche pioche;
	public boolean Avatar;

	public JeuPublic() {
		this.PlateauDeJeu = new PlateauDeJeu();
		this.numeroConfiguration = numeroConfiguration;
		this.generateur = new Random();
	}

	public void jouer() {
		boolean exit = false;
		System.out.println(
				"Bienvenue dans la version de Citadelles codé par Gabin SIMOND, Kilian LABORDERIE et Sofiane DION.");
		while (exit == false) {
			System.out.println("Que souhaitez vous faire ?");
			System.out.println("1 - Jouer une partie");
			System.out.println("2 - Afficher les règles");
			System.out.println("3 - Quitter");
			System.out.println("");
			int choix = Interaction.lireUnEntier(1, 4);
			switch (choix) {
			case 1:
				jouerPartie();
				break;

			case 2:
				afficherLesRegles();
				break;

			case 3:
				exit = true;
				System.out.println("Au revoir !");
				break;
			default:
				System.out.println("Choix incorrect");
				break;
			}
		}
	}

	public void afficherLesRegles() {
		System.out.println("Il faudra ajouter les règles ici");
		System.out.println("");
	}

	public void jouerPartie() {
		int i=0;
		initialisation();
		do {
			i++;
			System.out.println("Tour n°"+i);
			choixPersonnages();
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();
		} while (!partieFinie());
		calculDesPoints();
	}

	public void initialisation() {
		pioche = Configuration.nouvellePioche();
		PlateauDeJeu = Configuration.configurationDeBase(pioche);
		pioche.melanger();
		this.nombreJoueurs = PlateauDeJeu.getNombreJoueurs();
		this.nombrePersonnages = PlateauDeJeu.getNombrePersonnages();

		for (int i = 0; i < nombreJoueurs; i++) {
			PlateauDeJeu.getJoueur(i).ajouterPieces(2);
			for (int j = 0; j < 4; j++) {
				PlateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
			}
		}
		PlateauDeJeu.getJoueur(generateur.nextInt(nombreJoueurs)).setPossedeCouronne(true);
	}

	public void gestionCouronne() {
		for (int i = 0; i < nombrePersonnages; i++) {
			if ((PlateauDeJeu.getPersonnage(i).getNom() == Caracteristiques.ROI)
					&& (PlateauDeJeu.getPersonnage(i).getJoueur() != null)) {
				PlateauDeJeu.getJoueur(i).setPossedeCouronne(true);
			}
		}

	}

	public void reinitialisationPersonnages() {
		for (int i = 0; i < nombrePersonnages; i++) {
			if (PlateauDeJeu.getPersonnage(i).getJoueur() != null) {
				PlateauDeJeu.getPersonnage(i).reinitialiser();
			}
		}
	}

	public boolean partieFinie() {
		boolean varReturn = false;

		switch (nombreJoueurs) {
		case 4, 5, 6, 7:
			for (int i = 0; i < nombreJoueurs; i++) {
				if (PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 7) {
					varReturn = true;
					break;
				} else {
					varReturn = false;
				}
			}

		case 2, 3, 8:
			for (int i = 0; i < nombreJoueurs; i++) {
				if (PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8) {
					varReturn = true;
					break;
				} else {
					varReturn = false;
				}
			}
		}
		return varReturn;
	}

	public void tourDeJeu() {
		Personnage personnageActuel;
		Joueur joueurActuel;
		for (int i = 0; i < nombrePersonnages; i++) {
			personnageActuel = PlateauDeJeu.getPersonnage(i);
			joueurActuel = personnageActuel.getJoueur();
			System.out.println("Le personnage " + personnageActuel.getNom() + " est appelé !");
			if ((personnageActuel.getAssassine() == false) && (joueurActuel != null)) {
				System.out.println("Le joueur " + joueurActuel.getNom() + " commence son tour !");
				if (Avatar) {
					if (personnageActuel.getVole() == true) {
						for (int j = 0; j < nombreJoueurs; j++) {
							if (PlateauDeJeu.getJoueur(j).getPersonnage().getNom() == Caracteristiques.VOLEUR) {
								PlateauDeJeu.getJoueur(j).ajouterPieces(joueurActuel.nbPieces());
							}
						}
						joueurActuel.retirerPieces(joueurActuel.nbPieces());
					}
					percevoirRessource(joueurActuel);
					personnageActuel.percevoirRessourcesSpecifiques();
					if (generateur.nextInt(2)==1) {
						//personnageActuel.utiliserPouvoirAvatar();
						System.out.println("Le pouvoir de l'Avatar " + joueurActuel.getNom() + " a été utilisé !");
					}
					if ((generateur.nextInt(2)==1 && (joueurActuel.nbQuartiersDansMain()!=0))) {
						int nombreQuartiersConstruit = 0;
						int out = 0;
						boolean check = false;
						do {
							int choixQuartier = generateur.nextInt(joueurActuel.nbQuartiersDansMain());
							Quartier nomQuartier = joueurActuel.getMain().get(choixQuartier);
							int verifCoutQuartier = joueurActuel.getMain().get(choixQuartier).getCout();
							if (verifCoutQuartier > joueurActuel.nbPieces()) {
								check = false;
								out++;
								if (out>=10) {
									check = true;
								}
							} else {
								check = true;
								joueurActuel.retirerPieces(verifCoutQuartier);
								joueurActuel.ajouterQuartierDansCite(nomQuartier);
								System.out.println("L'Avatar " + joueurActuel.getNom() + " a construit le quartier "+ nomQuartier.getNom());
								ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(joueurActuel.getMain());
								int tailleMain = joueurActuel.getMain().size();
								PlateauDeJeu.getPioche().ajouter(copieTableau.get(choixQuartier));
								copieTableau.remove(choixQuartier);

								for (int j = 0; j < tailleMain; j++) {
									joueurActuel.retirerQuartierDansMain();
								}
								for (int j = 0; j < copieTableau.size(); j++) {
									joueurActuel.ajouterQuartierDansMain(copieTableau.get(j));
								}
								nombreQuartiersConstruit += 1;
								if ((personnageActuel.getNom() == Caracteristiques.ARCHITECTE)
										&& nombreQuartiersConstruit != 3) {
									
									for (int j = 0; j < joueurActuel.nbQuartiersDansMain(); j++) {
										if (joueurActuel.nbPieces()>=joueurActuel.getMain().get(j).getCout()) {
											if (generateur.nextInt(2) == 1) {
												nombreQuartiersConstruit = 3;
											}
										}
									}
								}
							}
						} while (((check != true) && (personnageActuel.getNom() != Caracteristiques.ARCHITECTE))
								|| nombreQuartiersConstruit == 3);
					}
					
				} else {
					System.out.println("");
					System.out.println(personnageActuel.getNom() + ", c'est à ton tour !");
					if (personnageActuel.getVole() == true) {
						System.out.println(
								"Vous avez été volé ! Vous donnez " + joueurActuel.nbPieces() + " pièces d'or au Voleur");
						for (int j = 0; j < nombreJoueurs; j++) {
							if (PlateauDeJeu.getJoueur(j).getPersonnage().getNom() == Caracteristiques.VOLEUR) {
								PlateauDeJeu.getJoueur(j).ajouterPieces(joueurActuel.nbPieces());
							}
						}
						joueurActuel.retirerPieces(joueurActuel.nbPieces());
					}
					percevoirRessource(joueurActuel);
					
					personnageActuel.percevoirRessourcesSpecifiques();
					
					System.out.println("Voulez vous utilisez votre pouvoir ? ");
					System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
					if (Interaction.lireOuiOuNon()) {
						personnageActuel.utiliserPouvoir();
					}
					System.out.println("Voulez vous construire ? ");
					System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
					System.out.println("Pour rappel, voici votre main :");
					for (int j = 0; j < joueurActuel.nbQuartiersDansMain(); j++) {
						System.out.print(j + 1 + " " + joueurActuel.getMain().get(j).getNom() + "(coût "
								+ joueurActuel.getMain().get(j).getCout() + "), ");
					}
					System.out.println("et vous avez " + joueurActuel.nbPieces() + " pièces dans votre trésorerie.");
					if (Interaction.lireOuiOuNon()) {
						int nombreQuartiersConstruit = 0;
						System.out.println("Quel quartier choisissez-vous ? (0 pour annuler)");
						boolean check = false;
						do {
							int choixQuartier = Interaction.lireUnEntier(0, joueurActuel.nbQuartiersDansMain() + 1);
							if (choixQuartier == 0) {
								break;
							}
							choixQuartier -= 1;
							Quartier nomQuartier = joueurActuel.getMain().get(choixQuartier);
							System.out.println("Nom du quartier choisi : " + nomQuartier.getNom());
							int verifCoutQuartier = joueurActuel.getMain().get(choixQuartier).getCout();
							System.out.println("Le quartier coûte : " + verifCoutQuartier);
							if (verifCoutQuartier > joueurActuel.nbPieces()) {
								System.out.println("Votre trésor n'est pas suffisant");
								System.out.println("Votre choix ? (0 pour annuler)");
								check = false;
							} else {
								check = true;
								joueurActuel.retirerPieces(verifCoutQuartier);
								joueurActuel.ajouterQuartierDansCite(nomQuartier);
								ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(joueurActuel.getMain());
								int tailleMain = joueurActuel.getMain().size();
								PlateauDeJeu.getPioche().ajouter(copieTableau.get(choixQuartier));
								copieTableau.remove(choixQuartier);

								for (int j = 0; j < tailleMain; j++) {
									joueurActuel.retirerQuartierDansMain();
								}
								for (int j = 0; j < copieTableau.size(); j++) {
									joueurActuel.ajouterQuartierDansMain(copieTableau.get(i));
								}

								System.out.println("Pour information il vous reste " + joueurActuel.nbPieces()
										+ " pièces d'or dans votre trésorerie");
								nombreQuartiersConstruit += 1;
								if ((personnageActuel.getNom() == Caracteristiques.ARCHITECTE)
										&& nombreQuartiersConstruit != 3) {
									System.out.println("En tant qu'Architecte, vous pouvez construire "
											+ (3 - nombreQuartiersConstruit) + " quartiers restants");
									System.out.println("Voulez vous en construire un autre ?");
									if (!Interaction.lireOuiOuNon()) {
										nombreQuartiersConstruit = 3;
									}
								}
							}
						} while (((check != true) && (personnageActuel.getNom() != Caracteristiques.ARCHITECTE))
								|| nombreQuartiersConstruit == 3);

					}
				}
				System.out.println("Tour terminé !");
				System.out.println("");
			}
		}
	}

	public void choixPersonnages() {
		Personnage[] listePersonnage = new Personnage[9];
		System.out.println("Choix des personnages : ");
		int randomVisible1 = generateur.nextInt(nombrePersonnages);
		int randomVisible2 = generateur.nextInt(nombrePersonnages);
		int randomCache = generateur.nextInt(nombrePersonnages);
		while (randomVisible2 == randomVisible1 || randomCache == randomVisible1 || randomVisible2 == randomCache) {
			randomVisible2 = generateur.nextInt(nombrePersonnages);
			randomCache = generateur.nextInt(nombrePersonnages);
		}
		System.out.println(
				"Le personnage " + PlateauDeJeu.getPersonnage(randomVisible1).getNom() + " est écarté face visible");
		System.out.println(
				"Le personnage " + PlateauDeJeu.getPersonnage(randomVisible2).getNom() + " est écarté face visible");
		System.out.println("Un personnage est écarté face cachée");

		for (int i = 0; i < nombrePersonnages; i++) {
			if (i == randomVisible1 || i == randomVisible2 || i == randomCache) {
				listePersonnage[i] = null;
			} else {
				listePersonnage[i] = PlateauDeJeu.getPersonnage(i);
			}
		}

		for (int j = 0; j < nombreJoueurs; j++) {
			if (PlateauDeJeu.getJoueur(j).getPossedeCouronne() == true) {
				System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a la couronne ! Il est le premier à choisir son personnage.");
				int choixPersonnage;
				if (Avatar) {
					do {
						choixPersonnage = generateur.nextInt(nombrePersonnages);
					} while (listePersonnage[choixPersonnage] == null);
					listePersonnage[choixPersonnage].setJoueur(PlateauDeJeu.getJoueur(j));
					//System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a choisi un personnage");
					System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a choisi le personnage "+listePersonnage[choixPersonnage].getNom());
					listePersonnage[choixPersonnage] = null;
				} else {
					for (int i = 0; i < nombrePersonnages; i++) {
						if (i != randomVisible1 && i != randomVisible2 && i != randomCache
								&& listePersonnage[i] != null) {
							System.out.println(listePersonnage[i].getRang() + " " + listePersonnage[i].getNom());
						} else {
							listePersonnage[i] = null;
						}
					}

					System.out.println("Quel personnage choisissez-vous ? ");
					do {
						choixPersonnage = Interaction.lireUnEntier(0, nombrePersonnages + 1) - 1;
					} while (listePersonnage[choixPersonnage] == null);
					listePersonnage[choixPersonnage].setJoueur(PlateauDeJeu.getJoueur(j));
					System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a choisi un personnage");
					listePersonnage[choixPersonnage] = null;
				}
			}

		}
		for (int j = 0; j < nombreJoueurs; j++) {
			if (PlateauDeJeu.getJoueur(j).getPossedeCouronne() == false) {
				System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " choisit son personnage.");
				int choixPersonnage;
				if (Avatar) {
					do {
						choixPersonnage = generateur.nextInt(nombrePersonnages);
					} while (listePersonnage[choixPersonnage] == null);
					listePersonnage[choixPersonnage].setJoueur(PlateauDeJeu.getJoueur(j));
					//System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a choisi un personnage");
					System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a choisi le personnage "+listePersonnage[choixPersonnage].getNom());
					listePersonnage[choixPersonnage] = null;
				} else {
					for (int i = 0; i < nombrePersonnages; i++) {
						if (i != randomVisible1 && i != randomVisible2 && i != randomCache
								&& listePersonnage[i] != null) {
							System.out.println(listePersonnage[i].getRang() + " " + listePersonnage[i].getNom());
						} else {
							listePersonnage[i] = null;
						}
					}
					System.out.println("Quel personnage choisissez-vous ? ");
					if (PlateauDeJeu.getJoueur(j).getPersonnage() == null) {
						do {
							choixPersonnage = Interaction.lireUnEntier(0, nombrePersonnages + 1) - 1;
						} while (listePersonnage[choixPersonnage] == null);
						listePersonnage[choixPersonnage].setJoueur(PlateauDeJeu.getJoueur(j));
						System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a choisi un personnage");
						listePersonnage[choixPersonnage] = null;
					}
				}
			}
		}
		System.out.println("L'attribution des personnages est terminée");
	}

	public void percevoirRessource(Joueur joueurActuel) {
		int choixRessource;
		if (Avatar) {
			choixRessource = generateur.nextInt(2);
		} else {
			System.out.println("Souhaitez vous obtenir 2 pièces d'or ou choisir entre 2 cartes quartiers ? ");
			System.out.println("1 - Ajouter 2 pièces d'or à votre trésorerie");
			System.out.println("2 - Choisir entre 2 cartes quartiers à ajouter à votre main");
			choixRessource = Interaction.lireUnEntier(1, 3);
		}
		if (choixRessource == 1) {
			joueurActuel.ajouterPieces(2);
		} else {
			int choixQuartier;
			Quartier choixQuartier1 = pioche.piocher();
			Quartier choixQuartier2 = pioche.piocher();
			if (Avatar) {
				choixQuartier = generateur.nextInt(2);
			} else {
				System.out.println("Quel quartier choississez vous ? ");
				
				System.out.println(
						"1 - Premier quartier : " + choixQuartier1.getNom() + " (coût " + choixQuartier1.getCout() + ")");
				
				System.out.println(
						"2 - Deuxième quartier : " + choixQuartier2.getNom() + " (coût " + choixQuartier2.getCout() + ")");
				choixQuartier = Interaction.lireUnEntier(1, 3);
			}
			if (choixQuartier == 1) {
				joueurActuel.ajouterQuartierDansMain(choixQuartier1);
				pioche.ajouter(choixQuartier2);
			} else {
				joueurActuel.ajouterQuartierDansMain(choixQuartier2);
				pioche.ajouter(choixQuartier1);
			}
		}
	}

	public void calculDesPoints() {
		int gagnant = 0;
		String gagnantNom = "";
		boolean premierJoueur = true;
		for (int i = 0; i < nombreJoueurs; i++) {
			int pointsCoutConstruction = 0;
			int pointsNombreType = 0;
			String typeQuartier;
			int[] quartierParType = { 0, 0, 0, 0, 0 };
			boolean typeDifferent = true;
			int pointsCiteTermine = 0;

			for (int j = 0; j < PlateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
				pointsCoutConstruction += PlateauDeJeu.getJoueur(i).getCite()[j].getCout();
				typeQuartier = PlateauDeJeu.getJoueur(i).getCite()[j].getType();
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
			}

			for (int j = 0; j < quartierParType.length; j++) {
				if (quartierParType[j] == 0) {
					typeDifferent = false;
				}
			}
			if (typeDifferent) {
				pointsNombreType = 3;
			}

			if (PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8) {
				if (premierJoueur) {
					pointsCiteTermine = 4;
					premierJoueur = false;
				} else {
					pointsCiteTermine = 2;
				}

			}

			int nombrePoints = pointsCoutConstruction + pointsNombreType + pointsCiteTermine;
			System.out.println(PlateauDeJeu.getJoueur(i).getNom() + " à obtenu " + nombrePoints + " points !");

			if (gagnant < nombrePoints) {
				gagnant = nombrePoints;
				gagnantNom = PlateauDeJeu.getJoueur(i).getNom();
			}
		}
		System.out.println(gagnantNom + " rempore la partie avec " + gagnant + " points !");
		System.out.println("");
	}

}