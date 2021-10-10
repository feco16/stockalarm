package stockalarm.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "alarm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALARM_ID")
    private long alarmId;

    @Column(name = "ALARM_UUID")
    private String alarmUUID;

    @Column(name = "ALARM_NAME")
    private String alarmName;

    @Column(name = "SAVED_PRICE")
    private Double savedPrice;

    @Column(name = "TARGET_PERCENTAGE")
    private Double targetPercentage;

    @Column(name = "ACTUAL_PERCENTAGE")
    private Double actualPercentage;

    @Column(name = "STATUS")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private StockUser stockUser;

    @Column(name = "user_id", insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private long userId;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "stock_id", insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private long stockId;

    @Builder
    public Alarm(final long alarmId, final String alarmName, final Double savedPrice, final Double targetPercentage,
                 final Double actualPercentage, final Boolean status, final StockUser stockUser, final Stock stock) {
        this.alarmId = alarmId;
        this.alarmUUID = UUID.randomUUID().toString();
        this.alarmName = alarmName;
        this.savedPrice = savedPrice;
        this.targetPercentage = targetPercentage;
        this.actualPercentage = actualPercentage;
        this.status = status;
        this.stockUser = stockUser;
        this.stock = stock;
    }
}
