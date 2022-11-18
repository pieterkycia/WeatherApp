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

    private final String DEGREE_SIGN = "\u00B0";
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
        setUpFirstHBox();
        setUpSecondHBox();
        setUpThirdHBox();
        setUpFourthHBox();
        setUpFifthHBox();
    }

    private void setUpFirstHBox() {
        this.currentCityNameLabel.setText(currentDataModelFx.getCityName());
        this.currentCountryLabel.setText(currentDataModelFx.getCountry());
    }

    private void setUpSecondHBox() {
        this.currentIconImageView.setImage(new Image(setIcon()));
        this.currentTempLabel.setText(Math.round(currentDataModelFx.getTemp()) + " " + DEGREE_SIGN + viewManager.getUnit());
    }

    private void setUpThirdHBox() {
        this.currentDescriptionLabel.setText(StringUtils.capitalize(currentDataModelFx.getDescription()));
    }

    private void setUpFourthHBox() {
        this.currentFeelsLikeLabel.setText("Temperatura odczuwalna " + Math.round(currentDataModelFx.getFeelsLike()) + " " + DEGREE_SIGN + viewManager.getUnit());
        this.currentWindSpeedLabel.setText("Wiatr " + Math.round(UnitConverterUtils.convertMetersPerSecondToKilometersPerHour(currentDataModelFx.getWindSpeed())) + " km/h");
        this.currentVisibilityLabel.setText("Widoczność " + currentDataModelFx.getVisibility() + " m");
    }

    private void setUpFifthHBox() {
        this.currentPressureLabel.setText("Ciśnienie " + currentDataModelFx.getPressure() + " hPa");
        this.currentHumidityLabel.setText("Wilgotność " + currentDataModelFx.getHumidity() + " %");
        this.currentDewPointLabel.setText("Temperatura punktu rosy " + Math.round(currentDataModelFx.getDewPoint()) + " " + DEGREE_SIGN + viewManager.getUnit());
    }

    private String setIcon() {
        String iconPath = "/pl/pieter/icon/empty.png";
        if (currentDataModelFx.hasIcon()) {
            iconPath = "/pl/pieter/icon/" + currentDataModelFx.getIcon() + ".png";
        }
        return iconPath;
    }
}

