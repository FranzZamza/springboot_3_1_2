package com.shamsutdinov.springboot_3_1_2.repository;

import com.shamsutdinov.springboot_3_1_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
