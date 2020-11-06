package reservation.personal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import reservation.personal.model.service.ReservationPersonalService;


/**
 * Servlet implementation class BasicInfoServlet
 */
@WebServlet("/basic/info")
public class BasicInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String onLine = request.getParameter("onLine");
		String reservation = request.getParameter("reservation");
		System.out.println(onLine);
		System.out.println(reservation);
		if(onLine.equals("N")) { // 자가문진으로 들어온 경우
			// 헌혈의집인지 아닌지 질문
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>var answer=confirm ('헌혈의 집에서 문진서비스를 이용하고 계신가요?');");
			// 헌혈의집(오프라인)이면(true) 로그인여부확인
			// 헌혈의집이 아니면(온라인)이면(false) onLine값을 Y로 바꿔준 후 쭉 진행
			writer.println("if(answer==true){location.href='"+"/login/confirm"+"';}else {location.href='"+"/online/confirm"+"';}</script>");
			writer.close();
		}
		else { // 예약하기로 들어온 경우
			//로그인여부 확인
			HttpSession session = request.getSession();
			//로그인이 되어있을 때
			if(session.getAttribute("member") != null) {
				String userId = ((Member)session.getAttribute("member")).getUserId();
				//이전의 예약정보가 있는지 확인
				int result = new ReservationPersonalService().reservationInquiry(userId);
				if(result > 0) { // 이전 예약정보가 있을 경우
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('이전예약정보가 있습니다.');location.href='"+"/reservation/confirm"+"';</script>");
					writer.close();
				}
				else {
					//개인정보입력으로 넘어감
					request.setAttribute("onLine", onLine);
					request.setAttribute("reservation", reservation);
					request.getRequestDispatcher("/WEB-INF/views/reservation/personal/basicInfo.jsp").forward(request, response);
				}
				
			}
			else{//로그인이 되어있지 않을때
				//로그인을 해야 이용할 수 있다고 alert창을 띄워줌
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('예약서비스는 로그인 후 이용할 수 있습니다..');location.href='"+"/reservation/info/agree"+"';</script>");
				writer.close();
				
			}
			
			
		}
		
		
//		HttpSession session = request.getSession();
//		String userId = ((Member)session.getAttribute("member")).getUserId();
//		System.out.println(userId);
//				
//		SelfPaperWeight selfPaperWeight = new SelfPaperWeight();
//		selfPaperWeight.setCrNumber(request.getParameter("number"));
//		selfPaperWeight.setUserId(userId);
//		
//		int result = new ReservationService().insertMemberNumber(selfPaperWeight);
//		if(result > 0) {
////			request.getRequestDispatcher("/WEB-INF/views/personal/selfPaperWeight.html").forward(request, response);
//			response.sendRedirect("/self/paper");
//		}
//		else {
//			request.getRequestDispatcher("/WEB-INF/views/personal/error.html").forward(request, response);
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
