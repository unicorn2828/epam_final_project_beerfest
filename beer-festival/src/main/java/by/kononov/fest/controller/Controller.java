package by.kononov.fest.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.CommandProvider;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PagePath.TransitionType;

/**
 * Servlet implementation class Controller; it extends HttpServlet.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
@WebServlet("/controller")
@MultipartConfig
public class Controller extends HttpServlet{
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private static final String REFERER = "referer";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * It is the main method of this servlet; it performs queries doPost
	 * and doGet simultaneously.
	 * 
	 * @param request  - the HttpServletRequest request;
	 * @param response - the HttpServletResponse response;
	 * @throws ServletException - if the servlet exception;
	 * @throws IOException      - if an I/O exception has occurred;
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionRequestContent content = new SessionRequestContent();
		content.extract(request);
		BaseCommand command = CommandProvider.defineCommand(content);
		PagePath page = command.execute(content);
		String url = page.getUrl();
		content.release(request);
		TransitionType type = page.getTransitionType();
		switch (type) {
			case FORWARD:
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
				dispatcher.forward(request, response);
				break;
			case REDIRECT:
				response.sendRedirect(request.getContextPath() + url);
				break;
			default:
				logger.warn("unknown transition type");
				response.sendRedirect(request.getHeader(REFERER));
		}
	}
}