package modele;

public class Roi extends Personnage {

	public Roi() {
		super("Roi",4,Caracteristique.ROI);
		// TODO Auto-generated constructor stub
	}
	
	public void utiliserPouvoir() {
		this.joueur.possedeCouronne=true;
		System.out.println("Je prend la couronne");
	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub
		super.percevoirRessourcesSpecifiques();
		Quartier[] Cite =this.joueur.getCite();
		int nbQuartierNoble;
		for(Quartier quartier : Cite) {
			if(quartier.getType()=="NOBLE") {
				nbQuartierNoble++;
			}
		}
		this.joueur.ajouterPiece(nbQuartierNoble);
		System.out.println("["+nbQuartierNoble+"]"+"pieces en plus dans votre trésor");
	}
	
}
