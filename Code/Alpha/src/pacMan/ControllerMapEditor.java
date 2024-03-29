package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Controller de l'editeur de carte
 */
public class ControllerMapEditor implements EventHandler<ActionEvent> {
    ModelMapEditor modelMapEditor;
    ViewMapEditor viewMapEditor;
    int type;

    public ControllerMapEditor(ModelMapEditor modelMapEditor, ViewMapEditor viewMapEditor) {
        this.modelMapEditor = modelMapEditor;
        this.viewMapEditor = viewMapEditor;
        type=0;
        this.viewMapEditor.setController(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image img;
                Node source = mouseEvent.getPickResult().getIntersectedNode();
                Integer colIndex = GridPane.getColumnIndex(source) ;
                Integer rowIndex = GridPane.getRowIndex(source);
                System.out.println(type);
                switch (type) {
                    //type 0 à 9, positionnement de mur
                    //type 10, positionnement du pacman
                    //type 11 à 14, positionnement de fantomes
                    case 0 ->     //VIDE
                            clean(colIndex, rowIndex); //vide tout contenu de la cellule
                    case 1 -> {     //HORI
                        img = viewMapEditor.listImage[0];//change l'image de la case pour mettre celle appropropriée
                        clean(colIndex, rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("EO");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 2 -> {     //VERTI
                        img = viewMapEditor.listImage[3];
                        clean(colIndex, rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("NS");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 3 -> {     //BASGAUCHE
                        img = viewMapEditor.listImage[1];
                        clean(colIndex, rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("NE");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 4 -> {     //BASDROITE
                        img = viewMapEditor.listImage[2];
                        clean(colIndex, rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("NO");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 5 -> {     //HAUTGAUCHE
                        img = viewMapEditor.listImage[7];
                        clean(colIndex, rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("SE");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 6 -> {     //HAUTDROIT
                        img = viewMapEditor.listImage[8];
                        clean(colIndex, rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("SO");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 7 -> {     //PORTE
                        img = viewMapEditor.listImage[4];
                        clean(colIndex, rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("P ");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 8 -> {     //GUM
                        img = viewMapEditor.listImage[5];
                        clean(colIndex, rowIndex);
                        modelMapEditor.nPoint++;
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("PG");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 9 -> {     //POINT
                        img = viewMapEditor.listImage[6];
                        clean(colIndex, rowIndex);
                        modelMapEditor.nPoint++;//incrémente le compteur de points posés sur la map
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("PO");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 10 -> {    //PACMAN
                        img = viewMapEditor.listImage[9];
                        clean(colIndex, rowIndex);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        if (modelMapEditor.pacman != null) {//change de position le pacman si déjà positionner --> il ne peut y avoir qu'un pacman sur la carte
                            Pacman pac = modelMapEditor.pacman;
                            clean(pac.emplacement.getX(), pac.emplacement.getY());
                            modelMapEditor.pacman = null;
                        }
                        modelMapEditor.pacman = new Pacman(modelMapEditor.cases[colIndex][rowIndex]);
                    }
                    case 11 -> {    //BLINKY
                        img = viewMapEditor.listImage[11];
                        clean(colIndex, rowIndex);
                        modelMapEditor.newFant(modelMapEditor.cases[colIndex][rowIndex], 1); //créée un fantome
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 12 -> {    //PINKY
                        img = viewMapEditor.listImage[12];
                        clean(colIndex, rowIndex);
                        modelMapEditor.newFant(modelMapEditor.cases[colIndex][rowIndex], 2);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 13 -> {    //INKY
                        img = viewMapEditor.listImage[13];
                        clean(colIndex, rowIndex);
                        modelMapEditor.newFant(modelMapEditor.cases[colIndex][rowIndex], 3);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                    case 14 -> {    //CLYDE
                        img = viewMapEditor.listImage[14];
                        clean(colIndex, rowIndex);
                        modelMapEditor.newFant(modelMapEditor.cases[colIndex][rowIndex], 4);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                    }
                }
            }
        };
        {//if permettant de définir quel bouton a été actionné
            if (actionEvent.getSource().equals(viewMapEditor.button0)) {
                type = 0;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonEO)) {
                type = 1;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonNS)) {
                type = 2;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonNE)) {
                type = 3;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonNO)) {
                type = 4;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonSE)) {
                type = 5;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonSO)) {
                type = 6;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonP)) {
                type = 7;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonPG)) {
                type = 8;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonPO)) {
                type = 9;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonPac)) {
                type = 10;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonf1)) {
                type = 11;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonf2)) {
                type = 12;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonf3)) {
                type = 13;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonf4)) {
                type = 14;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonExport)) {
                checkIntegrity(true);
                return;
            }
            if (actionEvent.getSource().equals(viewMapEditor.buttonCheck)) {
                checkIntegrity(false);
                return;
            }
            if(actionEvent.getSource().equals(viewMapEditor.buttonImport)){
                File file=viewMapEditor.throwChooser();
                if(file!=null){
                    try {
                        modelMapEditor.loadMap(file);
                        viewMapEditor.regen();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
            if(actionEvent.getSource().equals(viewMapEditor.buttonBack)){
                ViewMenu viewMenu = new ViewMenu(viewMapEditor.primaryStage);
            }
        }
        viewMapEditor.grid.setOnMouseClicked(mouseEvent);
    }

    private void clean(int i, int j){ //nettoie la case pour changer son contenu
        FantomeEditor f=modelMapEditor.searchFantome(modelMapEditor.cases[i][j]);
        if(f!=null){
            modelMapEditor.listFantome.remove(f);
        }
        if(modelMapEditor.cases[i][j].getTypeMur().equals("PG")||modelMapEditor.cases[i][j].getTypeMur().equals("PO")){
            modelMapEditor.nPoint--;
        }
        viewMapEditor.videCase(viewMapEditor.cases[i][j]);
        modelMapEditor.videCase(modelMapEditor.cases[i][j]);
        modelMapEditor.removeType(modelMapEditor.cases[i][j]);
    }

    public void checkIntegrity(Boolean exportation){ //vérifie que le carte conçue respecte les exigences nécessaire pour un bon fonctionnement du jeu
        Boolean nbl;
        Boolean bl=false;
        Boolean po;
        Boolean pa;
        Boolean nom;
        Case repere=null;
        ArrayList<FantomeEditor> listBlinky=modelMapEditor.getAllFantome(1);
        nbl=listBlinky.size()!=0;

        for(FantomeEditor f:listBlinky){
            if (modelMapEditor.cases[f.emplacement.x][f.emplacement.y + 1].getTypeMur().equals("P ")||modelMapEditor.cases[f.emplacement.x][f.emplacement.y + 1].getTypeMur().equals("P")) {
                bl = true;
                repere=f.emplacement;
                break;
            }
        }
        pa= modelMapEditor.pacman!=null;

        po= modelMapEditor.nPoint >= 1;

        nom= !viewMapEditor.tFOut.getText().equals("");

        if(exportation&&nbl&&bl&&po&&pa&&nom) { //lance l'exportation si le bouton export a été appuyé
            modelMapEditor.export(repere,viewMapEditor.tFOut.getText());
        }
            viewMapEditor.updateIntegrity(nbl, bl, pa, po,nom);

    }
}
