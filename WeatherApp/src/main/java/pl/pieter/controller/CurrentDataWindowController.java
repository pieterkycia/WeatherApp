package pl.pieter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.pieter.model.CurrentDataModelFx;
import pl.pieter.utils.StringUtils;
import pl.pieter.utils.UnitConverterUtils;
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
    private Label currentCountryLabel;

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
        this.currentCountryLabel.setText(currentDataModelFx.getCountry());
        this.currentIconImageView.setImage(new Image(setIcon()));
        this.currentTempLabel.setText(String.valueOf(Math.round(currentDataModelFx.getTemp()) + " \u00B0" + viewManager.getUnit()));
        this.currentDescriptionLabel.setText(StringUtils.capitalize(currentDataModelFx.getDescription()));
        this.currentFeelsLikeLabel.setText("Temperatura odczuwalna " + String.valueOf(Math.round(currentDataModelFx.getFeelsLike()) + " \u00B0" + viewManager.getUnit()));
        this.currentWindSpeedLabel.setText("Wiatr " + String.valueOf(Math.round(UnitConverterUtils.metersPerSecondToKilometersPerHour(currentDataModelFx.getWindSpeed()))) + " km/h");
        this.currentVisibilityLabel.setText("Widoczność " + String.valueOf(currentDataModelFx.getVisibility()) + " m");
        this.currentPressureLabel.setText("Ciśnienie " + String.valueOf(currentDataModelFx.getPressure()) + " hPa");
        this.currentHumidityLabel.setText("Wilgotność " + String.valueOf(currentDataModelFx.getHumidity()) + " %");
        this.currentDewPointLabel.setText("Temperatura punktu rosy " + String.valueOf(Math.round(currentDataModelFx.getDewPoint()) + " \u00B0" + viewManager.getUnit()));
    }

    private String setIcon() {
        String iconPath = "/pl/pieter/icon/empty.png";
        if (currentDataModelFx.hasIcon()) {
            iconPath = "/pl/pieter/icon/" + currentDataModelFx.getIcon() + ".png";
        }
        return iconPath;
    }
}

