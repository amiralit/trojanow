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
		
		JSONObject myReturnObject = null;
		
		try{
			
			final String myFullname = request.getParameter("fullname");
			final String myPassword = request.getParameter("password");
			final String myEmail = request.getParameter("email");
			
			final edu.trojanow.trojanowmodel.Profile myInsertedProfile = mProfileDao.insert(new edu.trojanow.trojanowmodel.Profile(myFullname, myPassword, myEmail));
			
			myReturnObject = new JSONObject(new edu.trojanow.trojanowmodel.Profile.UserId(myInsertedProfile.getUserId()));
			response.getWriter().println(myReturnObject.toString());
		} catch (Exception e){
			myReturnObject = new JSONObject(new edu.trojanow.trojanowmodel.Profile.UserId(-1));
			response.getWriter().println(myReturnObject.toString());
		}
	}

}
