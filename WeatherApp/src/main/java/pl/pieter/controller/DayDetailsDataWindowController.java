package pl.pieter.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.weathericons.WeatherIcon;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pl.pieter.utils.IconsUtils;
import pl.pieter.utils.SvgGlyphUtils;
import pl.pieter.view.ViewManager;
import pl.pieter.weather.library.DailyData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayDetailsDataWindowController extends BaseController {

    private DailyData.DayData dayData;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("  HH:mm");
    private static final char degreeSign = 176;

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
        setUpTemperatureData();
        setUpSunData();
        setUpMoonData();
        setUpOtherData();
    }

    private void setUpOtherData() {
        rainLabel.setGraphic(drawCircleBarProgress(Math.round(dayData.getPop() * 100), "%"));
        uvIndexLabel.setGraphic(drawCircleBarProgress(Math.round(dayData.getUvi()), "\nNiskie"));
        humidityLabel.setGraphic(drawCircleBarProgress(dayData.getHumidity(), "%"));
        windSpeedLabel.setGraphic(drawCircleBarWind((int) (Math.round(dayData.getWindSpeed() * 3.6)), dayData.getWindDeg()));
    }

    private void setUpMoonData() {
        Text moonRiseIcon = GlyphsDude.createIcon(WeatherIcon.MOONRISE, "25px");
        moonRiseIcon.getStyleClass().add("dayDetailsIcons");

        Text moonSetIcon = GlyphsDude.createIcon(WeatherIcon.MOONSET, "25px");
        moonSetIcon.getStyleClass().add("dayDetailsIcons");

        moonriseLabel.setText(simpleDateFormat.format(new Date(dayData.getMoonrise() * 1000)));
        moonriseLabel.setGraphic(moonRiseIcon);
        moonsetLabel.setText(simpleDateFormat.format(new Date(dayData.getMoonset() * 1000)));
        moonsetLabel.setGraphic(moonSetIcon);

        moonPhaseVBox.getChildren().add(setMoonPhase());
    }

    private void setUpSunData() {
        Text sunRiseIcon = GlyphsDude.createIcon(WeatherIcon.SUNRISE, "25px");
        sunRiseIcon.getStyleClass().add("dayDetailsIcons");

        Text sunSetIcon = GlyphsDude.createIcon(WeatherIcon.SUNSET, "25px");
        sunSetIcon.getStyleClass().add("dayDetailsIcons");

        sunriseLabel.setText(simpleDateFormat.format(new Date(dayData.getSunrise() * 1000)));
        sunriseLabel.setGraphic(sunRiseIcon);

        sunsetLabel.setText(simpleDateFormat.format(new Date(dayData.getSunset() * 1000)));
        sunsetLabel.setGraphic(sunSetIcon);
    }

    private void setUpTemperatureData() {
        maxTempLabel.setText("  " + Math.round(dayData.getTemp().getMax()) + " " + degreeSign + viewManager.getUnit());
        minTempLabel.setText("  " + Math.round(dayData.getTemp().getMin()) + " " + degreeSign + viewManager.getUnit());

        Region maxTempIcon = new SvgGlyphUtils(IconsUtils.TEMPERATURE_MAX.getSvgPath(), 20, 30);
        maxTempIcon.setRotate(180);
        maxTempIcon.getStyleClass().add("svgPathIcons");

        Region minTempIcon = new SvgGlyphUtils(IconsUtils.TEMPERATURE_MIN.getSvgPath(), 20, 30);
        minTempIcon.setRotate(180);
        minTempIcon.getStyleClass().add("svgPathIcons");

        maxTempLabel.setGraphic(maxTempIcon);
        minTempLabel.setGraphic(minTempIcon);
    }

    private Canvas drawCircleBarProgress(int progress, String text) {
        Canvas canvas = new Canvas(115, 115);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setLineWidth(7);
        graphicsContext.setFont(Font.font(20));
        graphicsContext.setTextAlign(TextAlignment.CENTER);

        graphicsContext.setStroke(Color.valueOf("#4B5BF2"));
        graphicsContext.strokeArc(17.5, 17.5, 80, 80, 0, 360, ArcType.OPEN);

        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.strokeArc(17.5, 17.5, 80, 80, 90, ((-progress * 3.6)), ArcType.OPEN);

        if (text == "" || text == "%") {
            graphicsContext.fillText(progress + text, 57.5, 65);
        } else {
            graphicsContext.fillText(progress + "", 57.5, 55);
            graphicsContext.setFont(Font.font(12));
            graphicsContext.fillText(text, 57.5, 55);
        }
        return canvas;
    }

    private Canvas drawCircleBarWind(int progress, int degree) {
        Canvas canvas = new Canvas(115, 115);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setLineWidth(7);
        graphicsContext.setFont(Font.font(20));
        graphicsContext.setTextAlign(TextAlignment.CENTER);

        graphicsContext.strokeArc(17.5, 17.5, 80, 80, 0, 360, ArcType.OPEN);

        graphicsContext.setStroke(Color.valueOf("#4B5BF2"));
        double deg = 28.5;
        for (int i = 0; i < 8; i++) {
            graphicsContext.strokeArc(17.5, 17.5, 80, 80, deg, 34, ArcType.OPEN);
            deg += 45;
        }

        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.strokeArc(17.5, 17.5, 80, 80, getWindDegree(degree), 34, ArcType.OPEN);

        graphicsContext.fillText(progress + "", 57.5, 55);
        graphicsContext.setFont(Font.font(12));
        graphicsContext.fillText("km/h", 57.5, 72);

        graphicsContext.setFont(Font.font(10));
        graphicsContext.fillText("Z", 5, 62.5);
        graphicsContext.fillText("W", 110, 62.5);
        graphicsContext.fillText("PN", 57.5, 10);
        graphicsContext.fillText("PD", 57.5, 112);

        return canvas;
    }

    private double getWindDegree(int degree) {
        if (degree <= 22.5) {
            return 253.5;
        } else if (degree <= 67.5) {
            return 208.5;
        } else if (degree <= 112.5) {
            return 163.5;
        } else if (degree <= 157.5) {
            return 118.5;
        } else if (degree <= 202.5) {
            return 73.5;
        } else if (degree <= 247.5) {
            return 28.5;
        } else if (degree <= 292.5) {
            return 343.5;
        } else if (degree <= 337.5) {
            return 298.5;
        } else {
            return 253.5;
        }
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
