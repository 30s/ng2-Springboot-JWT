package com.slk.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slk.app.entity.User;


@Transactional
@Repository
public interface UserProfileRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.loginId=:loginId")
    public Optional<User> findByloginId(@Param("loginId") String loginId);

}
