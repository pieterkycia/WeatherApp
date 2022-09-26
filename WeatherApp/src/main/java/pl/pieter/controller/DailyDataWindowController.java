package pl.pieter.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pl.pieter.model.DailyDataModelFx;
import pl.pieter.utils.DateUtils;
import pl.pieter.view.ViewManager;

public class DailyDataWindowController extends BaseController {

    private static final int FIRST_ITEM = 0;
    private DailyDataModelFx dailyDataModelFx;

    public DailyDataWindowController(ViewManager viewManager, String fxmlPath) {
        super(viewManager, fxmlPath);
        this.dailyDataModelFx = this.viewManager.getWeatherManager().getWeatherDataModelFx().getDailyDataModelFx();
    }

    @FXML
    private HBox dataHBox;

    @FXML
    private Pane dataPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void nextButtonOnAction() {
        double scrollHValue = 100 / (dataPane.getWidth() - scrollPane.getWidth());
        scrollPane.setHvalue(scrollPane.getHvalue() + scrollHValue);
    }

    @FXML
    void prevButtonOnAction() {
        double scrollHValue = 100 / (dataPane.getWidth() - scrollPane.getWidth());
        scrollPane.setHvalue(scrollPane.getHvalue() - scrollHValue);
    }

    public void initialize() {
        setUpScrollPane();
        setUpDataHBox();
        setStyle((VBox) dataHBox.getChildren().get(FIRST_ITEM));
        viewManager.loadDayDetailsDataWindow(FIRST_ITEM);
    }

    private void setUpScrollPane() {
        scrollPane.setMaxWidth(802);
        scrollPane.setMinWidth(100);
    }

    private void setUpDataHBox() {
        dataHBox.getChildren().clear();
        int size = dailyDataModelFx.getDailyData().getDayDataList().size();
        for (int i = 0; i < size; i++) {
            VBox vBox = createVBox(i);
            dataHBox.getChildren().add(vBox);
        }
    }

    private VBox createVBox(int index) {
        VBox vBox = new VBox();
        vBox.setId(String.valueOf(index));
        vBox.setPrefWidth(100);
        vBox.setPadding(new Insets(10));
        clearStyle(vBox);

        vBox.setOnMouseClicked(mouseEvent -> {
            dataHBox.getChildren().forEach(node -> {
                clearStyle((VBox) node);
            });
            setStyle(vBox);
            viewManager.loadDayDetailsDataWindow(index);
        });

        vBox.getChildren().addAll(
                createDtLabel(index),
                createIconImageView(index),
                createHboxWithTempsValue(index),
                createDescriptionLabel(index)
        );
        return vBox;
    }

    private HBox createHboxWithTempsValue(int index) {
        HBox hBox = new HBox();
        Label label = new Label("/");
        label.setFont(Font.font(30));

        hBox.getChildren().addAll(
                createTempDayLabel(index),
                label,
                createTempNightLabel(index));
        return hBox;
    }

    private Label createTempDayLabel(int index) {
        Label label = new Label(String.valueOf(Math.round(dailyDataModelFx.getTempDay(index))));
        label.setTextFill(Paint.valueOf("#FFFFFF"));
        label.setFont(Font.font(20));
        label.setPadding(new Insets(5, 0, 0, 0));

        return label;
    }

    private Label createTempNightLabel(int index) {
        Label label = new Label(String.valueOf(Math.round(dailyDataModelFx.getTempNight(index))));
        label.setTextFill(Paint.valueOf("#AAAAAA"));
        label.setFont(Font.font(15));
        label.setPadding(new Insets(15, 0, 0, 0));

        return label;
    }

    private Label createDtLabel(int index) {
        String dateString = DateUtils.getDayName(dailyDataModelFx.getDt(index));
        Label label = new Label(dateString);
        label.setTextFill(Paint.valueOf("#FFFFFF"));
        label.setFont(Font.font(15));

        return label;
    }

    private ImageView createIconImageView(int index) {
        ImageView imageView = new ImageView(new Image(setIcon(index)));

        return imageView;
    }

    private Label createDescriptionLabel(int index) {
        Label label = new Label(dailyDataModelFx.getDescription(index));
        label.setTextFill(Paint.valueOf("#FFFFFF"));
        label.setWrapText(true);

        return label;
    }

    private void clearStyle(VBox vBox) {
        vBox.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBox.setOpacity(1);
    }

    private void setStyle(VBox vBox) {
        vBox.setBorder(new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBox.setOpacity(0.8);
    }

    private String setIcon(int index) {
        String iconPath = "/pl/pieter/icon/empty.png";
        if (dailyDataModelFx.hasIcon(index)) {
            iconPath = "/pl/pieter/icon/" + dailyDataModelFx.getIcon(index) + ".png";
        }
        return iconPath;
    }
}
