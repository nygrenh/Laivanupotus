package logiikka;

import java.util.ArrayList;
import java.util.Collection;
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
    private Collection<Laiva> pelaajanLaivat, pelaaja2nLaivat;
    private Pelinvaihe pelinvaihe;
    private Kayttolittyma kayttolittyma;
    private long edellinenSilmukka;
    private long fps;
    private Tekoaly tekoaly;
    private int hiirenSijaintiX, hiirenSijaintiY;
    private Boolean asetettavaLaivaMeneeAlaspain;

    public Logiikka(Kayttolittyma kayttolittyma) {
        pelaaja = new Pelaaja(kayttolittyma);
        pelaaja2 = new Pelaaja();
        pelaajanLaivat = luoUusiLaivasto();
        pelaaja2nLaivat = luoUusiLaivasto();
        pelaaja.sijoitaLaivatLaudalle(pelaaja2nLaivat);
        pelinvaihe = Pelinvaihe.LAIVOJENSIJOITTELU;
        tekoaly = new Tekoaly(pelaaja.getVastustajanPelilauta());
        hiirenSijaintiX = 0;
        hiirenSijaintiY = 0;
        asetettavaLaivaMeneeAlaspain = true;
    }

    public void silmukka() {
        edellinenSilmukka = System.currentTimeMillis();
        while (true) {
            if (pelionLoppunut()) {
                pelinvaihe = Pelinvaihe.PELILOPPU;
                kayttolittyma.uudelleenPiirra();
                break;
            }
            if (pelinvaihe == Pelinvaihe.LAIVOJENSIJOITTELU && annaAsetettavaLaiva() == null) {
                vaihdaVuoro();
            }
            if (pelinvaihe == Pelinvaihe.PELAAJA2NVUORO) {
                try{
                    kayttolittyma.uudelleenPiirra();
                    Thread.sleep(437);
                } catch(InterruptedException e){
                    System.err.println("Ken kehtaa häiritä nukkuvan unta?");
                }
                if (!tekoaly.siirra()) {
                    vaihdaVuoro();
                }
            }
            kayttolittyma.uudelleenPiirra();
            nuku(30);
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
            palautettava += "Peli on loppunut.";
            if (pelaajaOnVoittanut()) {
                palautettava += " Voitit pelin.";
            } else {
                palautettava += " Hävisit pelin";
            }

        }
        if(pelinvaihe == Pelinvaihe.PELAAJA2NVUORO){
            palautettava += "Odota, kun vastustaja tekee siirtonsa.";
        }
        return palautettava + " " + fps + "fps";
    }

    public void setKayttolittyma(Kayttolittyma kayttolittyma) {
        this.kayttolittyma = kayttolittyma;
    }

    /**
     * Nukkuu sopivan verran, jottei pelin pääsilmukka tulisi aivan hulluksi
     *
     * @param haluttuFPS Määrittää ihanteellisen kierrosmaaran sekunnissa
     */
    private void nuku(int haluttuFPS) {
        int ihanteellinenPaivitysaika = (int) (1.0 / haluttuFPS * 1000);
        long aikaNyt = System.currentTimeMillis();
        long ero = aikaNyt - edellinenSilmukka;
        try {
            long nukuttavaika = (long) ihanteellinenPaivitysaika - ero;
            if (nukuttavaika > 0) {
                Thread.sleep(nukuttavaika);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Logiikka.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ((System.currentTimeMillis() - edellinenSilmukka) != 0) {
            fps = 1000 / (System.currentTimeMillis() - edellinenSilmukka);
        }
        this.edellinenSilmukka = aikaNyt;
    }

    public void vaihdaVuoro() {
        if (pelinvaihe == Pelinvaihe.LAIVOJENSIJOITTELU || pelinvaihe == Pelinvaihe.PELAAJA2NVUORO) {
            pelinvaihe = Pelinvaihe.PELAAJA1NVUORO;
        } else {
            pelinvaihe = Pelinvaihe.PELAAJA2NVUORO;
        }
    }

    /**
     * Käsittelee pelaajan siirron. Jos pelaaja osui laivaan, vuoro ei vaihdu.
     *
     * @param x
     * @param y
     * @param pelilauta
     */
    public void pelaaja1nSiirto(int x, int y, Pelilauta pelilauta) {
        if (!pelilauta.ruutuaOnJoPommitettu(x, y)) {
            if (!pelilauta.pommita(x, y)) {
                vaihdaVuoro();
            }
        }
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public void paivitaHiirenSijainti(int x, int y) {
        this.hiirenSijaintiX = x;
        this.hiirenSijaintiY = y;
    }

    public int getHiirenSijaintiX() {
        return hiirenSijaintiX;
    }

    public int getHiirenSijaintiY() {
        return hiirenSijaintiY;
    }

    public void vaihdaAsetettavanLaivanSuuntaa() {
        asetettavaLaivaMeneeAlaspain = !asetettavaLaivaMeneeAlaspain;
    }

    public Boolean asetettavaLaivaMeneeAlaspain() {
        return asetettavaLaivaMeneeAlaspain;
    }

    public boolean pelaajaOnVoittanut() {
        int laskuri = 0;
        for (Laiva laiva : pelaaja2nLaivat) {
            if (laiva.onTuhottu()) {
                laskuri++;
            }
        }
        return laskuri == pelaaja2nLaivat.size();
    }

    public boolean tietokoneOnVoittanut() {
        int laskuri = 0;
        for (Laiva laiva : pelaajanLaivat) {
            if (laiva.onTuhottu()) {
                laskuri++;
            }
        }
        return laskuri == pelaajanLaivat.size();
    }

    private boolean pelionLoppunut() {
        return pelaajaOnVoittanut() || tietokoneOnVoittanut();
    }
}
