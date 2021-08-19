package com.example.sms.controller;

import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Handler method to handle list students and return model and view
    // Here firsr create the Model Object
    // After add the ModelAttribute with name and value for Model point of view
    // Then add the view
   /* @GetMapping("/students")
    public String getAllStudents(Model model) {
        return "students";
    }*/
    // display list of employees
    @GetMapping("/students")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        //Create Student object to hold student form data
        //And this student object will reflected into create_student.html page
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", studentService.fetchStudentById(id));
        return "edit_student";
    }
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {

        // get student from database by id
        Student existingStudent = studentService.fetchStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAddress(student.getAddress());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }
    // handler method to handle delete student request
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Student> listStudents = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listStudents", listStudents);
        return "students";
    }
}
