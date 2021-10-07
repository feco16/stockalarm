package stockalarm.repository;

import stockalarm.model.entity.StockUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<StockUser, Long> {

    Optional<StockUser> findByEmail(String email);

}
