package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import logiikka.Logiikka;

/**
 * Vastuussa ikkunan luomisesta ja sen komponenttien luomisesta
 */
public class Kayttolittyma implements Runnable {

    private JFrame frame;
    private Logiikka logiikka;

    public void setLogiikka(Logiikka logiikka) {
        this.logiikka = logiikka;
    }

    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(690, 360));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        Piirtoalusta piirtoalusta = new Piirtoalusta(logiikka);
        container.add(piirtoalusta);
        piirtoalusta.addMouseListener(new HiirenKuuntelija(logiikka));
        piirtoalusta.addMouseMotionListener(new HiirenLikkeenKuuntelija(logiikka));
    }

    public void uudelleenPiirra() {
        this.frame.repaint();
    }
}
