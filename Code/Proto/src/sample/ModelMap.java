package sample;

public class ModelMap {
    protected Case[][] cases;
    protected int tailleCase;
    protected final int X;
    protected final int Y;
    protected Pacman pacman;

    public ModelMap(int x, int y) {
        this.X=x;
        this.Y=y;
        cases = new Case[X][Y];
        tailleCase = 16;
        for(int i=0;i<X;i++){
            for(int j=0;j<Y;j++) {
                cases[i][j] = new Case(i % X, i / X, false, this);
            }
        }
        cases[5][5].mur=true;
        pacman=new Pacman(cases[10][10]);
        cases[10][10].mobile=pacman;
    }

    public Case[][] getCases() {
        return cases;
    }

    public Case getCase(int x,int y){
        return cases[x][y];
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public int getTailleCase() {
        return tailleCase;
    }

    public void setTailleCase(int tailleCase) {
        this.tailleCase = tailleCase;
    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }
}
