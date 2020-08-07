package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.Course;
import com.todungnguyen.repository.CourseRepository;

@RestController
public class CourseController {
	@Autowired
	CourseRepository courseRepository;
	
	//CREAT
    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public ResponseEntity<Object> addCourse(@RequestBody Course course) {
        this.courseRepository.save(course);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllCourse() {
        Iterable<Course> courses= this.courseRepository.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/course/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showCourse(@PathVariable("name") String name) {
    	Course course = this.courseRepository.findByName(name);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/course/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCourse(@PathVariable("name") String name, @RequestBody Course course) {
    	courseRepository.deleteByName(name);
    	courseRepository.save(course);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/course/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCourse(@PathVariable("name") String name) {
        this.courseRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
