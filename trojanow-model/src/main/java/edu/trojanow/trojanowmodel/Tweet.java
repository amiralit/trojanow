package edu.trojanow.trojanowmodel;

import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {

	private final String mMessage;
	private final long mUserId;
	private final long mTweetId;
	
	public Tweet(final String pMessage, final long pUserId, final long pTweetId){
		mMessage = pMessage;
		mUserId = pUserId;
		mTweetId = pTweetId;
	}
	
	public Tweet(final String pMessage, final long pUserId){
		this(pMessage, pUserId, 0);
	}
	
	public Tweet(final JSONObject pJsonObject) throws JSONException{
		mUserId = pJsonObject.getLong("userId");
		mMessage = pJsonObject.getString("message");
		mTweetId = pJsonObject.has("tweetId") ? pJsonObject.getLong("tweetId") : -1;
	}
	
	public final String getMessage(){
		return mMessage;
	}
	
	public final long getUserId(){
		return mUserId;
	}
	
	public final long getTweetId(){
		return mTweetId;
	}
}
