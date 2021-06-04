package sample;

public class Case {
    protected int x;
    protected int y;
    //protected Entite contenu;
    protected boolean mur;

    public Case(int x, int y, boolean mur) {
        this.x = x;
        this.y = y;
        this.mur = mur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMur(boolean mur) {
        this.mur = mur;
    }

    public boolean isMur() {
        return mur;
    }
}
