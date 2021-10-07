package stockalarm.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import stockalarm.model.entity.StockUser;
import stockalarm.to.StockUserRegistrationDTO;

import java.util.UUID;

@Component
public class StockUserConverter implements Converter<StockUserRegistrationDTO, StockUser> {

    @Override
    public StockUser convert(final StockUserRegistrationDTO source) {
        final StockUser stockUser = new StockUser();
        stockUser.setUserUUID(UUID.randomUUID().toString());
        stockUser.setFirstName(source.getFirstName());
        stockUser.setLastName(source.getLastName());
        stockUser.setEmail(source.getEmail());

//        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        stockUser.setPassword(bCryptPasswordEncoder.encode(source.getPassword()));

        stockUser.setPassword(source.getPassword());
        return stockUser;
    }
}
