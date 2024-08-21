package com.prajwal.joinmyride1.Repository;

import com.prajwal.joinmyride1.entity.Passenger;
import com.prajwal.joinmyride1.entity.PassengerReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerReviewRepository extends JpaRepository<PassengerReview, Integer> {
    List<PassengerReview> findByPassenger(Passenger passenger);
}