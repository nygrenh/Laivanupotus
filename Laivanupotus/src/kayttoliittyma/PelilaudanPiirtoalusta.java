package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import logiikka.Logiikka;
import logiikka.Pelinvaihe;
import objektit.Laiva;
import objektit.Pelilauta;

/**
 * Vastaa yhden pelilaudan piirtämisestä
 */
public class PelilaudanPiirtoalusta extends JPanel {

    private int x, y;
    private Pelilauta pelilauta;
    private Logiikka logiikka;

    public PelilaudanPiirtoalusta(int x, int y, Pelilauta pelilauta, Logiikka logiikka) {
        this.x = x;
        this.y = y;
        this.pelilauta = pelilauta;
        this.logiikka = logiikka;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.translate(this.x, this.y);
        for (int i = 0; i < pelilauta.getKoko(); i++) {
            for (int j = 0; j < pelilauta.getKoko(); j++) {
                int x = i * 30;
                int y = j * 30;
                piirraRuudukko(g, x, y);
                piirraObjektit(g, x, y);
            }
            piirraKorostus(g);
        }
        g.translate(-this.x, -this.y);
    }

    private void piirraRuudukko(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 29, 29);
    }

    private void piirraObjektit(Graphics g, int x, int y) {
        int ruudunXKoordinaatti = x / 30;
        int ruudunYKoordinaatti = y / 30;
        if (pelilauta.ruutuaOnJoPommitettu(ruudunXKoordinaatti, ruudunYKoordinaatti)) {
            if (ruudussaOnLaiva(ruudunXKoordinaatti, ruudunYKoordinaatti)) {
                if (ruudussaOlevaLaivaOnTuhottu(ruudunXKoordinaatti, ruudunYKoordinaatti)) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(Color.yellow);
                }
            } else {
                g.setColor(Color.RED);
            }
            g.fillRect(x, y, 29, 29);
        } else if (pelilauta.laivatOnNakyvilla() && ruudussaOnLaiva(ruudunXKoordinaatti, ruudunYKoordinaatti)) {
            piirraNakymattomatLaivat(g, x, y);
        }
    }

    private boolean ruudussaOnLaiva(int ruudunXKoordinaatti, int ruudunYKoordinaatti) {
        return pelilauta.getRuutu(ruudunXKoordinaatti, ruudunYKoordinaatti).getLaiva() != null;
    }

    private boolean ruudussaOlevaLaivaOnTuhottu(int ruudunXKoordinaatti, int ruudunYKoordinaatti) {
        return pelilauta.getRuutu(ruudunXKoordinaatti, ruudunYKoordinaatti).getLaiva().onTuhottu();
    }

    private void piirraNakymattomatLaivat(Graphics g, int ruudunXKoordinaatti, int ruudunYKoordinaatti) {
        g.setColor(Color.GRAY);
        g.fillRect(ruudunXKoordinaatti, ruudunYKoordinaatti, 29, 29);
    }

    private void piirraKorostus(Graphics g) {
        int x = logiikka.getHiirenSijaintiX() - this.x;
        int y = logiikka.getHiirenSijaintiY() - this.y;
        if (x < 0 || y < 0) {
            return;
        }
        x /= 30;
        y /= 30;
        if (pelilauta.laivatOnNakyvilla() && logiikka.getPelinvaihe() == Pelinvaihe.LAIVOJENSIJOITTELU) {
            piirraHaamulaiva(g, x, y);
        }
    }

    private void piirraHaamulaiva(Graphics g, int alkuX, int alkuY) {
        Laiva seuraavaLaiva = logiikka.annaAsetettavaLaiva();
        int loppuX, loppuY;
        if (logiikka.asetettavaLaivaMeneeAlaspain()) {
            loppuX = alkuX;
            loppuY = alkuY + seuraavaLaiva.getPituus() - 1;
        } else {
            loppuX = alkuX + seuraavaLaiva.getPituus() - 1;
            loppuY = alkuY;
        }
        for (int x = alkuX; x <= loppuX; x++) {
            for (int y = alkuY; y <= loppuY; y++) {
                if (pelilauta.laivanVoiAsettaaTahan(seuraavaLaiva, alkuX, alkuY, logiikka.asetettavaLaivaMeneeAlaspain())) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.red);
                }
                g.drawRect(x * 30 - 1, y * 30 - 1, 30, 30);
            }
        }


    }
}
