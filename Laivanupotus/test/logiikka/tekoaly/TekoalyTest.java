package logiikka.tekoaly;

import objektit.Laiva;
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

    /**
     * Ei ole ihan idioottivarma, mutta ainoa tapa testata
     * asiaa, koska kaikki muut luokan metodit ovat yksityisiä
     */
    @Test
    public void siirraYrittaaEtsiaLaivaaOsumanJalkeen() {
        Laiva laiva = new Laiva(2, "Kumivene");
        pelilauta.asetaLaivaLaudalle(laiva, 1, 1, Boolean.FALSE);
        while (true) {
            if (tekoaly.siirra()) {
                break;
            }
        }
        tekoaly.siirra();
        Boolean viereistaRuutuaOnPommitettu = pelilauta.ruutuaOnJoPommitettu(0, 1) || pelilauta.ruutuaOnJoPommitettu(1, 0)
                || pelilauta.ruutuaOnJoPommitettu(2, 1) || pelilauta.ruutuaOnJoPommitettu(2, 2)
                || pelilauta.ruutuaOnJoPommitettu(1, 3) || pelilauta.ruutuaOnJoPommitettu(0, 2);
        assertTrue(viereistaRuutuaOnPommitettu);
    }
}