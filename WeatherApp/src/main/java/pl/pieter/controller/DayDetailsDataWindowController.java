package pl.pieter.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import de.jensd.fx.glyphs.weathericons.WeatherIcons;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private VBox moonPhaseVBox;

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
        moonPhaseVBox.getChildren().add(setMoonPhase());

        rainLabel.setText(String.valueOf(Math.round(dayData.getPop() * 100)) + " %");

        uvIndexLabel.setText(String.valueOf(Math.round(dayData.getUvi())));

        humidityLabel.setText(String.valueOf(dayData.getHumidity()) + " %");

        windSpeedLabel.setText(String.valueOf(Math.round(dayData.getWindSpeed()) + " m/s"));
    }

    private VBox setMoonPhase() {
        double step = 100.0 / (MoonPhase.values().length - 1);
        int moonPhase = (int) Math.round((dayData.getMoonPhase() * 100) / step);

        VBox moonIconVBox = new VBox(MoonPhase.values()[moonPhase].getIcon());

        Label description = new Label(MoonPhase.values()[moonPhase].getDescription());
        description.setTextFill(Paint.valueOf("#FFFFFF"));
        description.setFont(Font.font(14));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(moonIconVBox, description);

        return vBox;
    }

    private enum MoonPhase {
        MOON_NEW("Nów", GlyphsDude.createIcon(WeatherIcons.MOON_NEW, "30px")),
        MOON_WAXING_CRESCENT_1("Przychadzący sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_CRESENT_1, "30px")),
        MOON_WAXING_CRESCENT_2("Przychadzący sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_CRESENT_2, "30px")),
        MOON_WAXING_CRESCENT_3("Przychadzący sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_CRESENT_3, "30px")),
        MOON_WAXING_CRESCENT_4("Przychadzący sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_CRESENT_4, "30px")),
        MOON_WAXING_CRESCENT_5("Przychadzący sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_CRESENT_5, "30px")),
        MOON_WAXING_CRESCENT_6("Przychadzący sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_CRESENT_6, "30px")),

        MOON_FIRST_QUARTER("Pierwsza kwadra", GlyphsDude.createIcon(WeatherIcons.MOON_FIRST_QUARTER, "30px")),

        MOON_WAXING_GIBBOUS_1("Przychodzący garb", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_GIBBOUS_1, "30px")),
        MOON_WAXING_GIBBOUS_2("Przychodzący garb", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_GIBBOUS_1, "30px")),
        MOON_WAXING_GIBBOUS_3("Przychodzący garb", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_GIBBOUS_1, "30px")),
        MOON_WAXING_GIBBOUS_4("Przychodzący garb", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_GIBBOUS_1, "30px")),
        MOON_WAXING_GIBBOUS_5("Przychodzący garb", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_GIBBOUS_1, "30px")),
        MOON_WAXING_GIBBOUS_6("Przychodzący garb", GlyphsDude.createIcon(WeatherIcons.MOON_WAXING_GIBBOUS_1, "30px")),

        MOON_FULL("Pełnia", GlyphsDude.createIcon(WeatherIcons.MOON_FULL, "30px")),

        MOON_WANING_GIBBOUS_1("Zanikający garb", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_GIBBOUS_1, "30px")),
        MOON_WANING_GIBBOUS_2("Zanikający garb", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_GIBBOUS_1, "30px")),
        MOON_WANING_GIBBOUS_3("Zanikający garb", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_GIBBOUS_1, "30px")),
        MOON_WANING_GIBBOUS_4("Zanikający garb", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_GIBBOUS_1, "30px")),
        MOON_WANING_GIBBOUS_5("Zanikający garb", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_GIBBOUS_1, "30px")),
        MOON_WANING_GIBBOUS_6("Zanikający garb", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_GIBBOUS_1, "30px")),

        MOON_3RD_QUARTER("Trzecia kwadra", GlyphsDude.createIcon(WeatherIcons.MOON_3RD_QUARTER, "30px")),

        MOON_WANING_CRESCENT_1("Zanikający sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_CRESCENT_1, "30px")),
        MOON_WANING_CRESCENT_2("Zanikający sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_CRESCENT_1, "30px")),
        MOON_WANING_CRESCENT_3("Zanikający sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_CRESCENT_1, "30px")),
        MOON_WANING_CRESCENT_4("Zanikający sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_CRESCENT_1, "30px")),
        MOON_WANING_CRESCENT_5("Zanikający sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_CRESCENT_1, "30px")),
        MOON_WANING_CRESCENT_6("Zanikający sierp", GlyphsDude.createIcon(WeatherIcons.MOON_WANING_CRESCENT_1, "30px")),

        MOON_NEW_2("Zanikający sierp", GlyphsDude.createIcon(WeatherIcons.MOON_NEW, "30px"));

        private String description;
        private Text icon;

        MoonPhase(String description, Text icon) {
            this.description = description;
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public Text getIcon() {
            return icon;
        }
    }
}
