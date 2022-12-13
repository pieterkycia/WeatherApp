package pl.pieter.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pl.pieter.model.HourlyDataModelFx;
import pl.pieter.utils.AnimationUtils;
import pl.pieter.utils.DateUtils;
import pl.pieter.utils.StringUtils;
import pl.pieter.view.ViewManager;

public class HourlyDataWindowController extends BaseController {

    private HourlyDataModelFx hourlyDataModelFx;

    public HourlyDataWindowController(ViewManager viewManager, String fxmlPath) {
        super(viewManager, fxmlPath);
        this.hourlyDataModelFx = this.viewManager.getWeatherManager().getWeatherDataModelFx().getHourlyDataModelFx();
    }

    @FXML
    private HBox dataHBox;

    @FXML
    private Pane dataPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    @FXML
    void nextButtonOnAction() {
        double scrollStep = scrollPane.getWidth() / Math.abs(scrollPane.getWidth() - dataPane.getWidth());

        Animation scrollAnimation = AnimationUtils.scrollNextAnimation(scrollPane, scrollStep, 0.5);
        scrollAnimation.play();

        scrollAnimation.setOnFinished(actionEvent -> {
            changeButtonStyle();
        });
    }

    @FXML
    void prevButtonOnAction() {
        double scrollStep = scrollPane.getWidth() / Math.abs(scrollPane.getWidth() - dataPane.getWidth());

        Animation scrollAnimation = AnimationUtils.scrollPrevAnimation(scrollPane, scrollStep, 0.5);
        scrollAnimation.play();

        scrollAnimation.setOnFinished(actionEvent -> {
            changeButtonStyle();
        });
    }

    public void initialize() {
        setUpScrollPane();
        setUpDataHBox();
        dataPane.autosize();
        setUpPrevButton();
        setUpNextButton();
    }

    private VBox createVBox(int index) {
        VBox vBox = new VBox();
        vBox.setId(String.valueOf(index));
        vBox.setPrefWidth(100);
        vBox.setPadding(new Insets(10));
        vBox.getStyleClass().add("clearVbox");

        vBox.getChildren().addAll(
                createDtLabel(index),
                createIconImageView(index),
                createTempMaxLabel(index),
                createDescriptionLabel(index)
        );
        return vBox;
    }

    private Label createDtLabel(int index) {
        String dateString = DateUtils.getHour(hourlyDataModelFx.getDt(index));
        Label label = new Label(dateString);
        label.setTextFill(Paint.valueOf("#FFFFFF"));
        label.setFont(Font.font(15));

        return label;
    }

    private ImageView createIconImageView(int index) {
        ImageView imageView = new ImageView(new Image(setIcon(index)));

        return imageView;
    }

    private Label createTempMaxLabel(int index) {
        Label label = new Label(String.valueOf(Math.round(hourlyDataModelFx.getTempMax(index))) + " \u00B0C");
        label.setTextFill(Paint.valueOf("FFFFFF"));
        label.setFont(Font.font(20));

        return label;
    }

    private Label createDescriptionLabel(int index) {
        Label label = new Label(StringUtils.capitalize(hourlyDataModelFx.getDescription(index)));
        label.setTextFill(Paint.valueOf("#FFFFFF"));
        label.setWrapText(true);

        return label;
    }

    private void setUpScrollPane() {
        scrollPane.setMaxWidth(802);
        scrollPane.setMinWidth(100);
        scrollPane.widthProperty().addListener(changeListener());
    }

    private void setUpDataHBox() {
        dataHBox.getChildren().clear();
        int size = hourlyDataModelFx.getHourlyData().getHourDataList().size();

        for (int i = 0; i < size; i++) {
            VBox vBox = createVBox(i);
            dataHBox.getChildren().add(vBox);
        }
    }

    private void setUpNextButton() {
        Text nextButtonIcon = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_CIRCLE_ALT_RIGHT, "30px");
        nextButtonIcon.getStyleClass().add("hourlyWindowIcons");

        nextButton.getStyleClass().add("hourlyWindowButtons");
        nextButton.setGraphic(nextButtonIcon);
        nextButton.setAlignment(Pos.CENTER);
        nextButton.setPrefHeight(36.8);
    }

    private void setUpPrevButton() {
        Text prevButtonIcon = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_CIRCLE_ALT_LEFT, "30px");
        prevButtonIcon.getStyleClass().add("hourlyWindowIcons");

        prevButton.getStyleClass().add("hourlyWindowButtons");
        prevButton.setGraphic(prevButtonIcon);
        prevButton.setAlignment(Pos.CENTER);
        prevButton.setPrefHeight(36.8);
    }

    private String setIcon(int index) {
        String iconPath = "/pl/pieter/icon/not-available.png";
        String timeOfDay = "day";
        if (isEveningTime(index)) {
            timeOfDay = "night";
        }
        if (hourlyDataModelFx.hasId(index)) {
            iconPath = "/pl/pieter/icon/" + timeOfDay + "/" + hourlyDataModelFx.getId(index) + ".png";
        }
        return iconPath;
    }

    private boolean isEveningTime(int index) {
        int currentDt = DateUtils.getHourInt(hourlyDataModelFx.getDt(index));
        int eveningTime = 20;
        int morningTime = 5;
        if (currentDt >= eveningTime || currentDt < morningTime) {
            return true;
        }
        return false;
    }

    private ChangeListener<Number> changeListener() {
        return new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                changeButtonStyle();
            }
        };
    }

    private void changeButtonStyle() {
        if (scrollPane.getWidth() >= dataPane.getWidth()) {
            nextButton.setDisable(true);
            prevButton.setDisable(true);
            return;
        }
        if (scrollPane.getHvalue() == scrollPane.getHmin()) {
            prevButton.setDisable(true);
        } else {
            prevButton.setDisable(false);
        }
        if (scrollPane.getHvalue() == scrollPane.getHmax()) {
            nextButton.setDisable(true);
        } else {
            nextButton.setDisable(false);
        }
    }
}
