package com.dailycodework.lakesidehotel.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dailycodework.lakesidehotel.model.Room;
import com.dailycodework.lakesidehotel.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {

	private final RoomRepository roomRepository;

	@Override
	public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) {
		Room room = new Room();
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		if (!file.isEmpty()) {
			try {
				byte[] photoBytes = file.getBytes();
				room.setPhoto(photoBytes); 
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
		return roomRepository.save(room);
	}
}
