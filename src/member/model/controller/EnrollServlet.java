package member.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.UserService;
import member.model.vo.Member;

/**
 * Servlet implementation class EnrollServlet
 */
@WebServlet("/member/enroll")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Member user = new Member();
		user.setUserId(request.getParameter("userId"));
		user.setUserPwd(request.getParameter("userPwd"));
		user.setUserName(request.getParameter("userName"));
		user.setUserBD(request.getParameter("BD"));
		user.setUserPhone(request.getParameter("phone"));
		user.setUserAddr(request.getParameter("addr"));
		user.setUserEmail(request.getParameter("email"));
		user.setUserGender(request.getParameter("gender"));
		user.setUserABO(request.getParameter("abo"));
		
		int result =new UserService().insertUser(user);
		
		if(result >0 ) { 
			response.sendRedirect("/index.jsp");
		}else {  
			PrintWriter writer = response.getWriter(); 
			writer.println("<script>alert('아이디가 중복됩니다!!'); "
			+"location.href='/WEB-INF/views/login/enroll.jsp';</script>"); writer.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
