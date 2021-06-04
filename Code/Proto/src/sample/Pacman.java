package sample;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pacman extends Mobile {
    protected int direction; //1 up, 2 droite, 3 down, 4 left

    public Pacman(Case emplacement){
        super(emplacement,1);
    }



    public void move(){
        System.out.println(emplacement+"; direction :"+direction);
        Case nxtcase;
        int posX= emplacement.getX();
        int posY= emplacement.getY();
        switch (direction) {
            case 1 -> nxtcase = emplacement.model.getCase(posX,posY-1);
            case 2 -> nxtcase = emplacement.model.getCase(posX+1,posY);
            case 3 -> nxtcase = emplacement.model.getCase(posX,posY+1);
            case 4 -> nxtcase = emplacement.model.getCase(posX-1,posY);
            default -> {
                return;
            }
        }
        if (nxtcase.isMur()){
            direction=0;
            return;
        }else{
            emplacement.mobile=null;
            emplacement=nxtcase;
            nxtcase.mobile=this;
        }

    }


}
