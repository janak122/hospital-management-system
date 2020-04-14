package org.raghuvir.hms.dtos;

import java.util.LinkedList;
import java.util.List;

import org.raghuvir.hms.daos.ManageDoctorDAO;
import org.raghuvir.hms.daos.ManageDoctorDAOImpl;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.daos.ManagePatientDAOImpl;
import org.raghuvir.hms.daos.ManageRoomDAO;
import org.raghuvir.hms.daos.ManageRoomDAOImpl;
import org.raghuvir.hms.daos.ManageStaffDAO;
import org.raghuvir.hms.daos.ManageStaffDAOImpl;
import org.raghuvir.hms.utils.EntitiesConstants;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaginationDTO {
	public static final int PAGESIZE = 5;
	public static final int PAGEBARSIZE = PAGESIZE;

	private List<Integer> pagelist;
	private int currentPage, totalpages;
	private String entity;
	public PaginationDTO(List<Integer> pagelist, int currentPage, int totalpages,String entity) {
		this.pagelist = pagelist;
		this.currentPage = currentPage;
		this.totalpages = totalpages;
	}

	public static PaginationDTO getInstance(int currPage, String entity) {
		currPage=(currPage<=0)?1:currPage;
		List<Integer> list = new LinkedList<>();
		long totalEntities=1;
		int totalpages = 1;
		if (entity == EntitiesConstants.PATIENT) {
			totalEntities = ManagePatientDAOImpl.getInstance().getTotalPatients();
		} else if (entity == EntitiesConstants.DOCTOR) {
			totalEntities = ManageDoctorDAOImpl.getInstance().getTotalDoctors();
		} else if (entity == EntitiesConstants.STAFF) {
			totalEntities = ManageStaffDAOImpl.getInstance().getTotalStaff();
		} else if (entity == EntitiesConstants.ROOM) {
			totalEntities = ManageRoomDAOImpl.getInstance().getTotalRooms();
		}
		totalpages = (int) Math.ceil(totalEntities * 1.0 / PaginationDTO.PAGESIZE);
		for (int i = 1; i <= totalpages; i++) {
			list.add(i);
		}
		return new PaginationDTO(list, currPage, totalpages,entity);
	}
	
	public static int getStart(int pageno) {
		return (pageno - 1) * PaginationDTO.PAGESIZE;
	}
}
