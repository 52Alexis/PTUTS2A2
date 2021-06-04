package sample;

import javafx.stage.Stage;

public class ControllerMenu {
    ModelMenu modelMenu;
    ViewMenu viewMenu;

    public ControllerMenu(ModelMenu modelMenu, ViewMenu viewMenu) {
        this.modelMenu = modelMenu;
        this.viewMenu = viewMenu;
    }
}
