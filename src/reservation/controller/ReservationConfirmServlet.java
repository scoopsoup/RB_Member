package reservation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import reservation.group.model.service.ReservationGroupService;
import reservation.group.model.vo.GroupReservation;
import reservation.model.vo.PageData;
import reservation.personal.model.service.ReservationPersonalService;
import reservation.personal.model.vo.PersonalReservation;

/**
 * Servlet implementation class ReservationConfirmServlet
 */
@WebServlet("/reservation/confirm")
public class ReservationConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationConfirmServlet() {
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
		int currentPerPage = 0;
		if(request.getParameter("currentPerPage") == null) {
			currentPerPage = 1;
		}
		else {
			currentPerPage = Integer.parseInt(request.getParameter("currentPerPage"));
		}
		
		int currentGroupPage = 0;
		if(request.getParameter("currentGroupPage") == null) {
			currentGroupPage = 1;
		}
		else {
			currentGroupPage = Integer.parseInt(request.getParameter("currentGroupPage"));
		}
		
		PageData personalPageData = new ReservationPersonalService().personalSelectList(currentPerPage, userId);
		ArrayList<PersonalReservation> pList = personalPageData.getPersonalPageList();
		PageData groupPageData = new ReservationGroupService().groupSelectList(currentGroupPage, userId);
		ArrayList<GroupReservation> gList = groupPageData.getGroupPageList();
		new ReservationPersonalService().totalPerCount(userId);
		new ReservationGroupService().totalGroupCount(userId);
		if(!pList.isEmpty() && !gList.isEmpty()) {
			request.setAttribute("pList", pList);
			request.setAttribute("personalPageDatanNavi", personalPageData.getPersonalPageNavi());
			request.setAttribute("gList", gList);
			request.setAttribute("groupPagaDataNavi", groupPageData.getGroupPageNavi());
			request.getRequestDispatcher("/WEB-INF/views/reservation/confirmReservation/reservationConfirm.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/").forward(request, response);
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
