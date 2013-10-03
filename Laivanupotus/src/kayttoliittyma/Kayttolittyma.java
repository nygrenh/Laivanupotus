package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import logiikka.Logiikka;
import objektit.Pelilauta;

/**
 * Vastuussa ikkunan luomisesta ja sen komponenttien luomisesta
 */
public class Kayttolittyma implements Runnable {

    private JFrame frame;
    private Pelilauta pelilauta, pelilauta2;
    private Logiikka logiikka;

    public void setLogiikka(Logiikka logiikka) {
        this.logiikka = logiikka;
    }

    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(700, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        Piirtoalusta piirtoalusta = new Piirtoalusta(pelilauta, pelilauta2, logiikka);
        container.add(piirtoalusta);
        piirtoalusta.addMouseListener(new HiirenKuuntelija(pelilauta, pelilauta2, this, logiikka));
    }

    public void setPelilautat(Pelilauta pelilauta, Pelilauta pelilauta2) {
        this.pelilauta = pelilauta;
        this.pelilauta2 = pelilauta2;
    }

    public void uudelleenPiirra() {
        this.frame.repaint();
    }
}
