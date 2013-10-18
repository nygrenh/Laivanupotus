package logiikka.tekoaly;

import java.awt.Point;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import objektit.Laiva;
import objektit.Pelilauta;

public class Tekoaly {

    private Pelilauta pelilauta;
    private Boolean laivanEtsintaKaynnissa, etsittavaLaivaOnVaakatasossa, etsittavaLaivaOnPystytasossa, osuikoLaivaan;
    private int viimeinenOsumaX, viimeinenOsumaY, alkuperainenOsumaX, alkuperainenOsumaY;
    private TodennakoisyysKartta todennakoisyysKartta;

    public Tekoaly(Pelilauta vastustajanPelilauta, Collection<Laiva> laivasto) {
        this.pelilauta = vastustajanPelilauta;
        todennakoisyysKartta = new TodennakoisyysKartta(pelilauta, laivasto);
        this.laivanEtsintaKaynnissa = false;
    }

    /**
     *
     * @return osuiko siirto laivaan
     */
    public boolean siirra() {
        if (laivanEtsintaKaynnissa) {
            etsiLaivaa();
        } else {
            pommitaTodennakoistaRuutua();
            //pommitaSatunnaistaRuutua();
        }
        return osuikoLaivaan;
    }

    /**
     * Pommittaa satunnaista ruutua.
     */
    private void pommitaSatunnaistaRuutua() {
        Random r = new Random();
        int x, y;
        do {
            x = r.nextInt(pelilauta.getKoko());
            y = r.nextInt(pelilauta.getKoko());
        } while (pelilauta.ruutuaOnJoPommitettu(x, y));
        osuikoLaivaan = pelilauta.pommita(x, y);
        if (osuikoLaivaan && !pelilauta.getRuutu(x, y).getLaiva().onTuhottu()) {
            aloitaLaivanEtsinta();
            this.viimeinenOsumaX = x;
            this.viimeinenOsumaY = y;
            this.alkuperainenOsumaX = x;
            this.alkuperainenOsumaY = y;
        }
    }

    /**
     * Tätä metodia kutsutaan tilanteessa, jossa johonkin laivaan ollaan osuttu,
     * ja halutaan pommittaa laiva loppuun. Tämän takia metodi yritää pommitta
     * edellisen osuman viereisä ruutuja.
     */
    private void etsiLaivaa() {
        if (etsittavaLaivaOnPystytasossa) {
            etsiLaivaaPystysuunnassa();
            return;
        }
        if (etsittavaLaivaOnVaakatasossa) {
            etsiLaivaaVaakasuunnassa();
            return;
        }
        if (!etsiLaivaaPystysuunnassa()) {
            etsiLaivaaVaakasuunnassa();
        }

    }

    /**
     *
     * @return true, jos siirto tehtiin
     */
    private boolean etsiLaivaaPystysuunnassa() {
        if (etsiLaivaaYlosPain()) {
            if (osuikoLaivaan) {
                etsittavaLaivaOnPystytasossa = true;
            }
            return true;
        } else if (viimeinenOsumaY < alkuperainenOsumaY) {
            viimeinenOsumaY = alkuperainenOsumaY;
        }
        if (etsiLaivaaAlaspain()) {
            if (osuikoLaivaan) {
                etsittavaLaivaOnPystytasossa = true;
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @return true, jos siirto tehtiin
     */
    private boolean etsiLaivaaVaakasuunnassa() {
        if (etsiLaivaaVasemmalle()) {
            if (osuikoLaivaan) {
                etsittavaLaivaOnVaakatasossa = true;
            }
            return true;
        } else if (viimeinenOsumaX < alkuperainenOsumaX) {
            viimeinenOsumaX = alkuperainenOsumaX;
        }
        if (etsiLaivaaOikealle()) {
            if (osuikoLaivaan) {
                etsittavaLaivaOnVaakatasossa = true;
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @return true, jos sopiva ruutu löydetty, false, jos päädytty umpikujaan
     */
    private boolean etsiLaivaaYlosPain() {
        if (!laivaaVoiEtsiaYlosPain()) {
            return false;
        }
        int x = viimeinenOsumaX, y = viimeinenOsumaY - 1;
        return kokeilePommittaa(x, y);
    }

    /**
     *
     * @return true, jos sopiva ruutu löydetty, false, jos päädytty umpikujaan
     */
    private boolean etsiLaivaaAlaspain() {
        if (!laivaaVoiEtsiaAlasPain()) {
            return false;
        }
        int x = viimeinenOsumaX, y = viimeinenOsumaY + 1;
        return kokeilePommittaa(x, y);
    }

    /**
     *
     * @return true, jos sopiva ruutu löydetty, false, jos päädytty umpikujaan
     */
    private boolean etsiLaivaaVasemmalle() {
        if (!laivaaVoiEtsiaVasemmalle()) {
            return false;
        }
        int x = viimeinenOsumaX - 1, y = viimeinenOsumaY;
        return kokeilePommittaa(x, y);
    }

    /**
     *
     * @return true, jos sopiva ruutu löydetty, false, jos päädytty umpikujaan
     */
    private boolean etsiLaivaaOikealle() {
        if (!laivaaVoiEtsiaOikealle()) {
            return false;
        }
        int x = viimeinenOsumaX + 1, y = viimeinenOsumaY;
        return kokeilePommittaa(x, y);
    }

    /**
     *
     * Jos pommitus osuu laivaan, funktio paivittää viimeisen osuman
     * koordinaatit.
     *
     * @param x
     * @param y
     * @return Palauttaa false, jos jouduttiin umpikujaan, palauttaa true, jos
     * jotakin ruutua pommitettiin.
     */
    private boolean kokeilePommittaa(int x, int y) {
        if (pelilauta.ruutuaOnJoPommitettu(x, y)) {
            return false;
        }
        if (pelilauta.pommita(x, y)) {
            osuikoLaivaan = true;
            viimeinenOsumaX = x;
            viimeinenOsumaY = y;
            if (pelilauta.getRuutu(x, y).getLaiva().onTuhottu()) {
                laivanEtsintaKaynnissa = false;
                etsittavaLaivaOnPystytasossa = false;
                etsittavaLaivaOnVaakatasossa = false;
            }
        } else {
            osuikoLaivaan = false;
        }
        return true;
    }

    /**
     * Alustaa muuttujat siten, että laivojen etsintä toimii kunnolla. Kutsutaan
     * heti osuman jälkeen.
     */
    private void aloitaLaivanEtsinta() {
        laivanEtsintaKaynnissa = true;
        etsittavaLaivaOnPystytasossa = false;
        etsittavaLaivaOnVaakatasossa = false;
    }

    /**
     *
     * @return True, jos etsintäkohdan yläpuolella olevaa ruutua voidaan
     * pommittaa, muuten false
     */
    private boolean laivaaVoiEtsiaYlosPain() {
        if (viimeinenOsumaY == 0) {
            return false;
        }
        if (pelilauta.ruutuaOnJoPommitettu(viimeinenOsumaX, viimeinenOsumaY - 1)) {
            return false;
        }
        return true;
    }

    private boolean laivaaVoiEtsiaAlasPain() {
        if (viimeinenOsumaY == pelilauta.getKoko() - 1) {
            return false;
        }
        if (pelilauta.ruutuaOnJoPommitettu(viimeinenOsumaX, viimeinenOsumaY + 1)) {
            return false;
        }
        return true;
    }

    private boolean laivaaVoiEtsiaVasemmalle() {
        if (viimeinenOsumaX == 0) {
            return false;
        }
        if (pelilauta.ruutuaOnJoPommitettu(viimeinenOsumaX - 1, viimeinenOsumaY)) {
            return false;
        }
        return true;
    }

    private boolean laivaaVoiEtsiaOikealle() {
        if (viimeinenOsumaX == pelilauta.getKoko() - 1) {
            return false;
        }
        if (pelilauta.ruutuaOnJoPommitettu(viimeinenOsumaX + 1, viimeinenOsumaY)) {
            return false;
        }
        return true;
    }

    private void pommitaTodennakoistaRuutua() {
        Point iskukohde = todennakoisyysKartta.annaiskukohde();
        osuikoLaivaan = pelilauta.pommita(iskukohde.x, iskukohde.y);
        if (osuikoLaivaan && !pelilauta.getRuutu(iskukohde.x, iskukohde.y).getLaiva().onTuhottu()) {
            aloitaLaivanEtsinta();
            this.viimeinenOsumaX = iskukohde.x;
            this.viimeinenOsumaY = iskukohde.y;
            this.alkuperainenOsumaX = iskukohde.x;
            this.alkuperainenOsumaY = iskukohde.y;
        }
    }
}
