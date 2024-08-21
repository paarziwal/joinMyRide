package com.prajwal.joinmyride1.Repository;

import com.prajwal.joinmyride1.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRequestRepository extends JpaRepository<BookingRequest, Integer> {
}
