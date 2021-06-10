package pacMan;

/**
 * classe allégée de fantome pour contruire les cartes dans l'editeur
 */
public class FantomeEditor extends Mobile {
    protected int direction; //1 up, 2 droite, 3 down, 4 left
    protected int[] desti;

    public FantomeEditor(Case emplacement, int type, int[] desti) {
        super(emplacement, type);
        this.type = type;
    }

    @Override
    public void move() {

    }
}
