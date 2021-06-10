package pacMan;

/**
 * Classe Représentant les bonus et leurs specificités.
 */
public class Bonus extends Fixe{
    protected int timer;

    public Bonus(Case emplacement, int score,int timer){
        super(emplacement,score,0);
        this.timer=timer;
    }
}
