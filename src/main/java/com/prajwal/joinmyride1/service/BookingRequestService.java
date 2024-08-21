

package com.prajwal.joinmyride1.service;

import com.prajwal.joinmyride1.Repository.BookingRequestRepository;
import com.prajwal.joinmyride1.Repository.PassengerRepository;
import com.prajwal.joinmyride1.Repository.RideRepository;
import com.prajwal.joinmyride1.entity.BookingRequest;
import com.prajwal.joinmyride1.entity.Passenger;
import com.prajwal.joinmyride1.entity.Ride;
import com.prajwal.joinmyride1.entity.BookingRequest.BookingStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.management.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BookingRequestService {
    @Autowired
    private BookingRequestRepository bookingRequestRepository;
    @Autowired
    private RideService rideService;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    public BookingRequestService() {
    }

    @PreAuthorize("hasRole('PASSENGER')")
    public BookingRequest.BookingStatus getBookingStatus(int bookingRequestId) throws InstanceNotFoundException {
        Optional<BookingRequest> booking = this.bookingRequestRepository.findById(bookingRequestId);
        if (booking.isPresent()) {
            return ((BookingRequest)booking.get()).getStatus();
        } else {
            throw new InstanceNotFoundException("id is incorrect");
        }
    }

    @PreAuthorize("hasRole('PASSENGER')")
    public BookingRequest createBookingRequest(int rideId, int passengerId) {
        Optional<Ride> rideOpt = this.rideRepository.findById(rideId);
        Optional<Passenger> passengerOpt = this.passengerRepository.findById(passengerId);
        if (rideOpt.isPresent() && passengerOpt.isPresent()) {
            Ride ride = (Ride)rideOpt.get();
            Passenger passenger = (Passenger)passengerOpt.get();
            BookingRequest bookingRequest = new BookingRequest();
            bookingRequest.setRide(ride);
            bookingRequest.setPassenger(passenger);
            bookingRequest.setStatus(BookingStatus.PENDING);
            bookingRequest.setRequestedAt(LocalDateTime.now());
            bookingRequest.setUpdatedAt(LocalDateTime.now());
            return (BookingRequest)this.bookingRequestRepository.save(bookingRequest);
        } else {
            throw new RuntimeException("Ride or Passenger not found");
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    public BookingRequest acceptBookingRequest(int bookingRequestId) {
        Optional<BookingRequest> bookingRequestOpt = this.bookingRequestRepository.findById(bookingRequestId);
        if (bookingRequestOpt.isPresent()) {
            BookingRequest bookingRequest = (BookingRequest)bookingRequestOpt.get();
            bookingRequest.setStatus(BookingStatus.ACCEPTED);
            bookingRequest.setUpdatedAt(LocalDateTime.now());
            this.bookingRequestRepository.save(bookingRequest);
            this.rideService.addPassengerToRide(bookingRequest.getRide().getRideId(), bookingRequest.getPassenger().getPassengerId());
            return bookingRequest;
        } else {
            throw new RuntimeException("Booking request not found");
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    public BookingRequest rejectBookingRequest(int bookingRequestId) {
        Optional<BookingRequest> bookingRequestOpt = this.bookingRequestRepository.findById(bookingRequestId);
        if (bookingRequestOpt.isPresent()) {
            BookingRequest bookingRequest = (BookingRequest)bookingRequestOpt.get();
            bookingRequest.setStatus(BookingStatus.REJECTED);
            bookingRequest.setUpdatedAt(LocalDateTime.now());
            return (BookingRequest)this.bookingRequestRepository.save(bookingRequest);
        } else {
            throw new RuntimeException("Booking request not found");
        }
    }

    @PreAuthorize("hasRole('PASSENGER')")
    public String deleteBookingRequest(int bookingRequestId) {
        this.bookingRequestRepository.deleteById(bookingRequestId);
        return bookingRequestId + " deleted";
    }
}
