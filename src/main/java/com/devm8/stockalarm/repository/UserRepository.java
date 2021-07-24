package com.devm8.stockalarm.repository;

import com.devm8.stockalarm.model.entity.StockUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<StockUser, Long> {

    StockUser findByEmail(String email);

}
