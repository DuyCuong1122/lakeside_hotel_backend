package com.dailycodework.lakesidehotel.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dailycodework.lakesidehotel.model.Room;
import com.dailycodework.lakesidehotel.response.RoomResponse;
import com.dailycodework.lakesidehotel.service.IRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

	private final IRoomService roomService;

	@PostMapping("/add/new-room")
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,
			@RequestParam("roomType") String roomType, @RequestParam("roomPrice") BigDecimal roomPrice)
			throws SQLException, IOException {
		Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
		RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(), savedRoom.getRoomPrice());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/get/room-types")
	public List<String> getRoomTypes() {
		return roomService.getAllRoomType();
	}

	public ResponseEntity<List<RoomResponse>> getAllRooms() {
		List<Room> rooms = roomService.getAllRooms();
		List<RoomResponse> roomResponses = new ArrayList<RoomResponse>();
		for (Room room : rooms) {
			byte[] photoBytes = roomService.getRoomPhotoByRoomId(room.getId());
			if (photoBytes != null && photoBytes.length > 0) {
				String base64Photo = Base64.getEncoder().encodeToString(photoBytes);
				RoomResponse roomResponse = getRoomResponse(room);
				roomResponse.setPhoto(base64Photo);
				roomResponses.add(roomResponse);
			}
		}
		return ResponseEntity.ok(roomResponses);
	}
}
