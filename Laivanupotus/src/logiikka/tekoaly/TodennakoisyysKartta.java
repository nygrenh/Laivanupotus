package logiikka.tekoaly;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import objektit.Laiva;
import objektit.Pelilauta;
import objektit.TodennakoisyysRuutu;

class TodennakoisyysKartta {

    private Pelilauta pelilauta;
    private Collection<Laiva> laivasto;
    private TodennakoisyysRuutu[][] todennakoisyysKartta;
    private ArrayList<TodennakoisyysRuutu> ruudut;

    public TodennakoisyysKartta(Pelilauta pelilauta, Collection<Laiva> laivasto) {
        this.pelilauta = pelilauta;
        this.laivasto = laivasto;
        todennakoisyysKartta = new TodennakoisyysRuutu[pelilauta.getKoko()][pelilauta.getKoko()];
    }

    public void lasketodennakoisyydet() {
        alustaTodennakoisyyskartta();
        for (Laiva laiva : laivasto) {
            if (laiva.onTuhottu()) {
                continue;
            }
            kaykaikkiRuudutLapi(laiva);
        }
        tulostaTodennakoisyyskartta();
        ruudut = new ArrayList<>();
        for (TodennakoisyysRuutu[] ruuturivi : todennakoisyysKartta) {
            for(TodennakoisyysRuutu ruutu : ruuturivi){
                if(!pelilauta.ruutuaOnJoPommitettu(ruutu.getX(), ruutu.getY())){
                    ruudut.add(ruutu);
                }
            }
        }
        Collections.sort(ruudut, new TodennakoisyysRuutuComparator());
    }

    private void alustaTodennakoisyyskartta() {
        for (int i = 0; i < todennakoisyysKartta.length; i++) {
            for (int j = 0; j < todennakoisyysKartta[0].length; j++) {
                todennakoisyysKartta[i][j] = new TodennakoisyysRuutu(i, j);
            }
        }
    }

    private void tulostaTodennakoisyyskartta() {
        System.out.println("Todennäköisyyskartta:");
        for (int i = 0; i < todennakoisyysKartta.length; i++) {
            for (int j = 0; j < todennakoisyysKartta[0].length; j++) {
                System.out.print(todennakoisyysKartta[i][j].getTodennakoisyys() + "  ");
            }
            System.out.println();
        }
    }

    private void kaykaikkiRuudutLapi(Laiva laiva) {
        for (int x = 0; x < todennakoisyysKartta.length; x++) {
            for (int y = 0; y < todennakoisyysKartta[0].length; y++) {
                try {
                    Boolean kaikkiinRuutuihinMeneeLaiva = true;
                    int loppuY = y + laiva.getPituus() - 1;
                    for (int i = y; i <= loppuY; i++) {
                        if (!pelilauta.koordinaatitOnPelilaudanRajojenSisalla(x, i) || pelilauta.ruutuaOnJoPommitettu(x, i)) {
                            kaikkiinRuutuihinMeneeLaiva = false;
                        }
                    }
                    if (kaikkiinRuutuihinMeneeLaiva) {
                        for (int i = y; i <= loppuY; i++) {
                            todennakoisyysKartta[x][i].kasvataTodennakoisyytta();
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        for (int x = 0; x < todennakoisyysKartta.length; x++) {
            for (int y = 0; y < todennakoisyysKartta[0].length; y++) {
                try {
                    Boolean kaikkiinRuutuihinMeneeLaiva = true;
                    int loppuX = x + laiva.getPituus() - 1;
                    for (int i = x; i <= loppuX; i++) {
                        if (!pelilauta.koordinaatitOnPelilaudanRajojenSisalla(i, y) || pelilauta.ruutuaOnJoPommitettu(i, y)) {
                            kaikkiinRuutuihinMeneeLaiva = false;
                        }
                    }
                    if (kaikkiinRuutuihinMeneeLaiva) {
                        for (int i = x; i <= loppuX; i++) {
                            todennakoisyysKartta[i][y].kasvataTodennakoisyytta();
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

    public Point annaiskukohde() {
        lasketodennakoisyydet();
        TodennakoisyysRuutu ruutu = ruudut.get(0);
        Point palautettava = new Point(ruutu.getX(), ruutu.getY());
        return palautettava;
    }
}
