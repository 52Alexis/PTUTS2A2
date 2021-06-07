package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ModelMap {
    protected File file;
    protected Case[][] cases;
    protected int tailleCase;
    protected final int X;
    protected final int Y;
    protected Pacman pacman;
    protected int t;
    protected int temps;

    public ModelMap(File file) throws FileNotFoundException {
        t=0;
        temps=0;
        this.file=file;
        Scanner input= new Scanner(file);
        String txt= input.next();
        X=Integer.parseInt(txt);
        txt= input.next();
        Y=Integer.parseInt(txt);
        txt= input.next();
        int posXpac=Integer.parseInt(txt);
        txt= input.next();
        int posYpac=Integer.parseInt(txt);
        cases = new Case[X][Y];
        tailleCase = 16;
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++) {
                try{
                    txt= input.next();
                }catch (Exception e){
                    System.out.println("fin de lecture");
                }
                cases[j][i] = new Case(j, i,txt, this);
            }
        }
        pacman=new Pacman(cases[posXpac][posYpac]);
        cases[posXpac][posYpac].mobile=pacman;
    }

    public Case[][] getCases() {
        return cases;
    }

    public Case getCase(int x,int y){
        return cases[x][y];
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public int getTailleCase() {
        return tailleCase;
    }

    public void setTailleCase(int tailleCase) {
        this.tailleCase = tailleCase;
    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void increment(){
        t++;
        temps=t*(1000/24);
    }

    public int getT(){
        return t;
    }
}
