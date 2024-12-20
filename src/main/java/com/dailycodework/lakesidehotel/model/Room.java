package com.dailycodework.lakesidehotel.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roomType;
	private BigDecimal roomPrice;
	private boolean isBooked = false;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BookedRoom> bookings;

	public Room() {
		this.bookings = new ArrayList<BookedRoom>();
	}
	
	public void addBooking(BookedRoom booking) {
		if (this.bookings == null) {
			this.bookings = new ArrayList<BookedRoom>();
			
		}
		this.bookings.add(booking);
		booking.setRoom(this);
		this.isBooked = true;
	
	}
}
