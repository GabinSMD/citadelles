package modele;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private String nom;
	private int tresor;
	private Quartier[] cite;
	private int nbQuartiers;
	private ArrayList<Quartier> main;
	private boolean possedeCouronne;

	public Joueur(String name) {
		setNom(name);
	}

	public int nbPieces() {
		return tresor;
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
	
	public Quartier[] getCite() {
		return cite;
	}
	
	public void setCite() {
		this.cite = new Quartier[8];
	}
	
	public int nbQuartiersDansCite() {
		return cite.length;
	}

	public int setNbQuartiers() {
		setCite();
		nbQuartiers = 0;
		return nbQuartiers;
	}

	public int nbQuartiersDansMain() {
		return main.size();
	}
	
	public List<Quartier> getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = new ArrayList<Quartier>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getTresor() {
		return tresor;
	}

	public void setTresor(int tresor) {
		this.tresor = 0;
	}

	public boolean getPossedeCouronne() {
		return possedeCouronne;
	}

	public void setPossedeCouronne(boolean possedeCouronne) {
		this.possedeCouronne = false;
	}
}
