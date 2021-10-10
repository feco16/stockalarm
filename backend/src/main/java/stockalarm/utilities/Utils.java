package stockalarm.utilities;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

@Slf4j
public class Utils {

    public static Double formatDouble(final Double value) {
        final DecimalFormat df = new DecimalFormat("#.0000");
        double formattedDouble = Constants.DOUBLE_ZERO;
        try {
            formattedDouble = Double.parseDouble(df.format(value));
        } catch (IllegalArgumentException e) {
            log.warn("Can't format {}", value);
        }
        return formattedDouble;
    }

    public static boolean compareDouble(final Double value1, final Double value2) {
        if (null == value2) {
            return false;
        }
        return Math.abs(value1 - value2) < 0.00001;
    }
}
