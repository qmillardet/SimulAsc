// Ne pas ajouter ni modifier les attributs de cette classe.
// Ne pas exporter tableauPassager.
// Vous pouvez ajouter/modifier des méthodes.

import java.util.*;

public class Cabine extends Constantes {

    public Etage etage; // actuel

    public boolean porteOuverte;

    private char status; // '-' ou 'v' ou '^'

    private Passager[] tableauPassager;

    private int nbPassager;

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
        for (int i = tableauPassager.length - 1; i >= 0; i--) {
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

    public void changerStatus(char s) {
        assert (s == 'v') || (s == '^') || (s == '-');
        status = s;
    }

    public void appeler(Etage e, long date, Echeancier echeancier) {

        destinations.add(e);
            calculerMouvement(date, echeancier);
    }

    public void calculerMouvement(long date, Echeancier echeancier) {

        if (destinations.isEmpty()) {
            //status = '-';
        } else {
            if (destinations.contains(etage)) {
                destinations.remove(etage);
                // Ouverture porte cabine
                echeancier.ajouter(new EvenementOuverturePorteCabine(date + Constantes.tempsPourOuvrirOuFermerLesPortes));
                this.liberePersonneCabine(date);
                this.augmenterTempsChaquePassagerOuverturePorte();
            } else {
                status = destinations.peek().numero() > etage.numero() ? '^' : 'v';
                this.augmenterTempsChaquePassagerEtage();
                echeancier.ajouter(new EvenementPassageCabinePalier(date + Constantes.tempsPourBougerLaCabineDUnEtage, etage.immeuble().etage(etage.numero() + (status == '^' ? 1 : -1))));
            }
        }
    }

    public boolean ajouterPersonneCabinne(Passager p, Echeancier echeancier) {
        assert p != null : "Passager entrant null";
        int i = 0;
        while (this.tableauPassager[i] != null && i < this.tableauPassager.length) {
            i++;
        }
        if (i != this.tableauPassager.length - 1) {
            this.tableauPassager[i] = p;
            this.destinations.add(p.etageDestination());
            this.nbPassager++;
            return true;
        }
        return false;
    }

    public void liberePersonneCabine(long date) {
        for (int i = 0; i < this.tableauPassager.length; i++) {
            Passager p = this.tableauPassager[i];
            try {
                if (p.etageDestination() == etage) {
                    this.tableauPassager[i] = null;
                    etage.immeuble().ajouterUnPassagerSorti();
                    this.nbPassager--;
                    p.augmenterTempsEtageFinal();
                    this.etage.immeuble().cumulDesTempsDeTransport += p.tempsTransport;
                }
            } catch (Exception e) {
            }
        }
    }

    public int nbPassager() {
        return this.nbPassager;
    }

    public void augmenterTempsChaquePassagerEtage(){
        if (this.nbPassager() != 0){
            for(int i=0; i<this.tableauPassager.length; i++){
                if (this.tableauPassager[i] != null) {
                    this.tableauPassager[i].augmenterTempsEtage();
                }
            }
        }
    }

    public void augmenterTempsChaquePassagerOuverturePorte(){
        if (this.nbPassager() != 0){
            for(int i=0; i<this.tableauPassager.length; i++){
                if (this.tableauPassager[i] != null) {
                    this.tableauPassager[i].augmenterTempsArretEtageSansDescendre();
                }
            }
        }
    }
}
