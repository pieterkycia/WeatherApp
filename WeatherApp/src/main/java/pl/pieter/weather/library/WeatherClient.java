package pl.pieter.weather.library;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.pieter.utils.DefaultValue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class WeatherClient {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String JSON_CITY_NAME = "name";

    private Unit unit = Unit.METRIC;
    private Language language = Language.Polish;

    public enum Unit {
        STANDARD("standard", "K"),
        METRIC("metric", "C"),
        IMPERIAL("imperial", "F");

        private String name;
        private String shortName;

        Unit(String name, String shortName) {
            this.name = name;
            this.shortName = shortName;
        }

        public String getName() {
            return name;
        }

        public String getShortName() {
            return shortName;
        }
    }

    public enum Language {
        English("en"),
        Polish("pl");

        private String name;

        Language(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    private JSONObject getData(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(httpResponse.body());

        return jsonObject;
    }

    private JSONArray getDataInJsonArray(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JSONArray jsonArray = new JSONArray(httpResponse.body());
        return jsonArray;
    }

    private JSONObject getWeatherDataByCoordinates(String latitude, String longitude) throws IOException, InterruptedException {
        String subUrl = String.format(Locale.ROOT, "onecall?lat=%s&lon=%s&units=%s&lang=%s&appid=%s", latitude, longitude, getUnit().getName(), getLanguage().getName(), Config.API_KEY);
        String url = WeatherClient.BASE_URL + subUrl;
        JSONObject jsonObject = getData(url);

        return jsonObject;
    }

    public JSONObject getWeatherDataByCityName(String cityName, String country) throws IOException, InterruptedException {
        JSONObject coordinatesJsonObject = getCoordinates(cityName, country);

        if (coordinatesJsonObject != null) {
            String latitude = coordinatesJsonObject.optString("lat");
            String longitude = coordinatesJsonObject.optString("lon");
            JSONObject jsonObject = getWeatherDataByCoordinates(latitude, longitude);

            jsonObject.put("city", coordinatesJsonObject.optString("name"));
            jsonObject.put("country", coordinatesJsonObject.optString("country"));

            return jsonObject;
        }
        return null;
    }

    private JSONObject getCoordinates(String cityName, String countryCode) throws IOException, InterruptedException {
        String url = String.format(Locale.ROOT,"http://api.openweathermap.org/geo/1.0/direct?q=%s,%s&appid=%s",cityName, countryCode, Config.API_KEY);
        if (countryCode == "") {
            url = String.format(Locale.ROOT,"http://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s",cityName, Config.API_KEY);
        }
        JSONObject jsonObject = getDataInJsonArray(url).optJSONObject(0);

        return jsonObject;
    }

    private String getCityName(String latitude, String longitude) throws IOException, InterruptedException {
        String url = String.format(Locale.ROOT, "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s", latitude, longitude, Config.API_KEY);
        JSONObject jsonObject = getData(url).optJSONObject("city", DefaultValue.JSON_OBJECT);
        String cityName = jsonObject.optString(JSON_CITY_NAME, DefaultValue.STRING);
        return cityName;
    }
}
