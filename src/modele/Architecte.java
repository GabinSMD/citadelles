package modele;

public class Architecte extends Personnage{

	public Architecte() {
		super("Architecte", 7, Caracteristiques.ARCHITECTE);
	}
	
	// Utilisation du pouvoir par un joueur humain
	public void utiliserPouvoir() {
		for(int i=0; i < 2 ; i++) {
			this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
		}
	}
	
	// Utilisation du pouvoir par un avatar
	public void utiliserPouvoirAvatar() {
		for(int i=0; i < 2 ; i++) {
			this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
		}
	}
}