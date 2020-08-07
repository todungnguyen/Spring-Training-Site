package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.Student;
import com.todungnguyen.repository.StudentRepository;

@RestController
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	//CREAT
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<Object> addStudent(@RequestBody Student stu) {
        this.studentRepository.save(stu);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllStudent() {
        Iterable<Student> students = this.studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/student/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showStudent(@PathVariable("name") String name) {
    	Student stu = this.studentRepository.findByName(name);
        return new ResponseEntity<>(stu, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/student/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateStudent(@PathVariable("name") String name, @RequestBody Student stu) {
    	studentRepository.deleteByName(name);
    	studentRepository.save(stu);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/student/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteStudent(@PathVariable("name") String name) {
        this.studentRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
