package com.dailycodework.lakesidehotel.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

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

	@Override
	public List<String> getAllRoomType() {
		return roomRepository.findDistinctRoomTypes();
	}

	@Override
	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	@Override
	public byte[] getRoomPhotoByRoomId(Long roomId) {
		Optional<Room> theRoom = roomRepository.findById(roomId);
		if (theRoom.isEmpty()) {
			throw new ResourceNotFoundException("Sorry, Room not found");
		}
		Blob 
		return new byte[0];
	}

}
