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
            case 2 -> blinky();
            case 3 -> pinky();
            case 4 -> inky();
            case 5 -> clyde();
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
