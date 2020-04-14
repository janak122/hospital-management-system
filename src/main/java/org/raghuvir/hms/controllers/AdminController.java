package org.raghuvir.hms.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.raghuvir.hms.beans.ChiefComplaintBEAN;
import org.raghuvir.hms.beans.DoctorBEAN;
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
import org.raghuvir.hms.dtos.PaginationDTO;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.IDGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "/delete/{entity}/{key}")
	public String delete(@PathVariable String key, @PathVariable String entity,
			@RequestParam(value = "entitykey", required = false, defaultValue = "default") String entitykey)
			throws ParseException {
		GeneralDAO instance = GeneralDAOImpl.getInstance();
		if("doctor".equalsIgnoreCase(entity)){
            instance.delete(instance.getEntity(LoginBEAN.class,key));
            instance.delete(instance.getEntity(DoctorBEAN.class,key));
            return "redirect:/admin/doctorlist";
        }
        else if("staff".equalsIgnoreCase(entity)){
            LoginBEAN login=(LoginBEAN)instance.getEntity(LoginBEAN.class,key);
            if(login!=null){
                instance.delete(login);
            }
            instance.delete(instance.getEntity(StaffBEAN.class,key));
            return "redirect:/admin/stafflist";
        }
		return "redirect:/admin/doctorlist";
	}

	// Patient List
	@RequestMapping(value = "/patientlist/{pageno}")
	public ModelAndView getPatientListForAdmin(@PathVariable int pageno) {
		if (pageno <= 0) {
			pageno = 1;
		}
		ModelAndView mv;
		List list;
		mv = new ModelAndView("admin/PatientTable");
		mv.addObject("pages", PaginationDTO.getInstance(pageno,EntitiesConstants.PATIENT));
		list = ManagePatientDAOImpl.getInstance().getPatientList(pageno);
		mv.addObject("entitylist", list);
		return mv;
	}

	// Patient's Information
	@RequestMapping(value = "/patientinfo/{entity}/{key}", method = RequestMethod.GET)
	public ModelAndView getPatientInfoForAdmin(@PathVariable String entity, @PathVariable String key) {
		ModelAndView mv;

		mv = new ModelAndView("admin/ViewPatientInfo");
		mv.addObject("entity", ManagePatientDAOImpl.getInstance().getPatientInfo(key));

		return mv;
	}

	// Doctor List
	@RequestMapping(value = "/doctorlist")
	public ModelAndView getDoctorListForAdmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv;
		List list;
		mv = new ModelAndView("admin/DoctorTable");
		mv.addObject("entitylist", ManageDoctorDAOImpl.getInstance().getDoctorList(1));
		return mv;
	}

	// Doctor's Information
	@RequestMapping(value = "/doctor/{doctorinfo}/{key}", method = RequestMethod.GET)
	public ModelAndView getDoctorInfoForAdmin(@PathVariable String doctorinfo, @PathVariable String key) {
		ModelAndView mv = null;
		if ("viewdoctorinfo".equals(doctorinfo)) {
			mv = new ModelAndView("admin/ViewDoctorInfo");
			mv.addObject("userForm", ManageDoctorDAOImpl.getInstance().getDoctorInfo(key));
		} else if ("addupdatedoctor".equals(doctorinfo)) {
			mv = new ModelAndView("admin/AddUpdateDoctorInfo");
			mv.addObject("userForm", ManageDoctorDAOImpl.getInstance().getDoctorInfo(key));

			if ("new".equals(key)) {
				// System.out.println("Inside new of update "+mv.getView());
				mv.addObject("title", "Register New Doctor");
				mv.addObject("backpath", "/admin/doctorlist");
				
				mv.addObject("headername", "New Registration");
			} else {
				mv.addObject("title", "Update Doctor's Information");
				mv.addObject("backpath", "/admin/doctor/viewdoctorinfo/" + key);
				mv.addObject("headername", "Update Doctor's Information");
			}
			mv.addObject("login", ManageDoctorDAOImpl.getInstance().getIdPassword(key));
		}
		System.out.println(mv);
		return mv;
	}

	// Staff List
	@RequestMapping(value = "/stafflist")
	public ModelAndView getStaffListForAdmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv;
		List list;
		mv = new ModelAndView("admin/StaffTable");
		mv.addObject("entitylist", ManageStaffDAOImpl.getInstance().getStaffList(1));
		return mv;
	}

	// Staff's Information
	@RequestMapping(value = "/staff/{staffinfo}/{key}", method = RequestMethod.GET)
	public ModelAndView getStaffInfoForAdmin(@PathVariable String staffinfo, @PathVariable String key) {
		ModelAndView mv = null;
		if ("viewstaffinfo".equals(staffinfo)) {
			mv = new ModelAndView("admin/ViewStaffInfo");
			mv.addObject("userForm", ManageStaffDAOImpl.getInstance().getStaffInfo(key));
		} else if ("addupdatestaff".equals(staffinfo)) {
			mv = new ModelAndView("admin/AddUpdateStaffInfo");
			mv.addObject("userForm", ManageStaffDAOImpl.getInstance().getStaffInfo(key));

			if ("new".equals(key)) {
				// System.out.println("Inside new of update "+mv.getView());
				mv.addObject("title", "Register New Staff");
				mv.addObject("backpath", "/admin/stafflist");
				mv.addObject("headername", "New Registration");
			} else {
				mv.addObject("title", "Staff's Information");
				mv.addObject("backpath", "/admin/staff/viewstaffinfo/" + key);
				mv.addObject("headername", "Update Staff's Information");
			}
			mv.addObject("login", ManageStaffDAOImpl.getInstance().getIdPassword(key));
		} else if ("addupdateadmin".equals(staffinfo)) {
			mv = new ModelAndView("admin/AddUpdateStaffInfo");
			mv.addObject("userForm", ManageStaffDAOImpl.getInstance().getStaffInfo(key));
			mv.addObject("title", "My Information");
			mv.addObject("backpath", "/admin/profile/" + key);
			mv.addObject("headername", "Update Profile");
			mv.addObject("login", ManageStaffDAOImpl.getInstance().getIdPassword(key));
		}
		System.out.println(mv);
		return mv;
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
		model.forEach((k, v) -> System.out.println(k + "===" + v));

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
