package pl.pieter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.pieter.utils.FxmlUtils;
import pl.pieter.view.ViewManager;

import java.io.IOException;

public class MainWindowController extends BaseController {

    private static final int ALERTS_DATA_VBOX = 0;
    private static final int CURRENT_DATA_HBOX = 1;
    private static final int DAILY_DATA_VBOX = 2;
    private static final int HOURLY_DATA_VBOX = 3;
    private static final int DAY_DETAILS_DATA_VBOX = 4;

    @FXML
    private TextField cityNameTextField;

    @FXML
    private VBox dataVBox;

    @FXML
    private Button searchButton;

    @FXML
    private Button refreshButton;

    public MainWindowController(ViewManager viewManager, String fxmlPath) {
        super(viewManager, fxmlPath);
    }

    @FXML
    public void initialize() {
        cityNameTextField.setText("korytniki");
        searchOnAction();
    }

    @FXML
    public void refreshOnAction() {
        searchOnAction();
    }

    @FXML
    public void searchOnAction() {
        try {
            viewManager.getWeatherManager().createNewWeatherDataModelFx(cityNameTextField.getText());
            loadAllData();
        } catch (IOException e) {
            System.out.println("IOException");
            setEmptyWindow();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            setEmptyWindow();
        } catch (Exception e) {
            System.out.println("Exception");
            setEmptyWindow();
        }
    }

    private void loadAllData() {
        viewManager.loadAlertsDataWindow();
        viewManager.loadCurrentDataWindow();
        viewManager.loadDailyDataWindow();
        viewManager.loadHourlyDataWindow();
        viewManager.loadDayDetailsDataWindow();
    }

    public void setDataVBox(int index, BaseController controller) {
        dataVBox.getChildren().set(index, FxmlUtils.loadFxmlFile(controller));
    }

    private void clearDataVBox() {
        ((VBox) dataVBox.getChildren().get(ALERTS_DATA_VBOX)).getChildren().clear();
        ((HBox) dataVBox.getChildren().get(CURRENT_DATA_HBOX)).getChildren().clear();
        ((VBox) dataVBox.getChildren().get(DAILY_DATA_VBOX)).getChildren().clear();
        ((VBox) dataVBox.getChildren().get(HOURLY_DATA_VBOX)).getChildren().clear();
        ((VBox) dataVBox.getChildren().get(DAY_DETAILS_DATA_VBOX)).getChildren().clear();
    }

    private void setEmptyWindow() {
        clearDataVBox();
        viewManager.loadEmptyWindow();
    }
}
