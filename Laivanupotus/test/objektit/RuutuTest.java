
package objektit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuutuTest {
    
    public RuutuTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void kunRuudussaOnLaivaRuudussaOnLaivaPalauttaaTrue() {
        Ruutu ruutu = new Ruutu();
        ruutu.asetaruutuunLaiva(new Laiva(2, "Kumivene"));
        assertTrue(ruutu.ruudussaOnLaiva());
    }

}