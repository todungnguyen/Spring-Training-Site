package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.Admission;
import com.todungnguyen.repository.AdmissionRepository;

@RestController
public class AdmissionController {
	@Autowired
	AdmissionRepository admissionRepository;
	
	//CREAT
    @RequestMapping(value = "/admission", method = RequestMethod.POST)
    public ResponseEntity<Object> addAdmission(@RequestBody Admission adm) {
        this.admissionRepository.save(adm);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/admission", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllAdmission() {
        Iterable<Admission> adms= this.admissionRepository.findAll();
        return new ResponseEntity<>(adms, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/admission/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showAdmission(@PathVariable("name") String name) {
    	Admission adm = this.admissionRepository.findByName(name);
        return new ResponseEntity<>(adm, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/admission/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAdmission(@PathVariable("name") String name, @RequestBody Admission adm) {
    	admissionRepository.deleteByName(name);
    	admissionRepository.save(adm);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/admission/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAdmission(@PathVariable("name") String name) {
        this.admissionRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
