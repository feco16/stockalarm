package stockalarm.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import stockalarm.to.StockDTO;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// TODO don't use real database and keycloak server for testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockControllerTest {

    @LocalServerPort
    private int port;

    private final WebClient webClient = WebClient.create();

    private String token;

    @BeforeEach
    public void setup() {
        if (token != null) {
            return;
        }
        final AccessTokenResponse response = webClient.post()
                .uri("http://localhost:8180/auth/realms/stockalarm/protocol/openid-connect/token")
                .body(BodyInserters.fromFormData("client_id", "stockalarm-client")
                        .with("password", "password")
                        .with("username", "user1")
                        .with("grant_type", "password"))
                .retrieve().bodyToMono(AccessTokenResponse.class).block();
        assert response != null;
        this.token = response.getToken();
    }

    @Test
    public void getStocks_validToken_returnsAll() {
        // given

        // when
        List<?> result = webClient.get()
                .uri("http://localhost:" + port + "/stocks")
                .headers(h -> h.setBearerAuth(token))
                .retrieve().bodyToMono(List.class).block();
        // then

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }

}