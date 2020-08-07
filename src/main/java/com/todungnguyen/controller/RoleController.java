package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.Role;
import com.todungnguyen.repository.RoleRepository;

@RestController
public class RoleController {
	@Autowired
	RoleRepository roleRepository;
	
	//CREAT
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public ResponseEntity<Object> addRole(@RequestBody Role role) {
        this.roleRepository.save(role);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllRole() {
        Iterable<Role> roles = this.roleRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/role/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> showRole(@PathVariable("name") String name) {
    	Role role = this.roleRepository.findByName(name);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/role/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateRole(@PathVariable("name") String name, @RequestBody Role role) {
    	roleRepository.deleteByName(name);
    	roleRepository.save(role);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/role/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteRole(@PathVariable("name") String name) {
        this.roleRepository.deleteByName(name);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
