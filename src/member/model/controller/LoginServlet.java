package member.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.UserService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=UTF-8");
			String userId = request.getParameter("userId");
			String userPwd= request.getParameter("userPwd");
			Member member = new UserService().selectOne(userId, userPwd);
			if(member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				PrintWriter writer = response.getWriter(); 
				writer.println("<script>alert('로그인을 성공하였습니다.'); "
				+"location.href='/index.jsp';</script>"); writer.close();
//				response.sendRedirect("/views/common/header.jsp");				
			}else {
				PrintWriter writer = response.getWriter(); 
				writer.println("<script>alert('아이디 or 비밀번호가 다릅니다.'); "
				+"location.href='/login/user';</script>"); writer.close();
//				response.sendRedirect("/userLogin.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
