package pl.pieter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
        scrollPane.setMaxWidth(815);
//        scrollPane.setMaxWidth(813.6);
        scrollPane.setMinWidth(104);
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
                createTempMaxLabel(index),
                createDescriptionLabel(index)
        );
        return vBox;
    }

    private Label createDtLabel(int index) {
        String dateString = DateUtils.getDayName(dailyDataModelFx.getDt(index));
        Label label = new Label(dateString);
        label.setTextFill(Paint.valueOf("white"));

        return label;
    }

    private ImageView createIconImageView(int index) {
        ImageView imageView = new ImageView(new Image(setIcon(index)));

        return imageView;
    }

    private Label createTempMaxLabel(int index) {
        char degreeSign = 176;
        Label label = new Label(String.valueOf(Math.round(dailyDataModelFx.getTempDay(index)) + " " + degreeSign + viewManager.getUnit()));
        label.setTextFill(Paint.valueOf("white"));

        return label;
    }

    private Label createDescriptionLabel(int index) {
        Label label = new Label(dailyDataModelFx.getDescription(index));
        label.setTextFill(Paint.valueOf("white"));
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
