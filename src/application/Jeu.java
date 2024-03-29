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

public class Jeu{
	protected PlateauDeJeu plateauDeJeu;
	//public int numeroConfiguration;
	protected Random generateur;
	protected int nombreJoueurs;
	protected int nombrePersonnages;
	protected Pioche pioche;
	
	protected boolean first = false;
	
	protected Joueur winner;

	protected int choix=0;
	protected boolean choixBoolean=false;
	protected int nbTypeQuartier = Quartier.TYPE_QUARTIERS.length;
	protected int index=0;
	
	protected ArrayList<Integer> pointsCoutConstruction = new ArrayList<Integer>(this.nombreJoueurs);
	protected ArrayList<Integer> pointsMerveille = new ArrayList<Integer>(this.nombreJoueurs);
	protected ArrayList<Integer> pointsCiteTermine = new ArrayList<Integer>(this.nombreJoueurs);
	protected ArrayList<Integer> nombrePoints = new ArrayList<Integer>(this.nombreJoueurs);
	protected ArrayList<Integer> pointsNombreType = new ArrayList<Integer>(this.nombreJoueurs);
	
	public Jeu() {
		this.plateauDeJeu = new PlateauDeJeu();
		this.generateur = new Random();
		//useless		
		//this.numeroConfiguration = numeroConfiguration;
	}
	
	protected void initialisation() {
		pointsCoutConstruction.clear();
		pointsMerveille.clear();
		pointsCiteTermine.clear();
		nombrePoints.clear();
		pointsNombreType.clear();
		ArrayList<Joueur> joueursRobot;
		this.pioche = Configuration.nouvellePioche();
		this.plateauDeJeu = Configuration.configurationDeBase(pioche);
		this.pioche.melanger();
		this.nombreJoueurs = this.plateauDeJeu.getNombreJoueurs();
		this.nombrePersonnages = this.plateauDeJeu.getNombrePersonnages();
		joueursRobot= new ArrayList<Joueur>(this.nombreJoueurs);
		for (int j = 0; j < this.nombreJoueurs; j++) {
			joueursRobot.add(this.plateauDeJeu.getJoueur(j));
		}
		System.out.println("Choix des joueurs robot");
		System.out.println("Combien de joueurs robot souhaitez-vous ?");

		this.choix = Interaction.lireUnEntier(0, this.nombreJoueurs+1);
		//this.choix = 4;

		if(this.choix==this.nombreJoueurs) {
			for(int j=0; j<this.nombreJoueurs;j++) {
				this.plateauDeJeu.getJoueur(j).setAvatar(true);
			}
		}else {
			for(int i=this.choix; i>0;i--) {
				this.index=0;
				for(Joueur robot: joueursRobot) {
					if(!robot.getAvatar()) {
						System.out.println(joueursRobot.indexOf(robot)+". "+robot.getNom());
						this.index++;
					}
				}
				System.out.println("Quel joueur est un robot ? (" + (i + " robots à selectionner)"));
				this.choix = Interaction.lireUnEntier(0, this.index);
				
				joueursRobot.get(this.choix).setAvatar(true);
				joueursRobot.sort(Comparator.comparing(Joueur::getAvatar));
			}
			for(int i=0; i<this.nombreJoueurs;i++) {
				if(joueursRobot.get(i).getAvatar() && this.plateauDeJeu.getJoueur(i).getNom()==joueursRobot.get(i).getNom())
				this.plateauDeJeu.getJoueur(i).setAvatar(true);
			}
		}
		
		for (int i = 0; i < this.nombreJoueurs; i++) {
			
			//Ajout de la trésorerie de départ (2 pièces)
			this.plateauDeJeu.getJoueur(i).ajouterPieces(2);
			
			//Ajout des cartes de départ (4 cartes)
			for (int j = 0; j < 4; j++) {
				this.plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
			}
		}
		
		//Attribution aléatoire de la couronne à un joueur 
		this.plateauDeJeu.getJoueur(generateur.nextInt(nombreJoueurs)).setPossedeCouronne(true);
	}
	
	protected void choixPersonnages() {
		int randomVisible1 = 0;
		int randomVisible2= 0;
		int randomCache = 0;
		ArrayList<Personnage> listePersonnageDisponible = new ArrayList<Personnage>();
	
		System.out.println("Choix des personnages : ");
		//Génération des cartes écartés
		
		do{
			randomVisible1 = generateur.nextInt(nombrePersonnages);
			randomVisible2 = generateur.nextInt(nombrePersonnages);
			randomCache = generateur.nextInt(nombrePersonnages);
			
		}while (randomVisible1 == randomVisible2 || randomVisible2 == randomCache || randomVisible1 == randomCache); 
		
		//Affichage des cartes ecartés et définition de la liste de personnage disponible
		for (int i = 0; i < nombrePersonnages; i++) {
			if (i == randomVisible1 || i == randomVisible2) {
				System.out.println("Le personnage " + this.plateauDeJeu.getPersonnage(i).getNom() + " est écarté face visible");
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
					System.out.println(listePersonnageDisponible.indexOf(perso)+ ". "+ perso.getNom());
				}
				//Cas avatar
				if (this.plateauDeJeu.getJoueur(i).getAvatar()) {

					this.choix = this.generateur.nextInt(listePersonnageDisponible.size());
				} 
				//Cas joueur
				else {
					System.out.println("Quel personnage choisissez-vous ? ");

					this.choix = Interaction.lireUnEntier(0, listePersonnageDisponible.size());

				}
				//Attribution du joueur au personnage
				for(int j=0;j<this.nombrePersonnages;j++) {
					if(this.plateauDeJeu.getPersonnage(j).getNom()==listePersonnageDisponible.get(this.choix).getNom()) {
						this.plateauDeJeu.getPersonnage(j).setJoueur(this.plateauDeJeu.getJoueur(i));
					}
				}

				//Affichage du personnage choisi

				System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi le personnage : "+ listePersonnageDisponible.get(this.choix).getNom());

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
					System.out.println(listePersonnageDisponible.indexOf(perso)+ ". "+ perso.getNom());
				}
				//Cas avatar
				if (this.plateauDeJeu.getJoueur(i).getAvatar()) {

					this.choix = this.generateur.nextInt(listePersonnageDisponible.size());

				}
				//Cas joueur
				else {
					System.out.println("Quel personnage choisissez-vous ? ");

					this.choix = Interaction.lireUnEntier(0, listePersonnageDisponible.size());

				}
				//Attribution du joueur au personnage
				for(int j=0;j<this.nombrePersonnages;j++) {
					if(this.plateauDeJeu.getPersonnage(j).getNom()==listePersonnageDisponible.get(this.choix).getNom()) {
						this.plateauDeJeu.getPersonnage(j).setJoueur(this.plateauDeJeu.getJoueur(i));
					}
				}
				//Affichage du personnage choisi

				System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi le personnage : "+ listePersonnageDisponible.get(this.choix).getNom());

				//Retrait du personnage de la liste de personnage disponible
				listePersonnageDisponible.remove(choix);
				
			}
		}
		System.out.println("L'attribution des personnages est terminée");
	}
	
	protected Boolean bibliotheque(Personnage personnageActuel) {
		if (personnageActuel.getJoueur().quartierPresentDansCite("Bibliothèque")) {
			for (int i = 0; i < 2; i++) {
					Quartier choixQuartier = this.pioche.piocher();
					if (choixQuartier != null) {
						System.out.println(i + ". " + choixQuartier.getNom() + " (coût " + choixQuartier.getCout() + ")");
						personnageActuel.ajouterQuartier(choixQuartier);
					}

			}
			return true;
		}else {
			return false;
		}
	}

	protected void percevoirRessource(Personnage personnageActuel) {
		if(personnageActuel.getJoueur().getAvatar()) {
			this.choix = this.generateur.nextInt(1,3);
		}else {
			//Choix entre pièces (1) ou cartes (2)
			System.out.println("Souhaitez vous obtenir 2 pièces d'or ou choisir entre 2 cartes quartiers ? ");
			System.out.println("1. Ajouter 2 pièces d'or à votre trésorerie");
			System.out.println("2. Choisir entre 2 cartes quartiers à ajouter à votre main");
			this.choix = Interaction.lireUnEntier(1, 3);
		}
		switch (this.choix) {
			//Ajout des pièces
			case 1:
				personnageActuel.ajouterPieces();
				break;
			//Ajout des cartes
			case 2:
				//Cas de la merveille

				if (!bibliotheque(personnageActuel)) {
					Quartier choixQuartier1 = this.pioche.piocher();
					Quartier choixQuartier2 = this.pioche.piocher();
					if(personnageActuel.getJoueur().getAvatar()) {
						this.choix = this.generateur.nextInt(1,3);
					}else {
						System.out.println("Quel quartier voulez-vous garder ? ");
						System.out.println("1." + choixQuartier1.getNom() + " (coût "+ choixQuartier1.getCout() + ")");
						System.out.println("2." + choixQuartier2.getNom() + " (coût "+ choixQuartier2.getCout() + ")");
						this.choix = Interaction.lireUnEntier(1, 3);
					}
					switch (this.choix) {
						case 1:
							personnageActuel.ajouterQuartier(choixQuartier1);
							this.pioche.ajouter(choixQuartier2);
							break;
						case 2:
							personnageActuel.ajouterQuartier(choixQuartier2);
							this.pioche.ajouter(choixQuartier1);
							break;
					}
				}
				break;
		}

	}

	protected int manufacture(Personnage personnageActuel, Quartier quartierAConstruire) {
		int coutQuartier;
		coutQuartier=quartierAConstruire.getCout();
		if(personnageActuel.getJoueur().quartierPresentDansCite("Manufacture") && quartierAConstruire.getType() == Quartier.TYPE_QUARTIERS[4]) {
			coutQuartier = quartierAConstruire.getCout()-1;
			return coutQuartier;
		}else {
			return coutQuartier;
		}
	}
	
	protected Boolean tripot(Personnage personnageActuel, Quartier quartierAConstruire, int coutQuartier) {
		int nbCartePossedez=0;
		if(quartierAConstruire.getNom() == "Tripot") {
			if (coutQuartier > (personnageActuel.getJoueur().nbPieces() + personnageActuel.getJoueur().nbQuartiersDansMain())) {
				System.out.println("Votre trésor n'est pas suffisant");
			} else {
				if(personnageActuel.getJoueur().getAvatar()) {
					this.choix=this.generateur.nextInt(2);
					switch(choix) {
						case 0:
							this.choixBoolean=false;
							break;
						case 1:
							this.choixBoolean=true;
							break;
					}	
				}else {
					System.out.println("Vous pouvez payer tout ou partie du coût de construction du Tripot en cartes de votre main, au prix de 1 carte pour 1 pièce d’or.");
					System.out.println("Voulez vous payer en carte ? ");
					this.choixBoolean=Interaction.lireOuiOuNon();
				}
				if (this.choixBoolean) {
					ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(personnageActuel.getJoueur().getMain());
					for(int i=0; i<copieTableau.size(); i++) {
						if(copieTableau.get(i).getNom()=="Tripot") {
							copieTableau.remove(i);
						}
					}
					nbCartePossedez = copieTableau.size();

					if(!personnageActuel.getJoueur().getAvatar()) {
						System.out.println("Vous avez "+ nbCartePossedez +" carte(s)");
						System.out.println("Combien de carte voulez vous utiliser :");
					}
					do {
						if(personnageActuel.getJoueur().getAvatar()){
							this.choix = this.generateur.nextInt(nbCartePossedez+1);
						}else {
							this.choix = Interaction.lireUnEntier(0,nbCartePossedez+1);
						}
						if(this.choix==0) {
							break;
						}else if(this.choix==nbCartePossedez) {
							for(int i=0; i<nbCartePossedez; i++) {
								coutQuartier -=1;
								pioche.ajouter(personnageActuel.getJoueur().retirerQuartierDansMain());
							}
							personnageActuel.getJoueur().retirerQuartierDansMain();
						} else if (choix+personnageActuel.getJoueur().nbPieces() >= coutQuartier && choix <= nbCartePossedez) {
							for (int i = choix; i > 0; i--) {
								nbCartePossedez=copieTableau.size();
								
								if(personnageActuel.getJoueur().getAvatar()) {
									this.choix = this.generateur.nextInt(0,nbCartePossedez);
								}else {
									System.out.println("Quelle carte voulez-vous defausser ? (" + i + " cartes à defausser)");
									for (int j = 0; j < nbCartePossedez; j++) {
										System.out.println(j + ". " + copieTableau.get(j).getNom());
									} 
									this.choix = Interaction.lireUnEntier(0,nbCartePossedez);
								}
								
								pioche.ajouter(copieTableau.get(this.choix));
								copieTableau.remove(this.choix);

								coutQuartier -=1;
							}
							nbCartePossedez=personnageActuel.getJoueur().nbQuartiersDansMain();
							for(int i=0; i<nbCartePossedez; i++) {
								personnageActuel.getJoueur().retirerQuartierDansMain();
							}
							for(int i=0; i<copieTableau.size(); i++) {
								if(copieTableau.get(i).getNom()!="Tripot") {
									personnageActuel.getJoueur().ajouterQuartierDansMain(copieTableau.get(i));
								}
							}
						} else {
							System.out.println("Nombre de cartes insuffisant");
						}
					} while (this.choix+personnageActuel.getJoueur().nbPieces() < coutQuartier);

					personnageActuel.getJoueur().retirerPieces(coutQuartier);
					personnageActuel.construire(quartierAConstruire);
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	protected Boolean carriere(Personnage personnageActuel, Quartier quartierAConstruire) {
		if(personnageActuel.getJoueur().quartierPresentDansCite(quartierAConstruire.getNom()) && personnageActuel.getJoueur().quartierPresentDansCite("Carrière")) {
			return true;
		}else if(!personnageActuel.getJoueur().quartierPresentDansCite(quartierAConstruire.getNom())) {
			return true;
		}else {
			System.out.println("Impossible de construire ce quartier");
			return false;
		}
	}
	
	protected void architecte(Personnage personnageActuel) {
		if (personnageActuel.getNom() == "Architecte") {
			if(personnageActuel.getJoueur().getAvatar()) {
				this.choix=this.generateur.nextInt(2);
				switch(choix) {
					case 0:
						this.choixBoolean=false;
						break;
					case 1:
						this.choixBoolean=true;
						break;
				}	
			}else {
				System.out.println("En tant qu'Architecte, vous pouvez construire 2 quartiers supplémentaire");
				System.out.println("Voulez vous en construire des quartiers supplémentaire ?");
				this.choixBoolean=Interaction.lireOuiOuNon();
			}
			if (this.choixBoolean) {
				if(personnageActuel.getJoueur().getAvatar()) {
					this.choix = this.generateur.nextInt(3);
				}else {
					System.out.println("Combien de quartiers voulez-vous construire ?");
					this.choix = Interaction.lireUnEntier(0,3);
				}
				for(int i = choix;i>0; i--) {
					construire(personnageActuel);
				}
			}
		}
	}
	//MAYBE REFACTOR
	protected void construire(Personnage personnageActuel) {
		Quartier quartierAConstruire;
		int coutQuartier=0;
		int nbCartePossedez=0;
		
		if(personnageActuel.getJoueur().getAvatar()) {
			this.choix=this.generateur.nextInt(2);
			switch(choix) {
				case 0:
					this.choixBoolean=false;
					break;
				case 1:
					this.choixBoolean=true;
					break;
			}	
		}else {
			System.out.println("Voulez vous construire ? ");
			System.out.println("Vous avez " + personnageActuel.getJoueur().nbPieces()+ " pièces dans votre trésorerie et votre main est composé de :");
			for (int i = 0; i < personnageActuel.getJoueur().nbQuartiersDansMain(); i++) {
				System.out.print(i+1 + ". " + personnageActuel.getJoueur().getMain().get(i).getNom() + "(coût "+ personnageActuel.getJoueur().getMain().get(i).getCout() + ")\n");
			}
			this.choixBoolean=Interaction.lireOuiOuNon();
		}

		if (this.choixBoolean && personnageActuel.getJoueur().nbQuartiersDansMain()!=0) {
		
			do {
				if(personnageActuel.getJoueur().getAvatar()) {
					this.choix = this.generateur.nextInt(0,personnageActuel.getJoueur().nbQuartiersDansMain()+1);
				}else {
					System.out.println("Quel quartier voulez-vous construire ?");
					System.out.println("0. Pour sortir");
					this.choix = Interaction.lireUnEntier(0, personnageActuel.getJoueur().nbQuartiersDansMain()+1);
				}
				if(this.choix==0) {
					return;
				}else {
					quartierAConstruire = personnageActuel.getJoueur().getMain().get(this.choix-1);
				}
			}while(!this.carriere(personnageActuel, quartierAConstruire));
			
			
			coutQuartier = manufacture(personnageActuel, quartierAConstruire);
			System.out.println("La construction coute : " + coutQuartier + " pièces d'or");
			if (quartierAConstruire.getNom() != "Tripot" && coutQuartier > personnageActuel.getJoueur().nbPieces()) {
				System.out.println("Votre trésor n'est pas suffisant");
			} else if(!tripot(personnageActuel, quartierAConstruire, coutQuartier)){
				ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(personnageActuel.getJoueur().getMain());

				this.plateauDeJeu.getPioche().ajouter(copieTableau.get(this.choix-1));
				copieTableau.remove(this.choix-1);

				nbCartePossedez=personnageActuel.getJoueur().nbQuartiersDansMain();
				for (int i = 0; i < nbCartePossedez; i++) {
					personnageActuel.getJoueur().retirerQuartierDansMain();
				}
				for (int i = 0; i < copieTableau.size(); i++) {
					personnageActuel.getJoueur().ajouterQuartierDansMain(copieTableau.get(i));
				}
				personnageActuel.getJoueur().retirerPieces(coutQuartier);
				personnageActuel.construire(quartierAConstruire);
			}
		}
	}
	protected void ecoleDeMagie(Personnage personnageActuel) {
		for (int k = 0; k < personnageActuel.getJoueur() .nbQuartiersDansCite(); k++) {
			if (personnageActuel.getJoueur() .quartierPresentDansCite("Ecole de magie")) {
				if(personnageActuel.getJoueur() .getAvatar()) {
					this.choix = this.generateur.nextInt(this.nbTypeQuartier);
				}else {
					System.out.println("Comment considerez-vous l'école de magie ?");
					for (int l = 0; l < this.nbTypeQuartier; l++) {
						System.out.println(l + " - " + Quartier.TYPE_QUARTIERS[l]);
					}
					this.choix = Interaction.lireUnEntier(0, this.nbTypeQuartier);
				}
				
				switch (this.choix) {
					case 0:
						personnageActuel.getJoueur() .getCite()[k].setType(Quartier.TYPE_QUARTIERS[0]);
						break;
					case 1:
						personnageActuel.getJoueur() .getCite()[k].setType(Quartier.TYPE_QUARTIERS[1]);
						break;
					case 2:
						personnageActuel.getJoueur() .getCite()[k].setType(Quartier.TYPE_QUARTIERS[2]);
						break;
					case 3:
						personnageActuel.getJoueur() .getCite()[k].setType(Quartier.TYPE_QUARTIERS[3]);
						break;
					case 4:
						personnageActuel.getJoueur() .getCite()[k].setType(Quartier.TYPE_QUARTIERS[4]);
						break;
					default:
						break;
				}
			}
		}
	}
	
	protected void forge(Personnage personnageActuel) {
		if (personnageActuel.getJoueur().quartierPresentDansCite("Forge")) {
			if(personnageActuel.getJoueur() .getAvatar()) {
				this.choix=this.generateur.nextInt(2);
				switch(choix) {
					case 0:
						this.choixBoolean=false;
						break;
					case 1:
						this.choixBoolean=true;
						break;
				}	
			}else {
				System.out.println("Vous avez " + personnageActuel.getJoueur() .nbPieces() + " pièces dans votre trésorerie.");
				System.out.println("Voulez vous payez 2 pièces d’or pour piocher 3 cartes ?");
				this.choixBoolean=Interaction.lireOuiOuNon();
			}
			
			if (this.choixBoolean) {
				for (int k = 0; k < 3; k++) {
					if (this.plateauDeJeu.getPioche().nombreElements() != 0) {
						personnageActuel.getJoueur().ajouterQuartierDansMain(pioche.piocher());
					}

				}
				personnageActuel.getJoueur() .retirerPieces(2);
			}
		}
	}
	protected void laboratoire(Personnage personnageActuel) {
		if (personnageActuel.getJoueur() .quartierPresentDansCite("Laboratoire") && personnageActuel.getJoueur().getMain().size() != 0) {

			if(personnageActuel.getJoueur() .getAvatar()) {
				this.choix=this.generateur.nextInt(2);
				switch(choix) {
					case 0:
						this.choixBoolean=false;
						break;
					case 1:
						this.choixBoolean=true;
						break;
				}	
			}else {
				System.out.println("Voulez-vous vous défausser d'1 carte pour recevoir 2 pièces d’or  ?");
				this.choixBoolean=Interaction.lireOuiOuNon();
			}
			if (this.choixBoolean) {
				int nbCartePossedee = personnageActuel.getJoueur() .getMain().size();
				ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(personnageActuel.getJoueur() .getMain());
				if(personnageActuel.getJoueur() .getAvatar()) {
					this.choix = this.generateur.nextInt(nbCartePossedee);
				}else {
					System.out.println("Quelle carte voulez vous défausser ?");
					for (int k = 0; k < nbCartePossedee; k++) {
						System.out.println(k + ". " + copieTableau.get(k).getNom());
					}
					this.choix = Interaction.lireUnEntier(0, nbCartePossedee);
				}

				this.pioche.ajouter(copieTableau.get(choix));

				copieTableau.remove(choix);
				personnageActuel.getJoueur() .ajouterPieces(2);
				nbCartePossedee = personnageActuel.getJoueur() .getMain().size();
				for (int k = 0; k < nbCartePossedee; k++) {
					personnageActuel.getJoueur() .retirerQuartierDansMain();
				}
				for (int k = 0; k < copieTableau.size(); k++) {
					personnageActuel.getJoueur() .ajouterQuartierDansMain(copieTableau.get(k));
				}
			}
		}
	}
	//MAYBE REFACTOR
	protected void tourDeJeu() {
		Personnage personnageActuel;
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
				if(personnageActuel.getNom()==personnages.get(i).getNom()){
					System.out.println("Le personnage " + personnageActuel.getNom() + " est appelé !");
					
					if (!personnageActuel.getAssassine() && personnageActuel.getJoueur() != null) {
						System.out.println("Le joueur " + personnageActuel.getJoueur() .getNom() + " commence son tour !\n");
						
						//Transfert des fonds volés
						if (personnageActuel.getVole()) {
							System.out.println("Vous avez été volé ! Vous donnez " + personnageActuel.getJoueur() .nbPieces() + " pièces d'or au Voleur");
							for (int k = 0; k < nombreJoueurs; k++) {
								if (this.plateauDeJeu.getJoueur(k).getPersonnage().getNom() == Caracteristiques.VOLEUR) {
									this.plateauDeJeu.getJoueur(k).ajouterPieces(personnageActuel.getJoueur() .nbPieces());
									personnageActuel.getJoueur() .retirerPieces(personnageActuel.getJoueur() .nbPieces());
								}
							}
						}
						
						//Perception des ressources normales
						this.percevoirRessource(personnageActuel);
						
						//Changement de type pour la merveille : Ecole de magie
						this.ecoleDeMagie(personnageActuel);
						
						//Perception des ressources du personnage
						personnageActuel.percevoirRessourcesSpecifiques();
						
						this.forge(personnageActuel);
						
						this.laboratoire(personnageActuel);
						
						if(personnageActuel.getJoueur() .getAvatar()) {
							this.choix=this.generateur.nextInt(2);
							switch(choix) {
								case 0:
									this.choixBoolean=false;
									break;
								case 1:
									this.choixBoolean=true;
									break;
							}	
						}else {
							System.out.println("Voulez vous utilisez votre pouvoir ? ");
							this.choixBoolean=Interaction.lireOuiOuNon();
						}
						if (this.choixBoolean) {
							if(personnageActuel.getJoueur() .getAvatar()) {
								personnageActuel.utiliserPouvoirAvatar();
							} else {
								personnageActuel.utiliserPouvoir();
							}
						}
						//Appelle de la fonction construire
						this.construire(personnageActuel);
						
						this.architecte(personnageActuel);
					}

					if(!first && this.partieFinie()) {

						this.first = true;
						if (this.nombreJoueurs == 4 || this.nombreJoueurs == 5 || this.nombreJoueurs == 6 || this.nombreJoueurs == 7) {

							if (personnageActuel.getJoueur().nbQuartiersDansCite() >= 7) {
								winner = this.plateauDeJeu.getJoueur(i);
							}
						} else {
							if (personnageActuel.getJoueur().nbQuartiersDansCite() >= 8) {
								winner = this.plateauDeJeu.getJoueur(i);
							}
						}
					}
				}
			}	
		}
		System.out.println("Tour terminé !");
	}
	
	protected void gestionCouronne() {

		for (int i = 0; i < this.nombrePersonnages; i++) {
			if (this.plateauDeJeu.getPersonnage(i).getNom() == "Roi" && this.plateauDeJeu.getPersonnage(i).getJoueur() != null) {
				for (int j = 0; j < this.nombreJoueurs; j++) {
					if(this.plateauDeJeu.getJoueur(j).getPossedeCouronne()) {
						this.plateauDeJeu.getJoueur(j).setPossedeCouronne(false);
					}
				}
				this.plateauDeJeu.getPersonnage(i).getJoueur().setPossedeCouronne(true);
			}
		}

	}
	
	protected void reinitialisationPersonnages() {

		for (int i = 0; i < this.nombrePersonnages; i++) {

				this.plateauDeJeu.getPersonnage(i).reinitialiser();
		}
	}
	
	protected boolean partieFinie() {
		boolean end = false;
		
		switch (this.nombreJoueurs) {
			case 4, 5, 6, 7:
				for (int i = 0; i < this.nombreJoueurs; i++) {
					if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 7) {
						end = true;
						System.out.println(Configuration.GAME_INFO+"Partie Terminé !\n"+Configuration.TEXT_RESET);
						break;
					}
				}
			case 2, 3, 8:
				for (int i = 0; i < this.nombreJoueurs; i++) {
					if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8) {
						end = true;
						System.out.println(Configuration.GAME_INFO+"Partie Terminé !\n"+Configuration.TEXT_RESET);
						break;
					}

				}
		}
		return end;
	}
	
	protected void calculDesPoints() {		
		int point = 0;
		Joueur gameWinner = this.plateauDeJeu.getJoueur(0);
		
		for (int i = 0; i < nombreJoueurs; i++) {
			String typeQuartier = "";
			int[] nbQuartierParType = {0, 0, 0, 0, 0};
			pointsCoutConstruction.add(0);
			pointsMerveille.add(0);
			pointsCiteTermine.add(0);
			nombrePoints.add(0);
			pointsNombreType.add(0);
			
			if(this.plateauDeJeu.getJoueur(i).getPersonnage()!=null) {
				
				for (int j = 0; j < this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {

					pointsCoutConstruction.set(i, pointsCoutConstruction.get(i)+this.plateauDeJeu.getJoueur(i).getCite()[j].getCout());
					
					if (this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Ecole de magie") {
						this.plateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[4]);
					}
					
	
					if (this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Dracoport") {

						pointsMerveille.set(i,pointsMerveille.get(i)+2);

					}
					
					if (this.plateauDeJeu.getJoueur(i).getCite()[j].getNom() == "Cours des miracles") {
						if(this.plateauDeJeu.getJoueur(i).getAvatar()) {
							this.choix=this.generateur.nextInt(nbTypeQuartier);
						}else {
							System.out.println("Comment considerez-vous la Cours des miracles ?");
							for (int k = 0; k < nbTypeQuartier; k++) {
								System.out.println(k + " - " + Quartier.TYPE_QUARTIERS[k]);
							}

							this.choix = Interaction.lireUnEntier(0, this.nbTypeQuartier);
						}
									
						switch (this.choix) {
							case 0:
								this.plateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[0]);
								break;
							case 1:
								this.plateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[1]);
								break;
							case 2:
								this.plateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[2]);
								break;
							case 3:
								this.plateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[3]);
								break;
							case 4:
								this.plateauDeJeu.getJoueur(i).getCite()[j].setType(Quartier.TYPE_QUARTIERS[4]);
								break;
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
					
					typeQuartier = this.plateauDeJeu.getJoueur(i).getCite()[j].getType();
					
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
					
				}
				
				if (nbQuartierParType[0] != 0 && nbQuartierParType[1] != 0 && nbQuartierParType[2] != 0 && nbQuartierParType[3] != 0 && nbQuartierParType[4] != 0 ) {
					pointsNombreType.set(i, pointsNombreType.get(i)+3);
				}
	
				if (nombreJoueurs == 4 || nombreJoueurs == 5 || nombreJoueurs == 6 || nombreJoueurs == 7) {
					if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {

						if (this.plateauDeJeu.getJoueur(i) == this.winner) {
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
			}
			
			if (this.plateauDeJeu.getJoueur(i).quartierPresentDansCite("Fontaine aux souhaits")) {
				pointsMerveille.set(i,(pointsMerveille.get(i)+nbQuartierParType[4]));
			}
			
			nombrePoints.set(i, nombrePoints.get(i)+ pointsCoutConstruction.get(i)+pointsNombreType.get(i)+pointsCiteTermine.get(i)+pointsMerveille.get(i));
			System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " à obtenu " + nombrePoints.get(i) + " points !");

			if (nombrePoints.get(i)>point) {
				point = nombrePoints.get(i);
				gameWinner = this.plateauDeJeu.getJoueur(i);
			}else if(point == nombrePoints.get(i)){

				if(this.plateauDeJeu.getJoueur(i).getPersonnage().getRang()>gameWinner.getPersonnage().getRang()) {
					gameWinner = this.plateauDeJeu.getJoueur(i);
				}
			}
			
		}
		System.out.println(gameWinner.getNom() + " remporte la partie avec " + point + " points !\n");
	}
	
	protected void jouerPartie() {
		int i = 1;
		
		this.initialisation();
		
		do {
			System.out.println(Configuration.GAME_INFO+"Tour n°" + i+Configuration.TEXT_RESET);
			this.tourDeJeu();
			this.gestionCouronne();
			if(!this.first) {
				this.reinitialisationPersonnages();
			}
			i++;
		} while (!partieFinie());
		
		this.calculDesPoints();
	}
	
	public void afficherLesRegles() {
		System.out.println("Il faudra ajouter les règles ici \n");
	}

	public void jouer() {
		int choixJeu = 0;
		System.out.println("Bienvenue dans la version de Citadelles codé par Gabin SIMOND, Kilian LABORDERIE et Sofiane DION.");
		
		do{
			System.out.println("Que souhaitez vous faire ?\n"
							 + "1 - Jouer une partie\n"
							 + "2 - Afficher les règles\n"
							 + "3 - Quitter\n");
			choixJeu=Interaction.lireUnEntier(1,4);
			//choixJeu=1;

			switch (choixJeu) {
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
		} while ( choixJeu != 3);  
	}
}