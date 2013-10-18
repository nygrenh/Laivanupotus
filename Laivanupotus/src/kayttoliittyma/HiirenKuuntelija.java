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
    private Logiikka logiikka;
    private int hiirtaPainettuX, hiirtaPainettuY;

    public HiirenKuuntelija(Logiikka logiikka) {
        this.pelilauta = logiikka.getPelilauta();
        this.pelilauta2 = logiikka.getVastustajanPelilauta();
        this.logiikka = logiikka;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (logiikka.getPelinvaihe() == Pelinvaihe.PELAAJA1NVUORO) {
            pommita(me);
        }
        if (logiikka.getPelinvaihe() == Pelinvaihe.LAIVOJENSIJOITTELU) {
            asetaLaiva(me);
        }
    }

    /**
     * Tätä metodia käytetään auttamaan tapauksessa, jossa käyttäjä on aikonut
     * klikata ruutua, mutta vahingossa liikuttanut hiirtään samalla
     *
     * @param me
     */
    @Override
    public void mousePressed(MouseEvent me) {
        hiirtaPainettuX = me.getX();
        hiirtaPainettuY = me.getY();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (hiirtaEiOleKlikattu(me) && hiiriEiOleLiikkunutPaljon(me)) {
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
        if (me.getX() < 0 || me.getY() < 0) {
            return;
        }
        int x = me.getX() / 30;
        int y = me.getY() / 30;
        if (pelilauta.koordinaatitOnPelilaudanRajojenSisalla(x, y)) {
            logiikka.pelaaja1nSiirto(x, y, pelilauta);
        }
    }

    /**
     * Asettaa laivan pelilaudalle. Tämä ominaisuus tullaan ulkoistamaan
     * logiikalle, jotta tämä luokka voi keskittyä hiiren kuunteluun
     *
     * @param me
     */
    private void asetaLaiva(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON3) {
            logiikka.vaihdaAsetettavanLaivanSuuntaa();
            return;
        }
        me.translatePoint(-((logiikka.getPelilaudanKoko() + 2) * 30), -30);
        int x = me.getX() / 30;
        int y = me.getY() / 30;
        Laiva asetettavaLaiva = logiikka.annaAsetettavaLaiva();
        if (asetettavaLaiva != null) {
            pelilauta2.asetaLaivaLaudalle(asetettavaLaiva, x, y, logiikka.asetettavaLaivaMeneeAlaspain());
        }
    }

    private boolean hiiriEiOleLiikkunutPaljon(MouseEvent me) {
        boolean hiiriEiOleLiikkunutPaljon = Math.abs(hiirtaPainettuX - me.getX()) < 3 && Math.abs(hiirtaPainettuY - me.getY()) < 3;
        boolean hiiriOnYhaSamassaRuudussa = hiirtaPainettuX / 30 == me.getX() / 30 && hiirtaPainettuY / 30 == me.getY() / 30;
        return hiiriEiOleLiikkunutPaljon && hiiriOnYhaSamassaRuudussa;
    }

    private boolean hiirtaEiOleKlikattu(MouseEvent me) {
        return hiirtaPainettuX != me.getX() && hiirtaPainettuY != me.getY();
    }
}
