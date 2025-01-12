package com.example.jpa.Student;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentRepository studentRepo;

    StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("")
    List<Student> getAll() {
        return this.studentRepo.findAll();
    }

    @GetMapping("/{id}")
    Student getStudentById(@PathVariable("id") Long ID) {

        Optional<Student> student = this.studentRepo.findById(ID);
        if (student.isEmpty()) {
            throw new IllegalStateException("student id not found");
        }
        return student.get();
    }

    @PostMapping
    void store(@RequestBody Student student) {
        Optional<Student> existStudent = this.studentRepo.findStudentByEmail(student.getEmail());
        if (existStudent.isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        this.studentRepo.save(student);
    }

    @PutMapping("/{id}")
    ResponseEntity<Student> update(@RequestBody Student student, @PathVariable("id") Long ID) {
        Optional<Student> oldStudent = this.studentRepo.findById(ID);

        if (oldStudent.isPresent()) {
            Student theStudent = oldStudent.get();
            if (student.getEmail() != null) {
                theStudent.setEmail(student.getEmail());
            }
            if (student.getName() != null) {
                theStudent.setName(student.getName());
            }
            if (student.getDateOfBirth() != null) {
                theStudent.setDateOfBirth(student.getDateOfBirth());
            }

            this.studentRepo.save(theStudent);
            return ResponseEntity.ok(theStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable("id") Long ID){
        Optional<Student> existStudentOptional=this.studentRepo.findById(ID);
        if(existStudentOptional.isPresent()){
            this.studentRepo.delete(existStudentOptional.get());
            return ResponseEntity.ok("student with id "+ID +"deleted");
        }
        return ResponseEntity.notFound().build();
    }

}
