package test;
import modele.Quartier;

public class TestQuartier {
	public static void main(String[] args){
		TestQuartier testQuartier = new TestQuartier();
		testQuartier.test1();	
		testQuartier.test2();
		testQuartier.test3();
	}

	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR VIDE");
		Quartier quartier = new Quartier();
		Test.test(quartier.getNom().equals(""),"test du nom du quartier vide");
		Test.test(quartier.getType().equals(""),"test du type du quartier vide");
		Test.test(quartier.getCout() == 0,"test du cout du quartier null");
		Test.test(quartier.getCaracteristiques().equals(""),"test des caracteristiques du quartier vide");
	}

	public void test2(){
		System.out.println("TEST POUR UN QUARTIER NON VIDE");
		Quartier quartier = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Test.test(quartier.getNom().equals("temple"),"test du nom du quartier");
		Test.test(quartier.getType().equals("RELIGIEUX"),"test du type du quartier");
		Test.test(quartier.getCout() == 1,"test du cout du quartier");
		Test.test(quartier.getCaracteristiques().equals(""),"test des caracteristiques du quartier");
	}

	public void test3(){
		System.out.println("TEST DES ACCESSEURS EN ECRITURE");
		Quartier quartier = new Quartier();
		quartier.setNom("Basilique");
		Test.test(quartier.getNom().equals("Basilique"),"test du changement de nom du quartier");
		quartier.setCout(7);
		Test.test(quartier.getCout() == 0,"test d'un mauvais changement de cout");
		quartier.setCout(-1);
		Test.test(quartier.getCout() == 0,"test d'un deuxieme mauvais changement de cout");
		quartier.setCout(4);
		Test.test(quartier.getCout() == 4,"test d'un bon changement de cout");
		quartier.setType("merveille");
		Test.test(quartier.getType().equals(""),"test d'un mauvais changement du type");
		quartier.setType("MERVEILLE");
		Test.test(quartier.getType().equals("MERVEILLE"),"test d'un bon changement du type");
		quartier.setCaracteristiques("A la fin de la partie...");
		Test.test(quartier.getCaracteristiques().equals("A la fin de la partie..."), "test du changement des caracteristiques");
	}
}
