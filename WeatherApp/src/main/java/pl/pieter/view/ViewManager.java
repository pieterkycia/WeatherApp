
package pl.pieter.view;

import javafx.scene.layout.VBox;
import pl.pieter.WeatherManager;
import pl.pieter.controller.AlertsDataWindowController;
import pl.pieter.controller.BaseController;
import pl.pieter.controller.MainWindowController;
import pl.pieter.utils.FxmlUtils;

public class ViewManager {

    private static final int FIRST_ITEM = 0;

    private static final int ALERTS_DATA_VBOX = 0;
    private static final int CURRENT_DATA_HBOX = 1;
    private static final int DAILY_DATA_VBOX = 2;
    private static final int HOURLY_DATA_VBOX = 3;
    private static final int DAY_DETAILS_DATA_VBOX = 4;

    private WeatherManager weatherManager;
    private MainWindowController mainWindowController;

    public ViewManager(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
        this.mainWindowController = new MainWindowController(this, "/pl/pieter/fxml/MainWindow.fxml");
    }

    public WeatherManager getWeatherManager() {
        return weatherManager;
    }

    public VBox loadMainWindow() {
        BaseController controller = this.mainWindowController;
        return (VBox) FxmlUtils.loadFxmlFile(controller);
    }

    public void loadAlertsDataWindow() {
        BaseController controller = new AlertsDataWindowController(this, "/pl/pieter/fxml/AlertsDataWindow.fxml");
        mainWindowController.setDataVBox(ALERTS_DATA_VBOX, controller);
    }

}
