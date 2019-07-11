package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;

@WebServlet("/write")
public class BoardWriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BoardWriteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// DB에 넣을 값 받아오기
		HttpSession session = request.getSession();
		session.setAttribute("userId", "choiseonjae");

		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		System.out.println("titlte : " + title);
		System.out.println("contents : " + contents);
		String userId = (String) session.getAttribute("userId");

		// DB 연결
		BoardDao board = new BoardDao("board", "root", "");

		// 게시글 저장
		board.insertBoard(userId, title, contents);

		// 페이지 이동
		response.sendRedirect("./page");

	}

}
