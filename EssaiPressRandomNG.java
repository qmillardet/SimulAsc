// Classe à jeter.
public class EssaiPressRandomNG { 
    public static void main(String args[]) {
	PressRandomNumberGenerator random = new PressRandomNumberGenerator(81);
	
	// Tirage aleatoire de int dans l'intervalle [1 .. 50]
	int i = 50;
	while ( i > 0 ) {
	    System.out.println(random.intSuivant(50));
	    i--;
	}
	
    }
}

