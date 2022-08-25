
package pl.pieter.view;

import javafx.scene.layout.VBox;
import pl.pieter.WeatherManager;
import pl.pieter.controller.*;
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

    public void loadCurrentDataWindow() {
        BaseController controller = new CurrentDataWindowController(this, "/pl/pieter/fxml/CurrentDataWindow.fxml");
        mainWindowController.setDataVBox(CURRENT_DATA_HBOX, controller);
    }

    public void loadDailyDataWindow() {
        BaseController controller = new DailyDataWindowController(this, "/pl/pieter/fxml/DailyDataWindow.fxml");
        mainWindowController.setDataVBox(DAILY_DATA_VBOX, controller);
    }

    public void loadHourlyDataWindow() {
        BaseController controller = new HourlyDataWindowController(this, "/pl/pieter/fxml/HourlyDataWindow.fxml");
        mainWindowController.setDataVBox(HOURLY_DATA_VBOX, controller);
    }

    public void loadDayDetailsDataWindow(int index) {
        BaseController controller = new DayDetailsDataWindowController(this, "/pl/pieter/fxml/DayDetailsDataWindow.fxml", index);
        mainWindowController.setDataVBox(DAY_DETAILS_DATA_VBOX, controller);
    }
}
