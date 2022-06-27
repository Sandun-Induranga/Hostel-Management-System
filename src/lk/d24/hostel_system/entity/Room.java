package lk.d24.hostel_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Room implements SuperEntity {
    @Id
    String roomId;
    @Column(nullable = false)
    String type;
    @Column(nullable = false)
    BigDecimal KeyMoney;
    @Column(nullable = false)
    int qty;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<Reservation> reserveList = new ArrayList<>();

    public Room(String roomId, String type, BigDecimal keyMoney, int qty) {
        this.roomId = roomId;
        this.type = type;
        KeyMoney = keyMoney;
        this.qty = qty;
    }
}
