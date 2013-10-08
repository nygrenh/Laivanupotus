package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import logiikka.Logiikka;
import objektit.Pelilauta;

/**
 * Vastaa koko pelialueen piirtämisestä
 */
public class Piirtoalusta extends JPanel {

    private Pelilauta pelilauta, vastustajanPelilauta;
    private Logiikka logiikka;

    public Piirtoalusta(Logiikka logiikka) {
        super.setBackground(Color.WHITE);
        this.pelilauta = logiikka.getPelilauta();
        this.vastustajanPelilauta = logiikka.getVastustajanPelilauta();
        this.logiikka = logiikka;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paivitaViesti(graphics);
        PelilaudanPiirtoalusta pelilaudanPiirtoalusta = new PelilaudanPiirtoalusta(30, 30, pelilauta, logiikka);
        pelilaudanPiirtoalusta.paintComponent(graphics);
        PelilaudanPiirtoalusta pelilaudanPiirtoalusta2 = new PelilaudanPiirtoalusta(360, 30, vastustajanPelilauta, logiikka);
        pelilaudanPiirtoalusta2.paintComponent(graphics);
    }
    /**
     * Piirtää pelaajalle näytettävän ohjeviestin
     */
    private void paivitaViesti(Graphics graphics) {
        char[] viesti = logiikka.getViesti().toCharArray();
        graphics.drawChars(viesti, 0, viesti.length, 30, 19);
    }
}