package reservation.personal.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginConfirmServlet
 */
@WebServlet("/login/confirm")
public class LoginConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String onLine = "N";
		String reservation = "N";
		if(session.getAttribute("member") != null) { // 로그인되있음
			request.setAttribute("onLine", onLine);
			request.setAttribute("reservation", reservation);
			request.getRequestDispatcher("/WEB-INF/views/reservation/personal/basicInfo.jsp").forward(request, response);
		}
		else { // 로그인 안됨
			request.setAttribute("onLine", onLine);
			//바꿔주기
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
