package pl.pieter.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.weathericons.WeatherIcon;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
        int moonPhase = (int) Math.floor((dayData.getMoonPhase() * 100) / step);

        HBox moonIconHBox = new HBox();
        moonIconHBox.getChildren().addAll(
                setPreviousPhase(moonPhase),
                setCurrentPhase(moonPhase),
                setNextPhase(moonPhase)
        );

        Label description = new Label(MoonPhase.values()[moonPhase].getDescription());
        description.setTextFill(Paint.valueOf("#FFFFFF"));
        description.setFont(Font.font(14));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().addAll(moonIconHBox, description);

        return vBox;
    }

    private HBox setCurrentPhase(int number) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 3, 0, 3));
        hBox.getStyleClass().add("moonPhaseIconBright");

        hBox.getChildren().add(MoonPhase.values()[number].getIcon());

        return hBox;
    }

    private HBox setPreviousPhase(int number) {
        HBox hBox = new HBox(3);
        hBox.getStyleClass().add("moonPhaseIconDark");

        if (number == 0) {
            number = 28;
        } else if (number == 1) {
            number = 29;
        }
        hBox.getChildren().addAll(
                MoonPhase.values()[number - 2].getIcon(),
                MoonPhase.values()[number - 1].getIcon()
        );
        return hBox;
    }

    private HBox setNextPhase(int number) {
        HBox hBox = new HBox(3);
        hBox.getStyleClass().add("moonPhaseIconDark");

        if (number == 27) {
            number = -1;
        } else if (number == 28) {
            number = 0;
        }
        hBox.getChildren().addAll(
                MoonPhase.values()[number + 1].getIcon(),
                MoonPhase.values()[number + 2].getIcon()
        );
        return hBox;
    }

    public enum MoonPhase {
        MOON_NEW("Nów", WeatherIcon.MOON_ALT_FULL),
        MOON_WAXING_CRESCENT_1("Przychadzący sierp", WeatherIcon.MOON_ALT_WANING_GIBBOUS_1),
        MOON_WAXING_CRESCENT_2("Przychadzący sierp", WeatherIcon.MOON_ALT_WANING_GIBBOUS_2),
        MOON_WAXING_CRESCENT_3("Przychadzący sierp", WeatherIcon.MOON_ALT_WANING_GIBBOUS_3),
        MOON_WAXING_CRESCENT_4("Przychadzący sierp", WeatherIcon.MOON_ALT_WANING_GIBBOUS_4),
        MOON_WAXING_CRESCENT_5("Przychadzący sierp", WeatherIcon.MOON_ALT_WANING_GIBBOUS_5),
        MOON_WAXING_CRESCENT_6("Przychadzący sierp", WeatherIcon.MOON_ALT_WANING_GIBBOUS_6),

        MOON_FIRST_QUARTER("Pierwsza kwadra", WeatherIcon.MOON_ALT_THIRD_QUARTER),

        MOON_WAXING_GIBBOUS_1("Przychodzący garb", WeatherIcon.MOON_ALT_WANING_CRESCENT_1),
        MOON_WAXING_GIBBOUS_2("Przychodzący garb", WeatherIcon.MOON_ALT_WANING_CRESCENT_2),
        MOON_WAXING_GIBBOUS_3("Przychodzący garb", WeatherIcon.MOON_ALT_WANING_CRESCENT_3),
        MOON_WAXING_GIBBOUS_4("Przychodzący garb", WeatherIcon.MOON_ALT_WANING_CRESCENT_4),
        MOON_WAXING_GIBBOUS_5("Przychodzący garb", WeatherIcon.MOON_ALT_WANING_CRESCENT_5),
        MOON_WAXING_GIBBOUS_6("Przychodzący garb", WeatherIcon.MOON_ALT_WANING_CRESCENT_6),

        MOON_FULL("Pełnia", WeatherIcon.MOON_ALT_NEW),

        MOON_WANING_GIBBOUS_1("Zanikający garb", WeatherIcon.MOON_ALT_WAXING_CRESCENT_1),
        MOON_WANING_GIBBOUS_2("Zanikający garb", WeatherIcon.MOON_ALT_WAXING_CRESCENT_2),
        MOON_WANING_GIBBOUS_3("Zanikający garb", WeatherIcon.MOON_ALT_WAXING_CRESCENT_3),
        MOON_WANING_GIBBOUS_4("Zanikający garb", WeatherIcon.MOON_ALT_WAXING_CRESCENT_4),
        MOON_WANING_GIBBOUS_5("Zanikający garb", WeatherIcon.MOON_ALT_WAXING_CRESCENT_5),
        MOON_WANING_GIBBOUS_6("Zanikający garb", WeatherIcon.MOON_ALT_WAXING_CRESCENT_6),

        MOON_3RD_QUARTER("Trzecia kwadra", WeatherIcon.MOON_ALT_FIRST_QUARTER),

        MOON_WANING_CRESCENT_1("Zanikający sierp", WeatherIcon.MOON_ALT_WAXING_GIBBOUS_1),
        MOON_WANING_CRESCENT_2("Zanikający sierp", WeatherIcon.MOON_ALT_WAXING_GIBBOUS_2),
        MOON_WANING_CRESCENT_3("Zanikający sierp", WeatherIcon.MOON_ALT_WAXING_GIBBOUS_3),
        MOON_WANING_CRESCENT_4("Zanikający sierp", WeatherIcon.MOON_ALT_WAXING_GIBBOUS_4),
        MOON_WANING_CRESCENT_5("Zanikający sierp", WeatherIcon.MOON_ALT_WAXING_GIBBOUS_5),
        MOON_WANING_CRESCENT_6("Zanikający sierp", WeatherIcon.MOON_ALT_WAXING_GIBBOUS_6),

        MOON_NEW_2("Nów", WeatherIcon.MOON_ALT_FULL);

        private String description;
        private WeatherIcon icon;

        MoonPhase(String description, WeatherIcon icon) {
            this.description = description;
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public Text getIcon() {
            return GlyphsDude.createIcon(icon, "40px");
        }
    }
}
