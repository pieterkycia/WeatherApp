package pl.pieter.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pl.pieter.model.CurrentDataModelFx;
import pl.pieter.utils.DateUtils;
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
    private VBox currentIconVBox;

    @FXML
    private Label currentPressureLabel;

    @FXML
    private Label currentTempLabel;

    @FXML
    private Label currentUnitLabel;

    @FXML
    private Label currentVisibilityLabel;

    @FXML
    private Label currentWindDegreeLabel;

    @FXML
    private Label currentWindDescriptionLabel;

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
        this.currentIconVBox.getChildren().add(new ImageView(new Image(setIcon())));
        this.currentTempLabel.setText(Math.round(currentDataModelFx.getTemp()) + " " + DEGREE_SIGN + viewManager.getUnit());
    }

    private void setUpThirdHBox() {
        this.currentDescriptionLabel.setText(StringUtils.capitalize(currentDataModelFx.getDescription()));
    }

    private void setUpFourthHBox() {
        this.currentFeelsLikeLabel.setText("Temperatura odczuwalna " + Math.round(currentDataModelFx.getFeelsLike()) + " " + DEGREE_SIGN + viewManager.getUnit());
        setWind();
        setVisibility();
    }

    private void setUpFifthHBox() {
        this.currentPressureLabel.setText("Ciśnienie " + currentDataModelFx.getPressure() + " hPa");
        this.currentHumidityLabel.setText("Wilgotność " + currentDataModelFx.getHumidity() + " %");
        this.currentDewPointLabel.setText("Temperatura punktu rosy " + Math.round(currentDataModelFx.getDewPoint()) + " " + DEGREE_SIGN + viewManager.getUnit());
    }

    private void setVisibility() {
        int visibility = currentDataModelFx.getVisibility();
        if (visibility < 1000) {
            this.currentVisibilityLabel.setText("Widoczność " + visibility + " m");
        } else {
            this.currentVisibilityLabel.setText("Widoczność " + UnitConverterUtils.convertMetersToKilometers(visibility) + " km");
        }
    }

    private void setWind() {
        this.currentWindDescriptionLabel.setText("Wiatr ");
        this.currentWindDegreeLabel.setGraphic(setWindDegreeGraphic(currentDataModelFx.getWindDegree()));
        this.currentWindSpeedLabel.setText(Math.round(UnitConverterUtils.convertMetersPerSecondToKilometersPerHour(currentDataModelFx.getWindSpeed())) + " km/h");
    }

    private Text setWindDegreeGraphic(int windDegree) {
        Text text = GlyphsDude.createIcon(FontAwesomeIcon.LOCATION_ARROW, "15px");
        text.getStyleClass().add("windDegreeIcons");
        text.setRotate(135 + windDegree);
        return text;
    }

    private String setIcon() {
        String iconPath = "/pl/pieter/icon/not-available.png";
        String timeOfDay = "day";
        if (isEveningTime()) {
            timeOfDay = "night";
        }
        if (currentDataModelFx.hasId()) {
            iconPath = "/pl/pieter/icon/" + timeOfDay + "/" + currentDataModelFx.getId() + ".png";
        }
        return iconPath;
    }

    private boolean isEveningTime() {
        int currentDt = DateUtils.getHourInt(currentDataModelFx.getDt());
        int eveningTime = 20;
        int morningTime = 5;
        if (currentDt >= eveningTime || currentDt < morningTime) {
            return true;
        }
        return false;
    }
}

