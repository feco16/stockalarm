package stockalarm.service;

import stockalarm.model.entity.StockUser;

public interface UserResourceAccessInternal {

     StockUser getById(long id);
}
