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
 * Manages all requests for the work tickets in the work ticket system. GET requests are to view, confirm, and edit existing 
 * work tickets. POST requests are to edit, save and submit new work tickets to db.
 * @author TeamJoey
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
	 * Process a request to view, edit, confirm a work ticket.
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
		
		// helper variables
	    Boolean isManager = userTicket != null && userTicket.getRole().equals("Manager");
	    Boolean loggedIn = userTicket != null;
		
		if (loggedIn){
			if (command == null){ //initial request
				if (isManager) { // if manager display all tickets
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
			else if (command.equals("user_list")) { // List users for editing
				if (isManager) { // allowed if manager
					request.setAttribute("users", new WorkTicketDAO().listUsers());
			    	request.setAttribute("title", "Users");
			    	dispatcher = ctx.getRequestDispatcher("/listUsers.jsp");
				}
		    	else {
		    		notAuthorized = true;
		    	}
		    }
		    else if (command.equals("user_new")) { // New User form
		    	if (isManager) { // allowed if manager
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
		    else if (command.equals("user_edit")) { // Edit User form
				if (isManager) { // allowed if manager
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
		    else if (command.equals("user_changepassword")) { // change password form
		    	dispatcher = ctx.getRequestDispatcher("/changeUserPassword.jsp");
		    }
		    else if (command.equals("ticket_view")) { // ticket view
		    	String paramTicketId = request.getParameter("ticketId");
		    	
		    	TicketHelper ticketHelper = new TicketHelper(Integer.parseInt(paramTicketId));
		    	if (ticketHelper.getTicket().getTicketId() == 0) { // ticket not found
		    		notFound = true;
		    	}
		    	else {
		    		request.setAttribute("title", "View Ticket");
			    	request.setAttribute("formheader", "Ticket");
			    	request.setAttribute("ticketHelper", ticketHelper);
			    	request.setAttribute("userTicket", userTicket);
			    	dispatcher = ctx.getRequestDispatcher("/viewTicket.jsp");
		    	}
		    }
		    else { // any other command, not found
		    	notFound = true;
		    }
		}
		else {
			if (command == null) { // Default to Ticket Submit
				dispatcher = ctx.getRequestDispatcher("/submitTicket.jsp");
			}
			else if (command.equals("ticket_confirm")) { // Ticket Confirmation
				request.setAttribute("ticketId", request.getParameter("ticketId"));
				dispatcher = ctx.getRequestDispatcher("/confirmTicket.jsp");
			}
			else { // Anything else redirect to login
				redirect = "/login";
			}
		}
				
	    System.out.println("Redirect:"+redirect);
	    System.out.println(!redirect.equals(""));
		System.out.println("NotFound:"+notFound);
		
		if (!redirect.isEmpty()) { // Redirect if redirect is set
			response.sendRedirect(redirect);
			return;
		}
		if (notFound) { // displays not found page
			dispatcher = ctx.getRequestDispatcher("/notFound.jsp"); 
		}
		if (notAuthorized) { // displays not authorized
			dispatcher = ctx.getRequestDispatcher("/notAuthorized.jsp");
		}
		
		// set for all valid requests
    	request.setAttribute("isManager", isManager);
    	request.setAttribute("loggedIn", loggedIn);
    	
	    dispatcher.forward(request,response);
	}

	/**
	 * Process a request to edit, save and submit a work ticket.
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
		
		// TODO
		Boolean isManager = userTicket != null && userTicket.getRole().equals("Manager");
	    Boolean loggedIn = userTicket != null;
	    
	    if (command.equals("ticket_submit")) { // Saves a new Ticket
			String attrTitle = request.getParameter("title");
			String attrDescription = request.getParameter("description");
			if (!attrTitle.isEmpty() && !attrDescription.isEmpty()) { // save if new ticket description and title aren't empty
				int ticketId = TicketHelper.saveNewTicket(attrTitle, attrDescription);
				redirect = "/ticket?command=ticket_confirm&ticketId=" + ticketId; // redirect to ticket confirmation
			}
	    }
	    else if (command.equals("annotation_save")) { // Saves an Annotation
	    	if (loggedIn) { // Must be logged in
				String attrTicketId = request.getParameter("ticketId");
				String username = userTicket.getUsername();
				String attrText = request.getParameter("text");
				TicketHelper.saveNewAnnotation(Integer.parseInt(attrTicketId), username, attrText);
				// redirect back to Ticket details
				redirect = "/ticket?command=ticket_view&ticketId=" + attrTicketId; 
	    	}
	    	else { // Not Authorized
	    		notAuthorized = true;
	    	}
		}
	    else if (command.equals("ticket_updatestatus")) { // Updates ticket status
	    	if (loggedIn && isManager) { // Must be logged in and a manager
	    		String attrStatus = request.getParameter("status");
	    		String attrTicketId = request.getParameter("ticketId");
	    		
	    		// Input parameters must not be empty
	    		if (!attrStatus.isEmpty() && !attrTicketId.isEmpty()) {
	    			TicketHelper.updateTicketStatus(Integer.parseInt(attrTicketId), attrStatus);
	    		}
	    	}
	    }
	    else if (command.equals("user_save")) { // Saves an User
	    	if (loggedIn && isManager) { // Must be logged in and a manager
				String attrName = request.getParameter("name");
	    		String attrEmail = request.getParameter("email");
				String attrUsername = request.getParameter("username");
				String attrPassword = request.getParameter("password");
				String attrRole = request.getParameter("role");
	    		
				// Input parameters must not be empty
				if (!attrName.isEmpty() && !attrEmail.isEmpty() && !attrUsername.isEmpty() && 
					!attrPassword.isEmpty() && !attrRole.isEmpty()) {
					UserHelper.saveUser(attrName, attrEmail, attrUsername, attrPassword, attrRole);
					redirect = "/ticket?command=user_list"; // redirect back to list of Users
				}
	    	}
	    	else { // Not Authorized
	    		notAuthorized = true;	
	    	}
	    }
	    else if (command.equals("user_changepassword")) { // allows user to change password
	    	if (loggedIn) { // allowed if logged in
	    		String attrPassword = request.getParameter("password");
	    		UserHelper.changeUserPassword(userTicket.getUsername(), attrPassword);
	    		redirect = "/login?command=logout"; // logs user out to log back in with new password
	    	}
	    	else { // Not Authorized
	    		notAuthorized = true;	
	    	}
	    }
	    else if (command.equals("ticket_assign")) { // Assigns ticket to worker or manager
	    	if (loggedIn && isManager) { // Must be logged in and a manager
	    		TicketHelper ticketHelper = new TicketHelper(Integer.parseInt(request.getParameter("ticketId")));
				String paramAssignTo = request.getParameter("assignTo");
				
				// ticketId will be greater than zero if exists and user should exist
				if (ticketHelper.getTicket().getTicketId() > 0 && UserHelper.userExists(paramAssignTo)) { 
					TicketHelper.assignTicketTo(ticketHelper.getTicket(), paramAssignTo);
					// redirect back to Ticket details
					redirect = "/ticket?command=ticket_view&ticketId=" + ticketHelper.getTicket().getTicketId(); 
				}
				else { // Not Found
					notFound = true;
				}
	    	}
	    	else { // Not Authorized
	    		notAuthorized = true;	
	    	}
	    }
	    		
		System.out.println("Redirect:"+redirect);
		System.out.println("NotFound:"+notFound);
		
		if (!redirect.isEmpty()) { // Redirect if redirect is set
			response.sendRedirect(redirect);
			return;
		}
		if (notFound) { // displays not found page
			dispatcher = ctx.getRequestDispatcher("/notFound.jsp"); 
		}
		if (notAuthorized) { // displays not authorized
			dispatcher = ctx.getRequestDispatcher("/notAuthorized.jsp");
		}
		
		// set for all valid requests
    	request.setAttribute("isManager", isManager);
    	request.setAttribute("loggedIn", loggedIn);
		
		dispatcher.forward(request,response);
	}

}
