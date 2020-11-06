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
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/member/update")
public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserModifyServlet() {
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
		user.setUserPhone(request.getParameter("phone")); 
		user.setUserAddr(request.getParameter("address")); 
		user.setUserEmail(request.getParameter("email")); 
		user.setUserABO(request.getParameter("abo"));
		int result = new UserService().updateUser(user);
		if(result > 0) {
			PrintWriter writer = response.getWriter(); 
			writer.println("<script>alert('정보가 수정되었습니다.'); "
			+"location.href='/WEB-INF/views/login/usermodify.jsp';</script>"); writer.close();
		}else {
			PrintWriter writer = response.getWriter(); 
			writer.println("<script>alert('항목을 전부 입력해주세요'); "
			+"location.href='/WEB-INF/views/login/usermodify.jsp';</script>"); writer.close();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
