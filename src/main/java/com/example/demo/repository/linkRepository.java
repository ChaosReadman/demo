package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.link;

@Repository
public interface linkRepository extends JpaRepository<link, Integer> {
    @Modifying
    @Transactional
    @Query(value="insert into link (title, url) values(:#{#lnk.title}, :#{#lnk.url})", nativeQuery = true)
    public abstract void insert(@Param("lnk") link lnk); 
}
