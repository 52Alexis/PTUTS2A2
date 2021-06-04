package sample;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pacman extends Mobile {
    protected int direction; //1 up, 2 droite, 3 down, 4 left
    protected Case old;

    public Pacman(Case emplacement){
        super(emplacement,1);
    }



    public void move(){
        System.out.println(emplacement+"; direction :"+direction);
        Case nxtcase;
        int posX= emplacement.getX();
        int posY= emplacement.getY();
        switch (direction) {
            case 1:
                try{
                    nxtcase = emplacement.model.getCase(posX, posY - 1);
                } catch (Exception e){
                    nxtcase=emplacement.model.getCase(posX,emplacement.model.getY()-1);
                }
                break;
            case 2:
                try{
                    nxtcase = emplacement.model.getCase(posX + 1, posY);
                } catch (Exception e){
                    nxtcase=emplacement.model.getCase(0,posY);
                }
                break;
            case 3:
                try{
                    nxtcase = emplacement.model.getCase(posX, posY + 1);
                } catch (Exception e){
                    nxtcase=emplacement.model.getCase(posX,0);
                }
                break;
            case 4:
                try {
                    nxtcase = emplacement.model.getCase(posX - 1, posY);
                } catch(Exception e){
                    nxtcase=emplacement.model.getCase(emplacement.model.getX()-1,posY);
                }
                break;
            default:
                return;
        }
        if (nxtcase.isMur()){
            direction=0;
        }else{
            emplacement.mobile=null;
            emplacement=nxtcase;
            nxtcase.mobile=this;
        }

    }


}
