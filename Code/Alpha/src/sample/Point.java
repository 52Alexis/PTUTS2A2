package sample;

public class Point extends Fixe{
    protected boolean gum;
    public static final int valeur_gum = 50;
    public static final int valeur_nongum= 10;


    public Point(Case emplacement, boolean gum){
        super(emplacement,0,1);
        this.gum=gum;
        if(this.gum) {
            super.score = valeur_gum;
        }else{
            super.score = valeur_nongum;
        }
    }

    public boolean isGum() {
        return gum;
    }
}
