package com.ptit.edu.store.auth.dao;

import com.ptit.edu.store.auth.models.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

    @Query("select u.actived from User u where u.username = :username and u.role = :role")
    boolean isAccountActivated(@Param("username") String username,@Param("role") String role);

    @Modifying
    @Query("update User u set u.actived  = ?1 where u.username = ?2")
    @Transactional
    void activeAccount(boolean isActive,String username);
}
