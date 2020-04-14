package org.raghuvir.hms.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.daos.HibernateTemplet;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.dtos.PaginationDTO;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.NewHibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	/**
	 *
	 * @param id
	 * @param password
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/loginProcess")
	public String handleLogin(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password, HttpServletRequest request,
			HttpServletResponse response) {

		SessionFactory factory = NewHibernateUtil.getSessionFactory();
		LoginBEAN usr;
		usr = (LoginBEAN) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			return session.get(LoginBEAN.class, id);
		});

		if (usr != null && usr.getPassword().equals(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", id);
			session.setAttribute("password", password);
			if (id.startsWith(EntitiesConstants.STAFF_PREFIX)) {
				return "redirect:/receptionist/list/patient/1";
			} else if (id.startsWith(EntitiesConstants.DOCTOR_PREFIX)) {
				return "redirect:/doctor/patientlist/1";
			} else if (id.startsWith(EntitiesConstants.ADMIN_PREFIX)) {
				return "redirect:/admin/patientlist/1";
			}
		}
		return "forward:/login.jsp";
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "forward:/login.jsp";
	}
}
