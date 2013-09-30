package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import objektit.Pelilauta;

/**
 * Vastaa yhden pelilaudan piirtämisestä
 */
public class PelilaudanPiirtoalusta extends JPanel{
    
    private int x, y;
    private Pelilauta pelilauta;

    public PelilaudanPiirtoalusta(int x, int y, Pelilauta pelilauta) {
        this.x = x;
        this.y = y;
        this.pelilauta = pelilauta;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.translate(this.x, this.y);
        for(int i=0; i < pelilauta.getKoko();i++){
            for(int j=0; j < pelilauta.getKoko();j++){
                int x = i*30;
                int y = j *30;
                piirraRuudukko(g, x, y);
                piirraObjektit(g, x, y);
            }
        }
        g.translate(-this.x, -this.y);
    }

    private void piirraRuudukko(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 29, 29);
    }

    private void piirraObjektit(Graphics g, int x, int y) {
        int ruudunXKoordinaatti = x/30;
        int ruudunYKoordinaatti = y/30;
        if(pelilauta.ruutuaOnJoPommitettu(ruudunXKoordinaatti, ruudunYKoordinaatti)){
            if(ruudussaOnLaiva(ruudunXKoordinaatti, ruudunYKoordinaatti)){
                if(ruudussaOlevaLaivaOnTuhottu(ruudunXKoordinaatti, ruudunYKoordinaatti)){
                    g.setColor(Color.green);
                }else{
                    g.setColor(Color.yellow);
                }
            } else{
                g.setColor(Color.RED);
            }
            g.fillRect(x, y, 29, 29);
        }
    }

    private boolean ruudussaOnLaiva(int ruudunXKoordinaatti, int ruudunYKoordinaatti) {
        return pelilauta.getRuutu(ruudunXKoordinaatti, ruudunYKoordinaatti).getLaiva() != null;
    }

    private boolean ruudussaOlevaLaivaOnTuhottu(int ruudunXKoordinaatti, int ruudunYKoordinaatti) {
        return pelilauta.getRuutu(ruudunXKoordinaatti, ruudunYKoordinaatti).getLaiva().onTuhottu();
    }
}
