package com.example.sms.service;

import com.example.sms.entity.Student;
import com.example.sms.repositary.StudentRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepositary studentRepositary;

    public StudentServiceImpl(StudentRepositary studentRepositary) {
        this.studentRepositary = studentRepositary;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepositary.findAll();
    }

    @Override
    public Student fetchStudentById(long id) {
        return studentRepositary.findById(id).get();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepositary.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepositary.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepositary.deleteById(id);
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.studentRepositary.findAll(pageable);
    }
}
