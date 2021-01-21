package com.devm8.stockalarm;

import java.text.DecimalFormat;

public class Utils {

    public static Double formatDouble(Double value) {
        DecimalFormat df = new DecimalFormat("#.0000");
        Double formatedDouble = 0.;
        try {
            formatedDouble = Double.valueOf(df.format(value));
        } catch (IllegalArgumentException e) {
            System.out.println("Can't format " + value);
        }
        return formatedDouble;
    }

    public static boolean compareDouble(Double value1, Double value2) {
        if (null == value2) {
            return false;
        }
        return Math.abs(value1 - value2) < 0.00001;
    }
}
