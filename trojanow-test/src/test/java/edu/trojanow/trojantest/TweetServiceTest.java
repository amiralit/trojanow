package edu.trojanow.trojantest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import edu.trojanow.trojandataaccesss.ProfileDao;
import edu.trojanow.trojandataaccesss.ProfileDaoImpl;
import edu.trojanow.trojandataaccesss.TweetDaoImpl;
import edu.trojanow.trojanowmodel.Profile;
import edu.trojanow.trojanowmodel.Tweet;

public class TweetServiceTest extends TestSupport{
	
	private static final String TEST_EMAIL = "TEST_EMAIL";

	private static final String TEST_PASSWORD = "TEST_PASSWORD";

	private static final String TEST_FULLNAME = "TEST_FULLNAME";

	public static final Profile TEST_PROFILE = new Profile(TEST_FULLNAME, TEST_PASSWORD, TEST_EMAIL);
	
	private final ProfileDao mProfileDao = new ProfileDaoImpl();
	
	@Override
	public String[] tables(){
		return new String[] {ProfileDaoImpl.TABLE_NAME, TweetDaoImpl.TABLE_NAME};
	}
	
	@Test
	public void test(){
		
		mProfileDao.insert(TEST_PROFILE);
		
		final long myUserId = mProfileDao.Authenticate(TEST_EMAIL, TEST_PASSWORD);
		
		final List<NameValuePair> myNameValuePairList = new ArrayList<NameValuePair>();
		
		myNameValuePairList.add(new BasicNameValuePair("message", "TEST_MESSAGE"));
		myNameValuePairList.add(new BasicNameValuePair("userid", Long.toString(myUserId)));
		
		doPost("TweetService", myNameValuePairList);
		
		try {
			final JSONArray myArray = new JSONArray(doGet("TweetService", null));
			
			assertTrue(myArray.length() > 0);
			
			final Tweet myTweet = new Tweet(myArray.getJSONObject(0));
			
			assertEquals("TEST_MESSAGE", myTweet.getMessage());
			assertEquals(myUserId, myTweet.getUserId());
		} catch (JSONException e) {
			fail(e.getMessage());
		}
		
		
		System.out.println(doGet("TweetService", null));
	}
}
