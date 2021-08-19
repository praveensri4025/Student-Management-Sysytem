package com.example.sms.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Student_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name" ,nullable = false)
    private String firstName;
    @Column(name = "last_name" ,nullable = false)
    private String lastName;
    @Column(name = "email" )
    private String email;
    @Column(name = "address" )
    private String address;

}
