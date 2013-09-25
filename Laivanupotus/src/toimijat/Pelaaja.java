package toimijat;

import java.util.Collection;
import objektit.Laiva;
import objektit.Pelilauta;
import kayttoliittyma.Kayttolittyma;

public class Pelaaja {
    private Pelilauta pelilauta, vastustajanPelilauta;
    private Collection<Laiva> laivat;
    
    public Pelaaja(Kayttolittyma kayttolittyma){
        this();
        kayttolittyma.setPelilauta(pelilauta);     
    }
    
    public Pelaaja(){
        this.pelilauta = new Pelilauta(10);
    }
    
    public void sijoitaLaivatLaudalle(Collection<Laiva> laivat){
        for(Laiva laiva : laivat){
            
        }
    }
}
