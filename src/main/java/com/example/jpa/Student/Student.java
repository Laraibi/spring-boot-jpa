package com.example.jpa.Student;

import com.example.jpa.Classe.Classe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name="classe_id")
    private Classe classe;

}
