package modele;

public class Personnage {
	private String nom;
	private int rang;
	private String caracteristiques;
	private Joueur joueur;
	private Boolean assassine;
	private Boolean vole;
	private PlateauDeJeu plateau;
	
	
	Personnage(String nom,int rang,String caracteristiques){
		this.nom=nom;
		this.rang=rang;
		this.caracteristiques=caracteristiques;
		this.joueur=null;
		this.vole=false;
		this.assassine=false;
	}


	public Joueur getJoueur() {
		return this.joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
		this.joueur.monPersonnage = this;
	}


	public Boolean getAssassine() {
		return this.assassine;
	}


	public void setAssassine() {
		this.assassine = true;
	}


	public Boolean getVole() {
		return this.vole;
	}


	public void setVole() {
		this.vole = true;
	}


	public String getNom() {
		return this.nom;
	}


	public int getRang() {
		return this.rang;
	}


	public String getCaracteristiques() {
		return this.caracteristiques;
	}

	public PlateauDeJeu getPlateau() {
		return this.plateau;
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
			this.joueur.ajouterPieces(2);
		}
			
			
	}
	
	public void ajouterQuartier(Quartier nouveau) {
		if (this.joueur==null){
			System.out.println("Joueur nécessaire pour ajouter un quartier");
		}else if (this.assassine==true){
			System.out.println("Impossible d'ajouter un quartier si le personnage est assissiné");
		} else {
			this.joueur.ajouterQuartierDansMain(nouveau);
		}
	}
	public void construire(Quartier nouveau) {
		if (this.joueur==null){
			System.out.println("Joueur nécessaire pour construire un quartier");
		}else if (this.assassine==true){
			System.out.println("Impossible de construire un quartier si le personnage est assissiné");
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
		this.joueur.monPersonnage = null;
		this.joueur=null;
		this.vole=false;
		this.assassine=false;
		
	}
}
