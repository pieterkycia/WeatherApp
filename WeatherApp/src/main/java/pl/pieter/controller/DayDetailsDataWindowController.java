package pl.pieter.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.weathericons.WeatherIcon;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
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
        uvIndexLabel.setGraphic(drawCircleBarProgress(Math.round(dayData.getUvi() * 6.25f), getUviName()));
        humidityLabel.setGraphic(drawCircleBarProgress(dayData.getHumidity(), "%"));
        windSpeedLabel.setGraphic(drawCircleBarWind((int) (Math.round(dayData.getWindSpeed() * 3.6)), dayData.getWindDeg()));
    }

    private String getUviName() {
        float uvi = dayData.getUvi();

        if (uvi < 3) {
            return "Niskie";
        } else if (uvi < 6) {
            return "Średnie";
        } else if (uvi < 8) {
            return "Wysokie";
        } else if (uvi < 11) {
            return "Bardzo\nWysokie";
        } else {
            return "Ekstremalne";
        }
    }

    private void setUpMoonData() {
        moonriseLabel.setText(simpleDateFormat.format(new Date(dayData.getMoonrise() * 1000)));
        moonsetLabel.setText(simpleDateFormat.format(new Date(dayData.getMoonset() * 1000)));

        moonriseLabel.setGraphic(createGraphic("/pl/pieter/icon/details/moon/moonrise.png", "dayDetailsMoonIcon"));
        moonsetLabel.setGraphic(createGraphic("/pl/pieter/icon/details/moon/moonset.png", "dayDetailsMoonIcon"));

        moonPhaseVBox.getChildren().add(setMoonPhase());
    }

    private void setUpSunData() {
        sunriseLabel.setText(simpleDateFormat.format(new Date(dayData.getSunrise() * 1000)));
        sunsetLabel.setText(simpleDateFormat.format(new Date(dayData.getSunset() * 1000)));

        sunriseLabel.setGraphic(createGraphic("/pl/pieter/icon/details/sun/sunrise.png", "dayDetailsSunIcon"));
        sunsetLabel.setGraphic(createGraphic("/pl/pieter/icon/details/sun/sunset.png", "dayDetailsSunIcon"));
    }

    private void setUpTemperatureData() {
        maxTempLabel.setText(Math.round(dayData.getTemp().getMax()) + " " + degreeSign + viewManager.getUnit());
        minTempLabel.setText(Math.round(dayData.getTemp().getMin()) + " " + degreeSign + viewManager.getUnit());

        maxTempLabel.setGraphic(createGraphic("/pl/pieter/icon/details/temperature/thermometer-warmer.png", "dayDetailsTempIcon"));
        minTempLabel.setGraphic(createGraphic("/pl/pieter/icon/details/temperature/thermometer-colder.png", "dayDetailsTempIcon"));
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
            graphicsContext.fillText(Math.round(progress / 6.25) + "", 57.5, 55);
            graphicsContext.setFont(Font.font(12));
            graphicsContext.fillText("\n" + text, 57.5, 55);
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
        int moonPhase = (int) Math.round((dayData.getMoonPhase() * 100) / step);

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
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(moonIconHBox, description);

        return vBox;
    }

    private HBox setCurrentPhase(int number) {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("moonPhaseIconBright");

        hBox.getChildren().add(MoonPhase.values()[number].getIcon());

        return hBox;
    }

    private HBox setPreviousPhase(int number) {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("moonPhaseIconDark");

        if (number == 0) {
            number = (MoonPhase.values().length - 1);
        } else if (number == 1) {
            number = MoonPhase.values().length;
        }
        hBox.getChildren().addAll(
                MoonPhase.values()[number - 2].getIcon(),
                MoonPhase.values()[number - 1].getIcon()
        );
        return hBox;
    }

    private HBox setNextPhase(int number) {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("moonPhaseIconDark");

        if (number == (MoonPhase.values().length - 2)) {
            number = -1;
        } else if (number == (MoonPhase.values().length - 1)) {
            number = 0;
        }
        hBox.getChildren().addAll(
                MoonPhase.values()[number + 1].getIcon(),
                MoonPhase.values()[number + 2].getIcon()
        );
        return hBox;
    }

    public enum MoonPhase {
        MOON_NEW("Nów", "moon-new.png"),
        MOON_WAXING_CRESCENT("Przychodzący sierp", "moon-waxing-crescent.png"),
        MOON_FIRST_QUARTER("Pierwsza kwadra", "moon-first-quarter.png"),
        MOON_WAXING_GIBBOUS("Przychodzący garb", "moon-waxing-gibbous.png"),
        MOON_FULL("Pełnia", "moon-full.png"),
        MOON_WANING_GIBBOUS("Zanikający garb", "moon-waning-gibbous.png"),
        MOON_3RD_QUARTER("Trzecia kwadra", "moon-last-quarter.png"),
        MOON_WANING_CRESCENT("Zanikający sierp", "moon-waning-crescent.png"),
        MOON_NEW_2("Nów", "moon-new.png");

        private String description;
        private String iconPath;

        MoonPhase(String description, String iconPath) {
            this.description = description;
            this.iconPath = iconPath;
        }

        public String getDescription() {
            return description;
        }

        public HBox getIcon() {
            HBox hBox = new HBox(new ImageView(new Image(getClass().getResourceAsStream("/pl/pieter/icon/details/moon/" + this.iconPath))));
            hBox.getStyleClass().add("moonPhaseIcon");
            return hBox;
        }
    }

    private HBox createGraphic(String graphicPath, String styleClass) {
        HBox hBox = new HBox(new ImageView(new Image(getClass().getResourceAsStream(graphicPath))));
        hBox.getStyleClass().add(styleClass);

        return hBox;
    }
}
