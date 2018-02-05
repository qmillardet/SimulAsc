// Ne pas ajouter ni modifier les attributs de cette classe.
// Ne pas exporter tableauPassager.
// Vous pouvez ajouter/modifier des méthodes.

import java.util.*;

public class Cabine extends Constantes {
    
    public Etage etage; // actuel
    
    public boolean porteOuverte;
    
    private char status; // '-' ou 'v' ou '^'
    
    private Passager[] tableauPassager;

    private Queue<Etage> destinations;
    
    public Cabine(Etage e) {
	etage = e;
	tableauPassager = new Passager[nombreDePlacesDansLaCabine];
	porteOuverte = false;
	status = '-';
	destinations = new LinkedList<>();
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

    public void appeler (Etage e, long date, Echeancier echeancier) {

    	destinations.add(e);
    	if (status == '-')
    		calculerMouvement(date, echeancier);
    }

    public void calculerMouvement (long date, Echeancier echeancier) {

    	if (destinations.isEmpty()) {
    		status = '-';
    	} else {
    		if (destinations.contains(etage)) {
    			destinations.remove(etage);
    			status = '-';
    			//prendre passager
    			calculerMouvement(date, echeancier);
    			//Constantes.notYetImplemented ();
    		} else {
	    		status = destinations.peek().numero() > etage.numero() ? '^' : 'v';
	    		echeancier.ajouter(new EvenementPassageCabinePalier(date + Constantes.tempsPourBougerLaCabineDUnEtage, etage.immeuble().etage(etage.numero() + (status == '^' ? 1 : -1))));
	    	}
    	}
    }
}
