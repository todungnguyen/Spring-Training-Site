package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.Test;
import com.todungnguyen.model.University;
import com.todungnguyen.repository.UniversityRepository;

@RestController
public class UniversityController {
	@Autowired
	UniversityRepository uniRepository;
	
	//CREAT
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public ResponseEntity<Object> addUniversity(@RequestBody University uni) {
        this.uniRepository.save(uni);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllUniversity() {
        Iterable<University> unis = this.uniRepository.findAll();
        return new ResponseEntity<>(unis, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/university/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showUniversity(@PathVariable("name") String name) {
    	University uni = this.uniRepository.findByName(name);
        return new ResponseEntity<>(uni, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/university/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUniversity(@PathVariable("name") String name, @RequestBody University uni) {
    	uniRepository.deleteByName(name);
    	uniRepository.save(uni);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/university/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUniversity(@PathVariable("name") String name) {
        this.uniRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
