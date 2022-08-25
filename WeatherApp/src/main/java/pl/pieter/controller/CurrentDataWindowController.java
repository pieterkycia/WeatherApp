package pl.pieter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.pieter.model.CurrentDataModelFx;
import pl.pieter.view.ViewManager;

public class CurrentDataWindowController extends BaseController {

    private CurrentDataModelFx currentDataModelFx;

    public CurrentDataWindowController(ViewManager viewManager, String fxmlPath) {
        super(viewManager, fxmlPath);
        this.currentDataModelFx = this.viewManager.getWeatherManager().getWeatherDataModelFx().getCurrentDataModelFx();
    }

    @FXML
    private Label currentCityNameLabel;

    @FXML
    private Label currentDescriptionLabel;

    @FXML
    private Label currentDewPointLabel;

    @FXML
    private Label currentFeelsLikeLabel;

    @FXML
    private Label currentHumidityLabel;

    @FXML
    private ImageView currentIconImageView;

    @FXML
    private Label currentPressureLabel;

    @FXML
    private Label currentTempLabel;

    @FXML
    private Label currentUnitLabel;

    @FXML
    private Label currentVisibilityLabel;

    @FXML
    private Label currentWindSpeedLabel;

    public void initialize() {
        this.currentCityNameLabel.setText(currentDataModelFx.getCityName());

        this.currentIconImageView.setImage(new Image(setIcon()));

        this.currentTempLabel.setText(String.valueOf(currentDataModelFx.getTemp()));
        this.currentUnitLabel.setText(currentDataModelFx.getUnit());
        this.currentDescriptionLabel.setText(currentDataModelFx.getDescription());
        this.currentFeelsLikeLabel.setText("Temperatura odczuwalna " + String.valueOf(currentDataModelFx.getFeelsLike()));
        this.currentWindSpeedLabel.setText("Wiatr " + String.valueOf(currentDataModelFx.getWindSpeed()) + " m/s");
        this.currentVisibilityLabel.setText("Widoczność " + String.valueOf(currentDataModelFx.getVisibility()) + " m");
        this.currentPressureLabel.setText("Ciśnienie " + String.valueOf(currentDataModelFx.getPressure()) + " hPa");
        this.currentHumidityLabel.setText("Wilgotność " + String.valueOf(currentDataModelFx.getHumidity()) + " %");
        this.currentDewPointLabel.setText("Temperatura punktu rosy " + String.valueOf(currentDataModelFx.getDewPoint()));
    }

    private String setIcon() {
        String iconPath = "/pl/pieter/icon/empty.png";
        if (currentDataModelFx.hasIcon()) {
            iconPath = "/pl/pieter/icon/" + currentDataModelFx.getIcon() + ".png";
        }
        return iconPath;
    }
}

