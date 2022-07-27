package pl.pieter.weather.library;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.pieter.utils.DefaultValue;
import pl.pieter.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class HourlyData {

    private List<HourData> hourDataList;

    public class HourData {
        private static final String JSON_DT = "dt";
        private static final String JSON_TEMP = "temp";
        private static final String JSON_FEELS_LIKE = "feels_like";
        private static final String JSON_PRESSURE = "pressure";
        private static final String JSON_HUMIDITY = "humidity";
        private static final String JSON_DEW_POINT = "dew_point";
        private static final String JSON_UVI = "uvi";
        private static final String JSON_CLOUDS = "clouds";
        private static final String JSON_VISIBILITY = "visibility";
        private static final String JSON_WIND_SPEED = "wind_speed";
        private static final String JSON_WIND_GUST = "wind_gust";
        private static final String JSON_WIND_DEG = "wind_deg";
        private static final String JSON_POP = "pop";
        private static final String JSON_RAIN = "rain";
        private static final String JSON_SNOW = "snow";
        private static final String JSON_WEATHER = "weather";

        private long dt;
        private float temp;
        private float feelsLike;
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
        private Rain rain;
        private Snow snow;
        private Weather weather;

        public class Rain {
            private static final String JSON_1H = "1h";

            private float rainOneHour;

            public Rain(JSONObject jsonObject) {
                this.rainOneHour = jsonObject.optFloat(Rain.JSON_1H, DefaultValue.FLOAT);
            }

            public float getRainOneHour() {
                return rainOneHour;
            }
        }

        public class Snow {
            private static final String JSON_1H = "1h";

            private float snowOneHour;

            public Snow(JSONObject jsonObject) {
                this.snowOneHour = jsonObject.optFloat(Snow.JSON_1H, DefaultValue.FLOAT);
            }

            public float getSnowOneHour() {
                return snowOneHour;
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

        public HourData(JSONObject jsonObject) {
            this.dt = jsonObject.optLong(HourData.JSON_DT, DefaultValue.LONG);
            this.temp = jsonObject.optFloat(HourData.JSON_TEMP, DefaultValue.FLOAT);
            this.feelsLike = jsonObject.optFloat(HourData.JSON_FEELS_LIKE, DefaultValue.FLOAT);
            this.pressure = jsonObject.optInt(HourData.JSON_PRESSURE, DefaultValue.INT);
            this.humidity = jsonObject.optInt(HourData.JSON_HUMIDITY, DefaultValue.INT);
            this.dewPoint = jsonObject.optFloat(HourData.JSON_DEW_POINT, DefaultValue.FLOAT);
            this.uvi = jsonObject.optFloat(HourData.JSON_UVI, DefaultValue.FLOAT);
            this.clouds = jsonObject.optInt(HourData.JSON_CLOUDS, DefaultValue.INT);
            this.visibility = jsonObject.optInt(HourData.JSON_VISIBILITY, DefaultValue.INT);
            this.windSpeed = jsonObject.optFloat(HourData.JSON_WIND_SPEED, DefaultValue.FLOAT);
            this.windGust = jsonObject.optFloat(HourData.JSON_WIND_GUST, DefaultValue.FLOAT);
            this.windDeg = jsonObject.optInt(HourData.JSON_WIND_DEG, DefaultValue.INT);
            this.pop = jsonObject.optFloat(HourData.JSON_POP, DefaultValue.FLOAT);
            this.rain = new Rain(jsonObject.optJSONObject(HourData.JSON_RAIN, DefaultValue.JSON_OBJECT));
            this.snow = new Snow(jsonObject.optJSONObject(HourData.JSON_SNOW, DefaultValue.JSON_OBJECT));
            this.weather = new Weather(JsonUtils.checkJsonArray(jsonObject.optJSONArray(HourData.JSON_WEATHER), DefaultValue.JSON_ARRAY));
        }

        public long getDt() {
            return dt;
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

    public HourlyData(JSONArray jsonArray) {
        this.hourDataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            HourData hourData = new HourData(jsonArray.optJSONObject(i));
            this.hourDataList.add(hourData);
        }
    }

    public List<HourData> getHourDataList() {
        return hourDataList;
    }
}
