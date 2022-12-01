package modele;

public class Personnage {
	private String nom;
	private int rang;
	private String caracteristiques;
	private Joueur joueur;
	private Boolean assassine;
	private Boolean vole;
	private PlateauDeJeu plateau;
	
	
	public Personnage(String nom,int rang,String caracteristiques){
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

	public void setJoueur(Joueur j) {
		this.joueur = j;
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


	public void setPlateau(PlateauDeJeu nouveau) {
		this.plateau = nouveau;
	}


	public void ajouterPieces() {
		if (this.joueur==null || this.assassine==true){
			System.out.println("Impossible d'ajouter des pièces");
		} else {
			this.joueur.ajouterPieces(2);
		}
			
			
	}
	public void ajouterQuartier(Quartier nouveau) {
		if (this.joueur==null || this.assassine==true){
			System.out.println("Impossible d'ajouter un quartier");
		} else {
			this.joueur.ajouterQuartierDansMain(nouveau);
		}
	}
	public void construire(Quartier nouveau) {
		if (this.joueur==null || this.assassine==true){
			System.out.println("Impossible de construire le quartier");
		} else {
			this.joueur.ajouterQuartierDansCite(nouveau);
		}
	}
	public void percevoirRessourcesSpecifiques() {
		if (this.joueur==null || this.assassine==true){
			System.out.println("Impossible de percevoir les ressources spécifiques");
		} else {
			System.out.println("Aucune ressource spécifique");
		}
	}
	public void utiliserPouvoir() {
	}
	
	public void utiliserPouvoirAvatar() {
	}
	
	public void reinitialiser() {
		if(this.joueur!=null) {
			this.joueur.monPersonnage = null;
		}
		this.joueur=null;
		this.vole=false;
		this.assassine=false;
		
	}
}