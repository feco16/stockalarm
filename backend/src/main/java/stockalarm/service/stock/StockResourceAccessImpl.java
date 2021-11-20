package stockalarm.service.stock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stockalarm.exception.CustomBadRequestException;
import stockalarm.model.converter.StockDTOConverter;
import stockalarm.model.entity.Stock;
import stockalarm.repository.StockRepository;
import stockalarm.to.StockDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockResourceAccessImpl implements StockResourceAccessInternal {

    private final StockDTOConverter stockDTOConverter;
    private final StockRepository stockRepository;

    @Override
    public List<StockDTO> getAllStocks() {
        final List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(stockDTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Stock getById(long id) {
        final Optional<Stock> stockOptional = stockRepository.findById(id);
        return stockOptional.orElseThrow(() -> new CustomBadRequestException("Can't find stock with id " + id));
    }
}
