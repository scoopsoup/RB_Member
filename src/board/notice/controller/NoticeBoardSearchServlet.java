package board.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;
import board.notice.model.vo.Notice;
import board.notice.model.vo.PageData;


@WebServlet("/notice/search")
public class NoticeBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeBoardSearchServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage=  1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
		}
		String search = request.getParameter("search");
		String searchSelect = request.getParameter("searchSelect");
		PageData pageData = new NoticeService().searchNoticeList(search, currentPage, searchSelect);
		ArrayList<Notice> nList = pageData.getPageList();
		
		if (!nList.isEmpty()) {
			request.setAttribute("nList", nList);
			request.setAttribute("pageData", pageData.getPageNavi());
			request.setAttribute("searchSelect", searchSelect);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/noticeSearch.jsp");
			view.forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
