package pacMan;

/**
 * Classe mère pour le pacman et les fantomes pour s'assurer de leur distinction dans des listes
 */
public abstract class Mobile extends Entite{
    protected boolean gum;
    protected int type; //1 pacman, 2 blinky 3 pinky 4 inky 5 clyde


    public Mobile(Case emplacement,int type){
        super(emplacement);
        this.gum=false;
        this.type=type;
    }

    public abstract void move();

}
