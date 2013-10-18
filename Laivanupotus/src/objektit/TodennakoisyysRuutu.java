package objektit;

public class TodennakoisyysRuutu extends Ruutu {

    private int todennakoisyys, x, y;

    public TodennakoisyysRuutu(int x, int y) {
        super();
        this.todennakoisyys = 0;
        this.x = x;
        this.y = y;
    }

    public int getTodennakoisyys() {
        return todennakoisyys;
    }

    public void kasvataTodennakoisyytta() {
        todennakoisyys++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
