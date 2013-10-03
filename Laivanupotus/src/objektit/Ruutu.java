package objektit;

/**
 * Huolehtii yhteen ruutuun liityvän datan talletuksesta
 */
public class Ruutu {

    private Laiva laiva;
    private boolean onPommitettu;

    /**
     * Alustaa pommittamattoman ruudun ilman laivaa
     */
    public Ruutu() {
        laiva = null;
        onPommitettu = false;
    }

    public Laiva getLaiva() {
        return laiva;
    }

    public boolean onPommitettu() {
        return onPommitettu;
    }

    /**
     * Muuttaa ruudun pommitetuksi ruuduksi
     */
    public void pommita() {
        onPommitettu = true;
    }

    public boolean ruudussaOnLaiva() {
        return laiva != null;
    }

    public void asetaruutuunLaiva(Laiva laiva) {
        this.laiva = laiva;
    }
}
