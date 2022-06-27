package lk.d24.hostel_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

@Entity
public class Reservation implements SuperEntity {
    @Id
    String resId;
    @Column(nullable = false)
    LocalDate date;
    @Column(nullable = false)
    BigDecimal keyMoney;
    @Column(nullable = false)
    String keyMoneyStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    Room room;
}
