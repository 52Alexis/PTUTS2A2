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
//                Node imgSource = viewMapEditor.buttonNS.getGraphic();
//                ImageView imageView = (ImageView) imgSource;
//                Image img = imageView.getImage();
//                Rectangle r = new Rectangle();
//                r.setFill(new ImagePattern(img));
                Integer colIndex = GridPane.getColumnIndex(source) ;
                Integer rowIndex = GridPane.getRowIndex(source);
//                viewMapEditor.grid.add(r, colIndex, rowIndex);
                System.out.println(type);
                switch (type){
                    case 0:     //VIDE
                        clean(colIndex,rowIndex);
                        break;
                    case 1:     //HORI
                        img=viewMapEditor.listImage[0];
                        clean(colIndex,rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("EO");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 2:     //VERTI
                        img=viewMapEditor.listImage[3];
                        clean(colIndex,rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("NS");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 3:     //BASGAUCHE
                        img=viewMapEditor.listImage[1];
                        clean(colIndex,rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("NE");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 4:     //BASDROITE
                        img=viewMapEditor.listImage[2];
                        clean(colIndex,rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("NO");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 5:     //HAUTGAUCHE
                        img=viewMapEditor.listImage[7];
                        clean(colIndex,rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("SE");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 6:     //HAUTDROIT
                        img=viewMapEditor.listImage[8];
                        clean(colIndex,rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("SO");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 7:     //PORTE
                        img=viewMapEditor.listImage[4];
                        clean(colIndex,rowIndex);
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("P ");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 8:     //GUM
                        img=viewMapEditor.listImage[5];
                        clean(colIndex,rowIndex);
                        modelMapEditor.nPoint++;
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("PG");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 9:     //POINT
                        img=viewMapEditor.listImage[6];
                        clean(colIndex,rowIndex);
                        modelMapEditor.nPoint++;
                        modelMapEditor.cases[colIndex][rowIndex].setTypeMur("PO");
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 10:    //PACMAN
                        img=viewMapEditor.listImage[9];
                        clean(colIndex,rowIndex);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        if(modelMapEditor.pacman!=null){
                            Pacman pac= modelMapEditor.pacman;
                            clean(pac.emplacement.getX(),pac.emplacement.getY());
                            pac.emplacement.mobile=null;
                            modelMapEditor.pacman=null;
                        }
                        modelMapEditor.pacman=new Pacman(modelMapEditor.cases[colIndex][rowIndex]);
                        break;
                    case 11:    //BLINKY
                        img=viewMapEditor.listImage[11];
                        clean(colIndex,rowIndex);
                        modelMapEditor.newFant(modelMapEditor.cases[colIndex][rowIndex],1);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 12:    //PINKY
                        img=viewMapEditor.listImage[12];
                        clean(colIndex,rowIndex);
                        modelMapEditor.newFant(modelMapEditor.cases[colIndex][rowIndex],2);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 13:    //INKY
                        img=viewMapEditor.listImage[13];
                        clean(colIndex,rowIndex);
                        modelMapEditor.newFant(modelMapEditor.cases[colIndex][rowIndex],2);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                    case 14:    //CLYDE
                        img=viewMapEditor.listImage[14];
                        clean(colIndex,rowIndex);
                        modelMapEditor.newFant(modelMapEditor.cases[colIndex][rowIndex],2);
                        viewMapEditor.cases[colIndex][rowIndex].setFill(new ImagePattern(img));
                        break;
                }
            }
        };
        {
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
                modelMapEditor.checkIntegrity();
                return;
            }
        }
        viewMapEditor.grid.setOnMouseClicked(mouseEvent);
    }

    private void clean(int i, int j){
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
}
