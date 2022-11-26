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

public class Jeu {
	private PlateauDeJeu PlateauDeJeu;
	private int numeroConfiguration;
	private Random generateur;
	private int nombreJoueurs;
	private int nombrePersonnages;
	private Pioche pioche;
	private boolean Avatar;

	public Jeu() {
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

	private void afficherLesRegles() {
		System.out.println("Il faudra ajouter les règles ici");
		System.out.println("");
	}

	private void jouerPartie() {
		int i = 0;
		initialisation();
		do {
			i++;
			System.out.println("Tour n°" + i);
			choixPersonnages();
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();
		} while (!partieFinie());
		calculDesPoints();
	}

	private void initialisation() {
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

	private void gestionCouronne() {
		for (int i = 0; i < nombrePersonnages; i++) {
			if ((PlateauDeJeu.getPersonnage(i).getNom() == Caracteristiques.ROI)
					&& (PlateauDeJeu.getPersonnage(i).getJoueur() != null)) {
				PlateauDeJeu.getJoueur(i).setPossedeCouronne(true);
			}
		}

	}

	private void reinitialisationPersonnages() {
		for (int i = 0; i < nombrePersonnages; i++) {
			if (PlateauDeJeu.getPersonnage(i).getJoueur() != null) {
				PlateauDeJeu.getPersonnage(i).reinitialiser();
			}
		}
	}

	private boolean partieFinie() {
		boolean varReturn = false;

		switch (nombreJoueurs) {
		case 4, 5, 6, 7:
			for (int i = 0; i < nombreJoueurs; i++) {
				if (PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 7) {
					varReturn = true;
					System.out.println("Partie Terminé !");
					System.out.println("");
					break;
				} else {
					varReturn = false;
				}
			}

		case 2, 3, 8:
			for (int i = 0; i < nombreJoueurs; i++) {
				if (PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8) {
					varReturn = true;
					System.out.println("");
					System.out.println("Partie Terminé !");
					System.out.println("");
					break;
				} else {
					varReturn = false;
				}
			}
		}
		return varReturn;
	}

	private void tourDeJeu() {
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
					
					if (joueurActuel.quartierPresentDansCite("Ecole de magie")) {
						int nbType = Quartier.TYPE_QUARTIERS.length;
						for (int j = 0; j < joueurActuel.nbQuartiersDansCite(); j++) {
							String nomQuartier = joueurActuel.getCite()[j].getNom();
							if (nomQuartier == "Ecole de magie") {								
								switch (generateur.nextInt(nbType)) {
								case 0:
									joueurActuel.getCite()[j].setType(Quartier.TYPE_QUARTIERS[0]);

								case 1:
									joueurActuel.getCite()[j].setType(Quartier.TYPE_QUARTIERS[1]);

								case 2:
									joueurActuel.getCite()[j].setType(Quartier.TYPE_QUARTIERS[2]);

								case 3:
									joueurActuel.getCite()[j].setType(Quartier.TYPE_QUARTIERS[3]);
								}

							}
						}
					}

					personnageActuel.percevoirRessourcesSpecifiques();
					
					if (joueurActuel.quartierPresentDansCite("Forge") && (joueurActuel.nbPieces() >= 2) && (generateur.nextInt(2) == 1)) {
						joueurActuel.retirerPieces(2);
						for (int l = 0; l < 3; l++) {
							joueurActuel.ajouterQuartierDansMain(pioche.piocher());
						}
					}
					
					if (joueurActuel.quartierPresentDansCite("Laboratoire") && generateur.nextInt(2) == 1 && joueurActuel.getMain().size()>=1) {
						int nbCartePossedez = joueurActuel.getMain().size();
						ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(joueurActuel.getMain());
						int choice = generateur.nextInt(nbCartePossedez);
						pioche.ajouter(copieTableau.get(choice));
						copieTableau.remove(choice);
						joueurActuel.ajouterPieces(2);
						for (i = 0; i <= joueurActuel.getMain().size(); i++) {
							joueurActuel.retirerQuartierDansMain();
						}
						for (i = 0; i < copieTableau.size(); i++) {
							joueurActuel.ajouterQuartierDansMain(copieTableau.get(i));
						}
					}

					if (generateur.nextInt(2) == 1) {
						// personnageActuel.utiliserPouvoirAvatar();
						System.out.println("Le pouvoir de l'Avatar " + joueurActuel.getNom() + " a été utilisé !");
					}
					if ((generateur.nextInt(2) == 1 && (joueurActuel.nbQuartiersDansMain() != 0))) {
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
								if (out >= 10) {
									check = true;
								}
							} else {
								check = true;
								joueurActuel.retirerPieces(verifCoutQuartier);
								joueurActuel.ajouterQuartierDansCite(nomQuartier);
								System.out.println("L'Avatar " + joueurActuel.getNom() + " a construit le quartier "
										+ nomQuartier.getNom());
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
										if (joueurActuel.nbPieces() >= joueurActuel.getMain().get(j).getCout()) {
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
						System.out.println("Vous avez été volé ! Vous donnez " + joueurActuel.nbPieces()
								+ " pièces d'or au Voleur");
						for (int j = 0; j < nombreJoueurs; j++) {
							if (PlateauDeJeu.getJoueur(j).getPersonnage().getNom() == Caracteristiques.VOLEUR) {
								PlateauDeJeu.getJoueur(j).ajouterPieces(joueurActuel.nbPieces());
							}
						}
						joueurActuel.retirerPieces(joueurActuel.nbPieces());
					}
					percevoirRessource(joueurActuel);
					
					if (joueurActuel.quartierPresentDansCite("Ecole de magie")) {
						int nbType = 0;
						for (int j = 0; j < PlateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
							String nomQuartier = PlateauDeJeu.getJoueur(i).getCite()[j].getNom();
							if (nomQuartier == "Ecole de magie") {

								System.out.println("Comment considerez vous l'Ecole de magie ?");
								for (int k = 0; k < nbType - 1; k++) {
									System.out.println(k + " - " + Quartier.TYPE_QUARTIERS[k]);
								}
								int choix = Interaction.lireUnEntier(0, nbType);
								switch (choix) {
								case 0:
									PlateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[0]);

								case 1:
									PlateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[1]);

								case 2:
									PlateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[2]);

								case 3:
									PlateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[3]);
								}

							}
						}
					}

					personnageActuel.percevoirRessourcesSpecifiques();
					
					if (joueurActuel.quartierPresentDansCite("Forge")) {
						System.out.println("Vous avez " + joueurActuel.nbPieces() + " pièces dans votre trésorerie.");
						System.out.println("Voulez vous payez 2 pieces d’or pour piocher 3 cartes ?");
						System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
						if (Interaction.lireOuiOuNon()) {
							joueurActuel.retirerPieces(2);
							for (int l = 0; l < 3; l++) {
								joueurActuel.ajouterQuartierDansMain(pioche.piocher());
							}
						}
					}
					
					if (joueurActuel.quartierPresentDansCite("Laboratoire")) {
						int nbCartePossedez = joueurActuel.getMain().size();
						ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(joueurActuel.getMain());
						System.out.println("Quelle carte voulez vous supprimer ?");
						for (i = 0; i < nbCartePossedez; i++) {
							System.out.println(i + ". " + copieTableau.get(i).getNom());
						}
						int choice = Interaction.lireUnEntier(0, nbCartePossedez);
						pioche.ajouter(copieTableau.get(choice));
						copieTableau.remove(choice);
						joueurActuel.ajouterPieces(2);

						for (i = 0; i <= joueurActuel.getMain().size(); i++) {
							joueurActuel.retirerQuartierDansMain();
						}
						for (i = 0; i < copieTableau.size(); i++) {
							joueurActuel.ajouterQuartierDansMain(copieTableau.get(i));
						}
					}

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

	private void choixPersonnages() {
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
				System.out.println(PlateauDeJeu.getJoueur(j).getNom()
						+ " a la couronne ! Il est le premier à choisir son personnage.");
				int choixPersonnage;
				if (Avatar) {
					do {
						choixPersonnage = generateur.nextInt(nombrePersonnages);
					} while (listePersonnage[choixPersonnage] == null);
					System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a choisi un personnage");
					listePersonnage[choixPersonnage].setJoueur(PlateauDeJeu.getJoueur(j));
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
					System.out.println(PlateauDeJeu.getJoueur(j).getNom() + " a choisi un personnage");
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

	private void percevoirRessource(Joueur joueurActuel) {
		int choixRessource;
		if (Avatar) {
			choixRessource = generateur.nextInt(2);
			if (choixRessource == 1) {
				joueurActuel.ajouterPieces(2);
			} else {
				if (joueurActuel.quartierPresentDansCite("Bibliothèque")) {
					for (int i = 0; i < 2; i++) {
						Quartier choixQuartier = pioche.piocher();
						joueurActuel.ajouterQuartierDansMain(choixQuartier);
					}
				} else {
					Quartier choixQuartier1 = pioche.piocher();
					Quartier choixQuartier2 = pioche.piocher();
					if (generateur.nextInt(2) == 1) {
						joueurActuel.ajouterQuartierDansMain(choixQuartier1);
						pioche.ajouter(choixQuartier2);
					} else {
						joueurActuel.ajouterQuartierDansMain(choixQuartier2);
						pioche.ajouter(choixQuartier1);
					}
				}
			}
		} else {
			System.out.println("Souhaitez vous obtenir 2 pièces d'or ou choisir entre 2 cartes quartiers ? ");
			System.out.println("1 - Ajouter 2 pièces d'or à votre trésorerie");
			System.out.println("2 - Choisir entre 2 cartes quartiers à ajouter à votre main");
			choixRessource = Interaction.lireUnEntier(1, 3);
			if (choixRessource == 1) {
				joueurActuel.ajouterPieces(2);
			} else {
				if (joueurActuel.quartierPresentDansCite("Bibliothèque")) {
					for (int i = 0; i < 2; i++) {
						Quartier choixQuartier = pioche.piocher();
						System.out.println(i + " - " + choixQuartier.getNom() + " (coût " + choixQuartier.getCout() + ")");
						joueurActuel.ajouterQuartierDansMain(choixQuartier);
					}
				} else {
					System.out.println("Quel quartier choississez vous ? ");
					Quartier choixQuartier1 = pioche.piocher();
					System.out.println("1 - Premier quartier : " + choixQuartier1.getNom() + " (coût "
							+ choixQuartier1.getCout() + ")");
					Quartier choixQuartier2 = pioche.piocher();
					System.out.println("2 - Deuxième quartier : " + choixQuartier2.getNom() + " (coût "
							+ choixQuartier2.getCout() + ")");
					int choixQuartier = Interaction.lireUnEntier(1, 3);
					if (choixQuartier == 1) {
						joueurActuel.ajouterQuartierDansMain(choixQuartier1);
						pioche.ajouter(choixQuartier2);
					} else {
						joueurActuel.ajouterQuartierDansMain(choixQuartier2);
						pioche.ajouter(choixQuartier1);
					}
				}
			}
		}
	}

	private void calculDesPoints() {
		int gagnant = 0;
		String gagnantNom = "";
		boolean premierJoueur = true;
		int[] nombrePoints = {0,0,0,0};
		int[] pointsCoutConstruction = {0,0,0,0};
		int[] pointsNombreType = {0,0,0,0};
		int[] pointsCiteTermine = {0,0,0,0};
		int[] pointsMerveille = {0,0,0,0};
		for (int i = 0; i < nombreJoueurs; i++) {
			String typeQuartier;
			int[] quartierParType = { 0, 0, 0, 0, 0 };
			boolean typeDifferent = true;
			boolean possedeFontaine = false;
			int nbType = Quartier.TYPE_QUARTIERS.length;
			for (int j = 0; j < PlateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
				pointsCoutConstruction[i] += PlateauDeJeu.getJoueur(i).getCite()[j].getCout();
				typeQuartier = PlateauDeJeu.getJoueur(i).getCite()[j].getType();
				if (PlateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Ecole de magie") {
					quartierParType[4] += 1;
				}

				if (typeQuartier == "RELIGIEUX") {
					quartierParType[0] += 1;
				} else if (typeQuartier == "MILITAIRE") {
					quartierParType[1] += 1;
				} else if (typeQuartier == "NOBLE") {
					quartierParType[2] += 1;
				} else if (typeQuartier == "COMMERCANT") {
					quartierParType[3] += 1;
				} else if (typeQuartier == "MERVEILLE") {
					if (PlateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Cours des miracles") {
						if(Avatar) {
							switch (generateur.nextInt(nbType)) {
							case 0:
								quartierParType[0] += 1;
								break;
							case 1:
								quartierParType[1] += 1;
								break;
							case 2:
								quartierParType[2] += 1;
								break;
							case 3:
								quartierParType[3] += 1;
								break;
							}
						} else {
							System.out.println("Comment considerez vous la Cours des miracles ?");
							for (int k = 0; k < nbType; k++) {
								System.out.println(k + " - " + Quartier.TYPE_QUARTIERS[k]);
							}
							int choix = Interaction.lireUnEntier(0, nbType);
							switch (choix) {
							case 0:
								quartierParType[0] += 1;
								break;
							case 1:
								quartierParType[1] += 1;
								break;
							case 2:
								quartierParType[2] += 1;
								break;
							case 3:
								quartierParType[3] += 1;
								break;
							}
						}
					}

					if (PlateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Fontaine aux souhaits") {
						possedeFontaine = true;
					}
					quartierParType[4] += 1;
				}
			}

			for (int j = 0; j < quartierParType.length; j++) {
				if (quartierParType[j] == 0) {
					typeDifferent = false;
				}
			}
			if (typeDifferent) {
				pointsNombreType[i] = 3;
			}

			if (nombreJoueurs == 4 || nombreJoueurs == 5 || nombreJoueurs == 6 || nombreJoueurs == 7) {
				if (PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
					if (premierJoueur) {
						pointsCiteTermine[i] = 4;
						premierJoueur = false;
					} else {
						pointsCiteTermine[i] = 2;
					}

				}
			} else {
				if (PlateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8) {
					if (premierJoueur) {
						pointsCiteTermine[i] = 4;
						premierJoueur = false;
					} else {
						pointsCiteTermine[i] = 2;
					}

				}
			}


			if (PlateauDeJeu.getJoueur(i).quartierPresentDansCite("Dracoport")) {
				pointsMerveille[i] += 2;
			}
			if (possedeFontaine) {
				pointsMerveille[i] += quartierParType[4];
			}

			nombrePoints[i] = pointsCoutConstruction[i] + pointsNombreType[i] + pointsCiteTermine[i] + pointsMerveille[i];
			System.out.println(PlateauDeJeu.getJoueur(i).getNom() + " à obtenu " + nombrePoints[i] + " points !");

			if (gagnant < nombrePoints[i]) {
				gagnant = nombrePoints[i];
				gagnantNom = PlateauDeJeu.getJoueur(i).getNom();
			}
		}
		System.out.println(gagnantNom + " remporte la partie avec " + gagnant + " points !");
		System.out.println("");
	}

}
