package objektit;

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
        if(x>=0 && y>=0 && x<ruudut.length && y<ruudut[0].length){
            return ruudut[x][y];
        }
        return null;
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
}
