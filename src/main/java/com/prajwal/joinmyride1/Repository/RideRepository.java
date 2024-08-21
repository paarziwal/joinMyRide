package com.prajwal.joinmyride1.Repository;

import com.prajwal.joinmyride1.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Integer> {
    List<Ride> findByRideStatus(Ride.RideStatus rideStatus);
}
