package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dto.BoardBean;

@WebServlet("/page")
public class BoardListController extends HttpServlet {

	// 기본 설
	private final short defaultPage = 1;

	public BoardListController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("currentPage") != null)
			request.setAttribute("currentPage", request.getParameter("currentPage"));
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		// 기본 페이지 
		int currentPage = defaultPage;

		if (request.getAttribute("currentPage") != null)
			currentPage = Integer.parseInt((String) request.getAttribute("currentPage"));

		// DB 연결
		BoardDao board = new BoardDao("board", "root", "");
		
		// DB 가져오기 
		List<BoardBean> boards = board.getPage(currentPage);

		// html 두개 합치면 페이지 이동시 속도 더 빨라질듯 -> 불 필요한 로딩 없애니

		// 페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher("boardList.jsp");
		request.setAttribute("boards", boards);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", findStartPage(currentPage));
		rd.forward(request, response);
	}
	
	// 현재 페이지의 시작 페이지 
	private int findStartPage(int num) {
		if((num % 10) == 0)
			return num - 9;
		for(; (num % 10) != 0; num--) {}
		return num + 1;
	}
	

}
