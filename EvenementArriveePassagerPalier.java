// Vous pouvez ajouter des méthodes ou non.

public class EvenementArriveePassagerPalier extends Evenement {

    // Instant précis où un nouveau passager arrive sur un palier.
    
    private Etage etageDeDepart;

    public EvenementArriveePassagerPalier(long d, Etage edd) {
		super(d);
		etageDeDepart = edd;
    }

    public void afficheDetails(Immeuble immeuble) {
		System.out.print("APP ");
		System.out.print(etageDeDepart.numero());
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
		assert etageDeDepart != null;
		assert immeuble.etage(etageDeDepart.numero()) == etageDeDepart;
		Passager p = new Passager (date, etageDeDepart, immeuble);
		
		immeuble.cabine.appeler(etageDeDepart, date, echeancier);
    }
    
}
