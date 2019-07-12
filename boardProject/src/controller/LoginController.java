package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import dao.UserDao;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession(true).invalidate();
		response.sendRedirect("./page");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		UserDao userDao = new UserDao(Common.DATABASE_NAME, Common.DATABASE_ID, Common.DATABASE_PW);

		// 비밀번호 혹은 아이디가 틀린 경우
		if (!userDao.isLogin(id, pw)) {
			response.sendRedirect("index.html");
		} else {
			request.getSession().setAttribute("id", id);
		response.sendRedirect("./page");
		}
	}

}
