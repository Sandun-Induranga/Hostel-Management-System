package lk.d24.hostel_system.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
public class CustomEntity implements SuperEntity {
    String studentId;
    String name;
    String address;
    String phoneNumber;
    LocalDate dob;
    String gender;
    String roomId;
    String type;
    BigDecimal keyMoney;
    int qty;
    String resId;
    LocalDate date;
    BigDecimal key_money;
    String keyMoneyStatus;
    Student student;
    Room room;
    String username;
    String password;

    public CustomEntity(String resId, String studentId, String name, String roomId, String type, LocalDate date, BigDecimal key_money, String keyMoneyStatus) {
        this.studentId = studentId;
        this.name = name;
        this.roomId = roomId;
        this.type = type;
        this.resId = resId;
        this.date = date;
        this.key_money = key_money;
        this.keyMoneyStatus = keyMoneyStatus;
    }
}
