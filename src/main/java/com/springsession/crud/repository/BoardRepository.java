package com.springsession.crud.repository;

import com.springsession.crud.entty.Crud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Crud, Integer> {
}
