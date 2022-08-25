package pl.pieter.model;

import pl.pieter.weather.library.CurrentData;

public class CurrentDataModelFx {

    private CurrentData currentData;
    private String cityName;
    private String unit;

    public CurrentDataModelFx(CurrentData currentData, String cityName, String unit) {
        this.currentData = currentData;
        this.cityName = cityName;
        this.unit = unit;
    }

    public boolean hasCityName() {
        return !cityName.contains("-1");
    }

    public String getCityName() {
        return cityName;
    }

    public boolean hasUnit() {
        return !unit.contains("-1");
    }

    public String getUnit() {
        return unit;
    }

    public boolean hasIcon() {
        return !getIcon().contains("-1");
    }

    public String getIcon() {
        return currentData.getWeather().getIcon();
    }

    public boolean hasTemp() {
        return getTemp() != -1.0f;
    }

    public float getTemp() {
        return currentData.getTemp();
    }

    public boolean hasDescription() {
        return !getDescription().contains("-1");
    }

    public String getDescription() {
        return currentData.getWeather().getDescription();
    }

    public boolean hasFeelsLike() {
        return getFeelsLike() != -1.0f;
    }

    public float getFeelsLike() {
        return currentData.getFeelsLike();
    }

    public boolean hasWindSpeed() {
        return getWindSpeed() != -1.0f;
    }

    public float getWindSpeed() {
        return currentData.getWindSpeed();
    }

    public boolean hasVisibility() {
        return getVisibility() != -1;
    }

    public int getVisibility() {
        return currentData.getVisibility();
    }

    public boolean hasPressure() {
        return getPressure() != -1;
    }

    public int getPressure() {
        return currentData.getPressure();
    }

    public boolean hasHumidity() {
        return getHumidity() != -1;
    }

    public int getHumidity() {
        return currentData.getHumidity();
    }

    public boolean hasDewPoint() {
        return getDewPoint() != -1.0f;
    }

    public float getDewPoint() {
        return currentData.getDewPoint();
    }

}