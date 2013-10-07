
package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import logiikka.Logiikka;
import objektit.Pelilauta;

class HiirenLikkeenKuuntelija implements MouseMotionListener {
    private Pelilauta pelilauta, pelilauta2;
    private Logiikka logiikka;

    public HiirenLikkeenKuuntelija(Logiikka logiikka) {
        this.pelilauta = logiikka.getPelilauta();
        this.pelilauta2 = logiikka.getVastustajanPelilauta();
        this.logiikka = logiikka;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        logiikka.paivitaHiirenSijainti(e.getX(), e.getY());
    }
    
}
