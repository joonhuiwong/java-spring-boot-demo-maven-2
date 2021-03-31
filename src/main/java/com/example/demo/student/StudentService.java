package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll(); // JPA implements a lot of basic query methods already for us to use.
    }

    public void addNewStudent(Student student) {

        // Validation - Email Unique Check
        this.validateEmail(student);

        // If pass, save
        studentRepository.save(student);
    }


    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(("Student with ID:" + studentId + " does not exist!"));
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with ID:" + studentId + " does not exist!"
                ));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)) { // Checks that the name is equal or not
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            this.validateEmail(student);
            student.setEmail(email);
        }

    }

    private void validateEmail(Student student) {
        // Validation - Email Unique Check
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
    }
}
