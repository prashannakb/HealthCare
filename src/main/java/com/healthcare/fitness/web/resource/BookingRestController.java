package com.healthcare.fitness.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.fitness.entity.Booking;
import com.healthcare.fitness.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingRestController {
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/coach/{coachId}/user/{userId}")
	public ResponseEntity<?> appointmentBooking(@RequestBody Booking book,@PathVariable("coachId") Integer coachId,@PathVariable("userId") Integer UserId ){
		Boolean resp=false;
		try {
			 resp=bookingService.appointmentBooking(coachId, UserId, book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return new ResponseEntity<Boolean>(resp,new HttpHeaders(),HttpStatus.OK); 
	}
	
	@PutMapping("/{bookingId}")
	public ResponseEntity<?> rescheduleBooking(@RequestBody Booking book,@PathVariable("bookingId") Integer bookingId){
		Boolean resp=false;
		try {
			 resp=bookingService.rescheduleBooking(bookingId,book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return new ResponseEntity<Boolean>(resp,new HttpHeaders(),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<?> cancelBooking(@PathVariable("bookingId") Integer bookingId){
		Boolean resp=false;
		try {
			 resp=bookingService.cancelBooking(bookingId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return new ResponseEntity<Boolean>(resp,new HttpHeaders(),HttpStatus.OK);
	}

}