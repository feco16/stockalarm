package com.devm8.stockalarm.unit;

import com.devm8.stockalarm.StockalarmApplication;
import com.devm8.stockalarm.client.impl.ClientAlphavantage;
import com.devm8.stockalarm.client.impl.ClientMarketstack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = StockalarmApplication.class)
@ActiveProfiles("test")
public class TestClient {

    @Autowired
    ClientAlphavantage clientAlphavantage;

    @Autowired
    ClientMarketstack clientMarketstack;

    @Test
    public void testclient() {
        System.out.println(clientAlphavantage.getPrice("IBM"));
    }

    @Test
    public void testMarketstock() {
        System.out.println(clientMarketstack.getPrice("IBM"));
    }

}
