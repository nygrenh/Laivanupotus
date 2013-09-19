
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
    
    @Test
    public void ruutuaOnJoPommitettuPalauttaaFalseJosRuutuaEiOlePommitettu(){
        assertFalse(lauta.ruutuaOnJoPommitettu(3, 3));
    }
    
    @Test
    public void ruutuaOnJoPommitettuPalauttaaTrueJosRuutuaOnPommitettu(){
        lauta.pommita(7, 4);
        assertTrue(lauta.ruutuaOnJoPommitettu(7, 4));
    }
    
    @Test
    public void asetaLaivaLaudallePalauttaaFalseJosYritetaanAsettaaLaivaaLaudanUlkopuolelle(){
        assertFalse(lauta.asetaLaivaLaudalle(new Laiva(2, "Kumivene"), 55, 55, 56, 55));
    }
    
    @Test
    public void asetaLaivaLaudallePalauttaaFalseJosLaivanPituusOnVaara(){
        assertFalse(lauta.asetaLaivaLaudalle(new Laiva(1, "Kumivene"), 1, 1, 1, 2));
        assertFalse(lauta.asetaLaivaLaudalle(new Laiva(3, "Kumivene"), 1, 1, 1, 2));
    }
    
    
}

