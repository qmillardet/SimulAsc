// Vous pouvez ajouter des méthodes ou non.
public class EvenementFermeturePorteCabine extends Evenement {

    // Instant précis où les portes terminent de se fermer.
    
    public EvenementFermeturePorteCabine(long d) {
	super(d);
    }

    public void afficheDetails(Immeuble immeuble) {
	System.out.print("FPC");
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
	Cabine cabine = immeuble.cabine;
	assert cabine.porteOuverte;
	cabine.porteOuverte = false;
	assert ! cabine.porteOuverte;
	immeuble.augmenterCumulTempsDeTransportOuvertureOuFermetureCabine(cabine.nbPassager());
	cabine.calculerMouvement(date, echeancier);
    }

    public void setDate(long d){
	this.date = d;
    }

}
