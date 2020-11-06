package reservation.group.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservation.group.model.dao.ReservationGroupDAO;
import reservation.group.model.service.ReservationGroupService;

/**
 * Servlet implementation class GroupReservationCancleServlet
 */
@WebServlet("/group/reservation/cancle")
public class GroupReservationCancleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupReservationCancleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int groupReservationNo = Integer.parseInt(request.getParameter("groupReservationNo"));
		int result = new ReservationGroupService().groupReservationCancle(groupReservationNo);
		
		if(result > 0) {
			request.getRequestDispatcher("/reservation/confirm").forward(request, response);
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
