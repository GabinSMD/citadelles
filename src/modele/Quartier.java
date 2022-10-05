package modele;

import java.util.Arrays;

public class Quartier {
  public static final String[] TYPE_QUARTIERS = {"RELIGIEUX","MILITAIRE","NOBLE", "COMMERCANT", "MERVEILLE"};
  private String nom;
  private String type;
  private int coutConstruction;
  private String caracteristiques;

  public Quartier() {

  }
  public Quartier(String name, String type, int count) {
      setNom(name);
      setType(type);
      setCout(count);
  }

  public Quartier(String name, String type, int count, String characteristics){
      setNom(name);
      setType(type);
      setCout(count);
      setCaracteristiques(characteristics);
  }



  public String getType(){
	  if(this.type != null) {
	      return this.type;
	  }else {
		  return "";
	  }
  }
  public void setType(String type){
      if(Arrays.asList(TYPE_QUARTIERS).contains(type)){
          this.type = type;
      } else{
          this.type = "";
      }
  }

  public String getCaracteristiques() {
      if(this.caracteristiques != null){
          return this.caracteristiques;
      }else {
          return "";
      }
  }
  public void setCaracteristiques(String characteristics) {
      this.caracteristiques = characteristics;
  }

  public int getCout() {
      return this.coutConstruction;
  }

  public void setCout(int coutConstruction) {
      if(coutConstruction >=1 && coutConstruction<=6){
          this.coutConstruction = coutConstruction;
      }else{
          this.coutConstruction = 0;
      }
  }

  public String getNom(){
      if(this.nom!=null){
          return this.nom;
      }else{
          return "";
      }
  }
  public void setNom(String nom){
      this.nom = nom;
  }


}
