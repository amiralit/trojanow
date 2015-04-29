package edu.trojanow.trojanowweb;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.trojanow.trojandataaccesss.TweetDao;
import edu.trojanow.trojandataaccesss.TweetDaoImpl;
import edu.trojanow.trojanowmodel.Responses;
import edu.trojanow.trojanowmodel.Tweet;

/**
 * Servlet implementation class TweetService
 */
public class TweetService extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private final TweetDao mTweetDao = new TweetDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Collection<Tweet> myRetrievedTweets = mTweetDao.loadAll();
		
		final JSONArray myJArray = new JSONArray();
		
		for (Tweet myTweet : myRetrievedTweets){
			myJArray.put(new JSONObject(myTweet));
		}
		
		response.getWriter().println(myJArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			final String myMessage = request.getParameter("message");
			final long myUserId = Long.parseLong(request.getParameter("userid"));
	
			mTweetDao.insert(new Tweet(myMessage, myUserId));
			
			response.getWriter().println(new JSONObject(Responses.TWEET_INSERT_SUCCESS));
		} catch (Exception e){
			response.getWriter().println(new JSONObject(Responses.TWEET_INSERT_FAILURE));
		}
	}

}
