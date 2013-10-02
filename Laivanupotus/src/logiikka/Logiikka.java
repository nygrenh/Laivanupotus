package logiikka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import objektit.Laiva;
import toimijat.Pelaaja;
import kayttoliittyma.Kayttolittyma;

/**
 * Huolehtii pelin kulusta
 */
public class Logiikka {
    private Pelaaja pelaaja, pelaaja2;
    private Collection<Laiva> pelaajanLaivat;
    
    public Logiikka(Kayttolittyma kayttolittyma){
        pelaaja = new Pelaaja(kayttolittyma);
        pelaaja2 = new Pelaaja();
        pelaajanLaivat = luoUusiLaivasto();
        Collection<Laiva> pelaaja2nLaivat = luoUusiLaivasto();
        pelaaja.sijoitaLaivatLaudalle(pelaaja2nLaivat);
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
            palautettava.add(lisattavaLaiva);
        }
    }
    
    public Laiva annaAsetettavaLaiva() {
        for(Laiva laiva : pelaajanLaivat){
            if(laiva.onSijoitettu()){
                continue;
            }
            return laiva;
        }
        return null;
    }
    
    public static void main(String [] args){
        Kayttolittyma kayttolittyma = new Kayttolittyma();
        Logiikka logiikka = new Logiikka(kayttolittyma);
        kayttolittyma.setLogiikka(logiikka);
        kayttolittyma.run();
    }
}
