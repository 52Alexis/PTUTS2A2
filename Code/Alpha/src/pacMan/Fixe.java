package pacMan;

/**
 * classe mère de tous les points et bonus
 */
public abstract class Fixe extends Entite{
    protected int score;
    protected int type;

    public Fixe(Case emplacement, int score, int type){
        super(emplacement);
        this.score=score;
        this.type=type;
    }
}
