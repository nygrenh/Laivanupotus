package objektit;

import java.util.ArrayList;

/**
 * Pelilauta pitää sisältää pelilautaan kuuluvat ruudut ja tarjoaa niihin liittyviä funktioita
 */
public class Pelilauta {
    private Ruutu[][] ruudut;
    private boolean[][] pommitetutruudut; 
    
    public Pelilauta(int koko){
        ruudut = new Ruutu[koko][koko];
        pommitetutruudut = new boolean[koko][koko];
        for(int i=0; i<ruudut.length; i++){
            for(int j=0; j<ruudut[0].length; j++){
                ruudut[i][j] = new Ruutu();
            }
        }
        asetaLaivaLaudalle(new Laiva(3, "Kumivene"), 4, 5, true);
    }
    
    public Ruutu getRuutu(int x, int y){
        if(koordinaatitOnPelilaudanRajojenSisalla(x, y)){
            return ruudut[x][y];
        }
        return null;
    }
    
    public int getKoko(){
        return ruudut.length;
    }
    /**
     * Pommittaa pelilaudan ruutua
     * @param x Ruudun x-koordinaatti 
     * @param y Ruudun y-koordinaatti
     * @return Palauttaa true, jos ruudussa oli laiva, ja false, jos ruudussa ei ollut laivaa
     */
    public boolean pommita(int x, int y){
        pommitetutruudut[x][y] = true;
        Laiva laiva = ruudut[x][y].getLaiva();
        if(laiva == null){
            return false;
        }
        return true;      
    }
    
    public boolean ruutuaOnJoPommitettu(int x, int y){
        return pommitetutruudut[x][y];
    }
    /**
     * Tällä metodilla asetetaan laiva pelilaudalle
     * @param laiva Pelilaudalle asetettava laiva
     * @param alkuX Laivan alkupisteen x-koordinaatti
     * @param alkuY Laivan alkupisteen y-koordinaatti
     * @param meneeAlaspain True, jos laiva on pystytasossa, false jos laiva on vaakatasossa
     * @return Palauttaa true, jos laivan sijoittaminen onnistui, ja false, jos ei onnistunut
     */
    public boolean asetaLaivaLaudalle(Laiva laiva, int alkuX, int alkuY, Boolean meneeAlaspain){
        int loppuX, loppuY;
        if(meneeAlaspain){
            loppuX = alkuX;
            loppuY = alkuY + laiva.getPituus();
        } else {
            loppuX = alkuX + laiva.getPituus();
            loppuY = alkuY;
        }
        if(!koordinaatitOnPelilaudanRajojenSisalla(alkuX, alkuY) || !koordinaatitOnPelilaudanRajojenSisalla(loppuX, loppuY)){
            return false;
        }
        ArrayList<Ruutu> ruudut = new ArrayList<>();
        for(int x=alkuX; x<=loppuX;x++){
            for(int y=alkuY; y<=loppuY;y++){
                ruudut.add(this.ruudut[x][y]);
                this.ruudut[x][y].asetaruutuunLaiva(laiva);
            }
        }
        laiva.asetaRuudut(ruudut);
        return true;
    }

    public boolean koordinaatitOnPelilaudanRajojenSisalla(int x, int y) {
        return x>=0 && y>=0 && x<ruudut.length && y<ruudut[0].length;
    }
}
