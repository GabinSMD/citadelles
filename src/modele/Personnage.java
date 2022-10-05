package modele;

public class Personnage {
	String nom;
	int rang;
	String caracteristiques;
	Joueur joueur;
	Boolean assassine;
	Boolean vole;
	PlateauDeJeu plateau;
	
	
	Personnage(String nom,int rang,String caracteristiques){
		this.nom=nom;
		this.rang=rang;
		this.caracteristiques=caracteristiques;
		this.joueur=null;
		this.vole=false;
		this.assassine=false;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public Boolean getAssassine() {
		return assassine;
	}


	public void setAssassine() {
		this.assassine = true;
	}


	public Boolean getVole() {
		return vole;
	}


	public void setVole() {
		this.vole = true;
	}


	public String getNom() {
		return nom;
	}


	public int getRang() {
		return rang;
	}


	public String getCaracteristiques() {
		return caracteristiques;
	}

	public PlateauDeJeu getPlateau() {
		return plateau;
	}


	public void setPlateau(PlateauDeJeu plateau) {
		this.plateau = plateau;
	}


	public void ajouterPieces() {
		if (this.joueur==null){
			System.out.println("Joueur nécessaire pour ajouter des pièces");
		}else if (this.assassine==true){
			System.out.println("Impossible d'ajouter des pièces si le personnage est mort");
		} else {
			this.joueur.ajouterPiece(2);
		}
			
			
	}
	public void ajouterQuartier(Quartier nouveau) {
		if (this.joueur==null){
			System.out.println("Joueur nécessaire pour créer un quartier");
		}else if (this.assassine==true){
			System.out.println("Impossible de créer un quartier si le personnage est mort");
		} else {
			this.joueur.ajouterQuartierDansMain(nouveau);
		}
	}
	public void construire(Quartier nouveau) {
		if (this.joueur==null){
			System.out.println("Joueur nécessaire pour construire un quartier");
		}else if (this.assassine==true){
			System.out.println("Impossible de construire un quartier si le personnage est mort");
		} else {
			this.joueur.ajouterQuartierDansCite(nouveau);
		}
	}
	public void percevoirRessourcesSpecifiques() {
		if (this.joueur==null){
			System.out.println("Joueur nécessaire pour percevoir des ressources");
		}else if (this.assassine==true){
			System.out.println("Impossible de percevoir des ressources si le personnage est mort");
		} else {
			System.out.println("Aucune ressources spécifiques");
		}
	}
	public void utiliserPouvoir() {
		
	}
	public void reinitialiser() {
		this.joueur=null;
		this.vole=false;
		this.assassine=false;
	}
}
