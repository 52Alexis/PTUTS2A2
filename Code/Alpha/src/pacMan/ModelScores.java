package pacMan;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 */
public class ModelScores {
    protected ArrayList<String> scores;
    protected final String PATH_TO_SCORES = "user/scores.txt";

    public ModelScores() {
        readScores();
    }

    /**
     * Lit les scores stockés dans user
     */
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
    }

    /**
     * Trie les scores
     */
    public void updateScores(){
        ArrayList<Integer> listeScore=new ArrayList<>();
        File file = new File(PATH_TO_SCORES);
        for (String str:scores){
            listeScore.add(Integer.parseInt(str.substring(8)));
        }
        int[] tabScoreTriee = new int[listeScore.size()];
        for(int i=0;i<listeScore.size();i++){
            tabScoreTriee[i]=listeScore.get(i);
        }
        Arrays.sort(tabScoreTriee);
        ArrayList<Integer> listeScoreTriee=new ArrayList<>();
        for(int i=0;i<listeScore.size();i++){
            listeScoreTriee.add(tabScoreTriee[i]);
        }
        Collections.reverse(listeScoreTriee);
        ArrayList<String> nScore=new ArrayList<>(scores);
        for(int i=0;i<listeScore.size();i++){
            nScore.remove(listeScoreTriee.indexOf(listeScore.get(i)));
            nScore.add(listeScoreTriee.indexOf(listeScore.get(i)),scores.get(i));
        }
        scores=nScore;

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