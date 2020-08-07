package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.Student;



public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByName(String name);
    
    @Transactional
    @Modifying
    @Query("delete from Student where name = ?1")
    void deleteByName(String name);

}
