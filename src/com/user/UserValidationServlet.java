package com.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exceptions.UserException;
import com.user.form.User;
import com.user.services.UserService;
import com.user.services.impl.UserServiceImpl;

public class UserValidationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String APP_ID = "app1";

	private String name = null;
	private String address = null;
	private String emailId = null;
	UserService userService = new UserServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			validateForm(request);
		} catch (UserException userException) {
			sendToErrorPage(request, response, out);
		}
		
		User user = createUser(generateSignature());

		if (!userService.isUserSignatureExists(user)) {
			sendToWelcomePage(request, response, out);
		} else {
			sendToWelcomeBackPage(request, response, out);
		}

		out.close();
	}

	private void sendToErrorPage(HttpServletRequest request,
			HttpServletResponse response, PrintWriter out)
			throws ServletException, IOException {
		out.print("Invalid user");
		forwardTo(request, response, "index.html");
	}

	private void forwardTo(HttpServletRequest request,
			HttpServletResponse response, String pageName) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(pageName);
		rd.include(request, response);
	}
	
	private void sendToWelcomePage(HttpServletRequest request,
			HttpServletResponse response, PrintWriter out)
			throws ServletException, IOException {
		out.print("Welcome " + name);
		forwardTo(request, response, "success.html");
	}
	
	private void sendToWelcomeBackPage(HttpServletRequest request,
			HttpServletResponse response, PrintWriter out)
			throws ServletException, IOException {
		out.print("Verified your signature " + name);
		forwardTo(request, response, "success.html");
	}

	private void validateForm(HttpServletRequest req) throws UserException {
		if (!isEmptyOrNull(req.getParameter("username")) && !isEmptyOrNull(req.getParameter("emailId")) && !isEmptyOrNull(req.getParameter("address"))) {
			name = req.getParameter("username");
			emailId = req.getParameter("emailId");
			address = req.getParameter("address");
		} else {
			throw new UserException("Invalid user");
		}
		
		
	}

	private String generateSignature() {
		System.out.println(emailId + name + address);
		return emailId + name.substring(0, 1) + address.substring(0, 1)
				+ APP_ID;
	}

	private User createUser(String signature) {

		User user = new User(name, emailId, address, signature);
		return user;

	}

	private boolean isEmptyOrNull(String input) {
		if (input == null || input.isEmpty()) {
			return true;
		}
		return false;
	}

}
