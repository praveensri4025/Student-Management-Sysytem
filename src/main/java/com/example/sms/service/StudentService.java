package com.example.sms.service;

import com.example.sms.entity.Student;

import java.util.List;
import org.springframework.data.domain.Page;


public interface StudentService {

    public List<Student> getAllStudents();

    public Student fetchStudentById(long id);

    public Student saveStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteStudentById(Long id);

    Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
