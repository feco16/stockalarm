package com.devm8.stockalarm.integration;

import com.devm8.stockalarm.clients.stockapi.alphavantage.Alphavantage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAlphavantageAPI {

    public static MockWebServer mockWebServer;


    Alphavantage alphavantage;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        alphavantage = new Alphavantage();
        // Call the mock web server
        alphavantage.setUrl(baseUrl);
    }

    @Test
    public void getPrice_ValidAPI_Returns100p1234() throws JsonProcessingException, InterruptedException {
        Double mockPrice = 100.1234;
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = createJSONObject(mockPrice.toString());

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(jsonObject))
                .addHeader("Content-Type", "application/json"));

        Double returnedPrice = alphavantage.getPrice("VALID_SYMBOL");

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/?function=TIME_SERIES_INTRADAY&symbol=VALID_SYMBOL&interval=1min&outputsize=compact&apikey=I5TPWWHJJDCQ47IZ",
                recordedRequest.getPath());
        assertEquals(mockPrice, returnedPrice);
    }

    @Test
    public void getPrice_ValidAPIValue0_Returns0() throws JsonProcessingException, InterruptedException {
        Double mockPrice = 0.;
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = createJSONObject(mockPrice.toString());

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(jsonObject))
                .addHeader("Content-Type", "application/json"));

        Double returnedPrice = alphavantage.getPrice("TEST_SYMBOL");

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/?function=TIME_SERIES_INTRADAY&symbol=TEST_SYMBOL&interval=1min&outputsize=compact&apikey=I5TPWWHJJDCQ47IZ",
                recordedRequest.getPath());
        assertEquals(mockPrice, returnedPrice);
    }

    @Test
    public void getPrice_InvalidValue_Returns0() throws JsonProcessingException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = createJSONObject("NotANumber");

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(jsonObject))
                .addHeader("Content-Type", "application/json"));

        Double returnedPrice = alphavantage.getPrice("TEST_SYMBOL");

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/?function=TIME_SERIES_INTRADAY&symbol=TEST_SYMBOL&interval=1min&outputsize=compact&apikey=I5TPWWHJJDCQ47IZ",
                recordedRequest.getPath());
        assertEquals(Double.valueOf(0), returnedPrice);
    }

    @Test
    public void getPrice_InvalidAPI_Returns0() throws JsonProcessingException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = createObjectWithError();

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(jsonObject))
                .addHeader("Content-Type", "application/json"));

        Double returnedPrice = alphavantage.getPrice("TEST_SYMBOL");

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/?function=TIME_SERIES_INTRADAY&symbol=TEST_SYMBOL&interval=1min&outputsize=compact&apikey=I5TPWWHJJDCQ47IZ",
                recordedRequest.getPath());
        assertEquals(Double.valueOf(0), returnedPrice);
    }

    private JSONObject createJSONObject(String price) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Meta Data", new LinkedHashMap<>());

        Map<String, Map> results = new HashMap<>();
        Map<String, String> oneResult = new HashMap<>();

        oneResult.put("1. open", price);
        oneResult.put("2. high", "999.9");
        oneResult.put("3. low", "0.001");
        results.put("2021-01-21 20:00:00", oneResult);
        jsonObject.put("Time Series (1min)", results);
        return jsonObject;
    }

    private JSONObject createObjectWithError() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Error Message",
                "Invalid API call. Please retry or visit the documentation (https://www.alphavantage.co/documentation/) for TIME_SERIES_INTRADAY."
        );
        return jsonObject;
    }

}
