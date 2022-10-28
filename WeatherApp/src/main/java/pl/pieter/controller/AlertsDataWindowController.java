package pl.pieter.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.pieter.model.AlertsDataModelFx;
import pl.pieter.view.ViewManager;


public class AlertsDataWindowController extends BaseController {

    private AlertsDataModelFx alertsDataModelFx;

    public AlertsDataWindowController(ViewManager viewManager, String fxmlPath) {
        super(viewManager, fxmlPath);
        this.alertsDataModelFx = this.viewManager.getWeatherManager().getWeatherDataModelFx().getAlertsDataModelFx();
    }

    @FXML
    private VBox dataVBox;

    @FXML
    public void initialize() {
        setUpDataVBox();
    }

    private void setUpDataVBox() {
        dataVBox.getChildren().clear();
        int size = alertsDataModelFx.getAlertsData().getAlertDataList().size();
        for (int i = 0; i < size; i++) {
            VBox vBox = new VBox();
            vBox.getStyleClass().add("alert");
            VBox.setMargin(vBox, new Insets(5));

            for (int j = 0; j < alertsDataModelFx.getTags(i).size(); j++) {
                Label label = new Label(alertsDataModelFx.getTags(i).get(j));
                vBox.getChildren().add(label);
            }
            setDataVBoxBackground(vBox, alertsDataModelFx.getEvent(i));
            dataVBox.getChildren().add(vBox);
        }
    }

    private void setDataVBoxBackground(Node node, String event) {
        if (event.toLowerCase().contains("red")) {
            node.getStyleClass().add("redAlert");
        } else if (event.toLowerCase().contains("yellow")) {
            node.getStyleClass().add("yellowAlert");
        } else if (event.toLowerCase().contains("orange")) {
            node.getStyleClass().add("orangeAlert");
        } else {
            // green color
            node.getStyleClass().add("greenAlert");
        }
    }
}
