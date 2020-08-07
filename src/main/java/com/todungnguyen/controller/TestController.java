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
import com.todungnguyen.repository.TestRepository;

@RestController
public class TestController {
	@Autowired
	TestRepository testRepository;
	
	//CREAT
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<Object> addTest(@RequestBody Test test) {
        this.testRepository.save(test);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllTest() {
        Iterable<Test> tests = this.testRepository.findAll();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showTest(@PathVariable("name") String name) {
    	Test test = this.testRepository.findByName(name);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/test/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTest(@PathVariable("name") String name, @RequestBody Test test) {
    	testRepository.deleteByName(name);
    	testRepository.save(test);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/test/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTest(@PathVariable("name") String name) {
        this.testRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
