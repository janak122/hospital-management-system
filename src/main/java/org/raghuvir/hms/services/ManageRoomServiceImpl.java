package org.raghuvir.hms.services;

import java.util.List;

import org.raghuvir.hms.daos.ManageRoomDAO;
import org.raghuvir.hms.daos.ManageRoomDAOImpl;
import org.raghuvir.hms.dtos.RoomInfoDTO;
import org.raghuvir.hms.dtos.RoomListDTO;
import org.springframework.stereotype.Service;

@Service("roomservices")
public class ManageRoomServiceImpl implements ManageRoomService{

	
	ManageRoomDAO instance=ManageRoomDAOImpl.getInstance();
	
	@Override
	public List<RoomListDTO> searchRooms(String query) {
		return instance.searchRooms(query);
	}

	@Override
	public List<String> getAvailableRoomList(String type) {
		return instance.getAvailableRoomList(type);
	}

	@Override
	public void removePatient(String entryid) {
		instance.removePatient(entryid);
	}

	@Override
	public void addPatient(String pid, String roomno, String date1) {
		instance.addPatient(pid, roomno, date1);
	}

	@Override
	public List<RoomListDTO> getRoomList(int pageno) {
		return instance.getRoomList(pageno);
	}

	@Override
	public RoomInfoDTO getRoomInfo(String roomno) {
		return instance.getRoomInfo(roomno);
	}

	@Override
	public long getTotalRooms() {
		return instance.getTotalRooms();
	}

}
