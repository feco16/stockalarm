package com.devm8.stockalarm.service;

import com.devm8.stockalarm.converter.StockConverter;
import com.devm8.stockalarm.converter.StockDTOConverter;
import com.devm8.stockalarm.dto.StockDTO;
import com.devm8.stockalarm.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockDTOConverter stockDTOConverter;

    @Autowired
    private StockConverter stockConverter;

    public List<StockDTO> getAllStocks() {
        List<StockDTO> stockDTOList = new ArrayList<>();
        stockRepository.findAll().forEach(
                s -> stockDTOList.add(stockDTOConverter.convert(s))
        );
        return stockDTOList;
    }

    public void createStock(StockDTO stockDTO) {
        stockRepository.save(stockConverter.convert(stockDTO));
    }

}
