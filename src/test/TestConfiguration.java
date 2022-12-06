package test;

import application.Configuration;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

public class TestConfiguration {
	public static void main(String[] args) {
		TestConfiguration test = new TestConfiguration();
		test.test1();
		test.test2();
	}

	int taillePioche=0;
	public void test1() {
		Quartier carte;
		int nbReligieux =0;
		int nbNoble =0;
		int nbCommercant =0;
		int nbMilitaire =0;
		int nbTemple=0;
		int nbEglise=0;
		int nbMonastere=0;
		int nbCathedrale=0;
		int nbTour=0;
		int nbPrison=0;
		int nbCaserne=0;
		int nbForteresse=0;
		int nbManoir=0;
		int nbChateau=0;
		int nbTaverne=0;
		int nbMarche=0;
		int nbComptoir=0;
		int nbPort=0;
		int nbHotel=0;
		int nbPalais=0;
		int nbEchoppe=0;
		System.out.println("TEST DE LA NOUVELLE PIOCHE");
		Pioche pioche = Configuration.nouvellePioche();
		this.taillePioche = pioche.nombreElements();
		Test.test(pioche.nombreElements()==54,"Taille de la pioche (Quartiers)");
		for(int i=0; i<this.taillePioche;i++) {
			carte=pioche.piocher();
			if(carte.getType()==Quartier.TYPE_QUARTIERS[0]) {
				nbReligieux++;
			}else if(carte.getType()==Quartier.TYPE_QUARTIERS[1]) {
				nbMilitaire++;
			}else if(carte.getType()==Quartier.TYPE_QUARTIERS[2]) {
				nbNoble++;
			}else if(carte.getType()==Quartier.TYPE_QUARTIERS[3]) {
				nbCommercant++;
			}
			
			if(carte.getNom()=="Temple") {
				nbTemple++;
			}else if(carte.getNom()=="Eglise") {
				nbEglise++;
			}else if(carte.getNom()=="Monastère") {
				nbMonastere++;
			}else if(carte.getNom()=="Cathédrale") {
				nbCathedrale++;
			}else if(carte.getNom()=="Tour de guet") {
				nbTour++;
			}else if(carte.getNom()=="Prison") {
				nbPrison++;
			}else if(carte.getNom()=="Caserne") {
				nbCaserne++;
			}else if(carte.getNom()=="Forteresse") {
				nbForteresse++;
			}else if(carte.getNom()=="Manoir") {
				nbManoir++;
			}else if(carte.getNom()=="Château") {
				nbChateau++;
			}else if(carte.getNom()=="Palais") {
				nbPalais++;
			}else if(carte.getNom()=="Taverne") {
				nbTaverne++;
			}else if(carte.getNom()=="Echoppe") {
				nbEchoppe++;
			}else if(carte.getNom()=="Marché") {
				nbMarche++;
			}else if(carte.getNom()=="Comptoir") {
				nbComptoir++;
			}else if(carte.getNom()=="Port") {
				nbPort++;
			}else if(carte.getNom()=="Hôtel de ville") {
				nbHotel++;
			}
		}
		Test.test(nbReligieux==11,"Nombre de Quartier de type : RELIGIEUX");
		Test.test(nbMilitaire==11,"Nombre de Quartier de type : MILITAIRE");
		Test.test(nbNoble==12,"Nombre de Quartier de type : NOBLE");
		Test.test(nbCommercant==20,"Nombre de Quartier de type : COMMERCANT");
	
		
		Test.test(nbTemple==3,"Nombre de carte : TEMPLE");
		Test.test(nbEglise==3,"Nombre de carte : EGLISE");
		Test.test(nbMonastere==3,"Nombre de carte : MONASTERE");
		Test.test(nbCathedrale==2,"Nombre de carte : CATHEDRALE");
		Test.test(nbTour==3,"Nombre de carte : TOUR DE GUET");
		Test.test(nbPrison==3,"Nombre de carte : PRISON");
		Test.test(nbCaserne==3,"Nombre de carte : CASERNE");
		Test.test(nbForteresse==2,"Nombre de carte : FORTERESSE");
		Test.test(nbManoir==5,"Nombre de carte : MANOIR");
		Test.test(nbChateau==4,"Nombre de carte : CHATEAU");
		Test.test(nbPalais==3,"Nombre de carte : PALAIS");
		Test.test(nbTaverne==5,"Nombre de carte : TAVERNE");
		Test.test(nbEchoppe==3,"Nombre de carte : ECHOPPE");
		Test.test(nbMarche==4,"Nombre de carte : MARCHE");
		Test.test(nbComptoir==3,"Nombre de carte : COMPTOIR");
		Test.test(nbPort==3,"Nombre de carte : PORT");
		Test.test(nbHotel==2,"Nombre de carte : HOTEL DE VILLE");
	}
	public void test2() {
		Quartier carte;
		int nbMerveille=0;
		System.out.println("TEST DE LA CONFIGURATION DE BASE");
		Pioche pioche = Configuration.nouvellePioche();
		PlateauDeJeu plateau = Configuration.configurationDeBase(pioche);
		this.taillePioche = pioche.nombreElements();
		Test.test(plateau.getNombreJoueurs() == 4,"Nombre de joueurs");
		Test.test(plateau.getNombrePersonnages() == 8,"Nombre de personnages");
		for(int i=0; i<this.taillePioche;i++) {
			carte=pioche.piocher();
			if(carte.getType()==Quartier.TYPE_QUARTIERS[4]) {
				nbMerveille++;
			}
		}
		Test.test(nbMerveille == 14,"Nombre de Quartier de type : MERVEILLE");
		Test.test(this.taillePioche == 68,"Taille de la pioche (Quartiers+Merveilles)");
		
	}
}
