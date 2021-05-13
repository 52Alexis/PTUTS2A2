package sample;

public class ModelMap {
    protected Case[] cases;
    protected int tailleCase;

    public ModelMap() {
        cases = new Case[1008];
        tailleCase = 16;
    }

    public Case[] getCases() {
        return cases;
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
}
