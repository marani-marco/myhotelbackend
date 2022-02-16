package com.marco.myhotelbackend.services;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.marco.myhotelbackend.repository.BookingRepository;
import com.marco.myhotelbackend.requestwrapper.BookingCustomerRequestWrapper;
import com.marco.myhotelbackend.specifications.BookingSpecificationBuilder;
import com.marco.myhotelbackend.utilities.RandomString;
import com.marco.myhotelbackend.models.Booking;
import com.marco.myhotelbackend.models.Customer;
import com.marco.myhotelbackend.models.Room;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	RoomService roomService;

	@Autowired
	CustomerService customerService;

	@Autowired
	BookingCustomerService bookingCustomerService;
	
	@Autowired
	JavaMailSender javaMailSender;

	public void delete(int bookingID) {
		bookingRepository.deleteById(bookingID);
	}

	public Booking getById(int bookingID) {
		return bookingRepository.findById(bookingID).get();
	}

	public Booking getByExternalId(String externalID) {
		return bookingRepository.findByexternalId(externalID);
	}
	
	public List<Booking> getBySpecifications(Specification<Booking> spec) {
		return bookingRepository.findAll(spec);
	}

	/*
	 * Motore di ricerca
	 */

	public List<Room> searchBookings(String dateStart, String dateEnd, int people) {

		List<Room> roomsWithCapacityRequested = roomService.roomsWithCapacityRequested(people);
		List<Integer> roomsWithCapacityRequestedIDs = new ArrayList<Integer>();

		roomsWithCapacityRequested.stream().forEach(r -> {
			roomsWithCapacityRequestedIDs.add(r.getRoomID());
		});

		BookingSpecificationBuilder builder = new BookingSpecificationBuilder();

		builder.with("dateStart,dateEnd", "between", dateStart + "," + dateEnd);
		builder.with("roomID", "in", roomsWithCapacityRequestedIDs);

		Specification<Booking> spec = builder.build();

		List<Booking> searchResults = getBySpecifications(spec);

		if (searchResults.size() >= roomsWithCapacityRequested.size())
			return null;
		else {
			searchResults.stream().forEach(sr -> {
				roomsWithCapacityRequested.removeIf(r -> (r.getRoomID() == sr.getRoomID()));
			});

			return roomsWithCapacityRequested;
		}

	}

	public ResponseEntity<BookingCustomerRequestWrapper> saveBooking(
			BookingCustomerRequestWrapper bookingCustomerRequestWrapper) {

		Booking booking = bookingCustomerRequestWrapper.getBooking();
		
		if(booking.getExternalID().isEmpty()) {
			String easy = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
			RandomString externalID = new RandomString(23, new SecureRandom(), easy);
			
			booking.setExternalID(externalID.nextString());	
			
		}
		
		List<Customer> customers = bookingCustomerRequestWrapper.getCustomers();
		
		bookingRepository.save(booking);

		customerService.saveUpdateList(customers);

		customers.stream().forEach(customer -> {
			bookingCustomerService.save(booking.getBookingID(), customer.getCustomerID());
		});
		
		Optional<Room> room = roomService.getRoomByID(booking.getRoomID());
		
		sendSimpleEmail(customers.get(0).getCustomerEmail(), 
				"Hotel Bellavista - La camera " + room.get().getRoomName() + " ti aspetta!\n", 
				"Ciao " + customers.get(0).getCustomerFirstName() + ", la camera è stata prenotata correttamente! \n" +
				"Avremo l'onore di accoglierti dal " + booking.getDateStart() + " al " + booking.getDateEnd() + ". \n" +
				"Se vuoi modificare o cancellare la tua prenotazione, clicca il seguente link: http:localhost:8080/modifica-prenotazione/" + booking.getExternalID());

		return new ResponseEntity<BookingCustomerRequestWrapper>(new BookingCustomerRequestWrapper(booking, customers),
				HttpStatus.OK);

	}

	public ResponseEntity<List<BookingCustomerRequestWrapper>> getTodayArrivals() {

		BookingSpecificationBuilder builder = new BookingSpecificationBuilder();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate now = LocalDate.now();

		builder.with("dateStart", ":", now.format(formatter));

		Specification<Booking> spec = builder.build();

		return new ResponseEntity<List<BookingCustomerRequestWrapper>>(
				createBookingCustomerWrapperList(getBySpecifications(spec)), HttpStatus.OK);
	}



	public ResponseEntity<List<BookingCustomerRequestWrapper>> getTodayDepartures() {

		BookingSpecificationBuilder builder = new BookingSpecificationBuilder();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate now = LocalDate.now();

		builder.with("dateEnd", ":", now.format(formatter));

		Specification<Booking> spec = builder.build();

		return new ResponseEntity<List<BookingCustomerRequestWrapper>>(
				createBookingCustomerWrapperList(getBySpecifications(spec)), HttpStatus.OK);
	}
	
	private List<BookingCustomerRequestWrapper> createBookingCustomerWrapperList(List<Booking> bookings) {

		List<BookingCustomerRequestWrapper> listBookingCustomerWrapper = new ArrayList<BookingCustomerRequestWrapper>();

		bookings.stream().forEach(booking -> {
			listBookingCustomerWrapper.add(new BookingCustomerRequestWrapper(booking, customerService
					.getByIDs(bookingCustomerService.getCustomerIDsFromBookingID(booking.getBookingID()))));
		});

		return listBookingCustomerWrapper;

	}
	
	private void sendSimpleEmail(String emailTo, String subject, String text) {

		try {
		    SimpleMailMessage msg = new SimpleMailMessage();
		    
		    msg.setFrom("reception@hotelbellavistapaperopoli.it");
		    msg.setTo("marcolavoro90@gmail.com");

		    msg.setSubject(subject);
		    msg.setText(text);
		    
			javaMailSender.send(msg);

		    
		}catch(Exception e) {
			e.toString();
		}
		
	}

}
