package pacMan;

/**
 * Classe permetant la représentation des cases
 */
public class Case {
    protected int x; //position x et y des cases
    protected int y;
    protected Mobile mobile; // permet un lien permanent entre les cases et leurs contenu
    protected Fixe fixe;
    protected boolean mur; //critère de calcul pour les cillisions
    protected boolean porte;
    public ModelMap model; //lien vers le partie
    protected String typeMur; //représentation du mur

    public Case(int x, int y,String typeMur, ModelMap model) {
        this.x = x;
        this.y = y;
        if(typeMur.equals("PO")||typeMur.equals("PG")||typeMur.equals("0")){
            mur=false;
            porte=false;
        }else{
            mur=true;
            porte= typeMur.equals("P")||typeMur.equals("P ");;
        }
        this.model=model;
        this.typeMur=typeMur;
    }

    public Case(int x, int y,String typeMur, ModelMap model,Mobile mobile) {
        this.x = x;
        this.y = y;
        this.mur = !typeMur.equals("0");
        this.model=model;
        this.mobile=mobile;
    }

    public Case(int x, int y,String typeMur, ModelMap model, Fixe fixe) {
        this.x = x;
        this.y = y;
        this.mur = !typeMur.equals("0");
        this.model=model;
        this.fixe=fixe;
    }

    public Case(int x, int y, String typeMur, ModelMap model,Mobile mobile, Fixe fixe) {
        this.x = x;
        this.y = y;
        this.mur = !typeMur.equals("0");
        this.model=model;
        this.mobile=mobile;
        this.fixe=fixe;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMur(boolean mur) {
        this.mur = mur;
    }

    public boolean isMur() {
        return mur;
    }

    public boolean isPorte() {
        return porte;
    }

    public Mobile getMobile(){
        return mobile;
    }

    public void setMobile(Mobile mobile){
        this.mobile=mobile;
    }

    public boolean haveMobile(){
        return mobile!=null;
    }

    public boolean haveFixe(){
        return fixe!=null;
    }

    public Fixe getFixe() {
        return fixe;
    }

    public void setFixe(Fixe fixe) {
        this.fixe = fixe;
    }

    public String getTypeMur() {
        return typeMur;
    }

    public void setTypeMur(String typeMur) {
        this.typeMur = typeMur;
    }
}
