package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import objektit.Pelilauta;

public class Kayttolittyma implements Runnable{

    private JFrame frame;
    private Pelilauta pelilauta;
    
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
        Piirtoalusta piirtoalusta = new Piirtoalusta(pelilauta);
        container.add(piirtoalusta);
        piirtoalusta.addMouseListener(new HiirenKuuntelija(pelilauta, this));
    }

    public void setPelilauta(Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
    }

    public void uudelleenPiirra() {
        this.frame.repaint();
    }
    
}
