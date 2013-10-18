package logiikka;

import logiikka.tekoaly.Tekoaly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import objektit.Laiva;
import kayttoliittyma.Kayttolittyma;
import objektit.Pelilauta;
import kayttoliittyma.UudelleenPiirrettava;
import kayttoliittyma.Valinnat;

/**
 * Huolehtii pelin kulusta
 */
public class Logiikka {

    public static void uusiPeli(PelinArgumentit argumentit) {
        Kayttolittyma kayttolittyma = new Kayttolittyma();
        Logiikka logiikka = new Logiikka(kayttolittyma, argumentit);
        kayttolittyma.setLogiikka(logiikka);
        kayttolittyma.run();
        logiikka.silmukka();
    }

    private Collection<Laiva> pelaajanLaivat, pelaaja2nLaivat;
    private Pelinvaihe pelinvaihe;
    private UudelleenPiirrettava kayttolittyma;
    private long edellinenSilmukka;
    private Tekoaly tekoaly;
    private int hiirenSijaintiX, hiirenSijaintiY;
    private Boolean asetettavaLaivaMeneeAlaspain;
    private Pelilauta pelilauta, vastustajanPelilauta;
    private int pelilaudanKoko;

    /**
     *
     * @param kayttolittyma Ohjelman käyttöliittymä, jota voidaan piirtää
     * uudelleen
     */
    public Logiikka(UudelleenPiirrettava kayttolittyma, PelinArgumentit argumentit) {
        this.pelilaudanKoko = argumentit.getPelilaudanKoko();
        alustaPelilaudat(argumentit);
        this.kayttolittyma = kayttolittyma;
        pelinvaihe = Pelinvaihe.LAIVOJEN_SIJOITTELU;
        tekoaly = new Tekoaly(vastustajanPelilauta, pelaajanLaivat, argumentit.vaikeustaso);
        hiirenSijaintiX = 0;
        hiirenSijaintiY = 0;
        asetettavaLaivaMeneeAlaspain = true;
    }

    /**
     * Varmistaa, että käyttöliittymä piirretään uudelleen tasaisin väliajoin,
     * ja vastaa, että tietokone tekee omat siirtonsa
     */
    public void silmukka() {
        edellinenSilmukka = System.currentTimeMillis();
        while (true) {
            if (pelionLoppunut()) {
                pelinvaihe = Pelinvaihe.PELI_LOPPU;
                kayttolittyma.uudelleenPiirra();
                break;
            }
            if (pelinvaihe == Pelinvaihe.LAIVOJEN_SIJOITTELU && annaAsetettavaLaiva() == null) {
                vaihdaVuoro();
            }
            if (pelinvaihe == Pelinvaihe.PELAAJA_2_VUORO) {
                odotaHetki();
                if (!tekoaly.siirra()) {
                    vaihdaVuoro();
                }
            }
            kayttolittyma.uudelleenPiirra();
            nuku(30);
        }
    }



    /**
     * Palauttaa pelaajan seuraavan pelilaudalle asetettavan laivan.
     *
     * @return Laiva, jos asetettavia laivoja on jäljellä, muuten null
     */
    public Laiva annaAsetettavaLaiva() {
        for (Laiva laiva : pelaajanLaivat) {
            if (laiva.onSijoitettu()) {
                continue;
            }
            return laiva;
        }
        return null;
    }



    public Pelinvaihe getPelinvaihe() {
        return pelinvaihe;
    }

    /**
     * Palauttaa viestin, joka näytetään pelaajalle
     *
     * @return
     */
    public String getViesti() {
        String palautettava = "";
        if (pelinvaihe == Pelinvaihe.LAIVOJEN_SIJOITTELU && annaAsetettavaLaiva() != null) {
            palautettava += "Aseta " + annaAsetettavaLaiva().toString().toLowerCase() + " oikeanpuoleiseen pelilautaan. Hiiren oikea näppäin kääntää laivan suuntaa.";
        }
        if (pelinvaihe == Pelinvaihe.PELAAJA_1_VUORO) {
            palautettava += "Pommita vasemmanpuoleista pelilautaa.";
        }
        if (pelinvaihe == Pelinvaihe.PELI_LOPPU) {
            palautettava += "Peli on loppunut.";
            if (pelaajaOnVoittanut()) {
                palautettava += " Voitit pelin.";
            } else {
                palautettava += " Hävisit pelin";
            }

        }
        if (pelinvaihe == Pelinvaihe.PELAAJA_2_VUORO) {
            palautettava += "Odota, kun vastustaja tekee siirtonsa.";
        }
        return palautettava;
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
        this.edellinenSilmukka = aikaNyt;
    }

    /**
     * Vaihtaa vuoroa. Jos peli on vilä laivojen asettelussa, metodi vaihtaa
     * vuoron käyttäjälle.
     */
    public void vaihdaVuoro() {
        if (pelinvaihe == Pelinvaihe.LAIVOJEN_SIJOITTELU || pelinvaihe == Pelinvaihe.PELAAJA_2_VUORO) {
            pelinvaihe = Pelinvaihe.PELAAJA_1_VUORO;
        } else {
            pelinvaihe = Pelinvaihe.PELAAJA_2_VUORO;
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

    /**
     * Kertoo, onko pelaaja voittanut
     *
     * @return True, jos kaikki tietokoneen laivat ovat tuhottu. False, jos
     * kaikki tietokoneen laivat eivät ole tuhottu
     */
    public boolean pelaajaOnVoittanut() {
        int laskuri = 0;
        for (Laiva laiva : pelaaja2nLaivat) {
            if (laiva.onTuhottu()) {
                laskuri++;
            }
        }
        return laskuri == pelaaja2nLaivat.size();
    }

    /**
     * Kertoo, onko tietokone voittanut
     *
     * @return True, jos kaikki pelaajan laivat ovat tuhottu. False, jos kaikki
     * pelaajan laivat eivät ole tuhottu
     */
    public boolean tietokoneOnVoittanut() {
        int laskuri = 0;
        for (Laiva laiva : pelaajanLaivat) {
            if (laiva.onTuhottu()) {
                laskuri++;
            }
        }
        return laskuri == pelaajanLaivat.size();
    }

    /**
     * Kertoo, onko peli päättynyt
     *
     * @return True, jos joku on voittanut. False, jos kukaan ei ole voittanut.
     */
    private boolean pelionLoppunut() {
        return pelaajaOnVoittanut() || tietokoneOnVoittanut();
    }

    public void sijoitaLaivatLaudalle(Collection<Laiva> laivat) {
        Random random = new Random();
        for (Laiva laiva : laivat) {
            int lahtoKoordinaattiX, lahtoKoordinaattiY;
            Boolean suunta;
            do {
                lahtoKoordinaattiX = random.nextInt(pelilauta.getKoko());
                lahtoKoordinaattiY = random.nextInt(pelilauta.getKoko());
                suunta = random.nextBoolean();
            } while (!pelilauta.asetaLaivaLaudalle(laiva, lahtoKoordinaattiX, lahtoKoordinaattiY, suunta));
        }
    }

    public Pelilauta getVastustajanPelilauta() {
        return vastustajanPelilauta;
    }

    public Pelilauta getPelilauta() {
        return pelilauta;
    }

    /**
     * Luo pelaajan ja tietokoneen pelilaudat ja laivat ja sijoittaa tietokoneen
     * laivat pelilaudalle
     */
    private void alustaPelilaudat(PelinArgumentit argumentit) {
        this.pelilauta = new Pelilauta(pelilaudanKoko);
        this.vastustajanPelilauta = new Pelilauta(pelilaudanKoko);
        pelaajanLaivat = argumentit.luoUusiLaivasto();
        pelaaja2nLaivat = argumentit.luoUusiLaivasto();
        vastustajanPelilauta.vaihdaLaivoijenNakyvyytta();
        sijoitaLaivatLaudalle(pelaaja2nLaivat);
    }

    /**
     * Odottaa pienen hetken. Tällä metodilla varmistetaan, että tietokone ei
     * tee siirtojaan liian nopeasti.
     */
    private void odotaHetki() {
        try {
            kayttolittyma.uudelleenPiirra();
            Thread.sleep(437);
        } catch (InterruptedException e) {
            System.err.println("Ken kehtaa häiritä nukkuvan unta?");
        }
    }

    public int getPelilaudanKoko() {
        return pelilaudanKoko;
    }
}
