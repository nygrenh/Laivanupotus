
package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import objektit.Pelilauta;

public class Piirtoalusta extends JPanel {
private Pelilauta pelilauta;

    public Piirtoalusta(Pelilauta pelilauta) {
        super.setBackground(Color.WHITE);
        this.pelilauta = pelilauta;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        for(int i=1; i <= pelilauta.getKoko();i++){
            for(int j=1; j <= pelilauta.getKoko();j++){
                int x = i*30 + i;
                int y = j *30 + j;
                graphics.fillRect(x, y, 30, 30);
                System.out.println(x + " " + y);
            }
        }
    }
}