package objektit;

/**
 * Huolehtii yhteen ruutuun liityvän datan talletuksesta
 */
public class Ruutu {
    private Laiva laiva;
    private boolean onPommitettu;
    
    public Ruutu(){
        laiva = null;
        onPommitettu = false;
    }

    public Laiva getLaiva() {
        return laiva;
    }

    public boolean onPommitettu(){
        return onPommitettu;
    }
    public void pommita(){
        onPommitettu = true;
    }
    public boolean ruudussaOnLaiva(){
        return laiva != null;
    }
    
    public void asetaruutuunLaiva(Laiva laiva){
        this.laiva = laiva;
    }
}
