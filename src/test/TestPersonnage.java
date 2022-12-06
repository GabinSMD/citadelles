package test;

import modele.Joueur;
import modele.Personnage;
import modele.PlateauDeJeu;
import modele.Quartier;

public class TestPersonnage {
	public static void main(String[] args) {
		TestPersonnage test = new TestPersonnage();
		test.test1();
		test.test2();
		test.test3();
		test.test4();
}

	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		Personnage personnage = new Personnage("Fou",1,"Fictif");
		Test.test(personnage.getNom()== "Fou","test nom de personnages");
		Test.test(personnage.getRang()==1, "test du rang");
		Test.test(personnage.getCaracteristiques()=="Fictif", "test caracteristique");
		Test.test(personnage.getJoueur()==null, "test Joueur");
		Test.test(!personnage.getVole(), "test vole");
		Test.test(!personnage.getAssassine(), "test assassin");
		
	}
	
	public void test2() {
		System.out.println("TEST DU PERSONNAGE");
		Personnage personnage = new Personnage("Fou",1,"Fictif");
		Personnage personnage2 = new Personnage("Fou",1,"Fictif");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Joueur joueur = new Joueur("Billy");
		personnage.setJoueur(joueur);
		Test.test(personnage.getJoueur()==joueur, "test set/get Joueur");
		personnage.setAssassine();
		Test.test(personnage.getAssassine(), "test set/get assassine");
		personnage.setVole();
		Test.test(personnage.getVole(), "test set/get vole");
		Test.test(personnage.getNom()== "Fou","test nom de personnages");
		Test.test(personnage.getRang()==1, "test du rang");
		Test.test(personnage.getCaracteristiques()=="Fictif", "test caracteristique");
		Test.test(personnage.getJoueur()==joueur, "test Joueur");
		personnage.setPlateau(plateau);
		Test.test(personnage.getPlateau()==plateau, "test set/get plateau");
		
		personnage.ajouterPieces();
		Test.test(personnage.getJoueur().nbPieces()!=2, "test ajout de piece dans un personnage avec un joueur affecté mais assassiné");
		
//		personnage2.ajouterPieces();
//		Test.test(personnage2.getJoueur().nbPieces()!=2, "test ajout de piece dans un personnage avec un joueur non affecté");
		
		personnage.reinitialiser();
		personnage.setJoueur(joueur);
		personnage.ajouterPieces();
		Test.test(personnage.getJoueur().nbPieces()==2, "test ajout de piece dans un personnage avec un joueur affecté mais non assassiné");
		

		
	}
	
	public void test3() {
		System.out.println("TEST AJOUT D'UN QUARTIER");
		Personnage personnage = new Personnage("Fou",1,"Fictif");
		Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); 
		PlateauDeJeu plateau = new PlateauDeJeu();
		Joueur joueur = new Joueur("Billy");
		//personnage.ajouterQuartier(q);
		//Test.test(personnage.getJoueur().getMain().get(0)!=q, "test ajout quartier sans joueur");
		personnage.setJoueur(joueur);
		personnage.ajouterQuartier(q);
		Test.test(personnage.getJoueur().getMain().get(0)==q, "test ajout quartier avec joueur");
		
		personnage.setAssassine();
		personnage.ajouterQuartier(q);
		Test.test(personnage.getJoueur().getMain().size()==1, "test ajout quartier avec joueur mais assassiné");

		personnage.reinitialiser();
		//personnage.construire(q);
		//Test.test(personnage.getJoueur().getCite().get(0)!=q, "test construction quartier sans joueur");
		
		personnage.setJoueur(joueur);
		personnage.construire(q);
		Test.test(personnage.getJoueur().nbQuartiersDansCite()==1, "test construction quartier avec joueur");
		
		personnage.setAssassine();
		personnage.construire(q);
		Test.test(personnage.getJoueur().nbQuartiersDansCite()==1, "test construction quartier avec joueur assassine");
	}
	
	public void test4() {
		Personnage personnage = new Personnage("Fou",1,"Fictif");
		Joueur joueur = new Joueur("Billy");
		personnage.setJoueur(joueur);
		personnage.setAssassine();
		personnage.setVole();
		
		personnage.reinitialiser();
		Test.test(!personnage.getAssassine()&& !personnage.getVole()&& personnage.getJoueur()==null, "test reinitialisation");
		
	}
}
