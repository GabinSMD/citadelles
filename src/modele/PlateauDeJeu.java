package modele;

public class PlateauDeJeu {
	
	//Déclaration des variables
	private Personnage[] listePersonnages;
	private Joueur[] listeJoueurs;
	private Pioche pioche;
	private int nombrePersonnages;
	private int nombreJoueurs;
	private boolean plein = false;


	//Constructeur
	public PlateauDeJeu(){
		this.nombrePersonnages=0;
		this.nombreJoueurs=0;
		this.pioche= new Pioche();
		this.listeJoueurs= new Joueur[9];
		this.listePersonnages= new Personnage[9];
	}

	//Méthodes
	public Pioche getPioche() {
		return this.pioche;
	}

	public int getNombrePersonnages() {
		this.nombrePersonnages=0;
		for (Personnage perso: this.listePersonnages) {
			if (perso != null) {
				this.nombrePersonnages++;
			}
		}
		return this.nombrePersonnages;
	}

	public int getNombreJoueurs() {
		this.nombreJoueurs=0;
		for (Joueur joueur: this.listeJoueurs) {
			if (joueur != null) {
				this.nombreJoueurs++;
			}
		}
		return this.nombreJoueurs;
	}
	
	public Personnage getPersonnage(int i) {
		if(i>=0 && i<=(this.listePersonnages.length-1)){
			return this.listePersonnages[i];
		}else {
			return null;	
		}
	}
	
	public Joueur getJoueur(int i) {
		if(i>=0 && i<=(this.listeJoueurs.length-1)){
			return this.listeJoueurs[i];
		}else {
			return null;	
		} 
	}
	
	public void ajouterPersonnage(Personnage nouveau) {
		this.plein=false;
		if(nouveau!=null && nouveau.getNom()!=null && nouveau.getCaracteristiques()!=null && nouveau.getAssassine()!=null && nouveau.getVole()!=null) {
			for(int i=0; i<this.listePersonnages.length; i++){
			    if(this.listePersonnages[i] == null){
			    	this.listePersonnages[i]=nouveau;
			        nouveau.setPlateau(this);
			        this.plein=true;
			        break;
			    }
			    if(this.plein) {
			    	System.out.println("Impossible d'ajouter un nouveau Personnage (Liste pleine)");
			    }
			}
		}
	}
	
	public void ajouterJoueur(Joueur nouveau) {
		this.plein=false;
		if(nouveau!=null && nouveau.getNom()!=null && nouveau.getCite()!=null && nouveau.getMain()!=null ) {
			for(int i=0; i<this.listeJoueurs.length; i++){
			    if(this.listeJoueurs[i] == null){
			    	this.listeJoueurs[i]=nouveau;
			        this.plein=true;
			        break;
			    }
			    if(this.plein) {
			    	System.out.println("Impossible d'ajouter un nouveau Joueur (Liste pleine)");
			    }
			}
		}
	}
}
