package logiikka.tekoaly;

import logiikka.tekoaly.Tekoaly;
import objektit.Pelilauta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TekoalyTest {

    private Tekoaly tekoaly;
    private Pelilauta pelilauta;

    @Before
    public void setUp() {
        this.pelilauta = new Pelilauta(10);
        this.tekoaly = new Tekoaly(pelilauta);
    }

    @Test
    public void siirraPommittaaJotakinRuutua() {
        tekoaly.siirra();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pelilauta.ruutuaOnJoPommitettu(i, j)) {
                    return;
                }
            }
        }
        fail("Mitään ruutua ei ole pommitettu");
    }
}