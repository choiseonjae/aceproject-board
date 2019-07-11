package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dto.BoardBean;

@WebServlet("/board.do")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardDetailController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		final int boardId = Integer.parseInt(request.getParameter("boardId"));

		// dao 생성
		BoardDao boardDao = new BoardDao("board", "root", "");

		// 조회수 증가
		boardDao.updateView(boardId);
		
		// 내가 클릭한 게시글을 가져온다.
		BoardBean board = boardDao.getBoard(boardId);


		// 다른 컨트롤러랑 다르게 새로고침시 조회수 증가를 막으려면 쿠키 혹은 세션에 달기 고민해보자
		// 페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher("boardDetail.jsp");
		request.setAttribute("board", board);
		rd.forward(request, response);

	}

}
