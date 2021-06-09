package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ControllerMapEditor implements EventHandler<ActionEvent> {
    ModelMapEditor modelMapEditor;
    ViewMapEditor viewMapEditor;

    public ControllerMapEditor(ModelMapEditor modelMapEditor, ViewMapEditor viewMapEditor) {
        this.modelMapEditor = modelMapEditor;
        this.viewMapEditor = viewMapEditor;
        this.viewMapEditor.setController(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("l'envent marche");
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Node source = mouseEvent.getPickResult().getIntersectedNode();
                Node imgSource = viewMapEditor.buttonStraight.getGraphic();
                ImageView imageView = (ImageView) imgSource;
                Image img = imageView.getImage();
                Rectangle r = new Rectangle();
                r.setFill(new ImagePattern(img));
                Integer colIndex = GridPane.getColumnIndex(source) ;
                Integer rowIndex = GridPane.getRowIndex(source);
                viewMapEditor.paneMapEdited.add(r, colIndex, rowIndex);
            }
        };
        if (actionEvent.getSource().equals(viewMapEditor.buttonStraight)){
            System.out.println("clique sur le straight");
            viewMapEditor.paneMapEdited.setOnMouseClicked(mouseEvent);

        }
    }
}
