package com.prajwal.joinmyride1.Repository;

import com.prajwal.joinmyride1.entity.Driver;
import com.prajwal.joinmyride1.entity.DriverReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverReviewRepository extends JpaRepository<DriverReview, Integer> {
    List<DriverReview> findByDriver(Driver driver);
}