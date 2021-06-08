package sample;

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
            for (int i = 0; i<4;i++){
                scores.add(i,String.valueOf(reader.readLine()));
                //System.out.println("Read : " + configTouches.get(i));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateScores(){
        File file = new File(PATH_TO_SCORES);
        
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
