package logiikka;

import java.util.Random;
import objektit.Pelilauta;

class Tekoaly {

    private Pelilauta pelilauta;
    private Boolean laivanEtsintaKaynnissa;
    private int viimeinenOsumaX, viimeinenOsumaY;

    Tekoaly(Pelilauta vastustajanPelilauta) {
        this.pelilauta = vastustajanPelilauta;
        this.laivanEtsintaKaynnissa = false;
    }

    void siirra() {
        if (laivanEtsintaKaynnissa) {
            etsiLaivaa();
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
            laivanEtsintaKaynnissa = true;
            this.viimeinenOsumaX = x;
            this.viimeinenOsumaY = y;
        }
    }

    private void etsiLaivaa() {
        if (laivaKulkeeVarmastiPystysuunnassa()) {
            etsiLaivaaPystysuunnassa();
        }
        if (laivaKulkeeVarmastiVaakasuunnassa()) {
            etsiLaivaaVaakasuunnassa();
        }
        etsiLaivaaPystysuunnassa();
    }

    private boolean laivaKulkeeVarmastiPystysuunnassa() {
        if (pelilauta.ruudussaOnOsuma(viimeinenOsumaX, viimeinenOsumaY - 1)) {
            return true;
        }
        if (pelilauta.ruudussaOnOsuma(viimeinenOsumaX, viimeinenOsumaY + 1)) {
            return true;
        }
        return false;
    }

    private boolean laivaKulkeeVarmastiVaakasuunnassa() {
        if (pelilauta.ruudussaOnOsuma(viimeinenOsumaX - 1, viimeinenOsumaY)) {
            return true;
        }
        if (pelilauta.ruudussaOnOsuma(viimeinenOsumaX + 1, viimeinenOsumaY)) {
            return true;
        }
        return false;
    }

    private void etsiLaivaaPystysuunnassa() {
        int x = viimeinenOsumaX, y = viimeinenOsumaY;
        etsiLaivaaYlosPain();
    }

    private void etsiLaivaaVaakasuunnassa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return true, jos sopiva ruutu löydetty, false, jos päädytty umpikujaan
     */
    private boolean etsiLaivaaYlosPain() {
        if (viimeinenOsumaY == 0) {
            return false;
        }
        int x = viimeinenOsumaX, y = viimeinenOsumaY - 1;
        if(!ruutuaKannattaaPommittaa(x, y)){
            return false;
        }
        if(pelilauta.pommita(x, y) && pelilauta.getRuutu(x, y).getLaiva().onTuhottu()){
            laivanEtsintaKaynnissa = false;
        }
        return true;
    }

    private boolean ruutuaKannattaaPommittaa(int x, int y) {
        return !pelilauta.ruutuaOnJoPommitettu(x, y);
    }
}
