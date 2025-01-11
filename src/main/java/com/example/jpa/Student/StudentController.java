package com.example.jpa.Student;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentRepository studentRepo;

    StudentController(StudentRepository studentRepo){
        this.studentRepo = studentRepo;
    }

    @GetMapping("")
    List<Student> getAll(){
        return this.studentRepo.findAll();
    }

    @GetMapping("/{id}")
    Student getStudentById(@PathVariable("id") Long ID){

        Optional<Student> student= this.studentRepo.findById(ID);
        if(student.isEmpty()){
            throw new IllegalStateException("student id not found");
        }
        return student.get();
    }

    @PostMapping
    void store(@RequestBody Student student){
        Optional<Student> existStudent=this.studentRepo.findStudentByEmail(student.getEmail());
        if(existStudent.isPresent()){
            throw new IllegalStateException("email already taken");
        }
        this.studentRepo.save(student);
    }
}
