package test;

import modele.Caracteristiques;
import modele.Joueur;
import modele.Quartier;
import modele.Roi;

public class TestRoi {

	public static void main(String[] args) {
		TestRoi testRoi= new TestRoi();
		testRoi.test1();
		testRoi.test2();
		testRoi.test3();
		testRoi.test4();
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Roi roi = new Roi();
		Test.test(roi.getNom().equals("Roi"),"test du nom du personnage Roi");
		Test.test(roi.getRang()== 4,"test du rang du personnage Roi");
		Test.test(roi.getCaracteristiques().equals(Caracteristiques.ROI),
				"test des caractéristiques du personnage Roi");
		Test.test(roi.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(!roi.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
		Test.test(!roi.getVole(), "test de l'initialisation de la variable \"vole\"");
	}
	
	public void test2(){
		System.out.println("TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES");
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.setJoueur(joueur);
		roi.ajouterPieces();
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Test.test(roi.getJoueur().nbPieces() == 2, "test du nombre de pièces d'or avant perception");
		roi.percevoirRessourcesSpecifiques();
		Test.test(roi.getJoueur().nbPieces() == 2, "test du nombre de pièces d'or après perception de ressources spécifiques sans quartier noble présent dans la cité");
		roi.construire(quartier1);
		roi.construire(quartier2);
		roi.construire(quartier3);		
		roi.percevoirRessourcesSpecifiques();
		Test.test(roi.getJoueur().nbPieces() == 3, "test du nombre de pièces d'or après perception de ressources spécifiques avec 1 quartier noble présent dans la cité");
	}
	
	public void test3(){
		System.out.println("TEST DE L'UTILISATION DU POUVOIR DU ROI");
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.setJoueur(joueur);
		Test.test(!roi.getJoueur().getPossedeCouronne(), "test avant utilisation");
		roi.utiliserPouvoir();
		Test.test(roi.getJoueur().getPossedeCouronne(), "test après utilisation");
	}

	public void test4(){
		System.out.println("TEST DE L'UTILISATION DU POUVOIR DU ROI (AVATAR)");
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.setJoueur(joueur);
		Test.test(!roi.getJoueur().getPossedeCouronne(), "test avant utilisation");
		do {
			roi.utiliserPouvoirAvatar();
		}while(!roi.getJoueur().getPossedeCouronne());
		Test.test(roi.getJoueur().getPossedeCouronne(), "test après utilisation");
	}
}
