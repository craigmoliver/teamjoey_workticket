package workticket;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDTO;
import db.WorkTicketDAO;

/**
 * Servlet implementation class WorkTicketServlet
 */
public class WorkTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkTicketServlet() {
        super();
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
	    
	    
	    if (command == null){ //initial request
	    	dispatcher = ctx.getRequestDispatcher("/login.jsp");
	    }
	    
	    UserDTO userTicket = UserTicket.getUserTicket(session);
	    if (userTicket != null) { // TODO
	    	
	    	if (userTicket.getRole() == "Manager") { // TODO
	    		// User Commands
			    if (command.equals("user_list")) { // TODO
			    	request.setAttribute("users", new WorkTicketDAO().listUsers());
			    	dispatcher = ctx.getRequestDispatcher("/listUsers.jsp");
			    }
			    else if (command.equals("user_list")) { // TODO
			    	request.setAttribute("users", new WorkTicketDAO().listUsers());
			    	request.setAttribute("title", "Users");
			    	dispatcher = ctx.getRequestDispatcher("/listUsers.jsp");
			    }
			    else if (command.equals("user_new")) { // TODO
			    	UserHelper userHelper = new UserHelper();
			    	request.setAttribute("title", "Users");
			    	request.setAttribute("formheader", "New");
			    	request.setAttribute("userHelper", userHelper);
			    	dispatcher = ctx.getRequestDispatcher("/editUser.jsp");
			    }
			    else if (command.equals("user_edit")) { // TODO
			    	UserHelper userHelper = new UserHelper(request.getParameter("username"));
			    	request.setAttribute("title", "Users");
			    	request.setAttribute("formheader", "Edit");
			    	request.setAttribute("userHelper", userHelper);
			    	dispatcher = ctx.getRequestDispatcher("/editUser.jsp");
			    }	
	    	}
	    }
	    else {
	    	dispatcher = ctx.getRequestDispatcher("/notFound.jsp");//not found page
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
		
		if (command.equals("user_login")) {
			String attrUsername = request.getParameter("username");
			String attrPassword = request.getParameter("password");
			
			if (UserTicket.validPassword(attrUsername, attrPassword)) {
				UserTicket.setUserTicket(session, attrUsername);
				dispatcher = ctx.getRequestDispatcher("/ticketList.jsp");
			}
			else {
				UserTicket.destoryUserTicket(session);
				dispatcher = ctx.getRequestDispatcher("/loginFailed.jsp");
			}
			
		}
		else if (command.equals("user_save")) {
			UserHelper.saveUser(request.getParameter("name"), 
								request.getParameter("email"), 
								request.getParameter("username"), 
								request.getParameter("password"), 
								request.getParameter("role"));
			dispatcher = ctx.getRequestDispatcher("/createUser.jsp");
		}
		dispatcher.forward(request,response);
	}

}
