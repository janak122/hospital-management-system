package org.raghuvir.hms.daos;

import java.util.List;

import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.dtos.RoomInfoDTO;
import org.raghuvir.hms.dtos.RoomListDTO;

public interface ManageRoomDAO {

	List<RoomListDTO> searchRooms(String query);
	
    List<String> getAvailableRoomList(String type);

    void removePatient(String entryid);

    void addPatient(String pid, String roomno, String date1);
    
    List<RoomListDTO> getRoomList(int pageno);
    
    RoomInfoDTO getRoomInfo(String roomno);
    
    long getTotalRooms();
}
