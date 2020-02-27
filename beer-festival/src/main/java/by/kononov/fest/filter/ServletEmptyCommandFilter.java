package by.kononov.fest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/controller" }, servletNames = { "Controller" })
public class ServletEmptyCommandFilter implements Filter{
	private static final String URL = "/";
	private static final String PARAMETER_COMMAND = "command";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String action = request.getParameter(PARAMETER_COMMAND);
		if (action == null || action.isEmpty()) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(URL);
			dispatcher.forward(request, response);
			return;
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}