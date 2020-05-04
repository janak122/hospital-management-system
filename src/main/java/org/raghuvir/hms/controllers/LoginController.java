package org.raghuvir.hms.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.daos.HibernateTemplet;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.dtos.PlayGroundDTO;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.NewHibernateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
				return "redirect:/admin/list/patient/1";
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

//	@GetMapping("/playground")
//	public ModelAndView playGround() {
//		ModelAndView mv = new ModelAndView("dummy/playground");
//		mv.addObject("msg", "ram ram");
//		mv.addObject("order",new PlayGroundDTO());
//		return mv;
//	}
//
//	@PostMapping("/playground")
//	public String handlePlayGround(@Valid PlayGroundDTO play,Errors errors) {
//		System.out.println(play);
//		System.out.println(errors);
//		if(errors.hasErrors()) {			
//			return "dummy/playground";
//		}
//		return "dummy/success";
//	}
}
