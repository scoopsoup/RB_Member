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
 * Servlet implementation class AutoPaperWeightServlet
 */
@WebServlet("/auto/paper/weight")
public class AutoPaperWeightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReservationPersonalService reservationService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoPaperWeightServlet() {
        super();
        if ( reservationService == null) {
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

//		SelfPaperWeight selfPaperWeight = new SelfPaperWeight();//주민번호를 담기위함 -> 문진내용은 계속해서 여기에 담아줄것
//		Member member = new Member(); // 이름을 담기위함
		String numberfront = request.getParameter("numberfront"); // 생년월일
		String numberback = request.getParameter("numberback"); // 뒷자리
		String number = numberfront + "-" + numberback;
//		selfPaperWeight.setCrNumber(numberfront + "-" + numberback); // 주민번호
//		member.setUserName(request.getParameter("name")); // 이름
		String name = request.getParameter("name");
		String onLine = request.getParameter("onLine");
		String reservation = request.getParameter("reservation");
		System.out.println(onLine);
		System.out.println(reservation);
		
		
		
		//문진을 하는데 주민번호랑 이런게 계속 넘기고 마지막에 예약할때 전부 DB에 넣어주려고요...
		//예약한다고 하면 저장해놨던걸 전부 insert해주려고...
		
		String disease = reservationService.autoDisease(name, number);
		//금지검사(검사) inspection
		String inspection = reservationService.autoInspection(name, number);
		//금지약물(약처방) drug
		String drug = reservationService.autoDrug(name, number);
		
		
//		int disease = reservationService.autoDisease(name, number);
//		//금지검사(검사) inspection
//		int inspection = reservationService.autoInspection(name, number);
//		//금지약물(약처방) drug
//		int drug = reservationService.autoDrug(name, number);
//		int result = new ReservationService().autoPaperInspection();
		
		int totalResult = reservationService.autoTotalResult(disease, inspection, drug);
		
		if(totalResult > 0) { // 자동문진에서 안됨
			//로그인 여부확인
			//이유는 로그인이 되어있을 때는 헌혈이 안될 경우 데이터를 저장해주기 위함.
			
			if(session.getAttribute("member") != null) { // 로그인이 되어있을 때
				String userId = ((Member)session.getAttribute("member")).getUserId();
				System.out.println(userId);
				int result = reservationService.autoPaperInput(userId);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
//				writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/auto/paper/member"+"';</script>");
//				writer.println("<script>alert('헌혈을 할 수 없습니다.');</script>");
//				writer.close();
				
				if(result > 0) {
					writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/index.jsp"+"';</script>");
					writer.close();
//					response.sendRedirect("/Info/agree");
				}
				else { // 서버오류
					writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/index.jsp"+"';</script>");
					writer.close();
//					request.getRequestDispatcher("/WEB-INFO/views/person/error.html");
				}
//				request.getRequestDispatcher("/auto/paper/member").forward(request, response);
				
			}
			else { // 로그인이 되어있지 않을때
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/index.jsp"+"';</script>");
				writer.close();
			}
			
			
			
			/*
			String userId = ((Member)session.getAttribute("member")).getUserId();
			System.out.println(userId);
			int result = reservationService.autoPaperInput(userId);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
//			writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/auto/paper/member"+"';</script>");
//			writer.println("<script>alert('헌혈을 할 수 없습니다.');</script>");
//			writer.close();
			
			if(result > 0) {
				writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/Info/agree"+"';</script>");
				writer.close();
//				response.sendRedirect("/Info/agree");
			}
			else { // 서버오류
				writer.println("<script>alert('헌혈을 할 수 없습니다.');location.href='"+"/WEB-INFO/views/person/error.html"+"';</script>");
				writer.close();
//				request.getRequestDispatcher("/WEB-INFO/views/person/error.html");
			}*/
			
		}
		else {
//			response.sendRedirect("/self/paper?disease=" + disease + ",");
			request.setAttribute("memberName", name);
			request.setAttribute("memberNum", number);
			request.setAttribute("onLine", onLine);
			request.setAttribute("reservation", reservation);
			request.getRequestDispatcher("/WEB-INF/views/reservation/personal/selfPaperWeight.jsp").forward(request, response);
		}
		
		//자동문진
		//금지질병(진료) disease
		
//		int disease = reservationService.autoDisease(member.getUserName(), selfPaperWeight.getCrNumber(), selfPaperWeight);
//		//금지검사(검사) inspection
//		int inspection = reservationService.autoInspection(member.getUserName(), selfPaperWeight.getCrNumber(), selfPaperWeight);
//		//금지약물(약처방) drug
//		int drug = reservationService.autoDrug(member.getUserName(), selfPaperWeight.getCrNumber(), selfPaperWeight);
////		int result = new ReservationService().autoPaperInspection();
//		
//		int totalResult = reservationService.totalResult(disease, inspection, drug, selfPaperWeight);
		
//		new ReservationDAO().syso();
		
//		if(disease != 0 || inspection != 0 || drug != 0) {
////			selfPaperWeight.setCrRes("N");
//			
//		}
//		else {
////			selfPaperWeight.setCrRes("Y");
//		}
//		response.sendRedirect("");
		
//		System.out.println(selfPaperWeight.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
