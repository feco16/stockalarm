package stockalarm.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alarm")
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

}
