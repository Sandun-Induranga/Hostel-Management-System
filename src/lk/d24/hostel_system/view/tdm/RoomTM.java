package lk.d24.hostel_system.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class RoomTM {
    String roomId;
    String type;
    BigDecimal keyMoney;
    int qty;
}
