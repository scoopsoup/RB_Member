package reservation.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import reservation.group.model.service.ReservationGroupService;

/**
 * Servlet implementation class GroupReservationInfoInputServlet
 */
@WebServlet("/group/reservation/info/input")
public class GroupReservationInfoInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupReservationInfoInputServlet() {
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
		String date = request.getParameter("date");
		String afterTime = request.getParameter("aftertime");
		String beforeTime = request.getParameter("beforetime");
		String time = beforeTime + ":00" + "-" + afterTime + ":00"; 
		String postCode = request.getParameter("postCode");
		String roadAddr = request.getParameter("roadAddr");
		String detailAddr = request.getParameter("detailAddr");
		String place = roadAddr + "/" + detailAddr;
		String people = request.getParameter("people");
		
		int result = new ReservationGroupService().inputReservation(userId, date, time, place, people);
		
		if(result > 0) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/").forward(request, response);
		}
//		response.sendRedirect("");
//		request.getRequestDispatcher("/WEB-INF/views/reservation/group/groupReservationInfo.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
