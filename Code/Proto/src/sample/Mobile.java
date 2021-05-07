package sample;

public abstract class Mobile extends Entite{
    protected boolean gum;

    public Mobile(Case emplacement){
        super(emplacement);
        this.gum=false;
    }

    public abstract void move();

}
