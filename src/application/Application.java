package application;

public class Application {
	static Jeu jeu = new Jeu();
	public static void main(String[] args) {
		try {
			jeu.jouer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
