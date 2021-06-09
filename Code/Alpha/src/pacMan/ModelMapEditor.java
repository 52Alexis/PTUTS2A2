package pacMan;

import javax.print.DocFlavor;
import java.util.ArrayList;

public class ModelMapEditor {
    protected Pacman pacman;
    protected ArrayList<FantomeEditor> listFantome;
    protected int nPoint;
    protected Case[][] cases;

    public ModelMapEditor(){
        listFantome=new ArrayList<>();
        pacman=null;
        nPoint=0;
        cases=new Case[28][32];
        for(int i=0;i<28;i++){
            for(int j=0;j<32;j++){
                cases[i][j]=new Case(i,j,"0 ",null);
            }
        }
    }

    public void newFant(Case c, int type){
        listFantome.add(new FantomeEditor(c,type,new int[2]));
    }

    public FantomeEditor searchFantome(Case c){
        for(FantomeEditor f: listFantome){
            if(f.emplacement==c){
                return f;
            }
        }
        return null;
    }

    public ArrayList<FantomeEditor> getAllFantome(int type){
        ArrayList<FantomeEditor> listreturn=new ArrayList<>();
        for(FantomeEditor f:listFantome){
            if(f.type==type){
                listreturn.add(f);
            }
        }
        return listreturn;
    }

    public void videCase(Case c){
        if(c.mobile!=null) {
            c.mobile.emplacement = null;
            c.mobile = null;
        }
    }

    public void removeType(Case c){
        c.setTypeMur("0 ");
    }

    public void checkIntegrity(){
        boolean status=false;
        Case repere=null;
        ArrayList<FantomeEditor> listBlinky=getAllFantome(1);
        if(listBlinky.size()==0) {
            return;
        }
        for(FantomeEditor f:listBlinky){
            if (cases[f.emplacement.x][f.emplacement.y + 1].getTypeMur().equals("P ")) {
                status = true;
                repere=f.emplacement;
                break;
            }
        }
        if(!status){
            return;
        }
        if(pacman==null){
            return;
        }
        if(nPoint<1){
            return;
        }
        export(repere);
    }

    public void export(Case repere){
        StringBuilder builder = new StringBuilder();
        builder.append("28 31\n");
        builder.append((1+listFantome.size())).append("\n");
        builder.append(pacman.emplacement.x).append(" ").append(pacman.emplacement.y).append("\n");
        for(FantomeEditor f:listFantome){
            builder.append(f.emplacement.x).append(" ").append(f.emplacement.y).append("\n");
        }
        for(FantomeEditor f:listFantome){
            builder.append(f.type).append(" ");
        }
        builder.append('\n');
        builder.append(maptostring());
        builder.append(repere.x).append(" ").append(repere.y).append('\n');
        System.out.print(builder.toString());

    }

    public String maptostring(){
        StringBuilder builder=new StringBuilder();
        for(int j=0;j<32;j++){
            for (int i=0;i<28;i++){
                builder.append(cases[i][j].typeMur).append(" ");
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
