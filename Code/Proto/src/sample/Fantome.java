package sample;

public class Fantome extends Mobile{
    private final int type; //quel fantome il s'agit
    private boolean gum;

    public Fantome(Case emplacement, int type){
        super(emplacement);
        this.type=type;
        gum=false;
    }

    public void move(){

    }

    public void algo(){
        switch (type) {
            case 0 -> blinky();
            case 1 -> pinky();
            case 2 -> inky();
            case 3 -> clyde();
            default -> System.out.println("ERREUR FANTOME");
        }
    }

    public void blinky(){ //shadow

    }

    public void pinky(){ //speeky

    }

    public void inky(){ //bashful

    }

    public void clyde(){ //pokey

    }
}
