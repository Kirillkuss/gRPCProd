package com.rpc.itrail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rpc.itrail.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
