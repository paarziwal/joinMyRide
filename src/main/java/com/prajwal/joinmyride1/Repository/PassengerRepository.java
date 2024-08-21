package com.prajwal.joinmyride1.Repository;

import com.prajwal.joinmyride1.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Optional<Passenger> findByUserUserId(int userId);
}
