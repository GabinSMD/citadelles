package modele;

public class Roi extends Personnage {

	public Roi() {
		super("Roi",4,Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}
	
	public void utiliserPouvoir() {
		if(this.joueur!=null) {
	
			this.joueur.setPossedeCouronne(true);
			System.out.println("Je prend la couronne");
		}
	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		int nbQuartierNoble=0;
		
		super.percevoirRessourcesSpecifiques();
		if(this.joueur!=null) {
			
			Quartier[] Cite = new Quartier[this.joueur.nbQuartiersDansCite()];
			
			for(int i =0;i<Cite.length;i++) {
				for(int j=0;j<this.joueur.getCite().length;j++) {
					Cite[i]=this.joueur.getCite()[i];
				}
			}
			for(int k =0;k<Cite.length;k++) {
				if(Cite[k].getType()=="NOBLE") {
					nbQuartierNoble++;
				}
			}
			this.joueur.ajouterPieces(nbQuartierNoble);
			System.out.println("["+nbQuartierNoble+"]"+"pieces en plus dans votre trésor");
		}
	}
	
}
