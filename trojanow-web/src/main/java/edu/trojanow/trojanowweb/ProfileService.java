package edu.trojanow.trojanowweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import edu.trojanow.trojandataaccesss.ProfileDao;
import edu.trojanow.trojandataaccesss.ProfileDaoImpl;
import edu.trojanow.trojanowmodel.Responses;

/**
 * Servlet implementation class ProfileService
 */
public class ProfileService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ProfileDao mProfileDao = new ProfileDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			final String myFullname = request.getParameter("fullname");
			final String myPassword = request.getParameter("password");
			final String myEmail = request.getParameter("email");
			
			mProfileDao.insert(new edu.trojanow.trojanowmodel.Profile(myFullname, myPassword, myEmail));
			
			response.getWriter().println(new JSONObject(Responses.PROFILE_INSERT_SUCCESS));
		} catch (Exception e){
			response.getWriter().println(new JSONObject(Responses.PROFILE_INSERT_FAILURE));
		}
	}

}
