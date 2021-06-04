package sample;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pacman extends Mobile implements KeyListener {
    protected int direction; //1 up, 2 droite, 3 down, 4 left

    public Pacman(Case emplacement){
        super(emplacement,1);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_Z:
                direction=1;
                break;
            case KeyEvent.VK_D:
                direction=2;
                break;
            case KeyEvent.VK_S:
                direction=3;
                break;
            case KeyEvent.VK_Q:
                direction=4;
                break;
            default:
                break;
        }
        move();
    }

    public void keyReleased(KeyEvent e) {

    }

    public void move(){
        boolean mur=false;
        Case nxtcase;
        do {
            int pos=emplacement.getX()*emplacement.getY();
            switch (direction) {
                case 1 -> nxtcase = emplacement.model.getCase(pos - emplacement.model.getY());
                case 2 -> nxtcase = emplacement.model.getCase(pos + 1);
                case 3 -> nxtcase = emplacement.model.getCase(pos + emplacement.model.getY());
                case 4 -> nxtcase = emplacement.model.getCase(pos - 1);
                default -> {
                    nxtcase = null;
                    mur = true;
                }
            }
            if (!mur&&nxtcase.isMur()){
                mur=true;
            }else{
                emplacement=nxtcase;
            }
        }while (!mur);
        direction=0;
    }


}
