package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import dao.UserDao;

@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		// id pw 가 null 인 경우는 없음. required
		
		UserDao userDao = new UserDao(Common.DATABASE_NAME, Common.DATABASE_ID, Common.DATABASE_PW);
		
		userDao.signUp(id, pw);
		
		request.getSession().setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("./page");
		rd.forward(request, response);
		
		
		
	}

}
