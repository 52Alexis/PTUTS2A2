package pacMan;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe gérant les fantome et leur comportements
 */
public class Fantome extends Mobile{
    private boolean gum;
    private boolean dead;
    protected int direction; //1 up, 2 droite, 3 down, 4 left
    protected Case destination;
    protected Case lastCase;
    protected int[] desti; //coordonnées designant la case "mère" des fantomes où il (re)deviennent actif
    protected boolean door;

    public boolean isGum() {
        return gum;
    }

    public void setGum(boolean gum) {
        this.gum = gum;
    }

    public boolean isDead() {
        return dead;
    }

    public Fantome(Case emplacement, int type, int[] desti){
        super(emplacement,type);
        this.type=type;
        gum=false;
        door=false;
        this.desti=desti;
        destination=emplacement.model.getCase(desti[0],desti[1]);
    }

    public void move(){
        algo();
    }//implémentation de méthode abstaite lançant les algorithmes des fantomes

    /**
     * Fonction faisant le choix entre les differents possibilité des fantomes
     * le chemin dépend de la distance linéaire entre leur destination et la case choisie de manière semblable au pacman originel
     * Le fantome ne peut pas faire demi tour
     * @param destination destination
     */
    public void move(Case destination){
        ArrayList<Case> listValidCase;
        listValidCase=getCaseAdj(emplacement.model, emplacement.getX(), emplacement.getY());
        listValidCase.remove(lastCase);//empêche le fantome de faire demi tour
        Case nxtCase = null;
        double distMin=500000; //valeur impossible à atteindre
        for(int i=1; i<=listValidCase.size();i++){
            Case c=listValidCase.get(i-1);
            double dist=Math.sqrt((destination.getX()-c.getX())*(destination.getX()-c.getX())+(destination.getY()-c.getY())*(destination.getY()-c.getY())); //calcul de la distance
            if(dist<distMin){
                distMin=dist;
                nxtCase=c;
                direction=i;
            }
        }
        if(nxtCase==null){
            System.out.println("ERREUR DEPLACEMENT FANTOME");
            return;
        }
        exchange(nxtCase);
    }

    /**
     * fonction déterminant les déplacement possible du fantome
     * @param model modèle de la map
     * @param x coordonnée en x
     * @param y coordonnée en Y
     * @return retourne une lise te case posible
     */
    public ArrayList<Case> getCaseAdj(ModelMap model, int x, int y){
        ArrayList<Case> listAdjPrimitive = new ArrayList<>();
        try {
            listAdjPrimitive.add(model.getCase(x, y - 1));
        } catch (Exception e) {
            listAdjPrimitive.add(model.getCase(x, model.getY() - 1));
        }
        try {
            listAdjPrimitive.add(model.getCase(x + 1, y));
        } catch (Exception e) {
            listAdjPrimitive.add(model.getCase(0, y));
        }
        try {
            listAdjPrimitive.add(model.getCase(x, y + 1));
        } catch (Exception e) {
            listAdjPrimitive.add(model.getCase(x, 0));
        }
        try {
            listAdjPrimitive.add(model.getCase(x - 1, y));
        } catch (Exception e) {
            listAdjPrimitive.add(model.getCase(model.getX() - 1, y));
        }
        ArrayList<Case> listAdj=new ArrayList<>();
        for(Case c: listAdjPrimitive){ //vérifie si les déplacements sont possibles
            if(c!=null){
                if(!door) {
                    if(c.isPorte()||!c.isMur()){
                        listAdj.add(c);
                    }
                }else{
                    if(!c.isMur()){
                        listAdj.add(c);
                    }
                }
            }
        }
        return listAdj;
    }

    /**
     * réalise le mouvement
     * @param nxtCase case où se réalise le mouvement
     */
    public void exchange(Case nxtCase){
        emplacement.mobile=null;
        lastCase=emplacement;
        emplacement=nxtCase;
        emplacement.mobile=this;
    }

    /**
     * lance l'algo correspondant au type de fantome
     */
    public void algo(){
        if(type==2&&emplacement.model.getTemps()<15){
            gum=false;
            return;

        }
        if (type==3&&emplacement.model.getTemps()<30) {
            gum=false;
            return;

        }
        if(type==4&&emplacement.model.getTemps()<60){
            gum=false;
            return;

        }
        if(commonalgo()) { //lance l'algo selon le type de fantome
            switch (type) {
                case 1 -> blinky();
                case 2 -> pinky();
                case 3 -> inky();
                case 4 -> clyde();
                default -> System.out.println("ERREUR FANTOME");
            }
            move(destination);
        }
    }

    /**
     * Pièce d'algorithme communes à tous les types de fantomes
     * @return booléen si les algorithmes propres aux types de fantomes se lancent
     */
    public boolean commonalgo(){
        if(emplacement==destination){
            if(dead){
                dead=false;
                destination=emplacement.model.getCase(desti[0],desti[1]);
            }else {
                door = true;
            }
        }
        if(gum){
            panic();
            move(destination);
            return false;
        }
        if(dead){
            door=false;
        }
        if(door){
            return true;
        }else{
            move(destination);
            return false;
        }
    }

    public void blinky(){ //shadow suit aveuglement le pacman
        destination=emplacement.model.getPacman().emplacement;
    }

    public void pinky(){ //speeky prend un emplacement devant le pacman pour tenter d'arriver de devant
            int posX = emplacement.model.getPacman().emplacement.getX();
            int posY = emplacement.model.getPacman().emplacement.getY();
            switch (emplacement.model.getPacman().direction) {
                case 1:
                    try {
                        posY = posY - 4;
                        destination = emplacement.model.getCase(posX, posY);
                    } catch (Exception e) {
                        posY = emplacement.model.getY() - 4;
                        destination = emplacement.model.getCase(posX, posY);
                    }
                    break;
                case 2:
                    try {
                        posX = posX + 4;
                        destination = emplacement.model.getCase(posX, posY);
                    } catch (Exception e) {
                        posX = 4;
                        destination = emplacement.model.getCase(posX, posY);
                    }
                    break;
                case 3:
                    try {
                        posY = posY + 4;
                        destination = emplacement.model.getCase(posX, posY);
                    } catch (Exception e) {
                        posY = 4;
                        destination = emplacement.model.getCase(posX, posY);
                    }
                    break;
                case 4:
                    try {
                        posX=posX-4;
                        destination = emplacement.model.getCase(posX, posY);
                    } catch (Exception e) {
                        posX = emplacement.model.getX() - 4;
                        destination = emplacement.model.getCase(posX, posY);
                    }
                    break;
                default:
                    break;
            }
            destination = emplacement.model.getCase(posX, posY);

    }

    public void inky(){ //bashful comportement hybride entre blinky et pinky
            int posX = emplacement.model.getPacman().emplacement.getX();
            int posY = emplacement.model.getPacman().emplacement.getY();
            switch (emplacement.model.getPacman().direction) {
                case 1:
                    try {
                        posY = posY - 2;
                        destination = emplacement.model.getCase(posX, posY);
                    } catch (Exception e) {
                        posY = emplacement.model.getY() - 2;
                        destination = emplacement.model.getCase(posX, posY);
                    }
                    break;
                case 2:
                    try {
                        posX = posX + 2;
                        destination = emplacement.model.getCase(posX, posY);
                    } catch (Exception e) {
                        posX = 2;
                        destination = emplacement.model.getCase(posX, posY);
                    }
                    break;
                case 3:
                    try {
                        posY = posY + 2;
                        destination = emplacement.model.getCase(posX, posY);
                    } catch (Exception e) {
                        posY = 2;
                        destination = emplacement.model.getCase(posX, posY);
                    }
                    break;
                case 4:
                    try {
                        posX = posX - 2;
                        destination = emplacement.model.getCase(posX, posY);
                    } catch (Exception e) {
                        posX = emplacement.model.getX() - 2;
                        destination = emplacement.model.getCase(posX, posY);
                    }
                    break;
                default:
                    break;
            }
            destination = emplacement.model.getCase(posX, posY);
            Fantome f = emplacement.model.getFantomeByType(1);
            if (f != null) {
                try {
                    int vX = posX - f.emplacement.getX();
                    int vY = posY - f.emplacement.getY();
                    posX = posY + vX;
                    posY = posY + vY;
                    destination = emplacement.model.getCase(posX, posY);
                }catch (Exception ignored){

                }
            }

    }

    public void clyde(){ //pokey comportement dépendant de la distance oscillant entre blinky et la panique
            Pacman pc = emplacement.model.getPacman();
            double dist = Math.sqrt(((pc.emplacement.getX() - emplacement.getX()) * (pc.emplacement.getX() - emplacement.getX()) +
                    (pc.emplacement.getY() - emplacement.getY()) * (pc.emplacement.getY() - emplacement.getY())));//(
            if (dist < 8) {
                panic();
            } else {
                blinky();
            }

    }

    /**
     * choisi une destination aléatoire pour tenter d'échapper au pacman
     */
    public void panic(){
        Random rand= new Random();
        int x= rand.nextInt(emplacement.model.getX());
        int y= rand.nextInt(emplacement.model.getY());
        destination=emplacement.model.getCase(x,y);
    }

    /**
     * fait osciller le fantome d'état lorsqu'il se fait manger par pacman
     */
    public void die(){
        dead=true;
        gum=false;
        destination=emplacement.model.getCase(desti[0],desti[1]+2);
    }
}
