package lk.d24.hostel_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

@Entity
public class Student implements SuperEntity {
    @Id
    String studentId;
    @Column(nullable = false)
    String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    String address;
    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    LocalDate dob;
    @Column(nullable = false)
    String gender;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Reservation> reserveList = new ArrayList<>();

    public Student(String studentId, String name, String address, String phoneNumber, LocalDate dob, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
    }
}
