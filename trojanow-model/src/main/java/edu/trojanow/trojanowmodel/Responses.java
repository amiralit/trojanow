package edu.trojanow.trojanowmodel;


public enum Responses implements IResponse {
	PROFILE_INSERT_SUCCESS(1, "Profile was saved successfully", Status.SUCCESS),
	PROFILE_INSERT_FAILURE(2, "Unable to save Profile", Status.FAILURE),
	AUTHENTICATION_SUCCESS(3, "User was authenticated successfull", Status.SUCCESS),
	AUTHENTICATION_FAILURE(4, "Unable to authenticate user", Status.FAILURE),
	TWEET_INSERT_SUCCESS(5, "Tweet was saved successfully", Status.SUCCESS),
	TWEET_INSERT_FAILURE(2, "Unable to save tweet", Status.FAILURE),
	;
	
	private final long mCode;
	private final String mMessage;
	private final Status mStatus;
	
	private Responses(final long pCode, final String pMessage, final Status pStatus) {
		mCode = pCode;
		mMessage = pMessage;
		mStatus = pStatus;
	}

	public long getCode() {
		return mCode;
	}

	public String getMessage() {
		return mMessage;
	}

	public String getStatus() {
		return mStatus.toString();
	}

}
