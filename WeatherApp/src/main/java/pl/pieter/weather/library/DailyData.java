package pl.pieter.weather.library;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.pieter.utils.DefaultValue;
import pl.pieter.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DailyData {

    private List<DayData> dayDataList;

    public class DayData {
        private static final String JSON_DT = "dt";
        private static final String JSON_SUNRISE = "sunrise";
        private static final String JSON_SUNSET = "sunset";
        private static final String JSON_MOONRISE = "moonrise";
        private static final String JSON_MOONSET = "moonset";
        private static final String JSON_MOON_PHASE = "moon_phase";
        private static final String JSON_TEMP = "temp";
        private static final String JSON_FEELS_LIKE = "feels_like";
        private static final String JSON_PRESSURE = "pressure";
        private static final String JSON_HUMIDITY = "humidity";
        private static final String JSON_DEW_POINT = "dew_point";
        private static final String JSON_WIND_SPEED = "wind_speed";
        private static final String JSON_WIND_GUST = "wind_gust";
        private static final String JSON_WIND_DEG = "wind_deg";
        private static final String JSON_CLOUDS = "clouds";
        private static final String JSON_UVI = "uvi";
        private static final String JSON_POP = "pop";
        private static final String JSON_RAIN = "rain";
        private static final String JSON_SNOW = "snow";
        private static final String JSON_WEATHER = "weather";

        private long dt;
        private long sunrise;
        private long sunset;
        private long moonrise;
        private long moonset;
        private float moonPhase;
        private Temp temp;
        private FeelsLike feelsLike;
        private int pressure;
        private int humidity;
        private float dewPoint;
        private float uvi;
        private int clouds;
        private int visibility;
        private float windSpeed;
        private float windGust;
        private int windDeg;
        private float pop;
        private float rain;
        private float snow;
        private Weather weather;

        public class Temp {
            private static final String JSON_MORN = "morn";
            private static final String JSON_DAY = "day";
            private static final String JSON_EVE = "eve";
            private static final String JSON_NIGHT = "night";
            private static final String JSON_MIN = "min";
            private static final String JSON_MAX = "max";

            private float morn;
            private float day;
            private float eve;
            private float night;
            private float min;
            private float max;

            public Temp(JSONObject jsonObject) {
                this.morn = jsonObject.optFloat(Temp.JSON_MORN, DefaultValue.FLOAT);
                this.day = jsonObject.optFloat(Temp.JSON_DAY, DefaultValue.FLOAT);
                this.eve = jsonObject.optFloat(Temp.JSON_EVE, DefaultValue.FLOAT);
                this.night = jsonObject.optFloat(Temp.JSON_NIGHT, DefaultValue.FLOAT);
                this.min = jsonObject.optFloat(Temp.JSON_MIN, DefaultValue.FLOAT);
                this.max = jsonObject.optFloat(Temp.JSON_MAX, DefaultValue.FLOAT);
            }

            public float getMorn() {
                return morn;
            }

            public float getDay() {
                return day;
            }

            public float getEve() {
                return eve;
            }

            public float getNight() {
                return night;
            }

            public float getMin() {
                return min;
            }

            public float getMax() {
                return max;
            }
        }

        public class FeelsLike {

            private static final String JSON_MORN = "morn";
            private static final String JSON_DAY = "day";
            private static final String JSON_EVE = "eve";
            private static final String JSON_NIGHT = "night";

            private float morn;
            private float day;
            private float eve;
            private float night;

            public FeelsLike(JSONObject jsonObject) {
                this.morn = jsonObject.optFloat(FeelsLike.JSON_MORN, DefaultValue.FLOAT);
                this.day = jsonObject.optFloat(FeelsLike.JSON_DAY, DefaultValue.FLOAT);
                this.eve = jsonObject.optFloat(FeelsLike.JSON_EVE, DefaultValue.FLOAT);
                this.night = jsonObject.optFloat(FeelsLike.JSON_NIGHT, DefaultValue.FLOAT);
            }

            public float getMorn() {
                return morn;
            }

            public float getDay() {
                return day;
            }

            public float getEve() {
                return eve;
            }

            public float getNight() {
                return night;
            }
        }

        public class Weather {
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

        public DayData(JSONObject jsonObject) {
            this.dt = jsonObject.optLong(DayData.JSON_DT, DefaultValue.LONG);
            this.sunrise = jsonObject.optLong(DayData.JSON_SUNRISE, DefaultValue.LONG);
            this.sunset = jsonObject.optLong(DayData.JSON_SUNSET, DefaultValue.LONG);
            this.moonrise = jsonObject.optLong(DayData.JSON_MOONRISE, DefaultValue.LONG);
            this.moonset = jsonObject.optLong(DayData.JSON_MOONSET, DefaultValue.LONG);
            this.moonPhase = jsonObject.optFloat(DayData.JSON_MOON_PHASE, DefaultValue.FLOAT);
            this.temp = new Temp(jsonObject.optJSONObject(DayData.JSON_TEMP, DefaultValue.JSON_OBJECT));
            this.feelsLike = new FeelsLike(jsonObject.optJSONObject(DayData.JSON_FEELS_LIKE, DefaultValue.JSON_OBJECT));
            this.pressure = jsonObject.optInt(DayData.JSON_PRESSURE, DefaultValue.INT);
            this.humidity = jsonObject.optInt(DayData.JSON_HUMIDITY, DefaultValue.INT);
            this.dewPoint = jsonObject.optFloat(DayData.JSON_DEW_POINT, DefaultValue.FLOAT);
            this.windSpeed = jsonObject.optFloat(DayData.JSON_WIND_SPEED, DefaultValue.FLOAT);
            this.windGust = jsonObject.optFloat(DayData.JSON_WIND_GUST, DefaultValue.FLOAT);
            this.windDeg = jsonObject.optInt(DayData.JSON_WIND_DEG, DefaultValue.INT);
            this.clouds = jsonObject.optInt(DayData.JSON_CLOUDS, DefaultValue.INT);
            this.uvi = jsonObject.optFloat(DayData.JSON_UVI, DefaultValue.FLOAT);
            this.pop = jsonObject.optFloat(DayData.JSON_POP, DefaultValue.FLOAT);
            this.rain = jsonObject.optFloat(DayData.JSON_RAIN, DefaultValue.FLOAT);
            this.snow = jsonObject.optFloat(DayData.JSON_SNOW, DefaultValue.FLOAT);
            this.weather = new Weather(JsonUtils.checkJsonArray(jsonObject.optJSONArray(DayData.JSON_WEATHER), DefaultValue.JSON_ARRAY));
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

        public long getMoonrise() {
            return moonrise;
        }

        public long getMoonset() {
            return moonset;
        }

        public float getMoonPhase() {
            return moonPhase;
        }

        public Temp getTemp() {
            return temp;
        }

        public FeelsLike getFeelsLike() {
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

        public float getUvi() {
            return uvi;
        }

        public int getClouds() {
            return clouds;
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

        public float getPop() {
            return pop;
        }

        public float getRain() {
            return rain;
        }

        public float getSnow() {
            return snow;
        }

        public Weather getWeather() {
            return weather;
        }
    }

    public DailyData(JSONArray jsonArray) {
        this.dayDataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            DayData dayData = new DayData(jsonArray.optJSONObject(i));
            this.dayDataList.add(dayData);
        }
    }

    public List<DayData> getDayDataList() {
        return dayDataList;
    }
}
