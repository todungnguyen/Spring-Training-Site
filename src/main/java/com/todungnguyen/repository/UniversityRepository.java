package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.University;


public interface UniversityRepository extends CrudRepository<University, Integer> {

    University findByName(String name);
    
    @Transactional
    @Modifying
    @Query("delete from University where name = ?1")
    void deleteByName(String name);

}
