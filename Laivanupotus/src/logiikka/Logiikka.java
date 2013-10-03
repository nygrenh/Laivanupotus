package logiikka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import objektit.Laiva;
import toimijat.Pelaaja;
import kayttoliittyma.Kayttolittyma;
import objektit.Pelilauta;

/**
 * Huolehtii pelin kulusta
 */
public class Logiikka {

    private Pelaaja pelaaja, pelaaja2;
    private Collection<Laiva> pelaajanLaivat;
    private Pelinvaihe pelinvaihe;
    private Kayttolittyma kayttolittyma;
    private long edellinenSilmukka;
    private long fps;
    private Tekoaly tekoaly;

    public Logiikka(Kayttolittyma kayttolittyma) {
        pelaaja = new Pelaaja(kayttolittyma);
        pelaaja2 = new Pelaaja();
        pelaajanLaivat = luoUusiLaivasto();
        Collection<Laiva> pelaaja2nLaivat = luoUusiLaivasto();
        pelaaja.sijoitaLaivatLaudalle(pelaaja2nLaivat);
        pelinvaihe = Pelinvaihe.LAIVOJENSIJOITTELU;
        tekoaly = new Tekoaly(pelaaja.getVastustajanPelilauta());
    }

    public void silmukka() {
        edellinenSilmukka = System.currentTimeMillis();
        while (true) {
            if (pelinvaihe == Pelinvaihe.LAIVOJENSIJOITTELU && annaAsetettavaLaiva() == null) {
                vaihdaVuoro();
            }
            if (pelinvaihe == Pelinvaihe.PELAAJA2NVUORO) {
                tekoaly.siirra();
                vaihdaVuoro();
            }
            kayttolittyma.uudelleenPiirra();
            nuku();
        }
    }

    private Collection<Laiva> luoUusiLaivasto() {
        Collection<Laiva> palautettava = new ArrayList<>();
        lisaaLaiva(palautettava, 1, "Lentotukialus", 5);
        lisaaLaiva(palautettava, 1, "Taistelulaiva", 4);
        lisaaLaiva(palautettava, 2, "Risteilijä", 3);
        lisaaLaiva(palautettava, 2, "Hävittäjä", 2);
        return palautettava;
    }

    private void lisaaLaiva(Collection<Laiva> palautettava, int lukumaara, String nimi, int pituus) {
        for (int i = 0; i < lukumaara; i++) {
            Laiva lisattavaLaiva = new Laiva(pituus, nimi);
            palautettava.add(lisattavaLaiva);
        }
    }

    public Laiva annaAsetettavaLaiva() {
        for (Laiva laiva : pelaajanLaivat) {
            if (laiva.onSijoitettu()) {
                continue;
            }
            return laiva;
        }
        return null;
    }

    public static void main(String[] args) {
        Kayttolittyma kayttolittyma = new Kayttolittyma();
        Logiikka logiikka = new Logiikka(kayttolittyma);
        logiikka.setKayttolittyma(kayttolittyma);
        kayttolittyma.setLogiikka(logiikka);
        kayttolittyma.run();
        logiikka.silmukka();
    }

    public Pelinvaihe getPelinvaihe() {
        return pelinvaihe;
    }

    public String getViesti() {
        String palautettava = "";
        if (pelinvaihe == Pelinvaihe.LAIVOJENSIJOITTELU && annaAsetettavaLaiva() != null) {
            palautettava += "Aseta " + annaAsetettavaLaiva().toString().toLowerCase() + " oikeanpuoleiseen pelilautaan. Hiiren rullan painaminen kääntää laivan suuntaa.";
        }
        if (pelinvaihe == Pelinvaihe.PELAAJA1NVUORO) {
            palautettava += "Pommita vasemmanpuoleista pelilautaa";
        }
        if (pelinvaihe == Pelinvaihe.PELILOPPU) {
            palautettava += "Peli on loppunut";
        }
        return palautettava + " " + fps + "fps";
    }

    public void setKayttolittyma(Kayttolittyma kayttolittyma) {
        this.kayttolittyma = kayttolittyma;
    }

    /**
     * Yrittää nukkua täsmälleen sen verran, että silmukka pyörisi 60fps
     */
    private void nuku() {
        long aikaNyt = System.currentTimeMillis();
        long ero = aikaNyt - edellinenSilmukka;
        try {
            long nukuttavaika = (long) 16.67 - ero;
            if (nukuttavaika > 0) {
                Thread.sleep(nukuttavaika);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Logiikka.class.getName()).log(Level.SEVERE, null, ex);
        }
        fps = 1000 / (System.currentTimeMillis() - edellinenSilmukka);
        this.edellinenSilmukka = aikaNyt;
    }

    private void vaihdaVuoro() {
        if (pelinvaihe == Pelinvaihe.LAIVOJENSIJOITTELU || pelinvaihe == Pelinvaihe.PELAAJA2NVUORO) {
            pelinvaihe = Pelinvaihe.PELAAJA1NVUORO;
        } else {
            pelinvaihe = Pelinvaihe.PELAAJA2NVUORO;
        }
    }

    public void pelaaja1nSiirto(int x, int y, Pelilauta pelilauta) {
        if (!pelilauta.ruutuaOnJoPommitettu(x, y)) {
            pelilauta.pommita(x, y);
            vaihdaVuoro();
        }
    }
}
