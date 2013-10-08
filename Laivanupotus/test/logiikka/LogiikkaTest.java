package logiikka;

import kayttoliittyma.Kayttolittyma;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogiikkaTest {

    private Logiikka logiikka;

    @Before
    public void setUp() {
        logiikka = new Logiikka(new Kayttolittyma());
    }

    @Test
    public void peliAlkaaLaivojenAsettelulla() {
        assertEquals(Pelinvaihe.LAIVOJENSIJOITTELU, logiikka.getPelinvaihe());
    }

    @Test
    public void kunKeskenLaivojenSijoittelunVaihdetaanVuoroaTuleePelaaja1senVuoro() {
        logiikka.vaihdaVuoro();
        assertEquals(Pelinvaihe.PELAAJA1NVUORO, logiikka.getPelinvaihe());
    }

    @Test
    public void pelaaja1senVuoronJalkeenSeuraaPelaaja2senVuoro() {
        logiikka.vaihdaVuoro();
        logiikka.vaihdaVuoro();
        assertEquals(Pelinvaihe.PELAAJA2NVUORO, logiikka.getPelinvaihe());
    }

    @Test
    public void pelaaja2senVuoronJalkeenSeuraaPelaaja1senVuoro() {
        logiikka.vaihdaVuoro();
        logiikka.vaihdaVuoro();
        logiikka.vaihdaVuoro();
        assertEquals(Pelinvaihe.PELAAJA1NVUORO, logiikka.getPelinvaihe());
    }
}