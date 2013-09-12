package objektit;

public class Pelilauta {
    private Ruutu[][] ruudut;
    
    public Pelilauta(int koko){
        ruudut = new Ruutu[10][10];
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
}
