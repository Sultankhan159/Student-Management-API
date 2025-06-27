package com.spring;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

	//constructor injection
	 private final StudentRepo repository;

	    public StudentService(StudentRepo repository) {
	        this.repository = repository;
	    }

	    
	    public List<Student> getAllStudents() {
	        return repository.findAll();
	    }

	    //Calls findById(id)
	    //If student is found → returns it
        //If not → returns null
	    public Student getStudentById(Long id) {
	        return repository.findById(id).orElse(null);
	    }

	    public Student saveStudent(Student student) {
	        return repository.save(student);
	    }

	    public void deleteStudent(Long id) {
	        repository.deleteById(id);
	    }
}
