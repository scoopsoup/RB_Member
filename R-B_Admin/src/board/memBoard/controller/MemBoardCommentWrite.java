package board.memBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.memBoard.model.service.MemberBoardService;

@WebServlet("/memboard/insertComment")
public class MemBoardCommentWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemBoardCommentWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String commentContent = request.getParameter("boardCommentContent");
		int mbNo =Integer.parseInt(request.getParameter("noticeNo"));
		//String userId = request.getParameter("userId");

		//HttpSession session = request.getSession();
		//LoginVo loginVo = (LoginVO)session.getAttribute("loginVo");
		System.out.println(commentContent);

		String userId = "admin";
		int result = new MemberBoardService().insertMemBoardComment(mbNo,commentContent, userId);

		if (result > 0) {
			response.sendRedirect("/memboard/select?mbNo="+mbNo);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
