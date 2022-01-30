package stockalarm.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import stockalarm.repository.StockRepository;

import static org.assertj.core.api.Assertions.assertThat;

// create .testcontainers.properties on C:\Users\username containing testcontainers.reuse.enable=true
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Testcontainers
public class TestcontainerReuse {

    @Autowired
    private StockRepository stockRepository;

//    @Container
    private static MariaDBContainer mariaDBContainer = (MariaDBContainer) new MariaDBContainer("mariadb:10.3.32")
            .withReuse(true);

    @BeforeAll
    public static void setup() {
        mariaDBContainer.start();
    }

    @DynamicPropertySource
    public  static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDBContainer::getUsername);
        registry.add("spring.datasource.password", mariaDBContainer::getPassword);
    }

    @Test
    public void mytest() {
        assertThat(stockRepository.findAll().size()).isEqualTo(2);
    }
}



