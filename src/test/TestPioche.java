package test;

import modele.Pioche;
import modele.Quartier;

public class TestPioche {
	public static void main(String[] args) {
		TestPioche testPioche= new TestPioche();
		testPioche.test1();
		testPioche.test2();
		testPioche.test3();
		testPioche.test4();
	}
	
	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR DE LA PIOCHE");
		Pioche pioche = new Pioche();
		Test.test(pioche.nombreElements()==0,"taille de la pioche");
	}	
	public void test2() {
		System.out.println("TEST DE L'AJOUT D'UN QUARTIER");
		Pioche pioche = new Pioche();
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		pioche.ajouter(quartier1);
		pioche.ajouter(quartier2);
		pioche.ajouter(quartier3);
		Test.test(pioche.nombreElements()==3,"taille de la pioche");		
	}	
	public void test3() {
		Quartier q;
		System.out.println("TEST DU RETRAIT D'UN QUARTIER");
		Pioche pioche = new Pioche();
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);

		Test.test(pioche.piocher() == null,"test d'un retrait dans une pioche vide");		
		
		pioche.ajouter(quartier1);
		pioche.ajouter(quartier2);
		pioche.ajouter(quartier3);
		q = pioche.piocher();
		Test.test(pioche.nombreElements()==2 && q==quartier1, "test d'un retrait dans une pioche composée de trois cartes");
		q = pioche.piocher();
		Test.test(pioche.nombreElements()==1 && q==quartier2, "test d'un retrait dans une pioche composée de deux cartes");
		q = pioche.piocher();
		Test.test(pioche.nombreElements()==0 && q==quartier3, "test d'un retrait dans une pioche composée d'une seule carte");
	}
	public void test4() {
		System.out.println("TEST DU MELANGE DE LA PIOCHE");
		Pioche pioche = new Pioche();
		pioche.ajouter(new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1));
		pioche.ajouter(new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2));
		pioche.ajouter(new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5));
		pioche.ajouter(new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1));
		pioche.ajouter(new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4,"A la fin de la partie, ..."));
		pioche.ajouter(new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5));
		pioche.ajouter(new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3));
		pioche.ajouter(new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3));
		pioche.ajouter(new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],15));
		pioche.ajouter(new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez..."));

		System.out.println("Affichage de la pioche avant mélange : \n- temple \n- prison\n- palais \n- taverne\n- basilique\n- cathédrale\n- caserne\n- manoir\n- hôtel de ville\n- bibliothèque");
				
		pioche.melanger();		
		Test.test(pioche.nombreElements()==10, "taille de la pioche après mélange");
		System.out.println("Affichage de la pioche après mélange : ");
		for(int i =pioche.nombreElements(); i>0; i--) {
			System.out.println("- " + pioche.piocher().getNom());
		}
		System.out.println("Validation du test si l'ordre des cartes dans la pioche est différent après mélange");
	}
}
