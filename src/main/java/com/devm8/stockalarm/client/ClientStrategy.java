package com.devm8.stockalarm.client;

import java.util.List;
import java.util.Map;

public interface ClientStrategy {

    String getPrice(String symbol);

    Map<String, String> getPrices(List<String> symbols);

    ClientEnum getStrategyName();

}
