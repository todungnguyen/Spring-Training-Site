package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.Transcript;

public interface TranscriptRepository extends CrudRepository<Transcript, Integer> {

	Transcript findByTestCode(String code);
	
	@Transactional
    @Modifying
    @Query("delete from Transcript where code = ?1")
    void deleteByTestCode(String code);

}
