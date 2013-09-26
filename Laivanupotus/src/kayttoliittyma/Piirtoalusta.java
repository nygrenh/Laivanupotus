package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import objektit.Pelilauta;

/**
 * Vastaa koko pelialueen piirtämisestä
 */
public class Piirtoalusta extends JPanel {
private Pelilauta pelilauta;

    public Piirtoalusta(Pelilauta pelilauta) {
        super.setBackground(Color.WHITE);
        this.pelilauta = pelilauta;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        PelilaudanPiirtoalusta pelilaudanPiirtoalusta = new PelilaudanPiirtoalusta(30, 30, pelilauta);
        pelilaudanPiirtoalusta.paintComponent(graphics);
    }
}