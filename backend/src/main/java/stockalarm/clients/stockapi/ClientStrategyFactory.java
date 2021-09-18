package stockalarm.clients.stockapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ClientStrategyFactory {

    private Map<ClientEnum, ClientStrategy> strategies;

    @Autowired
    public ClientStrategyFactory(final Set<ClientStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(
                strategy -> strategies.put(strategy.getStrategyName(), strategy));
    }

    public ClientStrategy findStrategy(final ClientEnum strategyName) {
        return strategies.get(strategyName);
    }
}
