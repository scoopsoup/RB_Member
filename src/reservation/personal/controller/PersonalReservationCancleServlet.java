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

/**
 * Servlet implementation class PersonalReservationCancleServlet
 */
@WebServlet("/personal/reservation/cancle")
public class PersonalReservationCancleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalReservationCancleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String userId = ((Member)session.getAttribute("member")).getUserId();
		int reservationNo = Integer.parseInt(request.getParameter("reservaionNo"));
		ReservationPersonalService reservationService = new ReservationPersonalService();
		int result = reservationService.PersonalReservationCancle(reservationNo, userId);
		if(result > 0) {
			response.sendRedirect("/reservation/confirm");
		}
		else {
			response.sendRedirect("/index.jsp");
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
