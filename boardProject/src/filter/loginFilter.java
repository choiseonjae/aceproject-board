package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/loginFilter")
public class loginFilter implements Filter {

	public loginFilter() {
		System.out.println("로그인 필터 생성 ");
	}

	public void destroy() {
		System.out.println("로그인 필터 삭제 ");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("필터 시작");

		// 요청 래퍼 객체 생성
		HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);

		// 세션 얻기
		HttpSession session = requestWrapper.getSession();

		if (session == null)
			((HttpServletResponse) response).sendRedirect("./index.html");

		if (session.getAttribute("id") == null) {

			((HttpServletResponse) response).sendRedirect("./index.html");
		} else {

			System.out.println(session + " : " + session.getAttribute("id"));
			
			// 체인의 다음 필터에 요청 래퍼 객체 전달
			chain.doFilter(requestWrapper, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("필터 초기화  ");
	}

}
