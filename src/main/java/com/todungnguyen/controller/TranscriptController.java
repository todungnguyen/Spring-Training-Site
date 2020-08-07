package com.todungnguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.Transcript;
import com.todungnguyen.repository.TranscriptRepository;

@RestController
public class TranscriptController {
	@Autowired
	TranscriptRepository transcriptRepository;
	
	//CREAT
    @RequestMapping(value = "/transcript", method = RequestMethod.POST)
    public ResponseEntity<Object> addTranscript(@RequestBody Transcript trans) {
        this.transcriptRepository.save(trans);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
  //READ
    @RequestMapping(value = "/transcript", method = RequestMethod.GET)
    public ResponseEntity<Object> showAllTranscript() {
        Iterable<Transcript> transs = this.transcriptRepository.findAll();
        return new ResponseEntity<>(transs, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/transcript/{code}", method = RequestMethod.GET)
    public ResponseEntity<Object> showTranscript(@PathVariable("code") String code) {
    	Transcript trans = this.transcriptRepository.findByTestCode(code);
        return new ResponseEntity<>(trans, HttpStatus.OK);
    }
    
    //UPDATE
    @RequestMapping(value = "/transcript/{code}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTranscript(@PathVariable("code") String code, @RequestBody Transcript trans) {
    	transcriptRepository.deleteByTestCode(code);
    	transcriptRepository.save(trans);
		return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }
 
    //DELETE
    @RequestMapping(value = "/transcript/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTranscript(@PathVariable("code") String code) {
        this.transcriptRepository.deleteByTestCode(code);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
