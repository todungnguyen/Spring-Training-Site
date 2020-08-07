package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.Test;

public interface TestRepository extends CrudRepository<Test, Integer> {

	Test findByName(String name);

	@Transactional
    @Modifying
    @Query("delete from Test where name = ?1")
    void deleteByName(String name);
	
}
