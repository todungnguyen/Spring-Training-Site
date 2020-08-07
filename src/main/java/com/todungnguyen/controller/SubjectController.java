package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.Subject;
import com.todungnguyen.repository.SubjectRepository;

@RestController
public class SubjectController {
	@Autowired
	SubjectRepository subjectRepository;
	
	//CREAT
    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public ResponseEntity<Object> addSubject(@RequestBody Subject sub) {
        this.subjectRepository.save(sub);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllSubject() {
        Iterable<Subject> subs = this.subjectRepository.findAll();
        return new ResponseEntity<>(subs, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/subject/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showSubject(@PathVariable("name") String name) {
    	Subject sub = this.subjectRepository.findByName(name);
        return new ResponseEntity<>(sub, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/subject/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSubject(@PathVariable("name") String name, @RequestBody Subject sub) {
    	subjectRepository.deleteByName(name);
    	subjectRepository.save(sub);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/subject/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSubject(@PathVariable("name") String name) {
        this.subjectRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
