package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.information;

@Repository
public interface informationRepository  extends JpaRepository<information, Integer>{
    @Transactional
    @Modifying
    @Query(value="insert into information (message) values(:#{#info.message}, :#{#lnk.url})", nativeQuery = true)
    public abstract void insert(@Param("info") information info);

    // @Modifying
    // @Transactional
    // @Query(value="insert into information (category) values(:#{#info.category})", nativeQuery = true)
    // public abstract void insertCategory(@Param("category") information category);
}
