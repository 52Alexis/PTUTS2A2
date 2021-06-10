package pacMan;

import java.io.*;
import java.util.ArrayList;

public class ModelScores {
    protected ArrayList<String> scores;
    protected final String PATH_TO_SCORES = "user/scores.txt";

    public ModelScores() {
        readScores();
    }

    public void readScores(){
        File file = new File(PATH_TO_SCORES);
        scores = new ArrayList<>(10);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (int i = 0; i< 10;i++){
                scores.add(i,String.valueOf(reader.readLine()));
                //System.out.println("Read : " + configTouches.get(i));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(scores);
    }

    public void updateScores(){
        File file = new File(PATH_TO_SCORES);
        for (int i =0; i<scores.size();i++){
            int s = Integer.parseInt(scores.get(i).substring(8));
            System.out.println("i"+i);
            System.out.println("s:"+s);
            int j = 0;
            while (s < Integer.parseInt(scores.get(j).substring(8))){
                j++;
                System.out.println("j:"+j);
            }
            if (j>scores.size()){
                j=0;
            }else {
                scores.add(j,scores.get(i));
                System.out.println("score:"+scores.add(scores.get(j)));
                break;
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i<getScores().size(); i++){
                writer.write(getScores().get(i));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getScores() {
        return scores;
    }
}
