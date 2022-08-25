package pl.pieter.model;

import pl.pieter.weather.library.HourlyData;

public class HourlyDataModelFx {

    private HourlyData hourlyData;

    public HourlyDataModelFx(HourlyData hourlyData) {
        this.hourlyData = hourlyData;
    }

    public HourlyData getHourlyData() {
        return hourlyData;
    }

    public boolean hasDt(int index) {
        return getDt(index) != -1L;
    }

    public long getDt(int index) {
        return hourlyData.getHourDataList().get(index).getDt();
    }

    public boolean hasIcon(int index) {
        return !getIcon(index).contains("-1");
    }

    public String getIcon(int index) {
        return hourlyData.getHourDataList().get(index).getWeather().getIcon();
    }

    public boolean hasTempMax(int index) {
        return getTempMax(index) != -1.0f;
    }

    public float getTempMax(int index) {
        return hourlyData.getHourDataList().get(index).getTemp();
    }

    public boolean hasDescription(int index) {
        return !getDescription(index).contains("-1");
    }

    public String getDescription(int index) {
        return hourlyData.getHourDataList().get(index).getWeather().getDescription();
    }
}
