// Ne pas ajouter ni modifier les attributs de cette classe.
// Ne pas exporter la collection passagers.
// Vous pouvez ajouter/modifier des méthodes.
import java.util.ArrayList;

public class Etage extends Constantes {

    private int numero; // de l'Etage pour l'usager

    private Immeuble immeuble; // de l'Etage

    private LoiDePoisson poissonFrequenceArrivee; // dans l'Etage

    private ArrayList<Passager> passagers = new ArrayList<Passager>();

    public Etage(int n, int fa, Immeuble im) {
	numero = n;
	immeuble = im;
	int germe = n << 2;
	if (germe <= 0) {
	    germe = -germe + 1;
	}
	poissonFrequenceArrivee = new LoiDePoisson(germe, fa);
    }

    public void affiche() {
	if (numero() >= 0) {
	    System.out.print(' ');
	}
	System.out.print(numero());
	if (this == immeuble.cabine.etage) {
	    System.out.print(" C ");
	    if (immeuble.cabine.porteOuverte) {
		System.out.print("[  ]: ");
	    } else {
		System.out.print(" [] : ");
	    }
	} else {
	    System.out.print("   ");
	    System.out.print(" [] : ");
	}
	int i = 0;
	boolean stop = passagers.size() == 0;
	while (!stop) {
	    if (i >= passagers.size()) {
		stop = true;
	    } else if (i > 6) {
		stop = true;
		System.out.print("...(");
		System.out.print(passagers.size());
		System.out.print(')');
	    } else {
		passagers.get(i).affiche();
		i++;
		if (i < passagers.size()) {
		    System.out.print(", ");
		}
	    }
	}
	System.out.print('\n');
    }

    public int numero() {
	return this.numero;
    }

    public void ajouter(Passager passager) {
	assert passager != null;
	passagers.add(passager);
    }

    public long arriveeSuivant() {
	return poissonFrequenceArrivee.suivant();
    }

}
