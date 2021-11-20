package stockalarm.service.user;

import stockalarm.to.StockUserRegistrationDTO;

public interface UserService {

    void createUser(StockUserRegistrationDTO userRegistrationDTO);
}
