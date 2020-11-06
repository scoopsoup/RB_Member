package reservation.personal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import reservation.personal.model.service.ReservationPersonalService;
import reservation.personal.model.vo.PersonalReservation;

/**
 * Servlet implementation class ReservationInfoServlet
 */
@WebServlet("/reservation/info")
public class ReservationInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String name = request.getParameter("memberName");
		String number = request.getParameter("memberNum");
		String onLine = request.getParameter("onLine");
		String userId = ((Member)session.getAttribute("member")).getUserId();
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String place = request.getParameter("place");
		String bloodtype = request.getParameter("bloodtype");

		PersonalReservation personalReservation = new PersonalReservation();
		personalReservation.setRpOn(onLine);
		personalReservation.setRpDate(date);
		personalReservation.setRpPlace(place);
		personalReservation.setRpTime(time);
		personalReservation.setRpKind(bloodtype);
		personalReservation.setUserId(userId);
		
		ReservationPersonalService reservationService = new ReservationPersonalService();
		int result = reservationService.reservationInput(personalReservation);
		
		if(result > 0) {
			request.setAttribute("memberName", name);
			request.setAttribute("memberNum", number);
			request.getRequestDispatcher("/reservation/confirm").forward(request, response);;
//			response.sendRedirect("/reservation/list");
		}
		else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
