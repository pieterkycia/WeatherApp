
package pl.pieter.view;

import pl.pieter.WeatherManager;

public class ViewManager {

    private static final int FIRST_ITEM = 0;

    private static final int ALERTS_DATA_VBOX = 0;
    private static final int CURRENT_DATA_HBOX = 1;
    private static final int DAILY_DATA_VBOX = 2;
    private static final int HOURLY_DATA_VBOX = 3;
    private static final int DAY_DETAILS_DATA_VBOX = 4;

    private WeatherManager weatherManager;

    public ViewManager(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
    }

    public WeatherManager getWeatherManager() {
        return weatherManager;
    }

}
