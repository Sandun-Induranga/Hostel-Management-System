package lk.d24.hostel_system.bo.custom;

import lk.d24.hostel_system.dto.StudentDTO;

import java.util.List;

public interface StudentBO {
    public boolean saveStudent(StudentDTO studentDTO) throws Exception;

    public boolean updateStudent(StudentDTO studentDTO) throws Exception;

    public boolean delete(String id) throws Exception;

    public List<StudentDTO> getAllStudents() throws Exception;
}
