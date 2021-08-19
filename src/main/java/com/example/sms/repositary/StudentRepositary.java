package com.example.sms.repositary;

import com.example.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositary extends JpaRepository<Student , Long> {
}
