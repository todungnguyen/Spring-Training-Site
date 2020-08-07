package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

	Teacher findByName(String name);
	
	@Transactional
    @Modifying
    @Query("delete from Teacher where name = ?1")
    void deleteByName(String name);

}
