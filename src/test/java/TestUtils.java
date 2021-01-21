import com.devm8.stockalarm.StockalarmApplication;
import com.devm8.stockalarm.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertEquals;


@SpringBootTest(classes = StockalarmApplication.class)
@ActiveProfiles("test")
public class TestUtils {

    @Test
    public void testFormatDobule1() {

        Double value = 123.4567890000;
        Double formated = Utils.formatDouble(value);
        assertEquals(123.4568, formated, 0.);
    }

    @Test
    public void testFormatDobule2() {

        Double value = 1.4;
        Double formated = Utils.formatDouble(value);
        assertEquals(1.4000, formated, 0.);
    }
}
