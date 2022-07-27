package pl.pieter.weather.library;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.pieter.utils.DefaultValue;
import pl.pieter.utils.JsonUtils;

public class CurrentData {
    private static final String JSON_DT = "dt";
    private static final String JSON_SUNRISE = "sunrise";
    private static final String JSON_SUNSET = "sunset";
    private static final String JSON_TEMP = "temp";
    private static final String JSON_FEELS_LIKE = "feels_like";
    private static final String JSON_PRESSURE = "pressure";
    private static final String JSON_HUMIDITY = "humidity";
    private static final String JSON_DEW_POINT = "dew_point";
    private static final String JSON_CLOUDS = "clouds";
    private static final String JSON_UVI = "uvi";
    private static final String JSON_VISIBILITY = "visibility";
    private static final String JSON_WIND_SPEED = "wind_speed";
    private static final String JSON_WIND_GUST = "wind_gust";
    private static final String JSON_WIND_DEG = "wind_deg";
    private static final String JSON_RAIN = "rain";
    private static final String JSON_SNOW = "snow";
    private static final String JSON_WEATHER = "weather";

    private long dt;
    private long sunrise;
    private long sunset;
    private float temp;
    private float feelsLike;
    private int pressure;
    private int humidity;
    private float dewPoint;
    private int clouds;
    private float uvi;
    private int visibility;
    private float windSpeed;
    private float windGust;
    private int windDeg;
    private Rain rain;
    private Snow snow;
    private Weather weather;

    public static class Rain {
        private static final String JSON_1H = "1h";

        private float rainOneHour;

        public Rain(JSONObject jsonObject) {
            this.rainOneHour = jsonObject.optFloat(Rain.JSON_1H, DefaultValue.FLOAT);
        }

        public float getRainOneHour() {
            return rainOneHour;
        }
    }

    public static class Snow {
        private static final String JSON_1H = "1h";

        private float snowOneHour;

        public Snow(JSONObject jsonObject) {
            this.snowOneHour = jsonObject.optFloat(Snow.JSON_1H, DefaultValue.FLOAT);
        }

        public float getSnowOneHour() {
            return snowOneHour;
        }
    }

    public static class Weather {
        private static final String JSON_ID = "id";
        private static final String JSON_MAIN = "main";
        private static final String JSON_DESCRIPTION = "description";
        private static final String JSON_ICON = "icon";

        private long id;
        private String main;
        private String description;
        private String icon;

        public Weather(JSONArray jsonArray) {
            JSONObject jsonObject = jsonArray.optJSONObject(0);
            if (jsonObject == null) {
                jsonObject = DefaultValue.JSON_OBJECT;
            }
            this.id = jsonObject.optLong(Weather.JSON_ID, DefaultValue.LONG);
            this.main = jsonObject.optString(Weather.JSON_MAIN, DefaultValue.STRING);
            this.description = jsonObject.optString(Weather.JSON_DESCRIPTION, DefaultValue.STRING);
            this.icon = jsonObject.optString(Weather.JSON_ICON, DefaultValue.STRING);
        }

        public long getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public CurrentData(JSONObject jsonObject) {
        this.dt = jsonObject.optLong(CurrentData.JSON_DT, DefaultValue.LONG);
        this.sunrise = jsonObject.optLong(CurrentData.JSON_SUNRISE, DefaultValue.LONG);
        this.sunset = jsonObject.optLong(CurrentData.JSON_SUNSET, DefaultValue.LONG);
        this.temp = jsonObject.optFloat(CurrentData.JSON_TEMP, DefaultValue.FLOAT);
        this.feelsLike = jsonObject.optFloat(CurrentData.JSON_FEELS_LIKE, DefaultValue.FLOAT);
        this.pressure = jsonObject.optInt(CurrentData.JSON_PRESSURE, DefaultValue.INT);
        this.humidity = jsonObject.optInt(CurrentData.JSON_HUMIDITY, DefaultValue.INT);
        this.dewPoint = jsonObject.optFloat(CurrentData.JSON_DEW_POINT, DefaultValue.FLOAT);
        this.clouds = jsonObject.optInt(CurrentData.JSON_CLOUDS, DefaultValue.INT);
        this.uvi = jsonObject.optFloat(CurrentData.JSON_UVI, DefaultValue.FLOAT);
        this.visibility = jsonObject.optInt(CurrentData.JSON_VISIBILITY, DefaultValue.INT);
        this.windSpeed = jsonObject.optFloat(CurrentData.JSON_WIND_SPEED, DefaultValue.FLOAT);
        this.windGust = jsonObject.optFloat(CurrentData.JSON_WIND_GUST, DefaultValue.FLOAT);
        this.windDeg = jsonObject.optInt(CurrentData.JSON_WIND_DEG, DefaultValue.INT);
        this.rain = new Rain(jsonObject.optJSONObject(CurrentData.JSON_RAIN, DefaultValue.JSON_OBJECT));
        this.snow = new Snow(jsonObject.optJSONObject(CurrentData.JSON_SNOW, DefaultValue.JSON_OBJECT));
        this.weather = new Weather(JsonUtils.checkJsonArray(jsonObject.optJSONArray(CurrentData.JSON_WEATHER), DefaultValue.JSON_ARRAY));
    }

    public long getDt() {
        return dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getDewPoint() {
        return dewPoint;
    }

    public int getClouds() {
        return clouds;
    }

    public float getUvi() {
        return uvi;
    }

    public int getVisibility() {
        return visibility;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindGust() {
        return windGust;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public Rain getRain() {
        return rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public Weather getWeather() {
        return weather;
    }
}
