package com.devm8.stockalarm.client;

public interface ClientStrategy {

    public String getPrice(String symbol);

    public ClientEnum getStrategyName();

}
