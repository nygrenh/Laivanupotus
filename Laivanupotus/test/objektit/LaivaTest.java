
package objektit;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaivaTest {
    private Pelilauta lauta;
    private Laiva laiva;
    private Ruutu ruutu1;
    private Ruutu ruutu2;
    private Ruutu ruutu3;
    private ArrayList<Ruutu> ruudut;
    
    @Before
    public void setUp() {
        lauta = new Pelilauta(10);
        laiva = new Laiva(3, "Kumivene");
        ruutu1 = new Ruutu();
        ruutu2 = new Ruutu();
        ruutu3 = new Ruutu();
        ruudut = new ArrayList<>();
        ruudut.add(ruutu1);
        ruudut.add(ruutu2);
        ruudut.add(ruutu3);
        
    }

    @Test
    public void OnTuhottuPalauttaaFalseJosLaivaaEiOleTuhottu() {
        laiva.asetaRuudut(ruudut);
        ruutu1.pommita();
        ruutu2.pommita();
        assertFalse(laiva.onTuhottu());
    }
    
    @Test
    public void OnTuhottuPalauttaaTrueJosLaivaaOnTuhottu() {
        laiva.asetaRuudut(ruudut);
        ruutu1.pommita();
        ruutu2.pommita();
        ruutu3.pommita();
        assertTrue(laiva.onTuhottu());
    }
    
    @Test
    public void OnTuhottuPalauttaaFalseJosLaivaaEiOleSijoitettu() {
        assertFalse(laiva.onTuhottu());
    }

}