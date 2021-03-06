package logiikka.tekoaly;

import java.util.Comparator;
import objektit.TodennakoisyysRuutu;

/**
 * Tämä vertailija laittaa ruudut, joille on laskettu todennäköisyys
 * järjestykseen
 */
public class TodennakoisyysRuutuComparator implements Comparator<TodennakoisyysRuutu> {

    @Override
    public int compare(TodennakoisyysRuutu o1, TodennakoisyysRuutu o2) {
        return o2.getTodennakoisyys() - o1.getTodennakoisyys();
    }
}
