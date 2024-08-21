package com.prajwal.joinmyride1.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BookingRequest")
public class BookingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int bookingRequestId;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors, Getters, and Setters

    public BookingRequest() {
    }

    public BookingRequest(Ride ride, Passenger passenger, BookingStatus status, LocalDateTime requestedAt, LocalDateTime updatedAt) {
        this.ride = ride;
        this.passenger = passenger;
        this.status = status;
        this.requestedAt = requestedAt;
        this.updatedAt = updatedAt;
    }

    public int getBookingRequestId() {
        return bookingRequestId;
    }

    public void setBookingRequestId(int bookingRequestId) {
        this.bookingRequestId = bookingRequestId;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public enum BookingStatus {
        PENDING, ACCEPTED, REJECTED
    }
}
