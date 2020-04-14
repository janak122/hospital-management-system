package org.raghuvir.hms.controllers;

import java.text.ParseException;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.raghuvir.hms.beans.ChiefComplaintBEAN;
import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.daos.GeneralDAO;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.daos.ManageRoomDAO;
import org.raghuvir.hms.dtos.PaginationDTO;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/general")
public class GeneralController {


}
