package reservation.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.group.model.service.ReservationGroupService;

/**
 * Servlet implementation class GroupReservationInfoServlet
 */
@WebServlet("/group/reservation/info")
public class GroupReservationInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupReservationInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<String> disableDate = new ReservationGroupService().selectDisableDate();
		HttpSession session = request.getSession();
		String skipDate = "";
		for(int i = 0; i<disableDate.size();i++) {
			
			if(i==disableDate.size()-1) {
				skipDate += "\""+ disableDate.get(i) + "\"";
			}
			else {
				skipDate += "\""+ disableDate.get(i) + "\",";
			}
		}
		if(session.getAttribute("member") != null) { //로그인 되어있을 때
			request.setAttribute("skipDate", skipDate);
			request.getRequestDispatcher("/WEB-INF/views/reservation/group/groupReservationInfo.jsp").forward(request, response);
//			response.sendRedirect("");
		}
		else { // 로그인 되어있지 않을때
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('예약서비스는 회원 이용 서비스입니다.');location.href='"+"/group/info/agree"+"';</script>");
			writer.close();
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
