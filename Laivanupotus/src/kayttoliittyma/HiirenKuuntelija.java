package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import objektit.Pelilauta;

/**
 * Kuuntelee hiiren klikkauksia ja reagoi niihin
 */
public class HiirenKuuntelija implements MouseListener {

    private Pelilauta pelilauta;
    private Kayttolittyma kayttolittyma;

    public HiirenKuuntelija(Pelilauta pelilauta, Kayttolittyma kayttolittyma) {
        this.pelilauta = pelilauta;
        this.kayttolittyma = kayttolittyma;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = (me.getX() - 30)/30;
        int y = (me.getY() - 30)/30;
        if(pelilauta.koordinaatitOnPelilaudanRajojenSisalla(x, y)){
            if(pelilauta.pommita(x, y)){
                //
            }
        }
        kayttolittyma.uudelleenPiirra();
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
