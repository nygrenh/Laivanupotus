package logiikka;

import java.util.ArrayList;
import java.util.Collection;
import objektit.Laiva;
import toimijat.Pelaaja;
import kayttoliittyma.Kayttolittyma;

public class Logiikka {
    private Pelaaja pelaaja, pelaaja2;
    
    public Logiikka(Kayttolittyma kayttolittyma){
        pelaaja = new Pelaaja(kayttolittyma);
        pelaaja2 = new Pelaaja();
        Collection<Laiva> pelaajanLaivat = luoUusiLaivasto();
        Collection<Laiva> pelaaja2nLaivat = luoUusiLaivasto();
    }
    public void silmukka(){
        
    }

    private Collection<Laiva> luoUusiLaivasto() {
        Collection<Laiva> palautettava = new ArrayList<>();
        lisaaLaiva(palautettava, 1, "Lentotukialus", 5);
        lisaaLaiva(palautettava, 1, "Taistelulaiva",  4);
        lisaaLaiva(palautettava, 2, "Risteilijä",  3);
        lisaaLaiva(palautettava, 2, "Hävittäjä",  2);
        return palautettava;
    }

    private void lisaaLaiva(Collection<Laiva> palautettava, int lukumaara, String nimi, int pituus) {
        for(int i = 0; i < lukumaara; i++){
            Laiva lisattavaLaiva = new Laiva(pituus, nimi);
        }
    }
    
    public static void main(String [] args){
        Kayttolittyma kayttolittyma = new Kayttolittyma();
        new Logiikka(kayttolittyma);
        kayttolittyma.run();
    }
}
