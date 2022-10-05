package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Joueur {
	private String nom;
	private int tresor;
	private Quartier[] cite;
	private int nbQuartiers;
	private ArrayList<Quartier> main;
	private boolean possedeCouronne;

	public Joueur(String name) {
		this.nom = name;
		this.tresor = 0;
		this.nbQuartiers = 0;
		this.possedeCouronne = false;
		this.cite = new Quartier [8];
		this.main = new ArrayList<Quartier>();
	}


	public int ajouterPieces(int nbPieces) {
		if (nbPieces >= 0) {
			tresor += nbPieces;
		}
		return tresor;
	}

	public int retirerPieces(int nbPieces) {
		if (nbPieces >= 0) {
			if (tresor - nbPieces < 0) {
				tresor = 0;
			} else {
				tresor -= nbPieces;
			}
		}
		return tresor;
	}

	public String getNom() {
		return this.nom;
	}
	
	public int nbPieces() {
		return this.tresor;
	}

	public long nbQuartiersDansCite() {
		return Arrays.stream(this.cite).filter(Objects::nonNull).count();
	}
	
	public Quartier[] getCite() {
		return this.cite;
	}
	
	public List<Quartier> getMain() {
		return this.main;
	}
	

	public long nbQuartiersDansMain() {
		return this.main.size();
	}

	public boolean getPossedeCouronne() {
		return this.possedeCouronne;
	}

	public void setPossedeCouronne(boolean possedeCouronne) {
		this.possedeCouronne = possedeCouronne;
	}
}
