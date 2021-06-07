package sample;

public class Fantome extends Mobile{
    private boolean gum;

    public Fantome(Case emplacement, int type){
        super(emplacement,type);
        this.type=type;
        gum=false;
    }

    public void move(){

    }

    public void algo(){
        switch (type) {
            case 1 -> blinky();
            case 2 -> pinky();
            case 3 -> inky();
            case 4 -> clyde();
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
