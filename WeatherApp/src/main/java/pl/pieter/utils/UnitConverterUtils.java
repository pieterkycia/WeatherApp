package pl.pieter.utils;

public class UnitConverterUtils {

    public static float convertMetersPerSecondToKilometersPerHour(float metersPerSecond) {
        return metersPerSecond * 3.6f;
    }

    public static int convertMetersToKilometers(int meters) {
        return meters / 1000;
    }

}
