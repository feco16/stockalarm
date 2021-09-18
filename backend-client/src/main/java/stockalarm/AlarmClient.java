package stockalarm;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import stockalarm.to.AlarmDTO;

import java.util.List;

@Component
public class AlarmClient {

    String url = "localhost:8080/alarms";

    public void createAlarm(final AlarmDTO alarmDTO) {
        WebClient.builder()
                .baseUrl(url)
                .build()
                .post()
                .body(Mono.just(alarmDTO), AlarmDTO.class)
                .retrieve().toBodilessEntity().block();
    }

    public List<AlarmDTO> getAlarmsByUser(String username) {
        return WebClient
                .builder()
                .baseUrl(url)
                .build()
                .get().uri(uriBuilder -> uriBuilder
                        .build())
                .retrieve().bodyToMono(List.class).block();
    }

    public void updateAlarm(final AlarmDTO alarmDTO) {

    }

    public void deleteAlarm(final String id) {

    }
}
