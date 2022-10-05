package modele;

public class PlateauDeJeu {
	Personnage[] listePersonnage;
	Joueur[] listeJoueurs;
	Pioche pioche;
	int nombrePersonnages;
	int nombreJoueurs;

	PlateauDeJeu(){
		this.nombrePersonnages=0;
		this.nombreJoueurs=0;
		this.pioche= new Pioche();
		this.listeJoueurs= new Joueur[9];
		this.listePersonnage= new Personnage[9];
	}
}
