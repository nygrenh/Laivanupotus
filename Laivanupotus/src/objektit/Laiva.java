package objektit;

import java.util.List;

public class Laiva {
    private List<Ruutu> ruudut;
    private int pituus;
    
    public Laiva(int pituus){
        this.pituus = pituus;
    }
    
    public boolean onTuhottu(){
        int laskuri = 0;
        for(Ruutu r : ruudut){
            if(r.onPommitettu()) laskuri++;
        }
        if(laskuri==pituus){
            return true;
        }
        return false;
    }
    
    public void asetaRuudut(List<Ruutu> ruudut){
        this.ruudut = ruudut;
    }

    public int getPituus() {
        return pituus;
    }
    
}
