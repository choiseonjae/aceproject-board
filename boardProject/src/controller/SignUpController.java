package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import common.Common;
import dao.UserDao;

@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public SignUpController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");

		userDao = new UserDao(Common.DATABASE_NAME, Common.DATABASE_ID, Common.DATABASE_PW);

		switch (type) {
		case "check":
			check(request, response);
			break;
		case "signUp":
			signUp(request, response);
			break;
		}

	}

	// 아이디 중복 체
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JSONObject obj = new JSONObject();

			if (userDao.isOverlap( request.getParameter("id")))
				obj.put("result", "fail");
			else
				obj.put("result", "success");

			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	// 회원가입
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("password");

		// id pw 가 null 인 경우는 없음. required

		userDao.signUp(id, pw);

		request.getSession().setAttribute("id", id);

		response.sendRedirect("./page");
	}

}
