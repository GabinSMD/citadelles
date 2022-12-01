package modele;

import java.util.ArrayList;
import java.util.Collections;
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
		Random generateur = new Random();
		int i = generateur.nextInt(nombreElements());
		int j = generateur.nextInt(nombreElements());
		//Collections.swap(this.liste, i, j);
		Quartier firstQuartier = this.liste.get(i);
		Quartier secondQuartier = this.liste.get(j);
		this.liste.set(i, secondQuartier);
		this.liste.set(j, firstQuartier);

	}

}
