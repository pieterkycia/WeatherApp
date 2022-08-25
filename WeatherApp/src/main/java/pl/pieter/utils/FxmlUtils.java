package pl.pieter.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import pl.pieter.controller.BaseController;

import java.io.IOException;

public class FxmlUtils {

    public static Pane loadFxmlFile(BaseController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(FxmlUtils.class.getResource(controller.getFxmlPath()));
        fxmlLoader.setController(controller);

        Pane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }
}
