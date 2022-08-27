package pl.pieter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import pl.pieter.model.HourlyDataModelFx;
import pl.pieter.utils.DateUtils;
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
    }

    private VBox createVBox(int index) {
        VBox vBox = new VBox();
        vBox.setId(String.valueOf(index));
        vBox.setPrefWidth(101);

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
        label.setTextFill(Paint.valueOf("white"));

        return label;
    }

    private ImageView createIconImageView(int index) {
        ImageView imageView = new ImageView(new Image(setIcon(index)));

        return imageView;
    }

    private Label createTempMaxLabel(int index) {
        Label label = new Label(String.valueOf(Math.round(hourlyDataModelFx.getTempMax(index)) + " " + viewManager.getUnit()));
        label.setTextFill(Paint.valueOf("white"));

        return label;
    }

    private Label createDescriptionLabel(int index) {
        Label label = new Label(hourlyDataModelFx.getDescription(index));
        label.setTextFill(Paint.valueOf("white"));
        label.setWrapText(true);

        return label;
    }

    private void setUpScrollPane() {
        scrollPane.setMaxWidth(813.6);
        scrollPane.setMinWidth(104);
    }

    private void setUpDataHBox() {
        dataHBox.getChildren().clear();
        int size = hourlyDataModelFx.getHourlyData().getHourDataList().size();

        for (int i = 0; i < size; i++) {
            VBox vBox = createVBox(i);
            dataHBox.getChildren().add(vBox);
        }
    }

    private String setIcon(int index) {
        String iconPath = "/pl/pieter/icon/empty.png";
        if (hourlyDataModelFx.hasIcon(index)) {
            iconPath = "/pl/pieter/icon/" + hourlyDataModelFx.getIcon(index) + ".png";
        }
        return iconPath;
    }
}
