package lk.d24.hostel_system.dto;

import lk.d24.hostel_system.entity.Room;
import lk.d24.hostel_system.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class ReservationDTO {
    String resId;
    LocalDate date;
    BigDecimal key_money;
    String keyMoneyStatus;
    Student student;
    Room room;
}
