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

    public boolean hasId(int index) {
        return getId(index) != -1;
    }

    public long getId(int index) {
        return hourlyData.getHourDataList().get(index).getWeather().getId();
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

    public boolean hasHumidity(int index) {
        return getHumidity(index) != -1;
    }

    public int getHumidity(int index) {
        return hourlyData.getHourDataList().get(index).getHumidity();
    }

    public boolean hasWindDegree(int index) {
        return getWindDegree(index) != -1;
    }

    public int getWindDegree(int index) {
        return hourlyData.getHourDataList().get(index).getWindDeg();
    }

    public boolean hasWindSpeed(int index) {
        return getWindSpeed(index) != -1;
    }

    public float getWindSpeed(int index) {
        return hourlyData.getHourDataList().get(index).getWindSpeed();
    }
}
