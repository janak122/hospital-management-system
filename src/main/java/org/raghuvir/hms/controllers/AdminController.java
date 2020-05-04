package org.raghuvir.hms.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.raghuvir.hms.beans.ChiefComplaintBEAN;
import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.HmsUserBEAN;
import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.daos.GeneralDAO;
import org.raghuvir.hms.daos.GeneralDAOImpl;
import org.raghuvir.hms.daos.ManageDoctorDAO;
import org.raghuvir.hms.daos.ManageDoctorDAOImpl;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.daos.ManagePatientDAOImpl;
import org.raghuvir.hms.daos.ManageRoomDAO;
import org.raghuvir.hms.daos.ManageStaffDAO;
import org.raghuvir.hms.daos.ManageStaffDAOImpl;
import org.raghuvir.hms.dtos.DoctorInfoDTO;
import org.raghuvir.hms.dtos.StaffInfoDTO;
import org.raghuvir.hms.services.GeneralService;
import org.raghuvir.hms.services.ManageDoctorService;
import org.raghuvir.hms.services.ManagePatientService;
import org.raghuvir.hms.services.ManageRoomService;
import org.raghuvir.hms.services.ManageStaffService;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
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
	final int PAGE_SIZE = 5;
	final int PATIENT_PAGE_SIZE = 30;
	final int MAX_PAGEBAR = 10;

	@RequestMapping(value = "/list/{entity}/{page}")
	public ModelAndView getList(@PathVariable String entity, @PathVariable String page,
			@RequestParam(value = "q", required = false) String q) {
		int pageno = 1;
		if (!"query".equalsIgnoreCase(page)) {
			pageno = Integer.parseInt(page);
		}
		ModelAndView mv;
		Class clazz;
		List list = null;
		int totalpages = 1;
		List<Integer> pageNumbers = null;
		if (EntitiesConstants.DOCTOR.equalsIgnoreCase(entity)) {
			mv = new ModelAndView("admin/DoctorTable");
			if ("query".equalsIgnoreCase(page)) {
				list = doctorservices.searchDoctor(q);
			} else {
				Page<DoctorBEAN> doctors = doctorservices.getDoctorList(PageRequest.of(pageno - 1, PAGE_SIZE));
				list = doctors.getContent();
				totalpages = doctors.getTotalPages();
				pageNumbers = IntStream.rangeClosed(1, totalpages).boxed().collect(Collectors.toList());

			}
		} else if (EntitiesConstants.STAFF.equalsIgnoreCase(entity)) {
			mv = new ModelAndView("admin/StaffTable");
			if ("query".equalsIgnoreCase(page)) {
				list = staffservices.searchStaff(q);
			} else {
				Page<StaffBEAN> staffs = staffservices.getStaffList(PageRequest.of(pageno - 1, PAGE_SIZE));
				list = staffs.getContent();
				totalpages = staffs.getTotalPages();
				pageNumbers = IntStream.rangeClosed(1, totalpages).boxed().collect(Collectors.toList());

			}
		} else {
			mv = new ModelAndView("admin/PatientTable");
			if ("query".equalsIgnoreCase(page)) {
				list = patientservice.serchPatient(q, PageRequest.of(pageno - 1, PATIENT_PAGE_SIZE));
			} else {
				Page<PatientBEAN> patients = patientservice
						.getPatientList(PageRequest.of(pageno - 1, PATIENT_PAGE_SIZE));
				list = patients.getContent();
				totalpages = patients.getTotalPages();
				pageNumbers = generalservices.getListForPageBar(pageno, totalpages, MAX_PAGEBAR);
			}
		}
		mv.addObject("entitylist", list);
		mv.addObject("pages", pageNumbers);
		mv.addObject("currentPage", pageno);
		mv.addObject("totalpages", totalpages);
		return mv;
	}

	@RequestMapping(value = "/info/{entity}/{key}")
	public ModelAndView getInfo(@PathVariable String entity, @PathVariable String key) {
		ModelAndView mv;
		if (EntitiesConstants.DOCTOR.equalsIgnoreCase(entity)) {
			mv = new ModelAndView("admin/ViewDoctorInfo");
			mv.addObject("entity", doctorservices.getDoctorInfo(key));
		} else if (EntitiesConstants.STAFF.equalsIgnoreCase(entity)) {
			mv = new ModelAndView("admin/ViewStaffInfo");
			StaffInfoDTO staff=staffservices.getStaffInfo(key);
			staff.getUser().setImgurl(generalservices.getValidFilename(staff.getUser().getImgurl()));
			mv.addObject("entity",staff);
		} else {
			mv = new ModelAndView("admin/ViewPatientInfo");
			mv.addObject("entity", patientservice.getPatientInfo(key));
		}
		System.out.println(mv);
		return mv;
	}

	@RequestMapping(value = "/register/{entity}/{key}", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model, @PathVariable String entity, @PathVariable String key) {
		if (EntitiesConstants.DOCTOR.equalsIgnoreCase(entity)) {
			DoctorInfoDTO userForm=new DoctorInfoDTO();
			if (key.equalsIgnoreCase("new")) {
				userForm.setUser(new HmsUserBEAN(IDGenerator.getInstance().getNextId("D")));
			} else {
				userForm=doctorservices.getDoctorInfo(key);
				model.put("login", doctorservices.getIdPassword(key));
			}
			model.put("userForm",userForm);
			return "admin/AddUpdateDoctorInfo";
		} else {
			StaffInfoDTO userForm=new StaffInfoDTO();
			if (key.equalsIgnoreCase("new")) {
				userForm.setUser(new HmsUserBEAN(IDGenerator.getInstance().getNextId("S")));
			} else {
				userForm=staffservices.getStaffInfo(key);
				model.put("login", staffservices.getIdPassword(key));
			}
			model.put("userForm",userForm);
			return "admin/AddUpdateStaffInfo";
		}
	}

	/**
	 *
	 * @param model
	 * @return ModelAndView
	 * @throws ParseException
	 * 
	 */
	@RequestMapping(value = "/process/{entity}", method = RequestMethod.POST)
	public String processRegistration(@RequestParam Map<String, Object> model,
			@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable String entity)
			throws ParseException {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm a");

		String degrees = (String) model.get("degrees"), typeofdoctor = (String) model.get("typeofdoctor"),
				userId = (String) model.get("userId"), name = (String) model.get("name"),
				job = (String) model.get("job"), address = (String) model.get("address"),
				gender = (String) model.get("gender"), emailaddress = (String) model.get("emailaddress"),
				phoneno = (String) model.get("phoneno"), password = (String) model.get("password");
		String imgurl = generalservices.uploadFile(userId, file);
		int salary = Integer.parseInt((String) model.get("salary"));

		Date dateofjoin = dateformat.parse((String) model.get("dateofjoin")),
				arrivaltime = timeformat.parse((String) model.get("arrivaltime")),
				departtime = timeformat.parse((String) model.get("departtime")),
				birthdate = dateformat.parse((String) model.get("birthdate"));

		if (EntitiesConstants.DOCTOR.equalsIgnoreCase(entity)) {
			Set<ChiefComplaintBEAN> chiefcomplaint = new HashSet<>();
			String usertype = EntitiesConstants.DOCTOR;
			DoctorBEAN doc = new DoctorBEAN(degrees, typeofdoctor, chiefcomplaint, salary, dateofjoin, arrivaltime,
					departtime, job, userId, name, imgurl, phoneno, usertype, address, gender, emailaddress, birthdate);

			generalservices.insertUpdate(doc);
			generalservices.insertUpdate(new LoginBEAN(doc, password));

			return "redirect:/admin/info/doctor/" + userId;
		} else {
			StaffBEAN staff=new StaffBEAN(salary, dateofjoin, arrivaltime,
					departtime, job, userId, name, imgurl, phoneno, 
					EntitiesConstants.STAFF, address, gender, emailaddress, birthdate);
			generalservices.insertUpdate(staff);
			if(EntitiesConstants.RECEPTIONIST.equalsIgnoreCase(job)) {
				generalservices.insertUpdate(new LoginBEAN(staff, password));
			}
			
			return "redirect:/admin/info/staff/" + userId;
		}
	}

	@RequestMapping(value = "/delete/{entity}/{key}", method = RequestMethod.GET)
	public String delete(@PathVariable String key, @PathVariable String entity,
			@RequestParam(value = "entitykey", required = false, defaultValue = "default") String entitykey)
			throws ParseException {
		if ("doctor".equalsIgnoreCase(entity)) {
			generalservices.delete(generalservices.getEntity(LoginBEAN.class, key));
			generalservices.delete(generalservices.getEntity(DoctorBEAN.class, key));
			return "redirect:/admin/list/doctor/1";
		} else if ("staff".equalsIgnoreCase(entity)) {
			LoginBEAN login = (LoginBEAN) generalservices.getEntity(LoginBEAN.class, key);
			if (login != null) {
				generalservices.delete(login);
			}
			generalservices.delete(generalservices.getEntity(StaffBEAN.class, key));
			return "redirect:/admin/list/staff/1";
		}
		return "redirect:/admin/doctorlist";
	}

	// Admin's Profile
	@RequestMapping(value = "/profile/{key}", method = RequestMethod.GET)
	public ModelAndView getAdminProfile(@PathVariable String key) {
		ModelAndView mv;
		mv = new ModelAndView("admin/AdminProfile");
		mv.addObject("userForm", ManageStaffDAOImpl.getInstance().getStaffInfo(key));
		return mv;
	}

	// Insert or Update Doctor's / Staff's Information
	@RequestMapping(value = "/{updateentity}", method = RequestMethod.POST)
	public String addUpdateStaffProfile(@PathVariable String updateentity, @RequestParam Map<String, Object> model)
			throws ParseException {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm a");
//		model.forEach((k, v) -> System.out.println(k + "===" + v));

		String degrees = (String) model.get("degrees"), typeofdoctor = (String) model.get("typeofdoctor"),
				userId = (String) model.get("userId"), name = (String) model.get("name"),
				job = (String) model.get("job"), imgurl = (String) model.get("img"),
				address = (String) model.get("address"), gender = (String) model.get("gender"),
				emailaddress = (String) model.get("emailaddress"), phoneno = (String) model.get("phoneno"),
				password = (String) model.get("password");

		int salary = Integer.parseInt((String) model.get("salary"));

		Date dateofjoin = dateformat.parse((String) model.get("dateofjoin")),
				arrivaltime = timeformat.parse((String) model.get("arrivaltime")),
				departtime = timeformat.parse((String) model.get("departtime")),
				birthdate = dateformat.parse((String) model.get("birthdate"));

		DoctorBEAN doctor = null;
		StaffBEAN staff = null;
		LoginBEAN login = null;

		if ("updatedoctor".equalsIgnoreCase(updateentity)) {
			Set<ChiefComplaintBEAN> chiefcomplaint = new HashSet<>();
			String usertype = EntitiesConstants.DOCTOR;
			doctor = new DoctorBEAN(degrees, typeofdoctor, chiefcomplaint, salary, dateofjoin, arrivaltime, departtime,
					job, userId, name, imgurl, phoneno, usertype, address, gender, emailaddress, birthdate);

			login = new LoginBEAN(doctor, password);

			GeneralDAOImpl.getInstance().insertUpdate(doctor);
			GeneralDAOImpl.getInstance().insertUpdate(login);

			return "redirect:/admin/doctor/viewdoctorinfo/" + userId;

		} else if ("updatestaff".equalsIgnoreCase(updateentity)) {

			String usertype = EntitiesConstants.STAFF;
			String redirect = "/staff/viewstaffinfo/";
			boolean flag = false;
			if (EntitiesConstants.ADMIN.equalsIgnoreCase(job)) {
				flag = true;
				usertype = EntitiesConstants.ADMIN;
				redirect = "/profile/" + userId;
			}
			System.out.println("redirect : " + redirect);
			staff = new StaffBEAN(salary, dateofjoin, arrivaltime, departtime, job, userId, name, imgurl, phoneno,
					usertype, address, gender, emailaddress, birthdate);

			GeneralDAOImpl.getInstance().insertUpdate(staff);

			if (EntitiesConstants.RECEPTIONIST.equalsIgnoreCase(job) || flag) {
				login = new LoginBEAN(staff, password);
				GeneralDAOImpl.getInstance().insertUpdate(login);
			}

			return "redirect:/admin" + redirect + userId;
		}

		return null;
	}

}
