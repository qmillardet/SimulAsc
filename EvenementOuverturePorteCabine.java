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
		cabine.porteOuverte = true;
		Passager p = cabine.etage.reucpererPremierPassage();
		if (p != null) {
			cabine.ajouterPersonneCabinne(p, echeancier, date);
			etage.supprimerPassagerEtage(p);
		}
		cabine.liberePersonneCabine(date);
		echeancier.ajouter(new EvenementFermeturePorteCabine(date + Constantes.tempsPourOuvrirOuFermerLesPortes + Constantes.tempsPourEntrerOuSortirDeLaCabine));
    }

}
