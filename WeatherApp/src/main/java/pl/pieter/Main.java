package pl.pieter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONObject;
import pl.pieter.weather.library.WeatherClient;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        WeatherClient weatherClient = new WeatherClient();
        try {
            JSONObject jsonObject = weatherClient.getWeatherDataByCityName("Rzeszów");
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());
        stage.setScene(scene);
        stage.show();
    }

}