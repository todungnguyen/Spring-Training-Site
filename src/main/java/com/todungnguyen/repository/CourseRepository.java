package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course findByName(String name);
    
    @Transactional
    @Modifying
    @Query("delete from Course where name = ?1")
    void deleteByName(String name);

}
