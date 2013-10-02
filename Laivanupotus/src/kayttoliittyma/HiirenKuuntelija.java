package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import logiikka.Logiikka;
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
    private Logiikka logiikka;

    public HiirenKuuntelija(Pelilauta pelilauta, Pelilauta pelilauta2, Kayttolittyma kayttolittyma, Logiikka logiikka) {
        this.pelilauta = pelilauta;
        this.pelilauta2 = pelilauta2;
        this.kayttolittyma = kayttolittyma;
        laivojenAsetteluOnKaynnissa = true;
        asetettavaLaivaMeneeAlaspain = true;
        this.logiikka = logiikka;
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
        Laiva asetettavaLaiva = logiikka.annaAsetettavaLaiva();
        if(asetettavaLaiva!=null){
            pelilauta2.asetaLaivaLaudalle(asetettavaLaiva, x, y, asetettavaLaivaMeneeAlaspain); 
        } else{
            this.laivojenAsetteluOnKaynnissa = false;
        }

    }
    
}
