package workticket;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDTO;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final String loginSuccessUrl = "/ticket";
	private static final String logoutSuccessUrl = "/ticket";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctx = this.getServletContext();
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
	    String command = request.getParameter("command");
	    System.out.println("Controller GET command:"+command);
	    
	    UserDTO userTicket = UserTicket.getUserTicket(session);
	    String redirect = "";
		Boolean notFound = false;
	    dispatcher = ctx.getRequestDispatcher("/login.jsp"); // default view
	    
		if (command == null) { // TODO
	    	if (userTicket != null) { // TODO
	    		redirect = loginSuccessUrl;
	    	}
	    }
	    else if (command.equals("logout")) { // TODO
	    	UserTicket.destoryUserTicket(session);
	    	redirect = logoutSuccessUrl;
	    }
	    else {
	    	notFound = true;
	    }
	    
		System.out.println("Redirect:"+redirect);
		System.out.println("NotFound:"+notFound);
		
		if (!redirect.equals("")) { // Redirect if redirect is set
			response.sendRedirect(redirect);
			return;
		}
		if (notFound) { //TODO
			dispatcher = ctx.getRequestDispatcher("/notFound.jsp"); //not found page
		}

	    dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctx = this.getServletContext();
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		String command = request.getParameter("command");
		System.out.println("Controller POST command:"+command);
		
		UserDTO userTicket = UserTicket.getUserTicket(session);
		String redirect = null;
		Boolean notFound = false;
		dispatcher = ctx.getRequestDispatcher("/login.jsp"); // default view
		
		if (command == null) { // TODO
			if (userTicket != null) { // TODO
				redirect = loginSuccessUrl;
	    	}
			else {
				notFound = true;
			}
		}
		else if (command.equals("user_login")) {
			if (userTicket != null) { // TODO
				redirect = loginSuccessUrl;
	    	}
			else { // TODO
				String attrUsername = request.getParameter("username");
				String attrPassword = request.getParameter("password");
				
				if (UserTicket.validPassword(attrUsername, attrPassword)) { //TODO
					UserTicket.setUserTicket(session, attrUsername);
					redirect = loginSuccessUrl;
				}
				else { //TODO
					dispatcher = ctx.getRequestDispatcher("/loginFailed.jsp"); // Login Failed
				}
			}
		}
		else { //TODO
			notFound = true;
	    }
		
		System.out.println("Redirect:"+redirect);
		System.out.println("NotFound:"+notFound);
		
		if (redirect != null) { // Redirect if redirect is set
			response.sendRedirect(redirect);
			return;
		}
		if (notFound) { //TODO
			dispatcher = ctx.getRequestDispatcher("/notFound.jsp"); //not found page
		}

		dispatcher.forward(request,response);
	}

}
