package modele;

public class Architecte extends Personnage{

	public Architecte() {
		super("Architecte", 7, Caracteristiques.ARCHITECTE);
	}
	
	// Utilisation du pouvoir par un joueur humain
	public void utiliserPouvoir() {
		if(this.getJoueur()!=null) {
			for(int i=0; i < 2 ; i++) {
				this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
			}
		}
	}
	
	// Utilisation du pouvoir par un avatar
	public void utiliserPouvoirAvatar() {
		if(this.getJoueur()!=null) {
			for(int i=0; i < 2 ; i++) {
				this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
			}
		}
	}
}