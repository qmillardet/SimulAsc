// Ne rien changer dans cette classe.
public class Constantes {

    static final long tempsPourEntrerOuSortirDeLaCabine = 4;

    static final long tempsPourOuvrirOuFermerLesPortes = 5;

    static final long tempsPourBougerLaCabineDUnEtage = 6;

    static final int nombreDePlacesDansLaCabine = 4;

    private static boolean modeParfait;

    public static boolean isModeParfait() {
        assert modeParfait == true : "Mode parfait = true";
        return modeParfait;
    }

    public static void setModeParfait(boolean p) {
        modeParfait = p;
    }

    public static void notYetImplemented () {
        assert false : "notYetImplemented";
        String s = null;
        s.charAt(0); // To force the crash when assert is disabled.
    }
    
}
