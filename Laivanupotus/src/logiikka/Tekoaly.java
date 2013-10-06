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
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void etsiLaivaaVaakasuunnassa() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
