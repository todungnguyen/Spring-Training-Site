package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.User;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);
    
    @Transactional
    @Modifying
    @Query("delete from User where email = ?1")
    void deleteByEmail(String email);

}
