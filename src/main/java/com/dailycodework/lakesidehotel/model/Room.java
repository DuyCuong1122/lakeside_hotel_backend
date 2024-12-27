package com.dailycodework.lakesidehotel.model;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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

	@Lob
	@Column(name = "photo", columnDefinition="MEDIUMBLOB")
	private byte[] photo;

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BookedRoom> bookings;

	public Room() {
		this.bookings = new ArrayList<BookedRoom>();
	}
	
	public static String randomNumeric(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder randomNumber = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = secureRandom.nextInt(10);
            randomNumber.append(digit);
        }

        return randomNumber.toString();
    }

	public void addBooking(BookedRoom booking) {
		if (this.bookings == null) {
			this.bookings = new ArrayList<BookedRoom>();
		}
		this.bookings.add(booking);
		booking.setRoom(this);
		this.isBooked = true;
		String bookingCode = randomNumeric(10);
		booking.setBookingConfirmationCode(bookingCode);
	}
}
