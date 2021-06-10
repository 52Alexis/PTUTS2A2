package pacMan;

import java.io.*;
import java.util.ArrayList;

/**
 * Modèle logique gérant l'affectation des touches
 */
public class ModelParametres {
    protected ArrayList<String> configTouches;
    protected final String PATH_TO_CONFIG = "user/config.cfg";

    public ModelParametres() {
        readConfig();
    }

    /**
     * lit la configuration utilisateur
     */
    public void readConfig(){
        File file = new File(PATH_TO_CONFIG);
        configTouches = new ArrayList<>(4);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (int i = 0; i<4;i++){
                configTouches.add(i,String.valueOf(reader.readLine()));
                //System.out.println("Read : " + configTouches.get(i));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * met à jour la configuration utilisateur
     */
    public void updateConfig(){
        File file = new File(PATH_TO_CONFIG);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i<getConfigTouches().size(); i++){
                writer.write(getConfigTouches().get(i));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getConfigTouches() {
        return configTouches;
    }

    public void setConfigTouches(ArrayList<String> configTouches) {
        this.configTouches = configTouches;
    }
}
