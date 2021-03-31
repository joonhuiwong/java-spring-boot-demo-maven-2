package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student s1 = new Student(
                    "John Doe",
                    "johndoe@unknown.com",
                    LocalDate.of(1987, Month.DECEMBER, 14)
            );

            Student s2 = new Student(
                    "Jane Doe",
                    "janedoe@unknown.com",
                    LocalDate.of(1987, Month.DECEMBER, 14)
            );

            Optional<Student> studentOptional = repository
                    .findStudentByEmail(s1.getEmail());
            if (studentOptional.isEmpty()) {
                repository.save(s1);
            }

            studentOptional = repository
                    .findStudentByEmail(s2.getEmail());
            if (studentOptional.isEmpty()) {
                repository.save(s2);
            }
        };
    }
}
