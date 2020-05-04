package org.raghuvir.hms.services;

import java.util.LinkedList;
import java.util.List;

import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.daos.ManageRoomDAO;
import org.raghuvir.hms.daos.ManageRoomDAOImpl;
import org.raghuvir.hms.dtos.RoomInfoDTO;
import org.raghuvir.hms.dtos.RoomListDTO;
import org.raghuvir.hms.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roomservices")
public class ManageRoomServiceImpl implements ManageRoomService{

	
	ManageRoomDAO instance=ManageRoomDAOImpl.getInstance();
	@Autowired
	RoomRepository roomRepository;
	
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
	@Transactional
	public Object[] getRoomList(Pageable pageable) {
		Page<RoomBEAN> rooms=roomRepository.findAll(pageable);
		List<RoomListDTO> list=new LinkedList<>();
		rooms.getContent().forEach((RoomBEAN room)->list.add(new RoomListDTO(room.getRoomno()
				, room.getTypeofroom(), room.getRoomentries().size())));
		return new Object[] {rooms,list};
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
