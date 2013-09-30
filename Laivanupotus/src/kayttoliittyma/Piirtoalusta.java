package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import objektit.Pelilauta;

/**
 * Vastaa koko pelialueen piirtämisestä
 */
public class Piirtoalusta extends JPanel {
private Pelilauta pelilauta, vastustajanPelilauta;
private char[] viesti;

    public Piirtoalusta(Pelilauta pelilauta, Pelilauta vastustajanPelilauta) {
        super.setBackground(Color.WHITE);
        this.pelilauta = pelilauta;
        this.vastustajanPelilauta = vastustajanPelilauta;
        viesti ="Hello".toCharArray();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawChars(viesti, 0, viesti.length, 15, 15);
        PelilaudanPiirtoalusta pelilaudanPiirtoalusta = new PelilaudanPiirtoalusta(30, 30, pelilauta);
        pelilaudanPiirtoalusta.paintComponent(graphics);
        PelilaudanPiirtoalusta pelilaudanPiirtoalusta2 = new PelilaudanPiirtoalusta(360, 30, vastustajanPelilauta);
        pelilaudanPiirtoalusta2.paintComponent(graphics);      
    }
    
    public void asetaViesti(String viesti){
        this.viesti = viesti.toCharArray();
    }
}