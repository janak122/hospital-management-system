package org.raghuvir.hms.services;

import java.util.List;

import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.controllers.EntityToDTO;
import org.raghuvir.hms.daos.ManageStaffDAO;
import org.raghuvir.hms.daos.ManageStaffDAOImpl;
import org.raghuvir.hms.dtos.StaffInfoDTO;
import org.raghuvir.hms.repositories.StaffRepository;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("staffservices")
public class ManageStaffServiceImpl implements ManageStaffService{

	ManageStaffDAO instance=ManageStaffDAOImpl.getInstance();
	@Autowired
	StaffRepository staffRepository;
	@Autowired
	EntityToDTO entityToDTO;
	@Override
	public List<StaffBEAN> searchStaff(String query) {
		return instance.searchStaff(query);
	}

	@Override
	public Page<StaffBEAN> getStaffList(Pageable pageable) {
		return staffRepository.findByJobNotIgnoreCase(EntitiesConstants.DOCTOR, pageable);
	}

	@Override
	public StaffInfoDTO getStaffInfo(String sid) {		
		return entityToDTO.convertToStaffInfoDTO(staffRepository.findById(sid).get());
//		return instance.getStaffInfo(sid);
	}

	@Override
	public long getTotalStaff() {
		return instance.getTotalStaff();
	}

	@Override
	public LoginBEAN getIdPassword(String sid) {
		return instance.getIdPassword(sid);
	}

}
