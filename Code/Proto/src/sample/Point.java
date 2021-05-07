package sample;

public class Point extends Fixe{
    protected boolean gum;
    public static final int valeur_gum = 500;
    public static final int valeur_nongum=100;


    public Point(Case emplacement, boolean gum){
        super(emplacement,0);
        this.gum=gum;
        if(this.gum) {
            super.score = valeur_gum;
        }else{
            super.score = valeur_nongum;
        }
    }
}
