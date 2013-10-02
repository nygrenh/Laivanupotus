package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import logiikka.Logiikka;
import objektit.Laiva;
import objektit.Pelilauta;

/**
 * Vastaa koko pelialueen piirtämisestä
 */
public class Piirtoalusta extends JPanel {
private Pelilauta pelilauta, vastustajanPelilauta;
private char[] viesti;
private Logiikka logiikka;

    public Piirtoalusta(Pelilauta pelilauta, Pelilauta vastustajanPelilauta, Logiikka logiikka) {
        super.setBackground(Color.WHITE);
        this.pelilauta = pelilauta;
        this.vastustajanPelilauta = vastustajanPelilauta;
        viesti ="Hello".toCharArray();
        this.logiikka = logiikka;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paivitaViesti(graphics);
        PelilaudanPiirtoalusta pelilaudanPiirtoalusta = new PelilaudanPiirtoalusta(30, 30, pelilauta);
        pelilaudanPiirtoalusta.paintComponent(graphics);
        PelilaudanPiirtoalusta pelilaudanPiirtoalusta2 = new PelilaudanPiirtoalusta(360, 30, vastustajanPelilauta);
        pelilaudanPiirtoalusta2.paintComponent(graphics);      
    }
    
    public void asetaViesti(String viesti){
        this.viesti = viesti.toCharArray();
    }

    private void paivitaViesti(Graphics graphics) {
        Laiva laiva = logiikka.annaAsetettavaLaiva();
        if(laiva != null){
            asetaViesti("Aseta " + laiva.toString().toLowerCase() + " oikeanpuoleiseen pelilautaan. Hiiren rullan painaminen kääntää laivan suuntaa.");
        } else{
            asetaViesti("Pommita vasemmanpuoleista pelilautaa");
        }
        graphics.drawChars(viesti, 0, viesti.length, 15, 15);
    }
}