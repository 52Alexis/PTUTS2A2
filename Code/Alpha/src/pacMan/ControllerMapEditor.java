package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ControllerMapEditor implements EventHandler<ActionEvent> {
    ModelMapEditor modelMapEditor;
    ViewMapEditor viewMapEditor;

    public ControllerMapEditor(ModelMapEditor modelMapEditor, ViewMapEditor viewMapEditor) {
        this.modelMapEditor = modelMapEditor;
        this.viewMapEditor = viewMapEditor;
        viewMapEditor.setController(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Node source = (Node) mouseEvent.getSource();
                System.out.println(mouseEvent.getSource());
                Node imgSource = viewMapEditor.buttonStraight.getGraphic();
                ImageView imageView = (ImageView) imgSource;
                Image img = imageView.getImage();
                Rectangle r = new Rectangle();
                r.setFill(new ImagePattern(img));
                Integer colIndex = viewMapEditor.paneMapEdited.getColumnIndex(source);
                Integer rowIndex = viewMapEditor.paneMapEdited.getRowIndex(source);
                viewMapEditor.paneMapEdited.add(r, colIndex, rowIndex);
                viewMapEditor.paneMapEdited.requestFocus();
            }
        };
        if (actionEvent.getSource().equals(viewMapEditor.buttonStraight)){
            viewMapEditor.paneMapEdited.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent);
            viewMapEditor.paneMapEdited.removeEventFilter(MouseEvent.MOUSE_CLICKED,mouseEvent );
        }
    }
}
