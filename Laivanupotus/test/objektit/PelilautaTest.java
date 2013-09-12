
package objektit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilautaTest {
    private Pelilauta lauta;
    
    @Before
    public void setUp() {
        lauta = new Pelilauta(10);
    }

    @Test
    public void getRuutuPalauttaaNullJosRuutuaEiOleOlemassa() {
        assertNull(lauta.getRuutu(10, 10));
        assertNull(lauta.getRuutu(-1, -1));
    }
    
    @Test
    public void getRuutuPalauttaaRuudunJosRuutuOnOlemassa(){
        assertTrue(lauta.getRuutu(0, 0) instanceof Ruutu);
        assertTrue(lauta.getRuutu(9, 9) instanceof Ruutu);
    }
    
    @Test
    public void pommitaPalauttaaFalseJosRuudussaEiOleLaivaa(){
        assertFalse(lauta.pommita(0, 0));
    }
    
}

