package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.Teacher;
import com.todungnguyen.repository.TeacherRepository;

@RestController
public class TeacherController {
	@Autowired
	TeacherRepository teacherRepository;
	
	//CREAT
    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public ResponseEntity<Object> addTeacher(@RequestBody Teacher teacher) {
        this.teacherRepository.save(teacher);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllTeacher() {
        Iterable<Teacher> teachers = this.teacherRepository.findAll();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/teacher/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showTeacher(@PathVariable("name") String name) {
    	Teacher teacher = this.teacherRepository.findByName(name);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/teacher/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTeacher(@PathVariable("name") String name, @RequestBody Teacher teacher) {
    	teacherRepository.deleteByName(name);
    	teacherRepository.save(teacher);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/teacher/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTeacher(@PathVariable("name") String name) {
        this.teacherRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
