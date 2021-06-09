package pacMan;

public class FantomeEditor extends Mobile {
    private boolean gum;
    private boolean dead;
    protected int direction; //1 up, 2 droite, 3 down, 4 left
    protected Case destination;
    protected Case lastCase;
    protected int[] desti;
    protected boolean door;

    public boolean isGum() {
        return gum;
    }

    public void setGum(boolean gum) {
        this.gum = gum;
    }

    public boolean isDead() {
        return dead;
    }

    public FantomeEditor(Case emplacement, int type, int[] desti) {
        super(emplacement, type);
        this.type = type;
    }

    @Override
    public void move() {

    }
}
