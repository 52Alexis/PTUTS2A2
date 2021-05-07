package sample;

public abstract class Fixe extends Entite{
    protected int score;

    public Fixe(Case emplacement, int score){
        super(emplacement);
        this.score=score;
    }
}
