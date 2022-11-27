package modele;

public class PlateauDeJeu {
	Personnage[] listePersonnage;
	Joueur[] listeJoueurs;
	Pioche pioche;
	int nombrePersonnages;
	int nombreJoueurs;

	public PlateauDeJeu(){
		this.nombrePersonnages=0;
		this.nombreJoueurs=0;
		this.pioche= new Pioche();
		this.listeJoueurs= new Joueur[9];
		this.listePersonnage= new Personnage[9];
	}

	public Pioche getPioche() {
		return this.pioche;
	}

	public int getNombrePersonnages() {
		for (int i = 0; i < this.listePersonnage.length; i++) {
			if (this.listePersonnage[i] != null) {
				this.nombrePersonnages++;
			}
		}
		return this.nombrePersonnages;
	}

	public int getNombreJoueurs() {
		for (int i = 0; i < this.listeJoueurs.length; i++) {
			if (this.listeJoueurs[i] != null) {
				this.nombreJoueurs++;
			}
		}
		return this.nombreJoueurs;
	}
	public Personnage getPersonnage(int i) {
		if(i<0 || i >this.listePersonnage.length-1) {
			return null;
		}else {
			return this.listePersonnage[i];
		}
	}
	public Joueur getJoueur(int i) {
		if(i<0 || i >this.listeJoueurs.length-1) {
			return null;
		}else {
			return this.listeJoueurs[i];
		}
	}
	public void ajouterPersonnage(Personnage nouveau) {
		boolean vide = false;
		for(int i = 0; i < this.listePersonnage.length; i++){
		    if(this.listePersonnage[i] == null){
		        vide = true;
		        if(nouveau.getNom()!=null && nouveau.getCaracteristiques()!=null && nouveau.getAssassine()!=null && nouveau.getVole()!=null) {
				    this.listePersonnage[i]=nouveau;
			        nouveau.setPlateau(this);
			        break;
		        }
		    }
		}
		if(vide == false){
		    System.out.println("Impossible d'ajouter un nouveau Personnage (Liste pleine)");
		}
	}
	public void ajouterJoueur(Joueur nouveau) {
		boolean vide = false;
		for(int i = 0; i < this.listeJoueurs.length; i++){
		    if(this.listeJoueurs[i] == null){
		        vide = true;
		        if(nouveau.getNom()!=null && nouveau.getCite()!=null && nouveau.getMain()!=null ) {

			        this.listeJoueurs[i]=nouveau;
			        break;
		        }
		    }
		}
		if(vide == false){
		    System.out.println("Impossible d'ajouter un nouveau Personnage (Liste pleine)");
		}
	}
}
