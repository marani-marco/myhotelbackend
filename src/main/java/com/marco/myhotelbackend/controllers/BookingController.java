package com.marco.myhotelbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marco.myhotelbackend.models.Booking;
import com.marco.myhotelbackend.models.Room;
import com.marco.myhotelbackend.requestwrapper.BookingCustomerRequestWrapper;
import com.marco.myhotelbackend.services.BookingCustomerService;
import com.marco.myhotelbackend.services.BookingService;
import com.marco.myhotelbackend.services.CustomerService;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	CustomerService customerService;

	@Autowired
	BookingCustomerService bookingCustomerService;

	@RequestMapping(value = "/save-booking", method = RequestMethod.POST)
	private ResponseEntity<BookingCustomerRequestWrapper> saveBooking(
			@RequestBody BookingCustomerRequestWrapper bookingCustomerWrapper) {

		return bookingService.saveBooking(bookingCustomerWrapper);

	}

	@DeleteMapping("/delete-booking/{bookingid}")
	private void deleteBooking(@PathVariable("bookingid") int bookingID) {
		
		bookingService.delete(bookingID);
	}

	@RequestMapping(value = "/booking/{bookingid}", method = RequestMethod.GET)
	private ResponseEntity<BookingCustomerRequestWrapper> getBookingByID(@PathVariable("bookingid") int bookingID) {

		Booking booking = bookingService.getById(bookingID);

		List<Integer> customerIDs = bookingCustomerService.getCustomerIDsFromBookingID(bookingID);

		BookingCustomerRequestWrapper bookingCustomerWrapper = new BookingCustomerRequestWrapper(booking,
				customerService.getByIDs(customerIDs));

		return new ResponseEntity<BookingCustomerRequestWrapper>(bookingCustomerWrapper, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-booking-by-external-id/{externalId}", method = RequestMethod.GET)
	private ResponseEntity<BookingCustomerRequestWrapper> getBookingByID(@PathVariable("externalId") String externalId) {

		Booking booking = bookingService.getByExternalId(externalId);

		List<Integer> customerIDs = bookingCustomerService.getCustomerIDsFromBookingID(booking.getBookingID());

		BookingCustomerRequestWrapper bookingCustomerWrapper = new BookingCustomerRequestWrapper(booking,
				customerService.getByIDs(customerIDs));

		return new ResponseEntity<BookingCustomerRequestWrapper>(bookingCustomerWrapper, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/bookings")
	@ResponseBody
	public ResponseEntity<List<Room>> search(@RequestParam(value = "dateStart") String dateStart,
			@RequestParam(value = "dateEnd") String dateEnd, @RequestParam(value = "people") int people) {		
		
		List<Room> roomsWithCapacityRequested = bookingService.searchBookings(dateStart, dateEnd, people);
		
		return new ResponseEntity<List<Room>>(roomsWithCapacityRequested, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/today-arrivals")
	@ResponseBody
	public ResponseEntity<List<BookingCustomerRequestWrapper>> todayArrivals() {
		
		return bookingService.getTodayArrivals();

	}

	@RequestMapping(method = RequestMethod.GET, value = "/today-departures")
	@ResponseBody
	public ResponseEntity<List<BookingCustomerRequestWrapper>> todayDepartures() {

		return bookingService.getTodayDepartures();
		
	}



}
