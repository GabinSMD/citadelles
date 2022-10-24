package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Joueur {
	private String nom;
	private int tresor;
	private Quartier[] cite;
	private int nbQuartiers;
	private ArrayList<Quartier> main;
	private boolean possedeCouronne;
	protected Personnage monPersonnage;

	public Joueur(String name) {
		this.nom = name;
		this.tresor = 0;
		this.nbQuartiers = 0;
		this.possedeCouronne = false;
		this.cite = new Quartier[8];
		this.main = new ArrayList<Quartier>();
		this.monPersonnage = null;
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
		int count = 0;
		for (int i = 0; i < this.cite.length; i++) {
			if (this.cite[i] != null) {
				count++;
			}
			nbQuartiers = count;
		}
		return count;
	}

	public Quartier[] getCite() {
		return this.cite;
	}

	public List<Quartier> getMain() {
		return this.main;
	}

	public int nbQuartiersDansMain() {
		return this.main.size();
	}

	public boolean getPossedeCouronne() {
		return this.possedeCouronne;
	}

	public void setPossedeCouronne(boolean possedeCouronne) {
		this.possedeCouronne = possedeCouronne;
	}

	public int ajouterPieces(int nbPieces) {
		if (nbPieces >= 0) {
			this.tresor += nbPieces;
		}
		return this.tresor;
	}

	public int retirerPieces(int nbPieces) {
		if (nbPieces >= 0) {
			if (nbPieces <= this.tresor) {
				this.tresor -= nbPieces;
			} else if (this.tresor - nbPieces < 0) {
				return this.tresor;
			}
		}
		return this.tresor;
	}

	public Quartier[] ajouterQuartierDansCite(Quartier quartier) {
		for (int i = 0; i < this.cite.length; i++) {
			if (this.cite[i] == null) {
				this.cite[i] = quartier;
				break;
			}
		}
		return this.cite;
	}

	public boolean quartierPresentDansCite(String nom) {
		for (int i = 0; i < this.cite.length; i++) {
			if (this.cite[i] != null) {
				if (this.cite[i].getNom() == nom) {
					return true;
				}
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

			}
		}
		return null;
	}

	public void ajouterQuartierDansMain(Quartier quartier) {
		this.main.add(quartier);
	}

	public Quartier retirerQuartierDansMain() {
		Quartier removeQuartier;
		Random generateur = new Random();
		int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
		if (this.main == null) {
			return null;
		} else {
			removeQuartier = this.main.get(numeroHasard);
			this.main.remove(numeroHasard);
			return removeQuartier;

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
