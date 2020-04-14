package org.raghuvir.hms.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.raghuvir.hms.beans.*;
import org.raghuvir.hms.daos.*;
import org.raghuvir.hms.dtos.*;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.IDGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @RequestMapping(value = "/patientlist/{pageno}")
    public ModelAndView getPatientListForDoctorHomePage(@PathVariable int pageno,
            HttpServletRequest request, HttpServletResponse response) {
        if (pageno <= 0) {
            pageno = 1;
        }
        ModelAndView mv = null;
        Set<HmsUserBEAN> patients;
        HttpSession session = request.getSession(true);
        String did = (String) session.getAttribute("userid");

        mv = new ModelAndView("doctor/PatientTable");
//        mv.addObject("pages", PaginationDTO.getInstance(pageno));
        patients = ManagePatientDAOImpl.getInstance().getPatientListForDoctor(did);
        mv.addObject("complaints", patients);
        return mv;
    }
    
    @RequestMapping(value = "/patientinfo/{entity}/{key}")
    public ModelAndView getPatientInfoForDoctor(@PathVariable String entity, @PathVariable String key) {
        ModelAndView mv;
        
        mv = new ModelAndView("doctor/PatientCaseReport");
        mv.addObject("entity", ManagePatientDAOImpl.getInstance().getPatientInfo(key));
        
        return mv;
    }
    
    @RequestMapping(value = "writePrescription", method = RequestMethod.POST)
    public String writePrescriptionOfPatient(@RequestParam Map<String, Object> model) throws ParseException {
        String caseid=(String)model.get("caseid");
        ManagePatientDAOImpl.getInstance().addPrescription(Long.parseLong(caseid),(String)model.get("prescription"));
        
        return "redirect:/doctor/patientinfo/patient/"+(String)model.get("pid");
    }
    
    @RequestMapping(value = "profile/{key}", method = RequestMethod.GET)
    public String getDoctorProfile(Map<String, Object> model, @PathVariable String key) {
       
        DoctorInfoDTO userForm=ManageDoctorDAOImpl.getInstance().getDoctorInfo(key);       
        model.put("userForm",userForm);
        return "doctor/DoctorProfile";
    }
    
    @RequestMapping(value = "updateprofile", method = RequestMethod.POST)
    public String updateDoctorProfile(@RequestParam Map<String, Object> model) throws ParseException {
        
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm a");
        model.forEach((k, v) -> System.out.println(k + "===" + v));
        
        String degrees=(String)model.get("degrees"),
                typeofdoctor=(String)model.get("typeofdoctor"),
                userId=(String) model.get("userId"),
                name=(String) model.get("name"),
                job=EntitiesConstants.DOCTOR,
                imgurl=(String) model.get("img"),
                usertype=EntitiesConstants.DOCTOR,
                address=(String) model.get("address"),
                gender=(String) model.get("gender"),
                emailaddress=(String) model.get("emailaddress"),
                phoneno=(String)model.get("phoneno");
             
        int salary=Integer.parseInt((String)model.get("salary"));        
                
        Set<ChiefComplaintBEAN> chiefcomplaint =new HashSet<>();
        
        Date dateofjoin=dateformat.parse((String) model.get("dateofjoin")),
             arrivaltime=timeformat.parse((String) model.get("arrivaltime")),
             departtime=timeformat.parse((String) model.get("departtime")),  
             birthdate=dateformat.parse((String) model.get("birthdate"));   
                     
        DoctorBEAN doctor;
        doctor = new DoctorBEAN(degrees,typeofdoctor,chiefcomplaint,salary, 
                        dateofjoin,arrivaltime,departtime,job,userId,name,imgurl,
                        phoneno,usertype,address,gender,emailaddress,birthdate);
        
        GeneralDAOImpl.getInstance().insertUpdate(doctor);
        
        return "redirect:/doctor/profile/"+userId;
    }
}   