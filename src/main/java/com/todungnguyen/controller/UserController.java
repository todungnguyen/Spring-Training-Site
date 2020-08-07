package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.User;
import com.todungnguyen.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	//CREAT
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        this.userRepository.save(user);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllUser() {
        Iterable<User> users = this.userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/{email}", method = RequestMethod.GET)
    public ResponseEntity<Object> showUser(@PathVariable("email") String email) {
    	User user = this.userRepository.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/user/{email}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("email") String email, @RequestBody User user) {
    	userRepository.deleteByEmail(email);
    	userRepository.save(user);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("email") String email) {
        this.userRepository.deleteByEmail(email);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
