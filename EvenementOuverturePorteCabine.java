import java.util.ArrayList;

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
        cabine.porteOuverte = true;
        ArrayList<Passager> lp = cabine.etage.passagers();
        Passager p;
        int i = 0;
        int nbpass = 0;
        nbpass += cabine.liberePersonneCabine(date);
        while (i < lp.size()) {
            p = lp.get(i);
            if (p != null) {
                int b = cabine.ajouterPersonneCabinne(p);
                if (b == 0) {
                    etage.supprimerPassagerEtage(p);
                    nbpass ++;
                }
                else {
                    i++;
                }
            }
            
        }
        if (nbpass != 0) {
            echeancier.ajouter(new EvenementFermeturePorteCabine(date + Constantes.tempsPourOuvrirOuFermerLesPortes + Constantes.tempsPourEntrerOuSortirDeLaCabine * nbpass));
        }
        else{
            echeancier.ajouter(new EvenementFermeturePorteCabine(date + Constantes.tempsPourOuvrirOuFermerLesPortes));
        }
    }
    
}
