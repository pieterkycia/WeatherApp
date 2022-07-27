package pl.pieter.utils;

import org.json.JSONArray;

public class JsonUtils {

    public static JSONArray checkJsonArray(JSONArray jsonArray, JSONArray defaultValue) {
        if (jsonArray == null) {
            return defaultValue;
        } else {
            return jsonArray;
        }
    }
}
