package com.devm8.stockalarm.clients.stockapi;

import com.devm8.stockalarm.clients.stockapi.ClientEnum;

import java.util.List;
import java.util.Map;

public interface ClientStrategy {

    Double getPrice(String symbol);

    Map<String, String> getPrices(List<String> symbols);

    ClientEnum getStrategyName();

}
