package com.prajwal.joinmyride1.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.prajwal.joinmyride1.entity.BookingRequest;
import com.prajwal.joinmyride1.entity.Driver;
import com.prajwal.joinmyride1.entity.Ride;
import com.prajwal.joinmyride1.entity.User;
import com.prajwal.joinmyride1.service.BookingRequestService;
import com.prajwal.joinmyride1.service.DriverService;
import com.prajwal.joinmyride1.service.RideService;
import com.prajwal.joinmyride1.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private RideService rideService;

    @Autowired
    private BookingRequestService bookingRequestService;

    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    public User getUser(String email) {
        return userService.getUserByEmail(email);
    }

    public Driver getDriver(String email) {
        return driverService.getDriverByEmail(email);
    }
    public List<Ride> getRidesByStatus(Ride.RideStatus status) {
        return rideService.getRidesByStatus(status);
    }
    public BookingRequest.BookingStatus getBookingStatus(int BookingRequestId) throws InstanceNotFoundException {
        return bookingRequestService.getBookingStatus(BookingRequestId);
    }
}
