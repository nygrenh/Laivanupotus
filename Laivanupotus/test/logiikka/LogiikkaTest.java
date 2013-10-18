package logiikka;

import kayttoliittyma.Kayttolittyma;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogiikkaTest {

    private Logiikka logiikka;

    @Before
    public void setUp() {
        logiikka = new Logiikka(new Kayttolittyma(), new PelinArgumentit(10));
    }

    @Test
    public void peliAlkaaLaivojenAsettelulla() {
        assertEquals(Pelinvaihe.LAIVOJEN_SIJOITTELU, logiikka.getPelinvaihe());
    }

    @Test
    public void kunKeskenLaivojenSijoittelunVaihdetaanVuoroaTuleePelaaja1senVuoro() {
        logiikka.vaihdaVuoro();
        assertEquals(Pelinvaihe.PELAAJA_1_VUORO, logiikka.getPelinvaihe());
    }

    @Test
    public void pelaaja1senVuoronJalkeenSeuraaPelaaja2senVuoro() {
        logiikka.vaihdaVuoro();
        logiikka.vaihdaVuoro();
        assertEquals(Pelinvaihe.PELAAJA_2_VUORO, logiikka.getPelinvaihe());
    }

    @Test
    public void pelaaja2senVuoronJalkeenSeuraaPelaaja1senVuoro() {
        logiikka.vaihdaVuoro();
        logiikka.vaihdaVuoro();
        logiikka.vaihdaVuoro();
        assertEquals(Pelinvaihe.PELAAJA_1_VUORO, logiikka.getPelinvaihe());
    }
}