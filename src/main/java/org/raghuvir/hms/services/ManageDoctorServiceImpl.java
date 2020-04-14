package org.raghuvir.hms.services;

import java.util.List;

import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.daos.ManageDoctorDAO;
import org.raghuvir.hms.daos.ManageDoctorDAOImpl;
import org.raghuvir.hms.dtos.DoctorInfoDTO;
import org.springframework.stereotype.Service;
@Service("doctorservices")
public class ManageDoctorServiceImpl implements ManageDoctorService{

	ManageDoctorDAO instance=ManageDoctorDAOImpl.getInstance();
	@Override
	public List<DoctorBEAN> searchDoctor(String query) {
		return instance.searchDoctor(query);
	}

	@Override
	public List<DoctorBEAN> getDoctorList(int pageno) {
		return instance.getDoctorList(pageno);
	}

	@Override
	public DoctorInfoDTO getDoctorInfo(String did) {
		return instance.getDoctorInfo(did);
	}

	@Override
	public long getTotalDoctors() {
		return instance.getTotalDoctors();
	}

}
