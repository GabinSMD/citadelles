package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import controleur.Interaction;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

public class Jeu {
	private PlateauDeJeu plateauDeJeu;
	//private int numeroConfiguration;
	private Random generateur;
	private int nombreJoueurs;
	private int nombrePersonnages;
	private Pioche pioche;
	
	private boolean first = false;
	
	private Joueur winner;

	private int choix=0;
	private int nbTypeQuartier = Quartier.TYPE_QUARTIERS.length;
	
	public Jeu() {
		this.plateauDeJeu = new PlateauDeJeu();
		this.generateur = new Random();
		//useless in this version
		//this.numeroConfiguration = numeroConfiguration;
	}
	
	//REFACTOR OK
	private void initialisation() {

		this.pioche = Configuration.nouvellePioche();
		this.plateauDeJeu = Configuration.configurationDeBase(pioche);
		this.pioche.melanger();
		
		this.nombreJoueurs = this.plateauDeJeu.getNombreJoueurs();
		this.nombrePersonnages = this.plateauDeJeu.getNombrePersonnages();
		
		System.out.println("Choix des joueurs robot");
		System.out.println("Combien de joueurs robot souhaitez-vous ?");
		choix = Interaction.lireUnEntier(0, nombreJoueurs+1);
		for(int i=choix; i>0;i--) {
			for (int j = 0; j < this.nombreJoueurs; j++) {
				System.out.println(j+". "+this.plateauDeJeu.getJoueur(j).getNom());
			}
			System.out.println("Quel joueur est un robot ? (" + (i + " robots à selectionner"));
			choix = Interaction.lireUnEntier(0, nombreJoueurs+1);
			this.plateauDeJeu.getJoueur(choix).setAvatar(true);
		}
		
		for (int i = 0; i < this.nombreJoueurs; i++) {
			
			//Ajout de la trésorerie de départ (2 pièces)
			this.plateauDeJeu.getPersonnage(i).ajouterPieces();
			
			//Ajout des cartes de départ (4 cartes)
			for (int j = 0; j < 4; j++) {
				this.plateauDeJeu.getPersonnage(i).ajouterQuartier(pioche.piocher());
			}
		}
		
		//Attribution aléatoire de la couronne à un joueur 
		this.plateauDeJeu.getJoueur(generateur.nextInt(nombreJoueurs)).setPossedeCouronne(true);
	}
	
	
	//REFACTOR OK AND AVATAR OK
	private void choixPersonnages() {
		int randomVisible1 = 0;
		int randomVisible2= 0;
		int randomCache = 0;
		int index=0;
		ArrayList<Personnage> listePersonnageDisponible = new ArrayList<Personnage>();
	
		System.out.println("Choix des personnages : ");
		//Génération des cartes écartés
		do{
			randomVisible1 = generateur.nextInt(nombrePersonnages);
			randomVisible2 = generateur.nextInt(nombrePersonnages);
			randomCache = generateur.nextInt(nombrePersonnages);
			
		}while (randomVisible1 != randomVisible2 && randomVisible2 != randomCache && randomVisible1 != randomCache); 
		
		//Affichage des cartes ecartés et définition de la liste de personnage disponible
		for (int i = 0; i < nombrePersonnages; i++) {
			if (i == randomVisible1 || i == randomVisible2) {
				System.out.println("Le personnage " + this.plateauDeJeu.getPersonnage(i) + " est écarté face visible");
			} else if (i == randomCache) {
				System.out.println("Un personnage est écarté face cachée");
			}
			else{
				listePersonnageDisponible.add(this.plateauDeJeu.getPersonnage(i));
			}	
		}

		//Choix des personnages par les joueurs

		//Le joueur qui a la couronne commence
		for (int i = 0; i < nombreJoueurs; i++) {
			if (this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				System.out.println(this.plateauDeJeu.getJoueur(i).getNom()+ " a la couronne ! Il est le premier à choisir son personnage.");
				//Affiche des personnages
				for(Personnage perso : listePersonnageDisponible) {
					System.out.println(index+ ". "+ perso.getNom());
					index++;
				}
				//Cas avatar
				if (this.plateauDeJeu.getJoueur(i).getAvatar()) {
					choix = generateur.nextInt(listePersonnageDisponible.size()+1);
				} 
				//Cas joueur
				else {
					System.out.println("Quel personnage choisissez-vous ? ");
					choix = Interaction.lireUnEntier(0, listePersonnageDisponible.size()+1);
				}
				//Attribution du joueur au personnage
				this.plateauDeJeu.getPersonnage(choix).setJoueur(this.plateauDeJeu.getJoueur(i));
				//Affichage du personnage choisi
				System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi le personnage : "+ listePersonnageDisponible.get(choix));
				//Retrait du personnage de la liste de personnage disponible
				listePersonnageDisponible.remove(choix);
			}
		}
		
		//Les autres joueurs
		for (int i = 0; i < nombreJoueurs; i++) {
			if (!this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " choisit son personnage.");
				//Affiche des personnages
				for(Personnage perso : listePersonnageDisponible) {
					System.out.println(index+ ". "+ perso.getNom());
					index++;
				}
				//Cas avatar
				if (this.plateauDeJeu.getJoueur(i).getAvatar()) {
					choix = generateur.nextInt(listePersonnageDisponible.size()+1);
				}
				//Cas joueur
				else {
					System.out.println("Quel personnage choisissez-vous ? ");
					choix = Interaction.lireUnEntier(0, listePersonnageDisponible.size()+1);
				}
				//Attribution du joueur au personnage
				this.plateauDeJeu.getPersonnage(choix).setJoueur(this.plateauDeJeu.getJoueur(i));
				//Affichage du personnage choisi
				System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi le personnage : "+ listePersonnageDisponible.get(choix));
				//Retrait du personnage de la liste de personnage disponible
				listePersonnageDisponible.remove(choix);
				
			}
		}
		System.out.println("L'attribution des personnages est terminée");
	}

	
	//REFACTOR OK AND AVATAR OK
	private void percevoirRessource(Personnage personnageActuel) {
		
		//Cas avatar
		if (personnageActuel.getJoueur().getAvatar()) {
			//Choix entre pièce (1) ou carte (2)
			choix = generateur.nextInt(2);
			switch (choix) {
				//Ajout des pieces
				case 1:
					personnageActuel.ajouterPieces();
					break;
				//Ajout des cartes
				case 2:
					//Cas merveille cité
					if (personnageActuel.getJoueur().quartierPresentDansCite("Bibliothèque")) {
						//Conserve les 2 cartes
						for (int i = 0; i < 2; i++) {
							Quartier choixQuartier = pioche.piocher();
							System.out.println(i + ". " + choixQuartier.getNom() + " (coût " + choixQuartier.getCout() + ")");
							personnageActuel.ajouterQuartier(choixQuartier);
						}
					} 
					//Cas classique
					else {
						//Définition des 2 cartes piochées
						Quartier choixQuartier1 = pioche.piocher();
						Quartier choixQuartier2 = pioche.piocher();
						System.out.println("Quel quartier voulez-vous garder ? ");
						System.out.println("1." + choixQuartier1.getNom() + " (coût "+ choixQuartier1.getCout() + ")");
						System.out.println("2." + choixQuartier2.getNom() + " (coût "+ choixQuartier2.getCout() + ")");
						//Choix aléatoire de la carte à garder
						switch (generateur.nextInt(2)) {
							case 1:
								personnageActuel.ajouterQuartier(choixQuartier1);
								pioche.ajouter(choixQuartier2);
								break;
							case 2:
								personnageActuel.ajouterQuartier(choixQuartier2);
								pioche.ajouter(choixQuartier1);
								break;
						}
					}
					break;
			}
		} 
		//Cas joueur
		else {
			//Choix entre pièces (1) ou cartes (2)
			System.out.println("Souhaitez vous obtenir 2 pièces d'or ou choisir entre 2 cartes quartiers ? ");
			System.out.println("1. Ajouter 2 pièces d'or à votre trésorerie");
			System.out.println("2. Choisir entre 2 cartes quartiers à ajouter à votre main");
			choix = Interaction.lireUnEntier(1, 3);
			switch (choix) {
				//Ajout des pièces
				case 1:
					personnageActuel.ajouterPieces();
					break;
				//Ajout des cartes
				case 2:
					//Cas de la merveille
					if (personnageActuel.getJoueur().quartierPresentDansCite("Bibliothèque")) {
						//Conserve les 2 cartes
						for (int i = 0; i < 2; i++) {
							Quartier choixQuartier = pioche.piocher();
							System.out.println(i + ". " + choixQuartier.getNom() + " (coût " + choixQuartier.getCout() + ")");
							personnageActuel.ajouterQuartier(choixQuartier);
						}
					} 
					//Cas classique
					else {
						Quartier choixQuartier1 = pioche.piocher();
						Quartier choixQuartier2 = pioche.piocher();
						System.out.println("Quel quartier voulez-vous garder ? ");
						System.out.println("1." + choixQuartier1.getNom() + " (coût "+ choixQuartier1.getCout() + ")");
						System.out.println("2." + choixQuartier2.getNom() + " (coût "+ choixQuartier2.getCout() + ")");
						choix = Interaction.lireUnEntier(1, 3);
						switch (choix) {
							case 1:
								personnageActuel.ajouterQuartier(choixQuartier1);
								pioche.ajouter(choixQuartier2);
								break;
							case 2:
								personnageActuel.ajouterQuartier(choixQuartier2);
								pioche.ajouter(choixQuartier1);
								break;
						}
					}
					break;
			}
		}
	}

	//REFACTOR OK BUT AVATAR NOT
	private void construire(Personnage personnageActuel) {
		Quartier quartierAConstruire;
		
		System.out.println("Voulez vous construire ? ");
		System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
		System.out.println("Vous avez " + personnageActuel.getJoueur().nbPieces()+ "pièces dans votre trésorerie et votre main est composé de :");
		for (int i = 0; i < personnageActuel.getJoueur().nbQuartiersDansMain(); i++) {
			System.out.print(i + ". " + personnageActuel.getJoueur().getMain().get(i).getNom() + "(coût "+ personnageActuel.getJoueur().getMain().get(i).getCout() + "), ");
		}
		
		if (Interaction.lireOuiOuNon()) {
			int coutQuartier=0;
			System.out.println("Quel quartier voulez-vous construire ?");
			choix = Interaction.lireUnEntier(0, personnageActuel.getJoueur().nbQuartiersDansMain());
			quartierAConstruire = personnageActuel.getJoueur().getMain().get(choix);
			System.out.println("Vous avez choisi : " + quartierAConstruire.getNom());
			
			if(personnageActuel.getJoueur().quartierPresentDansCite("Manufacture") && quartierAConstruire.getType() == Quartier.TYPE_QUARTIERS[4]) {
				coutQuartier = personnageActuel.getJoueur().getMain().get(choix).getCout()-1;
			}else {
				coutQuartier = personnageActuel.getJoueur().getMain().get(choix).getCout();
			}
			System.out.println("La construction vous coute : " + coutQuartier + " pièces d'or");
			
			
			if(quartierAConstruire.getNom() == "Tripot") {
				if (coutQuartier > personnageActuel.getJoueur().nbPieces() + personnageActuel.getJoueur().nbQuartiersDansMain()) {
					System.out.println("Votre trésor n'est pas suffisant");
				} else {
					System.out.println("Vous pouvez payer tout ou partie du coût de construction du Tripot en cartes de votre main, au prix de 1 carte pour 1 pièce d’or.");
					System.out.println("Voulez vous payer en carte ? ");
					System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
					if (Interaction.lireOuiOuNon()) {
						ArrayList<Quartier> copieTableau;
						int nbCartePossedez = personnageActuel.getJoueur().nbQuartiersDansMain();
						int max = coutQuartier+1;
						System.out.println("Vous avez "+ nbCartePossedez +" carte(s)");
						System.out.println("Combien de carte voulez vous utiliser :");
						int choix = Interaction.lireUnEntier(0,max);
						if(choix == coutQuartier && choix==nbCartePossedez) {
							copieTableau = new ArrayList<Quartier>(personnageActuel.getJoueur().getMain());
							for(int i=0; i<=copieTableau.size(); i++) {
								pioche.ajouter(personnageActuel.getJoueur().retirerQuartierDansMain());
								coutQuartier -=1;
							}
						}else if (choix > 0 && choix < nbCartePossedez) {
							copieTableau = new ArrayList<Quartier>(personnageActuel.getJoueur().getMain());
							for (int i = choix; i > 0; i--) {
								System.out.println("Quelle carte voulez-vous defausser ? (" + i + " cartes à defausser)");
								for (int j = 0; j < nbCartePossedez; j++) {
									System.out.println(j + ". " + copieTableau.get(j).getNom());
								} 
								choix = Interaction.lireUnEntier(0,max);
								pioche.ajouter(copieTableau.get(choix));
								copieTableau.remove(choix);
								coutQuartier -=1;
							}
							for(int i=0; i<personnageActuel.getJoueur().nbQuartiersDansMain(); i++) {
								personnageActuel.getJoueur().retirerQuartierDansMain();
							}
							for(int i=0; i<copieTableau.size(); i++) {
								personnageActuel.getJoueur().ajouterQuartierDansMain(copieTableau.get(i));
							}
						}
					
					}
					personnageActuel.getJoueur().retirerPieces(coutQuartier);
					personnageActuel.construire(quartierAConstruire);
				}
			}
			
			if (coutQuartier > personnageActuel.getJoueur().nbPieces()) {
				System.out.println("Votre trésor n'est pas suffisant");
			} else {
				
				ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(personnageActuel.getJoueur().getMain());

				this.plateauDeJeu.getPioche().ajouter(copieTableau.get(choix));
				copieTableau.remove(choix);
				for (int i = 0; i < personnageActuel.getJoueur().nbQuartiersDansMain(); i++) {
					personnageActuel.getJoueur().retirerQuartierDansMain();
				}
				for (int i = 0; i < copieTableau.size(); i++) {
					personnageActuel.getJoueur().ajouterQuartierDansMain(copieTableau.get(i));
				}
				System.out.println("Pour information il vous reste " + personnageActuel.getJoueur().nbPieces() + " pièces d'or dans votre trésorerie");
				
				personnageActuel.getJoueur().retirerPieces(coutQuartier);
				personnageActuel.construire(quartierAConstruire);
			}
			
			if (personnageActuel.getNom() == Caracteristiques.ARCHITECTE) {
				System.out.println("En tant qu'Architecte, vous pouvez construire 3 quartiers supplémentaire");
				System.out.println("Voulez vous en construire des quartiers supplémentaire ?");
				if (Interaction.lireOuiOuNon()) {
					System.out.println("Combien de quartiers voulez-vous construire ?");
					int choix = Interaction.lireUnEntier(0,3);
					for(int i = choix;i>0; i--) {
						for (int j = 0; j < personnageActuel.getJoueur().nbQuartiersDansMain(); j++) {
							System.out.print(j + ". " + personnageActuel.getJoueur().getMain().get(j).getNom() + "(coût "+ personnageActuel.getJoueur().getMain().get(j).getCout() + "), ");
						}
						System.out.println("Quel quartier voulez-vous construire ? ("+ i + " cartes à defausser)");
						choix = Interaction.lireUnEntier(0, personnageActuel.getJoueur().nbQuartiersDansMain());
						quartierAConstruire = personnageActuel.getJoueur().getMain().get(choix);
						System.out.println("Vous avez choisi : " + quartierAConstruire.getNom());
						if (coutQuartier > personnageActuel.getJoueur().nbPieces()+ personnageActuel.getJoueur().nbQuartiersDansMain()) {
							System.out.println("Votre trésor n'est pas suffisant");
						} else {
							ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(personnageActuel.getJoueur().getMain());

							this.plateauDeJeu.getPioche().ajouter(copieTableau.get(choix));
							copieTableau.remove(choix);
							for (int j = 0; j < personnageActuel.getJoueur().nbQuartiersDansMain(); j++) {
								personnageActuel.getJoueur().retirerQuartierDansMain();
							}
							for (int j = 0; j < copieTableau.size(); j++) {
								personnageActuel.getJoueur().ajouterQuartierDansMain(copieTableau.get(j));
							}
							personnageActuel.getJoueur().retirerPieces(coutQuartier);
							personnageActuel.construire(quartierAConstruire);
							
						}
					}
				}
			}
			System.out.println("Pour information il vous reste " + personnageActuel.getJoueur().nbPieces() + " pièces d'or dans votre trésorerie");
		}
	}
	
	private void tourDeJeu() {
		Personnage personnageActuel;
		Joueur joueurActuel;
		ArrayList<Personnage> personnages = new ArrayList<Personnage>();
		
		this.choixPersonnages();

		//Liste local des persos triés par rang
		for (int j = 0; j < nombrePersonnages; j++) {
			personnages.add(this.plateauDeJeu.getPersonnage(j));
		}
		personnages.sort(Comparator.comparing(Personnage::getRang));
		
		
		for(int i=0; i < nombrePersonnages; i++) {
			for (int j = 0; j < nombrePersonnages; j++) {
				personnageActuel = this.plateauDeJeu.getPersonnage(j);
				joueurActuel = personnageActuel.getJoueur();
				if(personnageActuel.getNom()==personnages.get(i).getNom()){
					System.out.println("Le personnage " + personnageActuel.getNom() + " est appelé !");
					
					if (!personnageActuel.getAssassine() && joueurActuel != null) {
						System.out.println("Le joueur " + joueurActuel.getNom() + " commence son tour !\n");
						
						//Transfert des fonds volés
						if (personnageActuel.getVole()) {
							System.out.println("Vous avez été volé ! Vous donnez " + joueurActuel.nbPieces() + " pièces d'or au Voleur");
							for (int k = 0; k < nombreJoueurs; k++) {
								if (this.plateauDeJeu.getJoueur(k).getPersonnage().getNom() == Caracteristiques.VOLEUR) {
									this.plateauDeJeu.getJoueur(k).ajouterPieces(joueurActuel.nbPieces());
									joueurActuel.retirerPieces(joueurActuel.nbPieces());
								}
							}
						}
						
						//Perception des ressources normales
						this.percevoirRessource(personnageActuel);
						
						//Changement de type pour la merveille : Ecole de magie
						if (joueurActuel.quartierPresentDansCite("Ecole de magie")) {
							for (int k = 0; k < joueurActuel.nbQuartiersDansCite(); k++) {
								if (joueurActuel.getCite()[k].getNom() == "Ecole de magie") {
									System.out.println("Comment considerez vous l'Ecole de magie ?");
									
									for (int l = 0; l < nbTypeQuartier-1; l++) {
										System.out.println(l + ". " + Quartier.TYPE_QUARTIERS[l]);
									}
									switch (Interaction.lireUnEntier(0, nbTypeQuartier)) {
										case 0:
											joueurActuel.getCite()[k].setType(Quartier.TYPE_QUARTIERS[0]);
			
										case 1:
											joueurActuel.getCite()[k].setType(Quartier.TYPE_QUARTIERS[1]);
			
										case 2:
											joueurActuel.getCite()[k].setType(Quartier.TYPE_QUARTIERS[2]);
			
										case 3:
											joueurActuel.getCite()[k].setType(Quartier.TYPE_QUARTIERS[3]);
									}
								}
							}
						}
						
						//Perception des ressources du personnage
						personnageActuel.percevoirRessourcesSpecifiques();
						
						if (joueurActuel.quartierPresentDansCite("Forge")) {
							System.out.println("Vous avez " + joueurActuel.nbPieces() + " pièces dans votre trésorerie.");
							System.out.println("Voulez vous payez 2 pièces d’or pour piocher 3 cartes ?");
							System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
							if (Interaction.lireOuiOuNon()) {
								for (int k = 0; k < 3; k++) {
									joueurActuel.ajouterQuartierDansMain(pioche.piocher());
								}
								joueurActuel.retirerPieces(2);
							}
						}
						
						if (joueurActuel.quartierPresentDansCite("Laboratoire")) {
							System.out.println("Voulez-vous vous défausser d'1 carte pour recevoir 2 pièces d’or  ?");
							System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
							if (Interaction.lireOuiOuNon()) {
								int nbCartePossedee = joueurActuel.getMain().size();
								ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(joueurActuel.getMain());
								System.out.println("Quelle carte voulez vous défausser ?");
								for (int k = 0; k < nbCartePossedee; k++) {
									System.out.println(k + ". " + copieTableau.get(k).getNom());
								}
								int choix = Interaction.lireUnEntier(0, nbCartePossedee);
								pioche.ajouter(copieTableau.get(choix));
								copieTableau.remove(choix);
								joueurActuel.ajouterPieces(2);
								for (int k = 0; k < joueurActuel.nbQuartiersDansMain(); k++) {
									joueurActuel.retirerQuartierDansMain();
								}
								for (int k = 0; k < copieTableau.size(); k++) {
									joueurActuel.ajouterQuartierDansMain(copieTableau.get(k));
								}
							}
						}
						
						System.out.println("Voulez vous utilisez votre pouvoir ? ");
						System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
						if (Interaction.lireOuiOuNon()) {
							personnageActuel.utiliserPouvoir();
						}
						
						//Appelle de la fonction construire
						this.construire(personnageActuel);
					}

					if(this.partieFinie() && !first) {
						first = true;
						if (nombreJoueurs == 4 || nombreJoueurs == 5 || nombreJoueurs == 6 || nombreJoueurs == 7) {
							if (joueurActuel.nbQuartiersDansCite() >= 7) {
								winner = this.plateauDeJeu.getJoueur(i);
							}
						} else {
							if (joueurActuel.nbQuartiersDansCite() >= 8) {
								winner = this.plateauDeJeu.getJoueur(i);
							}
						}
					}
				}
			}	
		}
		System.out.println("Tour terminé !");
	}
	
	//REFACTOR OK
	private void gestionCouronne() {
		for (int i = 0; i < nombrePersonnages; i++) {
			if (this.plateauDeJeu.getPersonnage(i).getNom() == Caracteristiques.ROI && this.plateauDeJeu.getPersonnage(i).getJoueur() != null) {
				this.plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
			}
		}

	}
	
	private void reinitialisationPersonnages() {
		for (int i = 0; i < nombrePersonnages; i++) {
			if (this.plateauDeJeu.getPersonnage(i).getJoueur() != null) {
				this.plateauDeJeu.getPersonnage(i).reinitialiser();
			}
		}
	}
	
	private boolean partieFinie() {
		boolean end = false;

		switch (nombreJoueurs) {
		case 4, 5, 6, 7:
			for (int i = 0; i < nombreJoueurs; i++) {
				if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 7) {
					end = true;
					System.out.println("Partie Terminé !\n");
					break;
				}
			}
		case 2, 3, 8:
			for (int i = 0; i < nombreJoueurs; i++) {
				if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8) {
					end = true;
					System.out.println("Partie Terminé !\n");
					break;
				}
			}
		}
		
		return end;
	}
	
	private void calculDesPoints() {
		int point = 0;
		boolean premierJoueur = true;
		
		ArrayList<Integer> pointsCoutConstruction = new ArrayList<Integer>();
		ArrayList<Integer> pointsMerveille = new ArrayList<Integer>();
		ArrayList<Integer> pointsCiteTermine = new ArrayList<Integer>();
		ArrayList<Integer> nombrePoints = new ArrayList<Integer>();
		ArrayList<Integer> pointsNombreType = new ArrayList<Integer>();
		
		
		for (int i = 0; i < nombreJoueurs; i++) {
			String typeQuartier = "";
			int[] nbQuartierParType = {0, 0, 0, 0, 0};
			
			
			for (int j = 0; j < this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
				pointsCoutConstruction.set(i, pointsCoutConstruction.get(i)+this.plateauDeJeu.getJoueur(i).getCite()[j].getCout());
				
				
				typeQuartier = this.plateauDeJeu.getJoueur(i).getCite()[j].getType();
				
				if (this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Ecole de magie") {
					this.plateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[4]);
				}
				

				if (this.plateauDeJeu.getJoueur(i).quartierPresentDansCite("Dracoport")) {
					pointsMerveille.set(i, pointsMerveille.get(i)+2);
				}
				
				if (this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Cours des miracles") {
					if(avatar) {
						switch (generateur.nextInt(nbTypeQuartier)) {
						case 0:
							nbQuartierParType[0] += 1;
							break;
						case 1:
							nbQuartierParType[1] += 1;
							break;
						case 2:
							nbQuartierParType[2] += 1;
							break;
						case 3:
							nbQuartierParType[3] += 1;
							break;
						}
					} else {
						System.out.println("Comment considerez-vous la Cours des miracles ?");
						for (int k = 0; k < nbTypeQuartier; k++) {
							System.out.println(k + " - " + Quartier.TYPE_QUARTIERS[k]);
						}
						int choix = Interaction.lireUnEntier(0, nbTypeQuartier);
						switch (choix) {
						case 0:
							nbQuartierParType[0] += 1;
							break;
						case 1:
							nbQuartierParType[1] += 1;
							break;
						case 2:
							nbQuartierParType[2] += 1;
							break;
						case 3:
							nbQuartierParType[3] += 1;
							break;
						case 4:
							nbQuartierParType[4] += 1;
							break;
						}
					}
				}
				
				if(this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Salle des cartes") {
					pointsMerveille.set(i, pointsMerveille.get(i)+this.plateauDeJeu.getJoueur(i).nbQuartiersDansMain());
				}
				
				if(this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Statue equestre" && this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
					pointsMerveille.set(i, pointsMerveille.get(i)+5);
				}
				
				if(this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Trésor Imperial") {
					pointsMerveille.set(i, pointsMerveille.get(i)+this.plateauDeJeu.getJoueur(i).nbPieces());
				}
				
				if (typeQuartier == "RELIGIEUX") {
					nbQuartierParType[0] += 1;
				} else if (typeQuartier == "MILITAIRE") {
					nbQuartierParType[1] += 1;
				} else if (typeQuartier == "NOBLE") {
					nbQuartierParType[2] += 1;
				} else if (typeQuartier == "COMMERCANT") {
					nbQuartierParType[3] += 1;
				} else if (typeQuartier == "MERVEILLE") {
					nbQuartierParType[4] += 1;
				}

				if (this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Fontaine aux souhaits") {
					nbQuartierParType[4] = nbQuartierParType[4]*2;
				}
				
			}
			
			if (nbQuartierParType[0] != 0 && nbQuartierParType[1] != 0 && nbQuartierParType[2] != 0 && nbQuartierParType[3] != 0 && nbQuartierParType[4] != 0 ) {
				pointsNombreType.set(i, pointsNombreType.get(i)+3);
			}

			if (nombreJoueurs == 4 || nombreJoueurs == 5 || nombreJoueurs == 6 || nombreJoueurs == 7) {
				if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
					if (this.plateauDeJeu.getJoueur(i) == winner) {
						pointsCiteTermine.set(i, pointsCiteTermine.get(i)+4);
					} else {
						pointsCiteTermine.set(i, pointsCiteTermine.get(i)+2);
					}
				}
			} else {
				if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 8) {
					if (this.plateauDeJeu.getJoueur(i) == winner) {
						pointsCiteTermine.set(i, pointsCiteTermine.get(i)+4);
					} else {
						pointsCiteTermine.set(i, pointsCiteTermine.get(i)+2);
					}
				}
			}
			
			nombrePoints.set(i, nombrePoints.get(i)+ pointsCoutConstruction.get(i)+pointsNombreType.get(i)+pointsCiteTermine.get(i)+pointsMerveille.get(i));
			System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " à obtenu " + nombrePoints.get(i) + " points !");

			if (nombrePoints.get(i)>point) {
				point = nombrePoints.get(i);
				winner = this.plateauDeJeu.getJoueur(i);
			}else if(point == nombrePoints.get(i)){
				if(this.plateauDeJeu.getJoueur(i).getPersonnage().getRang()>winner.getPersonnage().getRang()) {
					winner = this.plateauDeJeu.getJoueur(i);
				}
			}
		}
		System.out.println(winner.getNom() + " remporte la partie avec " + point + " points !\n");
	}
	
	private void jouerPartie() {
		int i = 1;
		
		this.initialisation();
		
		do {
			System.out.println("Tour n°" + i);
			this.tourDeJeu();
			this.gestionCouronne();
			this.reinitialisationPersonnages();
			i++;
		} while (!partieFinie());
		
		this.calculDesPoints();
	}
	
	private void afficherLesRegles() {
		System.out.println("Il faudra ajouter les règles ici \n");
	}

	public void jouer() {
		int choix = 0;
		
		System.out.println("Bienvenue dans la version de Citadelles codé par Gabin SIMOND, Kilian LABORDERIE et Sofiane DION.");
		
		do{
			System.out.println("Que souhaitez vous faire ?\n"
							 + "1 - Jouer une partie\n"
							 + "2 - Afficher les règles\n"
							 + "3 - Quitter\n");
			
			switch (choix) {
				case 1:
					jouerPartie();
					break;
	
				case 2:
					afficherLesRegles();
					break;
	
				case 3:
					System.out.println("Au revoir !");
					break;
				default:
					System.out.println("Choix incorrect");
					break;
			}
		} while ( choix != 3);  
	}}
