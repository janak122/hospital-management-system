package org.raghuvir.hms.services;

import java.util.List;

import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.daos.ManageStaffDAO;
import org.raghuvir.hms.daos.ManageStaffDAOImpl;
import org.raghuvir.hms.dtos.StaffInfoDTO;
import org.springframework.stereotype.Service;

@Service("staffservices")
public class ManageStaffServiceImpl implements ManageStaffService{

	ManageStaffDAO instance=ManageStaffDAOImpl.getInstance();
	
	@Override
	public List<StaffBEAN> searchStaff(String query) {
		return instance.searchStaff(query);
	}

	@Override
	public List<StaffBEAN> getStaffList(int pageno) {
		return instance.getStaffList(pageno);
	}

	@Override
	public StaffInfoDTO getStaffInfo(String sid) {
		return instance.getStaffInfo(sid);
	}

	@Override
	public long getTotalStaff() {
		return instance.getTotalStaff();
	}

}
