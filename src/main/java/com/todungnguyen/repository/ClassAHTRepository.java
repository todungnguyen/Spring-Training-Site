package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.ClassAHT;

public interface ClassAHTRepository extends CrudRepository<ClassAHT, Integer> {

    ClassAHT findByName(String name);
    
    @Transactional
    @Modifying
    @Query("delete from ClassAHT where name = ?1")
    void deleteByName(String name);
}
