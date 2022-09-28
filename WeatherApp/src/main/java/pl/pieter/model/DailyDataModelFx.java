package pl.pieter.model;

import pl.pieter.weather.library.DailyData;

public class DailyDataModelFx {

    private DailyData dailyData;

    public DailyDataModelFx(DailyData dailyData) {
        this.dailyData = dailyData;
    }

    public DailyData getDailyData() {
        return dailyData;
    }

    public boolean hasDt(int index) {
        return getDt(index) != -1L;
    }

    public long getDt(int index) {
        return dailyData.getDayDataList().get(index).getDt();
    }

    public boolean hasIcon(int index) {
        return !getIcon(index).contains("-1");
    }

    public String getIcon(int index) {
        return dailyData.getDayDataList().get(index).getWeather().getIcon();
    }

    public boolean hasTempMax(int index) {
        return getTempMax(index) != -1.0f;
    }

    public float getTempMax(int index) {
        return dailyData.getDayDataList().get(index).getTemp().getMax();
    }

    public boolean hasTempDay(int index) {
        return getTempDay(index) != -1.0f;
    }

    public float getTempDay(int index) {
        return dailyData.getDayDataList().get(index).getTemp().getDay();
    }

    public boolean hasTempNight(int index) {
        return getTempNight(index) != -1.0f;
    }

    public float getTempNight(int index) {
        return dailyData.getDayDataList().get(index).getTemp().getNight();
    }

    public boolean hasDescription(int index) {
        return !getDescription(index).contains("-1");
    }

    public String getDescription(int index) {
        return dailyData.getDayDataList().get(index).getWeather().getDescription();
    }
}
