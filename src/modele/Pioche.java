package modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
	private ArrayList<Quartier> liste;

	public Pioche() {
		this.liste = new ArrayList<Quartier>();

	}

	public Quartier piocher() {
		if (this.liste.size() > 0) {
			Quartier carte = this.liste.get(0);
			this.liste.remove(0);
			return carte;
		}
		return null;
	}

	public void ajouter(Quartier quartier) {
		this.liste.add(quartier);
	}

	public int nombreElements() {
		return this.liste.size();
	}

	public void melanger() {
		for(int i=0; i<this.liste.size(); i++) {
			Random generateur = new Random();
			int j = generateur.nextInt(nombreElements());
			int k = generateur.nextInt(nombreElements());
			//Collections.swap(this.liste, i, j);
			Quartier firstQuartier = this.liste.get(j);
			Quartier secondQuartier = this.liste.get(k);
			this.liste.set(j, secondQuartier);
			this.liste.set(k, firstQuartier);
		}
	}
}