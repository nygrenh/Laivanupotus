package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import objektit.Pelilauta;

class HiirenKuuntelija implements MouseListener {

    private Pelilauta pelilauta;

    public HiirenKuuntelija(Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = (me.getX() - 30)/30;
        int y = (me.getY() - 30)/30;
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
