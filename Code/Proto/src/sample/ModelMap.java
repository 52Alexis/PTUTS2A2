package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModelMap {
    protected File file;
    protected Case[][] cases;
    protected int tailleCase;
    protected final int X;
    protected final int Y;
    protected int t;
    protected int temps;
    protected ArrayList<int[]> listPos;
    protected ArrayList<Mobile> listMobile;

    public ModelMap(File file) throws FileNotFoundException {
        t=0;
        temps=0;
        this.file=file;
        listPos=new ArrayList<>();
        listMobile=new ArrayList<>();
        Scanner input= new Scanner(file);
        String txt= input.next();
        X=Integer.parseInt(txt);
        txt= input.next();
        Y=Integer.parseInt(txt);
        txt= input.next();
        int nEntite=Integer.parseInt(txt);
        for(int i=0;i<nEntite;i++) {
            listPos.add(new int[2]);
            txt = input.next();
            listPos.get(i)[0] = Integer.parseInt(txt);
            txt = input.next();
            listPos.get(i)[1] = Integer.parseInt(txt);
        }
        int[] tabTypeFantome=new int[nEntite-1];
        for(int i=0;i<tabTypeFantome.length;i++){
            txt = input.next();
            tabTypeFantome[i]=Integer.parseInt(txt);
        }
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
        listMobile.add(new Pacman(cases[listPos.get(0)[0]][listPos.get(0)[1]]));
        listMobile.get(0).emplacement.mobile=listMobile.get(0);
        for(int i=1;i<listMobile.size();i++){
            listMobile.add(new Fantome(cases[listPos.get(i)[0]][listPos.get(i)[1]],tabTypeFantome[i-1]));
            listMobile.get(i).emplacement.mobile=listMobile.get(0);
        }
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
        return (Pacman)listMobile.get(0);
    }

    public Fantome getFantome(int id){
        return (Fantome)listMobile.get(id+1);
    }

    public void increment(){
        t++;
        temps=(t*(1000/24))/1000;
    }

    public int getT(){
        return t;
    }

    public int getTemps(){
        return temps;
    }
}
