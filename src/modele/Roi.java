package modele;

import controleur.Interaction;
import java.util.Random;

public class Roi extends Personnage {

	private boolean choixPouvoir;
	private int choix;

	public Roi() {
		super("Roi",4,Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}
	//Utilisation du pouvoir par un joueur humain
	public void utiliserPouvoir() {
		if(this.getJoueur()!=null) {
			System.out.println("Voulez-vous utiliser votre pouvoir ?");
			this.choixPouvoir = Interaction.lireOuiOuNon();
			if (this.choixPouvoir == true) {
				this.getJoueur().setPossedeCouronne(true);
				System.out.println("Le "+this.getNom()+" prend la couronne");
			}
		}
	}
	//Utilisation du pouvoir par un avatar
	public void utiliserPouvoirAvatar() {
		Random r = new Random();
		if(this.getJoueur() !=null) {
			this.choix =r.nextInt(2);
			switch(this.choix) {
			case 0:
				System.out.println("Le Voleur n'utilise pas son pouvoir");
				break;
			case 1:
				this.getJoueur().setPossedeCouronne(true);
				System.out.println("Le "+this.getNom()+" prend la couronne");
			default:
				break;
			}
		}
	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		int nbQuartierNoble=0;
		super.percevoirRessourcesSpecifiques();
		if(this.getJoueur()!=null) {
			Quartier[] Cite = new Quartier[this.getJoueur().nbQuartiersDansCite()];
			for(int i =0;i<Cite.length;i++) {
				for(int j=0;j<this.getJoueur().getCite().length;j++) {
					Cite[i]=this.getJoueur().getCite()[i];
				}
			}
			for(int k =0;k<Cite.length;k++) {
				if(Cite[k].getType()=="NOBLE") {
					nbQuartierNoble++;
				}
			}
			this.getJoueur().ajouterPieces(nbQuartierNoble);
			System.out.println("["+nbQuartierNoble+"]"+"pieces en plus dans votre trésor");
		}
	}
	
}
