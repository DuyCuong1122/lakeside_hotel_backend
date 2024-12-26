package com.dailycodework.lakesidehotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dailycodework.lakesidehotel.model.Room;

public interface RoomRepository extends JpaRepository<Room,Long>{

	@Query("Select distinct r.roomType from Room r")
	List<String> findDistinctRoomTypes();

}
