package pl.pieter.weather.library;

import org.json.JSONObject;
import pl.pieter.utils.DefaultValue;
import pl.pieter.utils.JsonUtils;

public class WeatherData {

    private static final String JSON_LAT = "lat";
    private static final String JSON_LON = "lon";
    private static final String JSON_TIMEZONE = "timezone";
    private static final String JSON_TIMEZONE_OFFSET = "timezone_offset";
    private static final String JSON_CURRENT = "current";
    private static final String JSON_MINUTELY = "minutely";
    private static final String JSON_HOURLY = "hourly";
    private static final String JSON_DAILY = "daily";
    private static final String JSON_ALERTS = "alerts";
    private static final String JSON_CITY = "city";
    private static final String JSON_COUNTRY = "country";

    private double lat;
    private double lon;
    private String timezone;
    private long timezone_offset;
    private CurrentData current;
    private MinutelyData minutely;
    private HourlyData hourly;
    private DailyData daily;
    private AlertsData alerts;
    private String cityName;
    private String country;

    public WeatherData(JSONObject jsonObject) {
        if (jsonObject == null) {
            jsonObject = DefaultValue.JSON_OBJECT;
        }
            this.lat = jsonObject.optDouble(WeatherData.JSON_LAT, DefaultValue.DOUBLE);
            this.lon = jsonObject.optDouble(WeatherData.JSON_LON, DefaultValue.DOUBLE);
            this.timezone = jsonObject.optString(WeatherData.JSON_TIMEZONE, DefaultValue.STRING);
            this.timezone_offset = jsonObject.optLong(WeatherData.JSON_TIMEZONE_OFFSET, DefaultValue.LONG);
            this.current = new CurrentData(jsonObject.optJSONObject(WeatherData.JSON_CURRENT, DefaultValue.JSON_OBJECT));
            this.minutely = new MinutelyData(JsonUtils.checkJsonArray(jsonObject.optJSONArray(WeatherData.JSON_MINUTELY), DefaultValue.JSON_ARRAY));
            this.hourly = new HourlyData(JsonUtils.checkJsonArray(jsonObject.optJSONArray(WeatherData.JSON_HOURLY), DefaultValue.JSON_ARRAY));
            this.daily = new DailyData(JsonUtils.checkJsonArray(jsonObject.optJSONArray(WeatherData.JSON_DAILY), DefaultValue.JSON_ARRAY));
            this.alerts = new AlertsData(JsonUtils.checkJsonArray(jsonObject.optJSONArray(WeatherData.JSON_ALERTS), DefaultValue.JSON_ARRAY));
            this.cityName = jsonObject.optString(WeatherData.JSON_CITY, DefaultValue.STRING);
            this.country = jsonObject.optString(WeatherData.JSON_COUNTRY, DefaultValue.STRING);
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public long getTimezone_offset() {
        return timezone_offset;
    }

    public CurrentData getCurrent() {
        return current;
    }

    public MinutelyData getMinutely() {
        return minutely;
    }

    public HourlyData getHourly() {
        return hourly;
    }

    public DailyData getDaily() {
        return daily;
    }

    public AlertsData getAlerts() {
        return alerts;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }
}
