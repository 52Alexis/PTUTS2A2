package sample;

public class ModelMap {
    protected Case[] cases;
    protected int tailleCase;
    protected final int X;
    protected final int Y;

    public ModelMap(int x, int y) {
        this.X=x;
        this.Y=y;
        cases = new Case[X*Y];
        tailleCase = 16;
        for(int i=0;i<cases.length;i++){
            cases[i]=new Case(i%X,i/X,false,this);
        }
        cases[500].mur=true;
        cases[400].mobile=new Pacman(cases[400]);
    }

    public Case[] getCases() {
        return cases;
    }

    public Case getCase(int index){
        return cases[index];
    }

    public void setCases(Case[] cases) {
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
