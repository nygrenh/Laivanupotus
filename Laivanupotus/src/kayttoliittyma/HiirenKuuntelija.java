package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import logiikka.Logiikka;
import logiikka.Pelinvaihe;
import objektit.Laiva;
import objektit.Pelilauta;

/**
 * Kuuntelee hiiren klikkauksia ja reagoi niihin
 */
public class HiirenKuuntelija implements MouseListener {

    private Pelilauta pelilauta, pelilauta2;
    private Kayttolittyma kayttolittyma;
    private Boolean asetettavaLaivaMeneeAlaspain;
    private Logiikka logiikka;
    private int hiirtaPainettuX, hiirtaPainettuY;

    public HiirenKuuntelija(Pelilauta pelilauta, Pelilauta pelilauta2, Kayttolittyma kayttolittyma, Logiikka logiikka) {
        this.pelilauta = pelilauta;
        this.pelilauta2 = pelilauta2;
        this.kayttolittyma = kayttolittyma;
        asetettavaLaivaMeneeAlaspain = true;
        this.logiikka = logiikka;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(logiikka.getPelinvaihe()== Pelinvaihe.PELAAJA1NVUORO){
            pommita(me);
        }
        if(logiikka.getPelinvaihe() == Pelinvaihe.LAIVOJENSIJOITTELU){
            asetaLaiva(me);
        }
    }

    /**
     * Tätä metodia käytetään auttamaan tapauksessa, jossa käyttäjä on aikonut klikata ruutua, mutta vahingossa liikuttanut
     * hiirtään samalla
     * @param me 
     */
    @Override
    public void mousePressed(MouseEvent me) {
        hiirtaPainettuX = me.getX();
        hiirtaPainettuY = me.getY();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(hiirtaEiOleKlikattu(me) && hiiriEiOleLiikkunutPaljon(me)){
            mouseClicked(me);
        }
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
        }
    }

    private boolean hiiriEiOleLiikkunutPaljon(MouseEvent me) {
        boolean hiiriEiOleLiikkunutPaljon = Math.abs(hiirtaPainettuX-me.getX())<3 && Math.abs(hiirtaPainettuY-me.getY())<3;
        boolean hiiriOnYhaSamassaRuudussa = hiirtaPainettuX/30==me.getX()/30 && hiirtaPainettuY/30==me.getY()/30;
        return hiiriEiOleLiikkunutPaljon && hiiriOnYhaSamassaRuudussa;
    }

    private boolean hiirtaEiOleKlikattu(MouseEvent me) {
        return hiirtaPainettuX != me.getX() && hiirtaPainettuY != me.getY();
    }
   
}
