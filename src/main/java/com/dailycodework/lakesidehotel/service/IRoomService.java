package com.dailycodework.lakesidehotel.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dailycodework.lakesidehotel.model.Room;

public interface IRoomService {

	 Room addNewRoom(MultipartFile photo, String roomType,BigDecimal roomPrice );
	 
	 List<String> getAllRoomType();

	List<Room> getAllRooms();

	byte[] getRoomPhotoByRoomId(Long id);
}
