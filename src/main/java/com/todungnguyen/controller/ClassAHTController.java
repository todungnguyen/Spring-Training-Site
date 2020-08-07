package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.ClassAHT;
import com.todungnguyen.repository.ClassAHTRepository;

@RestController
public class ClassAHTController {

	@Autowired
	ClassAHTRepository classAHTRepository;
	
	//CREAT
    @RequestMapping(value = "/class", method = RequestMethod.POST)
    public ResponseEntity<Object> addClass(@RequestBody ClassAHT classAHT) {
        this.classAHTRepository.save(classAHT);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/class", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllClass() {
        Iterable<ClassAHT> classAHTs = this.classAHTRepository.findAll();
        return new ResponseEntity<>(classAHTs, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/class/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showClass(@PathVariable("name") String name) {
        ClassAHT classAHT = this.classAHTRepository.findByName(name);
        return new ResponseEntity<>(classAHT, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/class/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateClass(@PathVariable("name") String name, @RequestBody ClassAHT classAHT) {
    	classAHTRepository.deleteByName(name);
    	classAHTRepository.save(classAHT);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/class/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClass(@PathVariable("name") String name) {
        this.classAHTRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
