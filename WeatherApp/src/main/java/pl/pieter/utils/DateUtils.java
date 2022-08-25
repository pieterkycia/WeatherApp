package pl.pieter.utils;

import java.util.Calendar;

public class DateUtils {

    public static String getDayName(long timeInSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInSeconds * 1000);
        int number = calendar.get(Calendar.DAY_OF_WEEK);

        return convertNumberToDayName(number);
    }

    public static String getHour(long timeInSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInSeconds * 1000);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String hourString = String.valueOf(hour) + ".00";
        return hourString;
    }

    private static String convertNumberToDayName(int number) {
        switch (number) {
            case 1:
                return "Nd";
            case 2:
                return "Pn";
            case 3:
                return "Wt";
            case 4:
                return "Śr";
            case 5:
                return "Czw";
            case 6:
                return "Pt";
            case 7:
                return "Sb";
            default:
                return "";
        }
    }
}
