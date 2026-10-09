
package com.example.student;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student) {
        student.setId(null);
        return repository.save(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student updatedStudent) {

        Student student = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found"));

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setDepartment(updatedStudent.getDepartment());
        student.setAge(updatedStudent.getAge());

        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found");
        }

        repository.deleteById(id);
    }
}