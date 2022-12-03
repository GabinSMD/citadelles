package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	private String nom;
	private int tresor;
	private Quartier[] cite;
	private int nbQuartiers;
	private ArrayList<Quartier> main;
	private boolean possedeCouronne;
	private boolean avatar;
	protected Personnage monPersonnage;

	public Joueur(String name) {
		this.nom = name;
		this.tresor = 0;
		this.nbQuartiers = 0;
		this.possedeCouronne = false;
		this.cite = new Quartier[8];
		this.main = new ArrayList<Quartier>();
		this.monPersonnage = null;
		this.avatar=false;
		
	}
	
	public boolean getAvatar() {
		return this.avatar;
	}
	
	public void setAvatar(Boolean a) {
		this.avatar = a;
	}

	public Personnage getPersonnage() {
		return this.monPersonnage;
	}

	public String getNom() {
		return this.nom;
	}

	public int nbPieces() {
		return this.tresor;
	}

	public int nbQuartiersDansCite() {
		this.nbQuartiers=0;
		for (Quartier q: this.cite) {
			if (q != null) {
				this.nbQuartiers++;
			}
		}
		return this.nbQuartiers;
	}

	public Quartier[] getCite() {
		return this.cite;
	}

	public ArrayList<Quartier> getMain() {
		return this.main;
	}

	public int nbQuartiersDansMain() {
		return this.main.size();
	}

	public boolean getPossedeCouronne() {
		return this.possedeCouronne;
	}

	public void setPossedeCouronne(boolean b) {
		this.possedeCouronne = b;
	}

	public int ajouterPieces(int nbPieces) {
		if (nbPieces >= 0) {
			this.tresor += nbPieces;
		}else {
			System.out.println("Le nombre de pièce doit être positif");
		}
		return this.tresor;
	}

	public int retirerPieces(int nbPieces) {
		if (nbPieces >= 0 && nbPieces <= this.tresor) {
				this.tresor -= nbPieces;
		}else {
			System.out.println("Impossible de retirer " + nbPieces + "pièces");
		}
		return this.tresor;
	}

	public Quartier[] ajouterQuartierDansCite(Quartier quartier) {
		boolean plein = false;
		if(quartier!=null) {
			for (int i = 0; i < this.cite.length; i++) {
				if (this.cite[i] == null) {
					this.cite[i] = quartier;
					plein = true;
					break;
				}
			    if(plein) {
			    	System.out.println("Impossible d'ajouter le quartier (La liste est pleine)");
			    }
			}
		}
		return this.cite;
	}

	public boolean quartierPresentDansCite(String nom) {
		for (Quartier q: this.cite) {
			if (q != null && q.getNom()== nom) {
				return true;
			}
		}
		return false;
	}

	public Quartier retirerQuartierDansCite(String nom) {
		Quartier removeQuartier;
		for (int i = 0; i < this.cite.length; i++) {
			if (this.cite[i] != null && this.cite[i].getNom() == nom) {
				removeQuartier = this.cite[i];
				for (int j = i; j < this.cite.length - 1; j++) {
					this.cite[j] = this.cite[j + 1];
				}
				return removeQuartier;
			}else {
				System.out.println("Impossible de retirer le/la "+nom);
			}
		}
		return null;
	}

	public void ajouterQuartierDansMain(Quartier quartier) {
		if (quartier!=null) {
			this.main.add(quartier);
		}
	}

	public Quartier retirerQuartierDansMain() {
		Quartier removeQuartier;
		Random generateur = new Random();
		int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
		if (this.main != null) {
			removeQuartier = this.main.get(numeroHasard);
			this.main.remove(numeroHasard);
			return removeQuartier;
		} else {
			System.out.println("Impossible de retirer le quartier de la main");
			return null;
		}
	}

	public void reinitialiser() {
		this.tresor = 0;
		this.nbQuartiers = 0;
		this.possedeCouronne = false;
		this.cite = new Quartier[8];
		this.main = new ArrayList<Quartier>();
	}

}