package edu.trojanow.trojanowweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JScrollBar;

import org.json.JSONObject;

import edu.trojanow.trojandataaccesss.ProfileDao;
import edu.trojanow.trojandataaccesss.ProfileDaoImpl;
import edu.trojanow.trojanowmodel.Responses;

/**
 * Servlet implementation class AuthenticateService
 */
public class AuthenticateService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ProfileDao mProfileDao = new ProfileDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String myPassword = request.getParameter("password");
		final String myEmail = request.getParameter("email");
		
		response.getWriter().println(new JSONObject(new edu.trojanow.trojanowmodel.Profile.UserId(mProfileDao.Authenticate(myEmail, myPassword))));
	}
}
