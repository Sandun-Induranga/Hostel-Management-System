package lk.d24.hostel_system.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationTM {
    String resId;
    String studentId;
    String name;
    String roomId;
    String type;
    LocalDate date;
    BigDecimal key_money;
    String keyMoneyStatus;
}
