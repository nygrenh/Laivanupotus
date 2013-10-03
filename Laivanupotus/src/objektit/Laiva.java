package objektit;

import java.util.List;

/**
 * Huolehtii laivoihin liittyvien tietojen talletuksesta
 */
public class Laiva {

    private List<Ruutu> ruudut;
    private int pituus;
    private String nimi;

    /**
     * @param pituus Laivan pituus
     * @param nimi Laivan nimi
     */
    public Laiva(int pituus, String nimi) {
        this.pituus = pituus;
        this.nimi = nimi;
    }

    /**
     * Tämä metodi kertoo, onko tämä laiva jo tuhottu
     *
     * @return Palauttaa true, jos laiva on tuhottu, ja false jos laivaa ei ole
     * tuhottu
     */
    public boolean onTuhottu() {
        if (this.ruudut == null) {
            return false;
        }
        int laskuri = 0;
        for (Ruutu r : ruudut) {
            if (r.onPommitettu()) {
                laskuri++;
            }
        }
        if (laskuri == pituus) {
            return true;
        }
        return false;
    }

    /**
     * Tällä metodilla kerrotaan laivalle, missä ruuduissa se sijaitsee
     *
     * @param ruudut
     */
    public void asetaRuudut(List<Ruutu> ruudut) {
        this.ruudut = ruudut;
    }

    public int getPituus() {
        return pituus;
    }

    public Boolean onSijoitettu() {
        return this.ruudut != null;
    }

    @Override
    public String toString() {
        return this.nimi;
    }
}
