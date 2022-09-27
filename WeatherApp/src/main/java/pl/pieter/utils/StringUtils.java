package pl.pieter.utils;

public class StringUtils {

    public static String capitalize(String text) {
        if (text.isEmpty()) {
            return text;
        } else
            return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
