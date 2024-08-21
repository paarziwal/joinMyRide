package com.prajwal.joinmyride1.Repository;

import com.prajwal.joinmyride1.entity.Driver;
import com.prajwal.joinmyride1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByUserUserId(int userId);
//    Optional<Driver> findByUserEmail(String email);
}
