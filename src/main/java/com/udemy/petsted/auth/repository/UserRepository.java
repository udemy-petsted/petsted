package com.udemy.petsted.auth.repository;

import com.udemy.petsted.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
