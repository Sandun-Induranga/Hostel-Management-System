package lk.d24.hostel_system.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class StudentTM {
    String studentId;
    String name;
    String address;
    String phoneNumber;
    LocalDate dob;
    String gender;
}
