package logiikka;

import java.util.ArrayList;
import java.util.Collection;
import logiikka.tekoaly.Vaikeustaso;
import objektit.Laiva;
/**
 * Luokka kasaa pelin asetukset yhteen
 */
public class PelinArgumentit {

    private int pelilaudanKoko, lentotukialustenMaara, taisteluLaivojenMaara, risteilijoidenMaara, havittajienMaara, sukellusVeneidenMaara;
    public Vaikeustaso vaikeustaso;

    public PelinArgumentit(int pelilaudanKoko) {
        this(pelilaudanKoko, 1, 1, 2, 2, 0, Vaikeustaso.HANKALA);
    }

    public PelinArgumentit(int pelilaudanKoko, int lentotukialustenMaara, int taisteluLaivojenMaara, int risteilijoidenMaara, int havittajienMaara, int sukellusVeneidenMaara, Vaikeustaso vaikeustaso) {
        this.pelilaudanKoko = pelilaudanKoko;
        this.lentotukialustenMaara = lentotukialustenMaara;
        this.taisteluLaivojenMaara = taisteluLaivojenMaara;
        this.risteilijoidenMaara = risteilijoidenMaara;
        this.havittajienMaara = havittajienMaara;
        this.sukellusVeneidenMaara = sukellusVeneidenMaara;
        this.vaikeustaso = vaikeustaso;
    }


    /**
     * Luo yhden pelaajan kaikki laivat
     *
     * @return Palauttaa kokoelman yhden pelaajan laivoista, jotka funktio on
     * luonut
     */
    public Collection<Laiva> luoUusiLaivasto() {
        Collection<Laiva> palautettava = new ArrayList<>();
        lisaaLaiva(palautettava, lentotukialustenMaara, "Lentotukialus", 5);
        lisaaLaiva(palautettava, taisteluLaivojenMaara, "Taistelulaiva", 4);
        lisaaLaiva(palautettava, risteilijoidenMaara, "Risteilijä", 3);
        lisaaLaiva(palautettava, havittajienMaara, "Hävittäjä", 2);
        lisaaLaiva(palautettava, sukellusVeneidenMaara, "Sukellusvene", 1);
        return palautettava;
    }

    /**
     * Lisää sopivan verran laivoja kokoelmaan
     *
     * @param kokoelma Kokoelma, johon laivat lisätään
     * @param lukumaara Lisättävien laivojen lukumäärä
     * @param nimi Lisättävän laivan nimi
     * @param pituus Lisättävän laivan pituus
     */
    private void lisaaLaiva(Collection<Laiva> kokoelma, int lukumaara, String nimi, int pituus) {
        for (int i = 0; i < lukumaara; i++) {
            Laiva lisattavaLaiva = new Laiva(pituus, nimi);
            kokoelma.add(lisattavaLaiva);
        }
    }

    int getPelilaudanKoko() {
        return pelilaudanKoko;
    }
}
