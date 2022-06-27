package lk.d24.hostel_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class StudentDTO {
    String studentId;
    String name;
    String address;
    String phoneNumber;
    LocalDate dob;
    String gender;
}
