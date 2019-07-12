package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dto.BoardBean;

@WebServlet("/board")
public class BoardController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private BoardDao board;

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("type"));
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String type = request.getParameter("type");

		// DB 연결
		board = new BoardDao("board", "root", "");

		// session 가져옴.
		session = request.getSession();

		switch (type) {
		case "write":
			write(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "replace":
			replace(request, response);
			break;
		case "update":
			update(request, response);
			break;
		}

	}

	private void write(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String id = (String) session.getAttribute("id");

		// 게시글 저장
		board.insertBoard(id, title, contents);

		// 페이지 이동
		response.sendRedirect("./page");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int boardId = Integer.parseInt(request.getParameter("boardId"));

		board.deleteBoard(boardId);

		// 페이지 이동
		response.sendRedirect("./page");

	}

	private void replace(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {

			// 페이지 이동
			RequestDispatcher rd = request.getRequestDispatcher("boardForm.jsp");

			int boardId = Integer.parseInt(request.getParameter("boardId"));

			BoardBean boardBean = board.getBoard(boardId);

			request.setAttribute("board", boardBean);
			rd.forward(request, response);

		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int boardId = Integer.parseInt(request.getParameter("boardId"));

		// 게시글 저장
		board.updateBoard(boardId, title, contents);

		// 페이지 이동
		response.sendRedirect("./page");

	}
}
