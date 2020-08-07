package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

	Subject findByName(String name);
	
	@Transactional
    @Modifying
    @Query("delete from Subject where name = ?1")
    void deleteByName(String name);

}
