package board.memBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.memBoard.model.service.MemberBoardService;


@WebServlet("/memboard/write")
public class MemberBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberBoardWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String subjet = request.getParameter("subject");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		//if (session != null && (session).getAttribute("member")!= null) {
//			String userId = ((Member)session.getAttribute("member")).getUserId();
			String userId = "admin";
			int result = new MemberBoardService().insertMemBoard(title, content, userId);
			if (result > 0 ) {
				response.sendRedirect("/memboard/list");
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/vews/memboard/error.jsp");
				view.include(request, response);
			}
//		}else {
//			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp");
		}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
