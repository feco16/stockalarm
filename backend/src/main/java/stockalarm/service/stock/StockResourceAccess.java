package stockalarm.service.stock;

import stockalarm.to.StockDTO;

import java.util.List;

public interface StockResourceAccess{

    List<StockDTO> getAllStocks();

}
