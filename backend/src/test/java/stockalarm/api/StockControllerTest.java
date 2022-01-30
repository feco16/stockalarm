package stockalarm.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// TODO don't use real keycloak server for testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class StockControllerTest {

    @Container
    private static MariaDBContainer mariaDBContainer = new MariaDBContainer<>("mariadb:10.3.32");

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

    @DynamicPropertySource
    public  static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDBContainer::getUsername);
        registry.add("spring.datasource.password", mariaDBContainer::getPassword);
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