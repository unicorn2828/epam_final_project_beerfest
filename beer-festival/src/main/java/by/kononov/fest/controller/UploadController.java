package by.kononov.fest.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.kononov.fest.command.impl.UploadCommand;

/**
 * Servlet implementation class UploadController; it extends
 * HttpServlet; it uses only for uploading file.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
@WebServlet("/uploadController")
@MultipartConfig
public class UploadController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String REFERER = "referer";
	private static final String EMPTY = "";

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
		UploadCommand command = new UploadCommand();
		String realPath = getServletContext().getRealPath(EMPTY);
		String page = command.execute(request, realPath);
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getHeader(REFERER));
		}
	}
}