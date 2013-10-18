package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import logiikka.Logiikka;


class HiirenLikkeenKuuntelija implements MouseMotionListener {

    private Logiikka logiikka;

    public HiirenLikkeenKuuntelija(Logiikka logiikka) {
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
