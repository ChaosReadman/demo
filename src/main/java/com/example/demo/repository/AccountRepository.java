package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUserName(String userName);

    @Modifying
    @Transactional
    @Query(value="update account a set a.user_name=:#{#ac.userName}, a.nick_name=:#{#ac.nickName}, a.password=:#{#ac.password}, a.privileges=:#{#ac.privileges}, a.org_password=:#{#ac.orgPassword}, a.age=:#{#ac.age}, a.last_update_user=:#{#ac.lastUpdateUser}, a.last_update_date=CURRENT_TIMESTAMP where a.id=:#{#ac.id}", nativeQuery = true)
    public abstract void update(@Param("ac") Account ac);

    @Modifying
    @Transactional
    @Query(value="insert into account (user_name, nick_name, org_password, password, age, privileges, last_update_user ) values (:#{#ac.userName}, :#{#ac.nickName}, :#{#ac.orgPassword}, :#{#ac.password}, :#{#ac.age}, :#{#ac.privileges}, :#{#ac.lastUpdateUser})", nativeQuery = true)
    public abstract void insert(@Param("ac") Account ac);
}
