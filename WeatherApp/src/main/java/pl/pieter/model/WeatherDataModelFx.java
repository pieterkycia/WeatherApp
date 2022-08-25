package pl.pieter.model;

import pl.pieter.weather.library.WeatherClient;
import pl.pieter.weather.library.WeatherData;

import java.io.IOException;

public class WeatherDataModelFx {

    private WeatherClient weatherClient;
    private AlertsDataModelFx alertsDataModelFx;
    private CurrentDataModelFx currentDataModelFx;
    private DailyDataModelFx dailyDataModelFx;
    private HourlyDataModelFx hourlyDataModelFx;

    public WeatherDataModelFx(String cityName) throws IOException, InterruptedException {
        this.weatherClient = new WeatherClient();
        WeatherData weatherData = new WeatherData(weatherClient.getWeatherDataByCityName(cityName));
        this.alertsDataModelFx = new AlertsDataModelFx(weatherData.getAlerts());
        this.currentDataModelFx = new CurrentDataModelFx(weatherData.getCurrent(), weatherData.getCityName(), weatherClient.getUnit().getShortName());
        this.dailyDataModelFx = new DailyDataModelFx(weatherData.getDaily());
        this.hourlyDataModelFx = new HourlyDataModelFx(weatherData.getHourly());
    }

    public WeatherClient getWeatherClient() {
        return weatherClient;
    }

    public AlertsDataModelFx getAlertsDataModelFx() {
        return alertsDataModelFx;
    }

    public CurrentDataModelFx getCurrentDataModelFx() {
        return currentDataModelFx;
    }

    public DailyDataModelFx getDailyDataModelFx() {
        return dailyDataModelFx;
    }

    public HourlyDataModelFx getHourlyDataModelFx() {
        return hourlyDataModelFx;
    }
}
