package stockalarm.unit;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import stockalarm.StockalarmApplication;
import stockalarm.Utils;

@SpringBootTest(classes = StockalarmApplication.class)
public class TestUtils {

    @Test
    public void testFormatDobule1() {

        Double value = 123.4567890000;
        Double formated = Utils.formatDouble(value);
        Assert.assertEquals(123.4568, formated, 0.);
    }

    @Test
    public void testFormatDobule2() {

        Double value = 1.4;
        Double formated = Utils.formatDouble(value);
        Assert.assertEquals(1.4000, formated, 0.);
    }
}
