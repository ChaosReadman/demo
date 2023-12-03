package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.messageBoard;

@Repository
public interface messageBoardRepository extends JpaRepository<messageBoard, Integer>{
    @Modifying
    @Transactional
    @Query(value="insert into message_board (user_name, message) values(:#{#mb.userName}, :#{#mb.message})", nativeQuery = true)
    public abstract void insert(@Param("mb") messageBoard mb);

}
