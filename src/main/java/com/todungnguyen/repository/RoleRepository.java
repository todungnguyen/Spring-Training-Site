package com.todungnguyen.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.todungnguyen.model.Role;


public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);
    
    @Transactional
    @Modifying
    @Query("delete from Role where name = ?1")
    void deleteByName(String name);

}
