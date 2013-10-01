package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import objektit.Laiva;
import objektit.Pelilauta;

/**
 * Kuuntelee hiiren klikkauksia ja reagoi niihin
 */
public class HiirenKuuntelija implements MouseListener {

    private Pelilauta pelilauta, pelilauta2;
    private Kayttolittyma kayttolittyma;
    private Boolean laivojenAsetteluOnKaynnissa;
    private Boolean asetettavaLaivaMeneeAlaspain;

    public HiirenKuuntelija(Pelilauta pelilauta, Pelilauta pelilauta2, Kayttolittyma kayttolittyma) {
        this.pelilauta = pelilauta;
        this.pelilauta2 = pelilauta2;
        this.kayttolittyma = kayttolittyma;
        laivojenAsetteluOnKaynnissa = false;
        asetettavaLaivaMeneeAlaspain = true;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(!laivojenAsetteluOnKaynnissa){
            pommita(me);
        } else{
            asetaLaiva(me);
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
    

    private void pommita(MouseEvent me) {
        me.translatePoint(-30, -30);
        int x = me.getX()/30;
        int y = me.getY()/30;
        if(pelilauta.koordinaatitOnPelilaudanRajojenSisalla(x, y)){
            if(pelilauta.pommita(x, y)){
                //
            }
        }
    }

    private void asetaLaiva(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON2){
            asetettavaLaivaMeneeAlaspain = !asetettavaLaivaMeneeAlaspain;
            return;
        }
        me.translatePoint(-360, -30);
        int x = me.getX()/30;
        int y = me.getY()/30;
        Laiva asetettavaLaiva = pelilauta2.annaAsetettavaLaiva();
        pelilauta2.asetaLaivaLaudalle(asetettavaLaiva, x, y, asetettavaLaivaMeneeAlaspain);
    }
    
}
