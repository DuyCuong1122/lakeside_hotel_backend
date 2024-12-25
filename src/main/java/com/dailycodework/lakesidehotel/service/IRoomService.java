package com.dailycodework.lakesidehotel.service;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.dailycodework.lakesidehotel.model.Room;

public interface IRoomService {

	 Room addNewRoom(MultipartFile photo, String roomType,BigDecimal roomPrice );
}
