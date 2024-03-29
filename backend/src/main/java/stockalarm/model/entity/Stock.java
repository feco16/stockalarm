package stockalarm.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private long stockId;

    @Column(name = "stock_uuid")
    private String stockUUID;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "current_price")
    private Double currentPrice;

}
