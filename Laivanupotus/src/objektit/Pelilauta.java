package objektit;

import java.util.ArrayList;

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
    
    public boolean asetaLaivaLaudalle(Laiva laiva, int alkuX, int alkuY, int loppuX, int loppuY){
        if(!koordinaatitOnPelilaudanRajojenSisalla(alkuX, alkuY) || !koordinaatitOnPelilaudanRajojenSisalla(loppuX, loppuY)){
            return false;
        }
        int pituus = (loppuX+loppuY) - (alkuX+alkuY) + 1;
        if(pituus != laiva.getPituus()){
            return false;
        }
        ArrayList<Ruutu> ruudut = new ArrayList<>();
        try{
        for(int x=alkuX; x<=loppuX;x++){
            for(int y=alkuY; y<=loppuY;y++){
                ruudut.add(this.ruudut[x][y]);
                this.ruudut[x][y].asetaruutuunLaiva(laiva);
            }
        }
        }catch(Exception e){
            System.err.println("Index out of bounds");
        }
        laiva.asetaRuudut(ruudut);
        return true;
    }

    public boolean koordinaatitOnPelilaudanRajojenSisalla(int x, int y) {
        return x>=0 && y>=0 && x<ruudut.length && y<ruudut[0].length;
    }
}
