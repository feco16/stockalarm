package stockalarm.service;

import stockalarm.model.entity.Stock;

public interface StockResourceAccessInternal extends StockResourceAccess {

    Stock getById(long id);
}
