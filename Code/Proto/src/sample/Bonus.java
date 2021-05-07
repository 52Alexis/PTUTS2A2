package sample;

public class Bonus extends Fixe{
    protected int timer;

    public Bonus(Case emplacement, int score,int timer){
        super(emplacement,score);
        this.timer=timer;
    }
}
