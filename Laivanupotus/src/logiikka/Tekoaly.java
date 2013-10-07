package logiikka;

import java.util.Random;
import objektit.Pelilauta;

class Tekoaly {

    private Pelilauta pelilauta;

    Tekoaly(Pelilauta vastustajanPelilauta) {
        this.pelilauta = vastustajanPelilauta;
    }
    
/**
 * 
 * @return Palauttaa true, jos siirto osui johonkin, muuten false.
 */
    boolean siirra() {
        Random r = new Random();
        int x, y;
        do {
            x = r.nextInt(pelilauta.getKoko());
            y = r.nextInt(pelilauta.getKoko());
        } while (pelilauta.ruutuaOnJoPommitettu(x, y));
        return pelilauta.pommita(x, y);
    }
}
