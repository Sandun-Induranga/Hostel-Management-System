package lk.d24.hostel_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class RoomDTO {
    String roomId;
    String type;
    BigDecimal keyMoney;
    int qty;
}
