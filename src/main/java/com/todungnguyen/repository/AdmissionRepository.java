package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.Admission;


public interface AdmissionRepository extends CrudRepository<Admission, Integer> {

    Admission findByName(String name);
    
    @Transactional
    @Modifying
    @Query("delete from Admission where name = ?1")
    void deleteByName(String name);
    
}
