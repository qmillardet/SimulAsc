// Ne pas ajouter ni modifier les attributs de cette classe.
// Ne pas exporter tableauPassager.
// Vous pouvez ajouter/modifier des méthodes.

public class Cabine extends Constantes {
    
    public Etage etage; // actuel
    
    public boolean porteOuverte;
    
    private char status; // '-' ou 'v' ou '^'
    
    private Passager[] tableauPassager;
    
    public Cabine(Etage e) {
	etage = e;
	tableauPassager = new Passager[nombreDePlacesDansLaCabine];
	porteOuverte = false;
	status = '-';
    }
    
    public void afficheLaSituation() {
	System.out.print("Contenu de la cabine: ");
	for (int i = tableauPassager.length - 1; i >= 0 ; i--) {
	    Passager p = tableauPassager[i];
	    if (p != null) {
		p.affiche();
		System.out.print(' ');
	    }
	}
	assert (status == 'v') || (status == '^') || (status == '-');
	System.out.println("\nStatus de la cabine: " + status);
    }
    
    public char status() {
	assert (status == 'v') || (status == '^') || (status == '-');
	return status;
    }
    
    public void changerStatus(char s){
	assert (s == 'v') || (s == '^') || (s == '-');
	status = s;
    }

}
