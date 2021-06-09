package pacMan;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ModelMapEditor {
    protected Pacman pacman;
    protected ArrayList<FantomeEditor> listFantome;
    protected int nPoint;
    protected Case[][] cases;
    protected File file;

    public ModelMapEditor(){
        listFantome=new ArrayList<>();
        pacman=null;
        nPoint=0;
        cases=new Case[28][32];
        file=null;
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

    public void export(Case repere, String txt){
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

        String PATH = "data/";
        file=new File(PATH +txt+".map");
        try {
            file.createNewFile();
            FileWriter writer=new FileWriter(PATH +txt+".map");
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
