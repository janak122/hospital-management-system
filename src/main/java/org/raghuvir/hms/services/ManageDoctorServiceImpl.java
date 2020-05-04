package org.raghuvir.hms.services;

import java.util.List;

import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.controllers.EntityToDTO;
import org.raghuvir.hms.daos.ManageDoctorDAO;
import org.raghuvir.hms.daos.ManageDoctorDAOImpl;
import org.raghuvir.hms.dtos.DoctorInfoDTO;
import org.raghuvir.hms.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("doctorservices")
public class ManageDoctorServiceImpl implements ManageDoctorService{

	ManageDoctorDAO instance=ManageDoctorDAOImpl.getInstance();
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	EntityToDTO entityToDTO;
	@Override
	public List<DoctorBEAN> searchDoctor(String query) {
		return instance.searchDoctor(query);
	}

	@Override
	public Page<DoctorBEAN> getDoctorList(Pageable pageable){
		return doctorRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public DoctorInfoDTO getDoctorInfo(String did) {
		return entityToDTO.convertToDoctorInfoDTO(doctorRepository.findById(did).get());
//		return instance.getDoctorInfo(did);
	}

	@Override
	public long getTotalDoctors() {
		return instance.getTotalDoctors();
	}

	@Override
	public LoginBEAN getIdPassword(String did) {
		return instance.getIdPassword(did);
	}

}
