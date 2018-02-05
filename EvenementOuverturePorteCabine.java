// Vous pouvez ajouter des méthodes ou non.
public class EvenementOuverturePorteCabine extends Evenement {

    // Instant précis où les portes terminent de s'ouvrir.
    
    public EvenementOuverturePorteCabine(long d) {
	super(d);
    }

    public void afficheDetails(Immeuble immeuble) {
	System.out.print("OPC");
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
	Cabine cabine = immeuble.cabine;
	Etage etage = cabine.etage;
	int nbPassage=0;
	assert cabine.status() != '-';
	assert !cabine.porteOuverte;
	cabine.porteOuverte = true;

	notYetImplemented();

	assert cabine.porteOuverte;
    }

}
