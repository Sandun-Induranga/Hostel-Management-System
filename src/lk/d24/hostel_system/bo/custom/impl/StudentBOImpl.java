package lk.d24.hostel_system.bo.custom.impl;

import lk.d24.hostel_system.bo.SuperBO;
import lk.d24.hostel_system.bo.custom.StudentBO;
import lk.d24.hostel_system.dao.DAOFactory;
import lk.d24.hostel_system.dao.custom.StudentDAO;
import lk.d24.hostel_system.dto.StudentDTO;
import lk.d24.hostel_system.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO, SuperBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.save(new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getPhoneNumber(), studentDTO.getDob(), studentDTO.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getPhoneNumber(), studentDTO.getDob(), studentDTO.getGender()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> students = new ArrayList<>();
        for (Student student:
             all) {
            students.add(new StudentDTO(student.getStudentId(),student.getName(),student.getAddress(),student.getPhoneNumber(),student.getDob(),student.getGender()));
        }
        return students;
    }
}
