package logiikka.tekoaly;

import java.util.Random;
import objektit.Pelilauta;

public class Tekoaly {

    private Pelilauta pelilauta;
    private Boolean laivanEtsintaKaynnissa, etsittavaLaivaOnVaakatasossa, etsittavaLaivaOnPystytasossa, siirtoOnTehty;
    private int viimeinenOsumaX, viimeinenOsumaY, alkuperainenOsumaX, alkuperainenOsumaY;

    public Tekoaly(Pelilauta vastustajanPelilauta) {
        this.pelilauta = vastustajanPelilauta;
        this.laivanEtsintaKaynnissa = false;
    }

    public void siirra() {
        if (laivanEtsintaKaynnissa) {
            etsiLaivaa();
            siirtoOnTehty = false;
        } else {
            pommitaSatunnaistaRuutua();
        }

    }

    private void pommitaSatunnaistaRuutua() {
        Random r = new Random();
        int x, y;
        do {
            x = r.nextInt(pelilauta.getKoko());
            y = r.nextInt(pelilauta.getKoko());
        } while (pelilauta.ruutuaOnJoPommitettu(x, y));
        if (pelilauta.pommita(x, y)) {
            aloitaLaivanEtsinta();
            this.viimeinenOsumaX = x;
            this.viimeinenOsumaY = y;
            this.alkuperainenOsumaX = x;
            this.alkuperainenOsumaY = y;
        }
    }

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

    private boolean etsiLaivaaPystysuunnassa() {
        if (laivaaVoiEtsiaYlosPain()) {
            if (!etsiLaivaaYlosPain()) {
                viimeinenOsumaX = alkuperainenOsumaX;
                viimeinenOsumaY = alkuperainenOsumaY;
            }
        } else if (laivaaVoiEtsiaAlasPain()) {
            etsiLaivaaAlaspain();
        } else {
            etsittavaLaivaOnVaakatasossa = true;
            return false;
        }
        return true;
    }

    private boolean etsiLaivaaVaakasuunnassa() {
        if (laivaaVoiEtsiaVasemmalle()) {
            if (!etsiLaivaaVasemmalle()) {
                viimeinenOsumaX = alkuperainenOsumaX;
                viimeinenOsumaY = alkuperainenOsumaY;
            }
        } else if (laivaaVoiEtsiaOikealle()) {
            etsiLaivaaOikealle();
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @return true, jos sopiva ruutu löydetty, false, jos päädytty umpikujaan
     */
    private boolean etsiLaivaaYlosPain() {
        int x = viimeinenOsumaX, y = viimeinenOsumaY - 1;
        return kokeilePommittaa(x, y);
    }

    private boolean etsiLaivaaAlaspain() {
        int x = viimeinenOsumaX, y = viimeinenOsumaY + 1;
        return kokeilePommittaa(x, y);
    }

    private boolean etsiLaivaaVasemmalle() {
        int x = viimeinenOsumaX - 1, y = viimeinenOsumaY;
        return kokeilePommittaa(x, y);
    }

    private boolean etsiLaivaaOikealle() {
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
            viimeinenOsumaX = x;
            viimeinenOsumaY = y;
            if (pelilauta.getRuutu(x, y).getLaiva().onTuhottu()) {
                laivanEtsintaKaynnissa = false;
                etsittavaLaivaOnPystytasossa = false;
                etsittavaLaivaOnVaakatasossa = false;
            }
        } else {
            return false;
        }
        return true;
    }

    private void aloitaLaivanEtsinta() {
        laivanEtsintaKaynnissa = true;
        etsittavaLaivaOnPystytasossa = false;
        etsittavaLaivaOnVaakatasossa = false;
    }

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
}
