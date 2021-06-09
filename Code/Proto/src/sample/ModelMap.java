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
    protected int level;
    protected static int[] bonusValue;
    protected ArrayList<Point> listPoint;
    protected Bonus currentBonus;
    private int nEntite;
    private int[] tabTypeFantome;
    private int[] desti;
    boolean activatedgum=false;
    int tps=0;

    public static void createBonus(){
        bonusValue=new int[8];
        bonusValue[0]=100;
        bonusValue[1]=300;
        bonusValue[2]=500;
        bonusValue[3]=700;
        bonusValue[4]=1000;
        bonusValue[5]=2000;
        bonusValue[6]=3000;
        bonusValue[7]=5000;
    }

    public ModelMap(File file) throws FileNotFoundException {
        t=0;
        temps=0;
        level=1;
        this.file=file;
        listPoint=new ArrayList<>();
        listPos=new ArrayList<>();
        listMobile=new ArrayList<>();
        Pacman.setVies(3);
        Pacman.setScore(0);
        Scanner input= new Scanner(file);
        String txt= input.next();
        X=Integer.parseInt(txt);
        txt= input.next();
        Y=Integer.parseInt(txt);
        txt= input.next();
        nEntite=Integer.parseInt(txt);
        for(int i=0;i<nEntite;i++) {
            listPos.add(new int[2]);
            txt = input.next();
            listPos.get(i)[0] = Integer.parseInt(txt);
            txt = input.next();
            listPos.get(i)[1] = Integer.parseInt(txt);
        }
        tabTypeFantome=new int[nEntite-1];
        for(int i=0;i<tabTypeFantome.length;i++){
            txt = input.next();
            tabTypeFantome[i]=Integer.parseInt(txt);
        }
        desti=new int[2];
        txt = input.next();
        desti[0]=Integer.parseInt(txt);
        txt = input.next();
        desti[1]=Integer.parseInt(txt);
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
                if(txt.equals("PO")){
                    Point po=new Point(cases[j][i],false);
                    listPoint.add(po);
                    cases[j][i].setFixe(po);
                }else{
                    if(txt.equals("PG")){
                        Point po=new Point(cases[j][i],true);
                        listPoint.add(po);
                        cases[j][i].setFixe(po);
                    }
                }
            }
        }
        death();
        input.close();
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
        return (Fantome)listMobile.get(id);
    }

    public Fantome getFantomeByType(int type){
        for(int i=1;i<listMobile.size();i++){
            Fantome f = (Fantome)listMobile.get(i);
            if(f.type==type){
                return f;
            }
        }
        return null;
    }

    public ArrayList<Fantome> getAllFantome(){
        ArrayList<Fantome> listFantome=new ArrayList<>();
        for(int i=1;i< listMobile.size();i++){
            listFantome.add(getFantome(i));
        }
        return listFantome;
    }

    public ArrayList<Mobile> getListMobile(){
        return listMobile;
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

    public void regen() throws FileNotFoundException {
        t=0;
        temps=0;
        listPos=new ArrayList<>();
        listMobile=new ArrayList<>();
        listPoint=new ArrayList<>();
        Scanner input= new Scanner(file);
        String txt= input.next();
        txt= input.next();
        txt= input.next();
        nEntite=Integer.parseInt(txt);
        for(int i=0;i<nEntite;i++) {
            listPos.add(new int[2]);
            txt = input.next();
            listPos.get(i)[0] = Integer.parseInt(txt);
            txt = input.next();
            listPos.get(i)[1] = Integer.parseInt(txt);
        }
        tabTypeFantome=new int[nEntite-1];
        for(int i=0;i<tabTypeFantome.length;i++){
            txt = input.next();
            tabTypeFantome[i]=Integer.parseInt(txt);
        }
        desti=new int[2];
        txt = input.next();
        desti[0]=Integer.parseInt(txt);
        txt = input.next();
        desti[1]=Integer.parseInt(txt);
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
                if(txt.equals("PO")){
                    Point po=new Point(cases[j][i],false);
                    listPoint.add(po);
                    cases[j][i].setFixe(po);
                }else{
                    if(txt.equals("PG")){
                        Point po=new Point(cases[j][i],true);
                        listPoint.add(po);
                        cases[j][i].setFixe(po);
                    }
                }
            }
        }
        death();
        input.close();
    }

    public ArrayList<Point> getListPointDispo() {
        return listPoint;
    }

    public void setBonus(){
        if(temps==20 && currentBonus==null) {
            int i;
            System.out.println(level);
            switch (level) {
                case 1 -> i = 0;
                case 2 -> i = 1;
                case 3, 4 -> i = 2;
                case 5, 6 -> i = 3;
                case 7, 8 -> i = 4;
                case 9, 10 -> i = 5;
                case 11, 12 -> i = 6;
                default -> i = 7;
            }
            System.out.println(i);
            currentBonus = new Bonus(cases[listPos.get(0)[0]][listPos.get(0)[1]], bonusValue[i],20);
            currentBonus.emplacement.setFixe(currentBonus);
        }else{
            if(currentBonus!=null && temps==20+currentBonus.timer){
                currentBonus=null;
            }
        }
    }

    public int searchBonusValue(int score){
        for(int i=0;i<bonusValue.length;i++){
            if(bonusValue[i]==score)return i;
        }
        return 0;
    }

    public Bonus getCurrentBonus() {
        return currentBonus;
    }

    public void death(){
        listMobile=new ArrayList<>();
        listMobile.add(new Pacman(cases[listPos.get(0)[0]][listPos.get(0)[1]]));
        listMobile.get(0).emplacement.mobile=listMobile.get(0);
        for(int i=1;i<nEntite;i++){
            listMobile.add(new Fantome(cases[listPos.get(i)[0]][listPos.get(i)[1]],tabTypeFantome[i-1], desti));
            listMobile.get(i).emplacement.mobile=listMobile.get(0);
        }
        currentBonus=null;
    }

    public void eatPoint(Fixe f){
        if(f.type==0){
            StaticMusic.fxBonus.play();
            currentBonus=null;
            f.emplacement.setFixe(null);
            f.emplacement=null;
        }else{
            Point p=(Point)f;
            if(p.isGum()){
                StaticMusic.musicMain.pause();
                StaticMusic.musicRunaway.stop();
                StaticMusic.musicRunaway.play();
                activatedgum=true;
                tps=0;
                getPacman().nFantome=0;
                for(int i=1;i<nEntite;i++){
                    Fantome fantome=(Fantome)listMobile.get(i);
                    fantome.setGum(true);
                }
            }
            p.emplacement.setFixe(null);
            p.emplacement=null;
            listPoint.remove(p);
        }
    }

    public void nextlvl() throws FileNotFoundException {
        level++;
        regen();
    }

    public int getLevel() {
        return level;
    }
}
