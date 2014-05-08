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
 * Servlet implementation class TicketController
 */
public class TicketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketController() {
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
	    String redirect = "";
		Boolean notFound = false;
		Boolean notAuthorized = false;
		UserDTO userTicket = UserTicket.getUserTicket(session);
	    
		if (userTicket != null){
			if (command == null){ //initial request
				
				if (userTicket.getRole().equals("Manager")) { // TODO
					request.setAttribute("title", "All Tickets (Manager View)");
			    	request.setAttribute("formheader", "Tickets");
			    	request.setAttribute("tickets", TicketHelper.listTickets());
				}
				else { // assumes Worker rule
					request.setAttribute("title", "Your Tickets");
			    	request.setAttribute("formheader", "Tickets");
			    	request.setAttribute("tickets", TicketHelper.listTickets(userTicket.getUsername()));
				}
				dispatcher = ctx.getRequestDispatcher("/listTicket.jsp");
		    }
			else if (command.equals("user_list")) { // TODO
				if (userTicket.getRole().equals("Manager")) {
					request.setAttribute("users", new WorkTicketDAO().listUsers());
			    	request.setAttribute("title", "Users");
			    	dispatcher = ctx.getRequestDispatcher("/listUsers.jsp");
				}
		    	else {
		    		notAuthorized = true;
		    	}
		    }
		    else if (command.equals("user_new")) { // TODO
		    	if (userTicket.getRole().equals("Manager")) {
		    		UserHelper userHelper = new UserHelper();
			    	request.setAttribute("title", "Users");
			    	request.setAttribute("formheader", "New");
			    	request.setAttribute("userHelper", userHelper);
			    	dispatcher = ctx.getRequestDispatcher("/editUser.jsp");
				}
		    	else {
		    		notAuthorized = true;
		    	}
		    }
		    else if (command.equals("user_edit")) { // TODO
				if (userTicket.getRole().equals("Manager")) {
					String paramUsername = request.getParameter("username");
					if (paramUsername != null) {
						UserHelper userHelper = new UserHelper(paramUsername);
						if (userHelper.getUser().getUsername().equals("")) { // user not found
							notFound = true;
						}
						else {
							request.setAttribute("title", "Users");
					    	request.setAttribute("formheader", "Edit");
					    	request.setAttribute("userHelper", userHelper);
					    	dispatcher = ctx.getRequestDispatcher("/editUser.jsp");
						}
					}
				}
		    	else {
		    		notAuthorized = true;
		    	}
		    }
		    else if (command.equals("ticket_view")) {
		    	String paramTicketId = request.getParameter("ticketId");
		    	
		    	TicketHelper ticketHelper = new TicketHelper(Integer.parseInt(paramTicketId));
		    	if (ticketHelper.getTicket().getTicketId() == 0) { // ticket not found
		    		notFound = true;
		    	}
		    	else {
		    		request.setAttribute("title", "View Ticket");
			    	request.setAttribute("formheader", "Ticket");
			    	request.setAttribute("ticketHelper", ticketHelper);
			    	request.setAttribute("isManager", userTicket.getRole().equals("Manager"));
			    	request.setAttribute("userTicket", userTicket);
			    	dispatcher = ctx.getRequestDispatcher("/viewTicket.jsp");
		    	}
		    }
		    else { // TODO
			    
			    
		    }
		}
		else {
			redirect = "/login";
		}
		
		
				
	    System.out.println("Redirect:"+redirect);
	    System.out.println(!redirect.equals(""));
		System.out.println("NotFound:"+notFound);
		
		if (!redirect.equals("")) { // Redirect if redirect is set
			response.sendRedirect(redirect);
			return;
		}
		if (notFound) { //TODO
			dispatcher = ctx.getRequestDispatcher("/notFound.jsp"); //not found page
		}
		if (notAuthorized) { // TODO
			dispatcher = ctx.getRequestDispatcher("/notAuthorized.jsp"); //not authorized
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
		String redirect = "";
		Boolean notFound = false;
		Boolean notAuthorized = false;
		UserDTO userTicket = UserTicket.getUserTicket(session);
		
		if (command.equals("user_login")) { //TODO
			
		}
		else if (command.equals("user_save")) { //TODO
			UserHelper.saveUser(request.getParameter("name"), 
								request.getParameter("email"), 
								request.getParameter("username"), 
								request.getParameter("password"), 
								request.getParameter("role"));
			dispatcher = ctx.getRequestDispatcher("/listUsers.jsp");
		}
		else if (command.equals("ticket_assign")) { //TODO
			TicketHelper ticketHelper = new TicketHelper(Integer.parseInt(request.getParameter("ticketId")));
			String paramAssignTo = request.getParameter("assignTo");
			
			// ticketId will be greater than zero if exists and user should exist
			if (ticketHelper.getTicket().getTicketId() > 0 && UserHelper.userExists(paramAssignTo)) { 
				TicketHelper.assignTo(ticketHelper.getTicket(), paramAssignTo);
				redirect = "/ticket?command=ticket_view&ticketId=" + ticketHelper.getTicket().getTicketId();
			}
			else { 
				notFound = true;
			}
		}
		
		System.out.println("Redirect:"+redirect);
	    System.out.println(!redirect.equals(""));
		System.out.println("NotFound:"+notFound);
		
		if (!redirect.isEmpty()) { // Redirect if redirect is set
			response.sendRedirect(redirect);
			return;
		}
		if (notFound) { //TODO
			dispatcher = ctx.getRequestDispatcher("/notFound.jsp"); //not found page
		}
		if (notAuthorized) { // TODO
			dispatcher = ctx.getRequestDispatcher("/notAuthorized.jsp"); //not authorized
		}
		
		
		
		dispatcher.forward(request,response);
	}

}
