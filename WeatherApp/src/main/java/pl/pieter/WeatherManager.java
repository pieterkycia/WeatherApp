package pl.pieter;

import pl.pieter.model.WeatherDataModelFx;
import pl.pieter.view.ViewManager;

import java.io.IOException;

public class WeatherManager {

    private WeatherDataModelFx weatherDataModelFx;
    private ViewManager viewManager;

    public WeatherManager() {
        this.viewManager = new ViewManager(this);
    }

    public WeatherDataModelFx getWeatherDataModelFx() {
        return weatherDataModelFx;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public void createNewWeatherDataModelFx(String cityName) throws IOException, InterruptedException {
        this.weatherDataModelFx = new WeatherDataModelFx(cityName);
    }
}
