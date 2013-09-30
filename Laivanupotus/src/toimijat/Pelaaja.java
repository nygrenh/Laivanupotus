package toimijat;

import java.util.Collection;
import java.util.Random;
import objektit.Laiva;
import objektit.Pelilauta;
import kayttoliittyma.Kayttolittyma;

/**
 * Huolehtii pelaajan toiminnasta
 */
public class Pelaaja {
    private Pelilauta pelilauta, vastustajanPelilauta;
    private Collection<Laiva> laivat;
    
    public Pelaaja(Kayttolittyma kayttolittyma){
        this();
        kayttolittyma.setPelilautat(pelilauta, vastustajanPelilauta);     
    }
    
    public Pelaaja(){
        this.pelilauta = new Pelilauta(10);
        this.vastustajanPelilauta = new Pelilauta(10);
    }
    /**
     * Tämä metodi on toistaiseksi ihan väärässä luokassa
     * @param laivat 
     */
    public void sijoitaLaivatLaudalle(Collection<Laiva> laivat){
        Random random = new Random();
        for(Laiva laiva : laivat){
            int lahtoKoordinaattiX, lahtoKoordinaattiY;
            Boolean suunta;
            do{
                lahtoKoordinaattiX = random.nextInt(pelilauta.getKoko());
                lahtoKoordinaattiY = random.nextInt(pelilauta.getKoko());
                suunta = random.nextBoolean();
            }while(!pelilauta.asetaLaivaLaudalle(laiva, lahtoKoordinaattiX, lahtoKoordinaattiY, suunta));
        }
    }
}
