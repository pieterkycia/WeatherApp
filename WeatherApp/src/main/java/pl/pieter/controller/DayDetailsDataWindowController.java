package pl.pieter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import pl.pieter.view.ViewManager;
import pl.pieter.weather.library.DailyData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayDetailsDataWindowController extends BaseController {

    private DailyData.DayData dayData;

    public DayDetailsDataWindowController(ViewManager viewManager, String fxmlPath, int index) {
        super(viewManager, fxmlPath);
        this.dayData = viewManager.getWeatherManager().getWeatherDataModelFx().getDailyDataModelFx().getDailyData().getDayDataList().get(index);
    }

    @FXML
    private Label humidityLabel;

    @FXML
    private Label maxTempLabel;

    @FXML
    private Label minTempLabel;

    @FXML
    private ProgressIndicator moonphaseProgressIndicator;

    @FXML
    private Label moonriseLabel;

    @FXML
    private Label moonsetLabel;

    @FXML
    private Label rainLabel;

    @FXML
    private Label sunriseLabel;

    @FXML
    private Label sunsetLabel;

    @FXML
    private Label uvIndexLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private VBox vBoxData;


    @FXML
    public void initialize() {
        char degreeSign = 176;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        maxTempLabel.setText(String.valueOf(Math.round(dayData.getTemp().getMax()) + " " + degreeSign + viewManager.getUnit()));
        minTempLabel.setText(String.valueOf(Math.round(dayData.getTemp().getMin()) + " " + degreeSign + viewManager.getUnit()));


        sunriseLabel.setText(simpleDateFormat.format(new Date(dayData.getSunrise() * 1000)));
        sunsetLabel.setText(simpleDateFormat.format(new Date(dayData.getSunset() * 1000)));

        moonriseLabel.setText(simpleDateFormat.format(new Date(dayData.getMoonrise() * 1000)));
        moonsetLabel.setText(simpleDateFormat.format(new Date(dayData.getMoonset() * 1000)));
        moonphaseProgressIndicator.setProgress(dayData.getMoonPhase());

        rainLabel.setText(String.valueOf(Math.round(dayData.getPop() * 100)) + " %");

        uvIndexLabel.setText(String.valueOf(Math.round(dayData.getUvi())));

        humidityLabel.setText(String.valueOf(dayData.getHumidity()) + " %");

        windSpeedLabel.setText(String.valueOf(Math.round(dayData.getWindSpeed()) + " m/s"));
    }
}
