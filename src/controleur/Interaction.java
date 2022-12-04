package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);
	
	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				System.out.print("Veuillez entrer un chiffre : \n");
				i = sc.nextInt();
				continu = false;
			} catch (InputMismatchException e) {
				sc.next(); // passe l'entier pour éviter de boucler
			}
		} while(continu);
		return i;
	}
	
	// renvoie un entier lu au clavier compris dans l'intervalle
	//     [borneMin, borneMax[
	public static int lireUnEntier(int borneMin, int borneMax) {
		int i = 0;
		do {
			try {
				System.out.print("Veuillez entrer un chiffre dans l'intervalle ["+borneMin+","+borneMax+"[: \n");
				i = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.next(); // passe l'entier pour éviter de boucler
			}
		} while(i < borneMin || i >= borneMax);
		return i;
	}

	// lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
	public static boolean lireOuiOuNon() {
		boolean retour = true;
		boolean check = false;
		String val="";
		do {
			try {
				val = sc.nextLine();
				switch(val)
			    {
			        case "oui":
			            retour = true;
			            check = true;
			            break;
			        case "non":
			            retour = false;
			            check = true;
			            break;
			        case "o":
			            retour = true;
			            check = true;
			            break;
			        case "n":
			            retour = false;
			            check = true;
			            break;
			        default:
			        	System.out.print("Veuillez entrer \"oui\", \"o\", \"non\" ou \"n\" : ");
			        	break;
			    }
			} catch (InputMismatchException e) {
				System.out.print("Veuillez entrer \"oui\", \"o\", \"non\" ou \"n\" : ");
				sc.next(); // passe l'entier pour éviter de boucler
			}
		} while(!check);
		return retour;
	}

	// renvoie une chaîne de caractère lue au clavier:
	public static String lireUneChaine() {
		System.out.print("Veuillez entrer un mot :");
		String retour = "";
		retour = sc.nextLine();
		return retour;
	}
}