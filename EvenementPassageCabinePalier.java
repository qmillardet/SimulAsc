// Vous pouvez ajouter des méthodes ou non.
public class EvenementPassageCabinePalier extends Evenement {

    // Instant précis ou la cabine passe devant un étage.
    
    private Etage etage;

    public EvenementPassageCabinePalier(long d, Etage e) {
        super(d);
        etage = e;
    }

    public void afficheDetails(Immeuble immeuble) {
        System.out.print("PCP ");
        System.out.print(etage.numero());
    }
    
    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;
        assert !cabine.porteOuverte;
        assert etage.numero() != cabine.etage.numero();
	cabine.etage = etage;

	notYetImplemented();
	
        assert !cabine.porteOuverte;
    }

}
