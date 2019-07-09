package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BoardDao;

@WebServlet("/MasterController")
public class MasterController extends HttpServlet {

	public MasterController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", "choiseonjae");
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String userId = (String) session.getAttribute("userId");
		
		BoardDao board = new BoardDao("board", "root", "");
		
		board.insertBoard(userId, title, contents);
		
		
	}

}
