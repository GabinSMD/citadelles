package application;

import modele.Architecte;
import modele.Assassin;
import modele.Caracteristiques;
import modele.Condottiere;
import modele.Eveque;
import modele.Joueur;
import modele.Magicienne;
import modele.Marchande;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;
import modele.Voleur;

public class Configuration {

    public static final String GAME_ERROR = "\u001B[31m";
    public static final String GAME_WARNING = "\u001B[33m";
    public static final String GAME_SUCCESS = "\u001B[32m";
    public static final String GAME_INFO = "\u001B[36m";
    
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BOLD = "\u001B[1m";
    public static final String TEXT_UNBOLD = "\u001B[21m";
    public static final String TEXT_UNDERLINE = "\u001B[4m";
    public static final String ANSI_STOP_UNDERLINE = "\u001B[24m";
    
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_LIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLINK = "\u001B[5m";
	
	
	
	 public static Pioche nouvellePioche() {
		 String[] nomQuartier = {"Temple", "Eglise", "Monastère", "Cathédrale", "Tour de guet", "Prison", "Caserne", "Forteresse","Manoir", "Château", "Palais", "Taverne", "Echoppe", "Marché", "Comptoir", "Port", "Hôtel de ville"};
		 int[] coutQuartier = {1,2,3,5,1,2,3,5,3,4,5,1,2,2,3,4,5};
		 int[] nbQuartier = {3,3,3,2,3,3,3,2,5,4,3,5,3,4,3,3,2};
		 int i;
		 int j;
		 Pioche pioche = new Pioche();

		 for(i=0; i<=3; i++) {
			 for(j=0; j<nbQuartier[i]; j++) {
				 pioche.ajouter(new Quartier(nomQuartier[i], Quartier.TYPE_QUARTIERS[0], coutQuartier[i]));
			 }
		 }
		 for(i=4; i<=7; i++) {
			 for(j=0; j<nbQuartier[i]; j++) {
				 pioche.ajouter(new Quartier(nomQuartier[i], Quartier.TYPE_QUARTIERS[1], coutQuartier[i]));
			 }
		 }
		 for(i=8; i<=10; i++) {
			 for(j=0; j<nbQuartier[i]; j++) {
				 pioche.ajouter(new Quartier(nomQuartier[i], Quartier.TYPE_QUARTIERS[2], coutQuartier[i]));
			 }
		 }
		 for(i=11; i<=16; i++) {
			 for(j=0; j<nbQuartier[i]; j++) {
				 pioche.ajouter(new Quartier(nomQuartier[i], Quartier.TYPE_QUARTIERS[3], coutQuartier[i]));
			 }
		 } 
		 
		 return pioche;
	}
	 
	public static PlateauDeJeu configurationDeBase(Pioche p) {
		PlateauDeJeu plateau = new PlateauDeJeu();
		plateau.ajouterPersonnage(new Assassin());
		plateau.ajouterPersonnage(new Voleur());
		plateau.ajouterPersonnage(new Magicienne());
		plateau.ajouterPersonnage(new Roi());
		plateau.ajouterPersonnage(new Eveque());
		plateau.ajouterPersonnage(new Marchande());
		plateau.ajouterPersonnage(new Architecte());
		plateau.ajouterPersonnage(new Condottiere());
		
		plateau.ajouterJoueur(new Joueur("Sofiane"));
		plateau.ajouterJoueur(new Joueur("Kilian"));
		plateau.ajouterJoueur(new Joueur("Gabin"));
		plateau.ajouterJoueur(new Joueur("Patrick"));
		
		p.ajouter(new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.BIBLIOTHEQUE));
		p.ajouter(new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARRIERE));
		p.ajouter(new Quartier("Cours des miracles",Quartier.TYPE_QUARTIERS[4], 2, Caracteristiques.COURS));
		p.ajouter(new Quartier("Donjon",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.DONJON));
		p.ajouter(new Quartier("Dracoport",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.DRACOPORT));
		p.ajouter(new Quartier("Ecole de magie",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.MAGIE));
		p.ajouter(new Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FONTAINE));
		p.ajouter(new Quartier("Forge",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.FORGE));
		p.ajouter(new Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.LABORATOIRE));
		p.ajouter(new Quartier("Manufacture",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.MANUFACTURE));
		p.ajouter(new Quartier("Salle des cartes",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.CARTE));
		p.ajouter(new Quartier("Statue equestre",Quartier.TYPE_QUARTIERS[4], 3, Caracteristiques.STATUE));
		p.ajouter(new Quartier("Trésor Imperial",Quartier.TYPE_QUARTIERS[4], 5, Caracteristiques.TRESOR));
		p.ajouter(new Quartier("Tripot",Quartier.TYPE_QUARTIERS[4], 6, Caracteristiques.TRIPOT));
		
		
		return plateau;
	}

}
