package edu.trojanow.trojandataaccesss;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import edu.trojanow.trojanowmodel.Tweet;

public class TweetDaoImplTest extends TestSupport {
	private static final String TEST_MESSAGE = "TEST_MESSAGE";

	@Override 
	public String[] tables(){
		return new String[]{TweetDaoImpl.TABLE_NAME, ProfileDaoImpl.TABLE_NAME};
	}
	
	private final ProfileDao mProfileDao = new ProfileDaoImpl();
	
	private final TweetDao mTweetDao = new TweetDaoImpl();
	
	@Test
	public void test(){
		mProfileDao.insert(ProfileDaoImplTest.TEST_PROFILE);
		
		final long myUserId = mProfileDao.Authenticate(ProfileDaoImplTest.TEST_PROFILE.getEmail(), ProfileDaoImplTest.TEST_PROFILE.getPassword());
		
		final Tweet myTweet = new Tweet(TEST_MESSAGE, myUserId);
		
		mTweetDao.insert(myTweet);
		
		final Collection<Tweet> myRetrievedTweets = mTweetDao.loadAll();
		
		assertNotNull(myRetrievedTweets);
		assertEquals(1, myRetrievedTweets.size());
		
		final Tweet myRetrievedTweet = myRetrievedTweets.iterator().next();
		
		assertEquals(TEST_MESSAGE, myRetrievedTweet.getMessage());
		assertEquals(myUserId, myRetrievedTweet.getUserId());
	}
}
