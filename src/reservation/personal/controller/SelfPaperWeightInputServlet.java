package reservation.personal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import reservation.personal.model.service.ReservationPersonalService;
import reservation.personal.model.vo.SelfPaperWeight;

/**
 * Servlet implementation class SelfPaperWeightInputServlet
 */
@WebServlet("/self/paper/weight/input")
public class SelfPaperWeightInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	ReservationPersonalService reservationService;
    public SelfPaperWeightInputServlet() {
        super();
        if(reservationService == null) {
        	reservationService = new ReservationPersonalService();
        }
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
//		ReservationService reservationService = new ReservationService();
		//여기에서 new로 생성을 해줘서
		//아예 새로운 객체가 생성되버리니까 그러는거같은데...
//		String name = request.getParameter("name");
//		String number = request.getParameter("number");
//		
//		SelfPaperWeight selfPaper1 = new SelfPaperWeight();
//		SelfPaperWeight selfPaper2 = new SelfPaperWeight();
//		SelfPaperWeight selfPaper3 = new SelfPaperWeight(); //디비에서 데이터 가져오기 이러면 등록할때 활용가능하지않나요 ?? 
		
		String name = request.getParameter("memberName");//이름 받아옴
		String number = request.getParameter("memberNum");//주민번호 받아옴
		String onLine = request.getParameter("onLine"); // 온라인여부 -> 헌혈의집인지 아닌지 판단
		String reservation = request.getParameter("reservation"); // 예약으로 들어온건지 자가문진으로 들어온건지 판단
		System.out.println(onLine);
		System.out.println(reservation);
		
		SelfPaperWeight selfPaper = new SelfPaperWeight();
		String body = request.getParameter("r1");
		String in = request.getParameter("r2");
		String out = request.getParameter("r3");
		String drg = request.getParameter("r4");
		String kor = request.getParameter("r5");
		String over = request.getParameter("r6");
		String family = request.getParameter("r7");
		selfPaper.setCrBody(body);
		selfPaper.setCrIn(in);
		selfPaper.setCrOut(out);
		selfPaper.setCrDrg(drg);
		selfPaper.setCrKor(kor);
		selfPaper.setCrOver(over);
		selfPaper.setCrFamily(family);
		
//		reservationService.selfPaperWeightResult(selfPaper);
		int result = reservationService.selfTotalResult(body, in, out, drg, kor, over, family);
		
		if(result > 0) { // 자가문진 헌혈안되는경우

			if(session.getAttribute("member") != null) { // 로그인이 되어있을 때
				String userId = ((Member)session.getAttribute("member")).getUserId();
				System.out.println(userId);
				System.out.println(name);
				int selfTotalResult = reservationService.selfResultInput(selfPaper, number, userId); // DB에 값을 저장하기위한 준비
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				
				if(selfTotalResult > 0) {
					writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/index.jsp"+"';</script>");
					writer.close();
				}
				else {
					writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/index.jsp"+"';</script>");
					writer.close();
				}
				
			}
			else {//로그인이 되어있지 않은경우
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/"+"';</script>");
				writer.close();
			}
			
			
		}
		else { // 자가문진 헌혈되는경우
			if(session.getAttribute("member") != null) { // 로그인이 되어있는 경우
				String userId = ((Member)session.getAttribute("member")).getUserId(); // 사용자의 Id가져오기
				if(onLine.equals("Y") && reservation.equals("Y")) { // 예약
					int selfTotalResult = reservationService.selfResultInput(selfPaper, number, userId);
					if(selfTotalResult > 0) {
						request.setAttribute("memberName", name);
						request.setAttribute("memberNum", number);
						request.setAttribute("onLine", onLine);
						request.setAttribute("reservation", reservation);
						request.getRequestDispatcher("/WEB-INF/views/reservation/personal/reservationInfo.jsp").forward(request, response);
					}
					else {
						response.sendRedirect("");
					}
				}
				else if(onLine.equals("N") && reservation.equals("N")) { // 자가, 헌혈의집
					int selfTotalResult = reservationService.selfResultInput(selfPaper, number, userId);
					int houseReservation = reservationService.houseReservation(userId, onLine);
					if(selfTotalResult > 0 && houseReservation > 0) {
						request.setAttribute("memberName", name);
						request.setAttribute("memberNum", number);
						request.setAttribute("onLine", onLine);
						request.setAttribute("reservation", reservation);
						
						request.getRequestDispatcher("/").forward(request, response);
					}
					else {
						response.sendRedirect("");
					}
				}
				else if(onLine.equals("Y") && reservation.equals("N")) { // 자가, 온라인
					request.getRequestDispatcher("/").forward(request, response);
				}
				
				
			}
			else { // 로그인이 되어있지 않은 경우
//				confirm창을 이용해서 회원 비회원 구분하기
//				예약서비스는 회원서비스 입니다. 로그인 후 예약서비스를 이용하시겠습니까?
				//위에꺼 없어도됨
				if(body.equals("Y") && in.equals("Y") && out.equals("Y") && drg.equals("Y") && kor.equals("Y") && over.equals("Y") && family.equals("Y")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('헌혈대상자입니다.');location.href='"+"/"+"';</script>");
					writer.close();
				}
				
			}
		}
		
//		String body = request.getParameter("r1");
//		String in = request.getParameter("r2");
//		String out = request.getParameter("r3");
//		String drg = request.getParameter("r4");
//		String kor = request.getParameter("r5");
//		String over = request.getParameter("r6");
//		String family = request.getParameter("r7");
		
//		HttpSession session = request.getSession();
//		if(session.getAttribute("member") != null) {
//			String userId = request.getParameter("userId");
//			SelfPaperWeight selfPaperWeight = new SelfPaperWeight();
//			selfPaperWeight.setCrBody(request.getParameter("r1"));
//			selfPaperWeight.setCrIn(request.getParameter("r2"));
//			selfPaperWeight.setCrOut(request.getParameter("r3"));
//			selfPaperWeight.setCrDrg(request.getParameter("r4"));
//			
//			int result = new ReservationService().UpdateSlefPaper(selfPaperWeight);
//			
//			if(result>0) {
//				response.sendRedirect("");//N을 체크했을 때 처리하기 -> DB에서 select해서 n이 있으면 count, 없으면 0
//			}
//			else {
//				request.getRequestDispatcher("/WEB-INF/views/personal/error.html").forward(request, response);
//			}
//		}
//		else {
//			request.getRequestDispatcher("/WEB-INF/views/personal/").forward(request, response);
		//}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
