package objektit;

import java.util.ArrayList;

/**
 * Pelilauta pitää sisältää pelilautaan kuuluvat ruudut ja tarjoaa niihin
 * liittyviä funktioita
 */
public class Pelilauta {

    private Ruutu[][] ruudut;
    private boolean[][] pommitetutruudut;
    private boolean[][] varatutRuudut;
    private boolean laivatOnNakyvilla;

    public Pelilauta(int koko) {
        ruudut = new Ruutu[koko][koko];
        pommitetutruudut = new boolean[koko][koko];
        varatutRuudut = new boolean[koko][koko];
        for (int i = 0; i < ruudut.length; i++) {
            for (int j = 0; j < ruudut[0].length; j++) {
                ruudut[i][j] = new Ruutu();
            }
        }
        laivatOnNakyvilla = false;
    }

    public Ruutu getRuutu(int x, int y) {
        if (koordinaatitOnPelilaudanRajojenSisalla(x, y)) {
            return ruudut[x][y];
        }
        return null;
    }

    public int getKoko() {
        return ruudut.length;
    }

    /**
     * Pommittaa pelilaudan ruutua
     *
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     * @return Palauttaa true, jos ruudussa oli laiva, ja false, jos ruudussa ei
     * ollut laivaa
     */
    public boolean pommita(int x, int y) {
        pommitetutruudut[x][y] = true;
        ruudut[x][y].pommita();
        Laiva laiva = ruudut[x][y].getLaiva();
        if (laiva == null) {
            return false;
        }
        return true;
    }

    public boolean ruutuaOnJoPommitettu(int x, int y) {
        return pommitetutruudut[x][y];
    }

    /**
     * Tällä metodilla asetetaan laiva pelilaudalle
     *
     * @param laiva Pelilaudalle asetettava laiva
     * @param alkuX Laivan alkupisteen x-koordinaatti
     * @param alkuY Laivan alkupisteen y-koordinaatti
     * @param meneeAlaspain True, jos laiva on pystytasossa, false jos laiva on
     * vaakatasossa
     * @return Palauttaa true, jos laivan sijoittaminen onnistui, ja false, jos
     * ei onnistunut
     */
    public boolean asetaLaivaLaudalle(Laiva laiva, int alkuX, int alkuY, Boolean meneeAlaspain) {
        int loppuX, loppuY;
        if (meneeAlaspain) {
            loppuX = alkuX;
            loppuY = alkuY + laiva.getPituus() - 1;
        } else {
            loppuX = alkuX + laiva.getPituus() - 1;
            loppuY = alkuY;
        }
        if (!koordinaatitOnPelilaudanRajojenSisalla(alkuX, alkuY) || !koordinaatitOnPelilaudanRajojenSisalla(loppuX, loppuY)) {
            return false;
        }
        if (laivaMeneeLiianLahelleMuitaLaivoija(alkuX, alkuY, loppuX, loppuY)) {
            return false;
        }
        ArrayList<Ruutu> ruudut = new ArrayList<>();
        for (int x = alkuX; x <= loppuX; x++) {
            for (int y = alkuY; y <= loppuY; y++) {
                ruudut.add(this.ruudut[x][y]);
                this.ruudut[x][y].asetaruutuunLaiva(laiva);
                varaaLahistonRuudut(x, y);
            }
        }
        laiva.asetaRuudut(ruudut);
        return true;
    }

    /**
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return Palauttaa true, jos koordinaatit osuvat pelilaudalle, muuten
     * false
     */
    public boolean koordinaatitOnPelilaudanRajojenSisalla(int x, int y) {
        return x >= 0 && y >= 0 && x < ruudut.length && y < ruudut[0].length;
    }

    private boolean laivaMeneeLiianLahelleMuitaLaivoija(int alkuX, int alkuY, int loppuX, int loppuY) {
        for (int x = alkuX; x <= loppuX; x++) {
            for (int y = alkuY; y <= loppuY; y++) {
                if (this.varatutRuudut[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tämä merkkaa annettujen koordinaattien ruudun sen välittömän ympäristön
     * varatuksi. Tämä tehdään, koska laivanupotuksessa laivoja ei saa sijoittaa
     * vierekkäin
     *
     * @param x Lähtöruudun x-koordinaatti
     * @param y Lähtöruudun y-koordinaatti
     */
    private void varaaLahistonRuudut(int x, int y) {
        this.varatutRuudut[x][y] = true;
        if (x < varatutRuudut.length - 1) {
            this.varatutRuudut[x + 1][y] = true;
        }
        if (y < varatutRuudut[0].length - 1) {
            this.varatutRuudut[x][y + 1] = true;
        }
        if (x > 0) {
            this.varatutRuudut[x - 1][y] = true;
        }
        if (y > 0) {
            this.varatutRuudut[x][y - 1] = true;
        }
    }

    public boolean laivatOnNakyvilla() {
        return laivatOnNakyvilla;
    }

    public void vaihdaLaivoijenNakyvyytta() {
        laivatOnNakyvilla = !laivatOnNakyvilla;
    }
}
