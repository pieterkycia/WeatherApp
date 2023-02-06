package pl.pieter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        WeatherManager weatherManager = new WeatherManager();
        VBox parent = weatherManager.getViewManager().loadMainWindow();

        Scene scene = new Scene(parent, 1000, 700);
        stage.setScene(scene);
        stage.show();
    }

}