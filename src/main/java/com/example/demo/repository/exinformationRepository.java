package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.exinformation;

@Repository
public interface exinformationRepository  extends JpaRepository<exinformation, Integer>{
    @Modifying
    @Transactional
    @Query(value="insert into exinformation (message) values(:#{#exinfo.message})", nativeQuery = true)
    public abstract void insert(@Param("exinfo") exinformation exinfo);
}
