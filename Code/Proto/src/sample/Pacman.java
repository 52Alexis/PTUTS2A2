package sample;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pacman extends Mobile {
    protected int direction; //1 up, 2 droite, 3 down, 4 left
    protected int lastDirection;
    protected int nextDirection;
    protected static int vies;
    protected static int score;
    protected int nFantome;

    public static void setVies(int vies) {
        Pacman.vies = vies;
    }

    public static int getVies() {
        return vies;
    }

    public static void setScore(int score) {
        Pacman.score = score;
    }

    public static int getScore() {
        return score;
    }

    public Pacman(Case emplacement){
        super(emplacement,1);
        lastDirection=0;
        nextDirection=0;
        nFantome=0;
    }



    public void move(){
        //System.out.println(emplacement+"; nextdirection :"+nextDirection+"; direction :"+direction+"; lastdirection :"+lastDirection);
        Case nxtcase=getNxtCase(nextDirection);
        if(nxtcase==null){
            return;
        }
        if(nxtcase.isMur()){
            nxtcase=getNxtCase();
            if(nxtcase==null){
                return;
            }
            if(nxtcase.isMur()){
                direction=lastDirection;
                nxtcase=getNxtCase();
                if(nxtcase==null){
                    return;
                }
                if(nxtcase.isMur()){
                    direction=0;
                }else {
                    exchange(nxtcase);
                }
            }else{
                lastDirection=direction;
                exchange(nxtcase);
            }
        }else{
            direction=nextDirection;
            lastDirection=direction;
            exchange(nxtcase);
        }
        if(emplacement.haveFixe()){
            score+=emplacement.fixe.score;
            emplacement.model.eatPoint(emplacement.getFixe());
        }


    }

    public Case getNxtCase(){
        return getNxtCase(direction);
    }

    public Case getNxtCase(int dir){
        Case nxtcase;
        int posX= emplacement.getX();
        int posY= emplacement.getY();
        switch (dir) {
            case 1:
                try {
                    nxtcase = emplacement.model.getCase(posX, posY - 1);
                } catch (Exception e) {
                    nxtcase = emplacement.model.getCase(posX, emplacement.model.getY() - 1);
                }
                break;
            case 2:
                try {
                    nxtcase = emplacement.model.getCase(posX + 1, posY);
                } catch (Exception e) {
                    nxtcase = emplacement.model.getCase(0, posY);
                }
                break;
            case 3:
                try {
                    nxtcase = emplacement.model.getCase(posX, posY + 1);
                } catch (Exception e) {
                    nxtcase = emplacement.model.getCase(posX, 0);
                }
                break;
            case 4:
                try {
                    nxtcase = emplacement.model.getCase(posX - 1, posY);
                } catch (Exception e) {
                    nxtcase = emplacement.model.getCase(emplacement.model.getX() - 1, posY);
                }
                break;
            default:
                nxtcase=null;
        }
        return nxtcase;
    }

    public void exchange(Case nxtcase){
        emplacement.mobile=null;
        emplacement=nxtcase;
        nxtcase.mobile=this;
    }


}
