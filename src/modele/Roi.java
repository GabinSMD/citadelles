package modele;

public class Roi extends Personnage {

	public Roi() {
		super("Roi",4,Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}
	
	public void utiliserPouvoir() {
		if(this.getJoueur().getNom() !=null) {
	
			this.getJoueur().setPossedeCouronne(true);
			System.out.println("Je prend la couronne");
		}
	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		int nbQuartierNoble=0;
		
		super.percevoirRessourcesSpecifiques();
		if(this.getJoueur().getNom()!=null) {
			
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
