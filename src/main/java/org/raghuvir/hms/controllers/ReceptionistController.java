package org.raghuvir.hms.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.hibernate.criterion.Restrictions;
import org.raghuvir.hms.beans.ChiefComplaintBEAN;
import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.HmsUserBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.beans.RoomEntryBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.daos.GeneralDAO;
import org.raghuvir.hms.daos.GeneralDAOImpl;
import org.raghuvir.hms.daos.ManageDoctorDAO;
import org.raghuvir.hms.daos.ManageDoctorDAOImpl;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.daos.ManagePatientDAOImpl;
import org.raghuvir.hms.daos.ManageRoomDAO;
import org.raghuvir.hms.daos.ManageRoomDAOImpl;
import org.raghuvir.hms.daos.ManageStaffDAO;
import org.raghuvir.hms.daos.ManageStaffDAOImpl;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.dtos.PaginationDTO;
import org.raghuvir.hms.dtos.RoomInfoDTO;
import org.raghuvir.hms.services.GeneralService;
import org.raghuvir.hms.services.ManageDoctorService;
import org.raghuvir.hms.services.ManagePatientService;
import org.raghuvir.hms.services.ManageRoomService;
import org.raghuvir.hms.services.ManageStaffService;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {
	@Autowired
	ServletContext context; 
	@Autowired
	ManagePatientService patientservice;
	@Autowired
	ManageRoomService roomservices;
	@Autowired
	ManageDoctorService doctorservices;
	@Autowired
	ManageStaffService staffservices;
	@Autowired
	GeneralService generalservices;

	/**
	 *
	 * @param entity
	 * @param pageno
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/list/{entity}/{page}")
	public ModelAndView getList(@PathVariable String entity, @PathVariable String page,
			@RequestParam(value = "q", required = false) String q) {
		printPath();
		int pageno = 1;
		if (!"query".equalsIgnoreCase(page)) {
			pageno = Integer.parseInt(page);
		}
		ModelAndView mv;
		Class clazz;
		List list = null;
		if (EntitiesConstants.DOCTOR.equalsIgnoreCase(entity)) {
			mv = new ModelAndView("receptionist/DoctorTable");
			if ("query".equalsIgnoreCase(page)) {
				list = doctorservices.searchDoctor(q);
			} else {
				mv.addObject("pages", PaginationDTO.getInstance(pageno, EntitiesConstants.DOCTOR));
				list = doctorservices.getDoctorList(pageno);
			}
		} else if (EntitiesConstants.STAFF.equalsIgnoreCase(entity)) {
			mv = new ModelAndView("receptionist/StaffTable");
			if ("query".equalsIgnoreCase(page)) {
				list = staffservices.searchStaff(q);
			} else {
				mv.addObject("pages", PaginationDTO.getInstance(pageno, EntitiesConstants.STAFF));
				list = staffservices.getStaffList(pageno);
			}
		} else if (EntitiesConstants.ROOM.equalsIgnoreCase(entity)) {
			mv = new ModelAndView("receptionist/RoomList");
			if ("query".equalsIgnoreCase(page)) {
				list = roomservices.searchRooms(q);
			} else {
				mv.addObject("pages", PaginationDTO.getInstance(pageno, EntitiesConstants.ROOM));
				list = roomservices.getRoomList(pageno);
			}
		} else {
			mv = new ModelAndView("receptionist/PatientTable");
			if ("query".equalsIgnoreCase(page)) {
				list = patientservice.serchPatient(q);
			} else {
				mv.addObject("pages", PaginationDTO.getInstance(pageno, EntitiesConstants.PATIENT));
				list = patientservice.getPatientList(pageno);
			}
		}
		mv.addObject("entitylist", list);
		return mv;
	}

	/**
	 *
	 * @param entity
	 * @param key
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/info/{entity}/{key}")
	public ModelAndView getInfo(@PathVariable String entity, @PathVariable String key) {
		ModelAndView mv;

		if ("room".equalsIgnoreCase(entity)) {
			mv = new ModelAndView("receptionist/RoomInfo");
			mv.addObject("room", roomservices.getRoomInfo(key));
		} else {
			mv = new ModelAndView("receptionist/ViewPatientInfo");
			mv.addObject("entity", patientservice.getPatientInfo(key));
			mv.addObject("doctors", patientservice.getDoctorIds());
		}
		return mv;
	}

	/**
	 *
	 * @param key
	 * @param entity
	 * @param entitykey
	 * @return ModelAndView
	 * @throws ParseException
	 */
	@RequestMapping(value = "/delete/{entity}/{key}")
	public String delete(@PathVariable String key, @PathVariable String entity,
			@RequestParam(value = "entitykey", required = false, defaultValue = "default") String entitykey)
			throws ParseException {
		if ("patient".equalsIgnoreCase(entity)) {
			generalservices.delete(generalservices.getEntity(PatientBEAN.class, key));
			return "redirect:/receptionist/list/patient/1";
		} else if ("patientcomplaint".equalsIgnoreCase(entity)) {
			generalservices.delete(generalservices.getEntity(ChiefComplaintBEAN.class, Long.parseLong(key)));
			return "redirect:/receptionist/info/patient/" + entitykey;
		}
		return "redirect:/receptionist/list/patient/1";
	}

	/**
	 *
	 * @param model
	 * @param key
	 * @return String
	 */
	@RequestMapping(value = "registerpatient/{key}", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model, @PathVariable String key) {
		HmsUserBEAN userForm = new HmsUserBEAN();
		if (key.equalsIgnoreCase("new")) {
			userForm.setUserId(IDGenerator.getInstance().getNextId("P"));
		} else {
			PatientBEAN entity = (PatientBEAN) generalservices.getEntity(PatientBEAN.class, key);
			userForm.copy(entity);
		}
		model.put("userForm", userForm);
		model.put("imgpath",context.getRealPath("")
				+EntitiesConstants.UPLOAD_DIRECTORY
				+File.separator+EntitiesConstants.DEFAULT_IMAGE);
		return "receptionist/AddUpdatePatientInfo";
	}

	/**
	 *
	 * @param model
	 * @return ModelAndView
	 * @throws ParseException
	 * 
	 */
	@RequestMapping(value = "registerpatient", method = RequestMethod.POST)
	public String processRegistration(@RequestParam Map<String, Object> model,
			@RequestParam(value = "file", required = false) MultipartFile file) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String fnm=generalservices.uploadFile(context.getRealPath("")
				+File.separator+EntitiesConstants.UPLOAD_DIRECTORY
				,(String) model.get("userId"), file);
		PatientBEAN patient = new PatientBEAN(new HashSet<>(), new LinkedList<>(), (String) model.get("userId"),
				(String) model.get("name"),fnm ,
				(String) model.get("phoneno"), EntitiesConstants.PATIENT, (String) model.get("address"),
				(String) model.get("gender"), (String) model.get("emailaddress"),
				format.parse((String) model.get("birthdate")));
		generalservices.insertUpdate(patient);

		return "redirect:/receptionist/list/patient/1";
	}

	@RequestMapping(value = "chiefcomplaint", method = RequestMethod.POST)
	public String addChiefComplaint(@RequestParam Map<String, Object> model) {

		patientservice.addChiefComplaint(new ChiefComplaintDTO(IDGenerator.getInstance().getNextId(),
				(String) model.get("pid"), (String) model.get("did"), new Date(), (String) model.get("complaint"),
				(String) model.get("description"), "", EntitiesConstants.PENDING));

		return "redirect:/receptionist/info/patient/" + (String) model.get("pid");
	}

	@RequestMapping(value = "allocateRoom")
	public String allocateRoom(@RequestParam Map<String, Object> model) {
		patientservice.addRoomEntry((String) model.get("pid"), (String) model.get("roomno"));

		return "redirect:/receptionist/info/patient/" + (String) model.get("pid");
	}

	@RequestMapping(value = "deallocateRoom")
	public String deallocateRoom(@RequestParam(value = "entryid") long entryid,
			@RequestParam(value = "pid") String pid) {
		generalservices.delete(generalservices.getEntity(RoomEntryBEAN.class, entryid));

		return "redirect:/receptionist/info/patient/" + pid;
	}

	void printPath() {
//		System.out.println(context.getContextPath());
		System.out.println(context.getRealPath("/"));//very dangerous
//		System.out.println(System.getProperty("catalina.home"));
//		File uploadDir = new File("/resources/images");
//		if(!uploadDir.exists()) {
//			uploadDir.mkdirs();
//		}
		
//		try {
//			uploadFile.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
