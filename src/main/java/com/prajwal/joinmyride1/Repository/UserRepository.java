package com.prajwal.joinmyride1.Repository;

import com.prajwal.joinmyride1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {
    Optional<User> findByEmail(String email);

}
